package com.xmlproject.project_poverenik.repository;

import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.util.MetadataExtractor;
import com.xmlproject.project_poverenik.util.SparqlUtil;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Value;


import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class ZalbaNaCutanjeRepository extends Repository<ZalbaNaCutanje> {

    private static final String ZALBA_NAMED_GRAPH_URI = "/example/zalbanacutanje/metadata";

    @Value("${conn.uri}")
    private String connUri;

    @Value("${conn.user}")
    private String connUser;

    @Value("${conn.password}")
    private String connPassword;

    @Value("${conn.driver}")
    private String connDriver;

    @Value("${conn.update.endpoint}")
    private String connUpdateEndpoint;

    @Value("${conn.data.endpoint}")
    private String connDataEndpoint;

    public ZalbaNaCutanjeRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }

    public ZalbaNaCutanje getOne(String id) throws Exception {

        // initialize collection and document identifiers
        String collectionId = "/db/sample/zalbanacutanje";
        String documentId = id + ".xml";

        System.out.println("\t- collection ID: " + collectionId);
        System.out.println("\t- document ID: " + documentId + "\n");

        // initialize database driver
        System.out.println("[INFO] Loading driver class: " + connDriver);
        Class<?> cl = DatabaseImpl.class;

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        ZalbaNaCutanje zalbaNaCutanje = null;

        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(connUri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            System.out.println("[INFO] Retrieving the document: " + documentId);
            res = (XMLResource)col.getResource(documentId);

            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {

                System.out.println("[INFO] Binding XML resouce to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                zalbaNaCutanje = (ZalbaNaCutanje) unmarshaller.unmarshal(res.getContentAsDOM());

                System.out.println("[INFO] Showing the document as JAXB instance: ");
                System.out.println(zalbaNaCutanje);

            }
        } finally {
            //don't forget to clean up!

            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return zalbaNaCutanje;
    }


    public void save (String id, ZalbaNaCutanje zalbaNaCutanje) throws Exception {
        // generate id for document
        zalbaNaCutanje.setId(id);

        // initialize collection and document identifiers
        String collectionId = "/db/sample/zalbanacutanje";
        String documentId = id + ".xml";

        // initialize database driver
        Class<?> cl = DatabaseImpl.class;

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId);

            /*
             *  create new XMLResource with a given id
             *  an id is assigned to the new resource if left empty (null)
             */
            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje");

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(zalbaNaCutanje, os);

            // link the stream to the XML resource
            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
            System.out.println("[INFO] Done.");

            ByteArrayOutputStream rdfOutputStream = new ByteArrayOutputStream();
            marshaller.marshal(zalbaNaCutanje, rdfOutputStream);

            saveRDF(new ByteArrayInputStream(rdfOutputStream.toByteArray()));

        } finally {

            //don't forget to cleanup
            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }


    private Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(connUri + collectionUri, connUser, connPassword);

        // create the collection if it does not exist
        if(col == null) {

            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(connUri + path, connUser, connPassword);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(connUri + parentPath, connUser, connPassword);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    private void saveRDF(InputStream in) throws Exception {
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");

        ByteArrayOutputStream b = new ByteArrayOutputStream();

        metadataExtractor.extractMetadata(
                in,
                b);


        // Creates a default model
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(b.toByteArray()),null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Rendering model as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        // Creating the first named graph and updating it with RDF data
        System.out.println("[INFO] Writing the triples to a named graph \"" + ZALBA_NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(connDataEndpoint + ZALBA_NAMED_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, connUpdateEndpoint);
        processor.execute();

        System.out.println("[INFO] End.");
    }

    public void setPrihvaceno(String id, String status) throws XMLDBException, IllegalAccessException, InstantiationException {
        // update zalba
        String collectionId = "/db/sample/zalbanacutanje";
        Class<?> cl = DatabaseImpl.class;

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            System.out.println("MOZEEEEEEEEEEEEEEEEE");
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId);


            String tm = " <ns2:Status property=\"pred:status\">"+ status + "</ns2:Status>";
            String xupdate = "<xu:update:element name=\"ZalbaNaCutanje\"> " +
                    "<xu:update:attribute name=\"prihvacena\">true</xu:update:attribute>" +
                    "</xu:update:element>";
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String contextXPath4 = "/ZalbaNaCutanje/@status";
            String contextXPath5 = "/ZalbaNaCutanje/Status";

            String Update = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
                    + "\" xmlns=\"" + "http://ftn.uns.ac.rs/xml_zalba_na_cutanje" + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
                    + "</xu:modifications>";
            //long mods1 = xupdateService.updateResource(id + ".xml", String.format(Update, contextXPath4, "prihvacena"));
            long mods2 = xupdateService.updateResource(id + ".xml", String.format(Update, contextXPath5, status));
            //long mods = xupdateService.updateResource(id + ".xml", String.format(Update, contextXPath5, tm));



            //XUpdateQueryService service =
            //        (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            //service.update(xupdate);
        }
        catch (XMLDBException e) {
            System.err.println("XML:DB Exception occured " + e.errorCode + " " +
                    e.getMessage());
        }
        finally {
            if (col != null) {
                col.close();
            }
        }
    }
}
