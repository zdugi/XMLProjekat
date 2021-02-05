package com.xmlproject.project_poverenik.repository;

import com.xmlproject.project_poverenik.model.xml_zahtev.Zahtev;
import com.xmlproject.project_poverenik.util.MetadataExtractor;
import com.xmlproject.project_poverenik.util.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import pojo.ComplaintsListDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.*;
import java.util.ArrayList;

public abstract class Repository<T1> {
    private final String NAMED_GRAPH_URI;
    private final String COLLECTION_ID;
    private final String INSTANCE_PATH;

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

    @Value("${conn.query.endpoint}")
    private String queryEndpoint;

    private String xqueryTextContain;


    public Repository(
            String graphURI,
            String collectionId,
            String instancePath,
            String xqueryTextContain) {
        this.NAMED_GRAPH_URI = graphURI;
        this.COLLECTION_ID = collectionId;
        this.INSTANCE_PATH = instancePath;
        this.xqueryTextContain = xqueryTextContain;
    }

    public ByteArrayOutputStream queryRDF(String sparqlQuery) {
        QueryExecution query = QueryExecutionFactory.sparqlService(queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;

        // Query the collection, dump output response as XML
        results = query.execSelect();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsXML(out, results);

        query.close();

        return out;
    }

    public StringWriter getOneXMLStream(String id) throws Exception {
        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        String documentId = id;

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

        T1 obj = null;

        // get the collection
        System.out.println("[INFO] Retrieving the collection: " + collectionId);
        col = DatabaseManager.getCollection(connUri + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");

        System.out.println("[INFO] Retrieving the document: " + documentId);
        res = (XMLResource)col.getResource(documentId);

        JAXBContext context = JAXBContext.newInstance(this.INSTANCE_PATH);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        System.out.println(unmarshaller.unmarshal(res.getContentAsDOM()).toString());

        obj = (T1) unmarshaller.unmarshal(res.getContentAsDOM());

        StringWriter os = new StringWriter();

        // marshal the contents to an output stream
        marshaller.marshal(obj, os);

        return os;
    }
    public ByteArrayOutputStream getOneMetadataRDF(String id) throws Exception {
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        metadataExtractor.extractMetadata(
                new ByteArrayInputStream(getOneXMLStream(id).toString().getBytes()),
                out);

        return out;
    }

    public ByteArrayOutputStream getOneMetadataJSON(String id) throws Exception {
        Model model = ModelFactory.createDefaultModel();

        String rdfFilePath = "gen/contacts.rdf";

        // Automatic extraction of RDF triples from XML file
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");

        InputStream in = new ByteArrayInputStream(getOneMetadataRDF(id).toString().getBytes());
        model.read(in, null);

        System.out.println("[INFO] Extracted metadata as RDF/XML...");
        model.write(out, "JSON-LD");

        return out;
    }



    public T1 getOnXML(String id) throws Exception {

        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        String documentId = id;

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

        T1 obj = null;

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
                JAXBContext context = JAXBContext.newInstance(this.INSTANCE_PATH);

                Unmarshaller unmarshaller = context.createUnmarshaller();

                obj = (T1) unmarshaller.unmarshal(res.getContentAsDOM());

                System.out.println("[INFO] Showing the document as JAXB instance: ");
                System.out.println(obj);

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

        return obj;
    }

    public ArrayList<T1> getAllXMLInCollection() throws Exception {

        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        //String documentId = id;

        System.out.println("\t- collection ID: " + collectionId);
        //System.out.println("\t- document ID: " + documentId + "\n");

        // initialize database driver
        System.out.println("[INFO] Loading driver class: " + connDriver);
        Class<?> cl = DatabaseImpl.class;

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        T1 obj = null;
        ArrayList<T1> arrayList = new ArrayList<>();

        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(connUri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            for(String documentID : col.listResources()) {
                System.out.println("[INFO] Retrieving the document: " + documentID);
                res = (XMLResource)col.getResource(documentID);

                if (res == null) {
                    System.out.println("[WARNING] Document '" + documentID + "' can not be found!");
                } else {

                    System.out.println("[INFO] Binding XML resouce to an JAXB instance: ");
                    JAXBContext context = JAXBContext.newInstance(this.INSTANCE_PATH);

                    Unmarshaller unmarshaller = context.createUnmarshaller();

                    obj = (T1) unmarshaller.unmarshal(res.getContentAsDOM());
                    arrayList.add(obj);

                    System.out.println("[INFO] Showing the document as JAXB instance: ");
                    System.out.println(obj);

                }
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

        return arrayList;
    }

    public ComplaintsListDTO searchText(String queryStr) throws IllegalAccessException, InstantiationException, XMLDBException {
        ComplaintsListDTO resourcesListDTO = new ComplaintsListDTO();
        resourcesListDTO.complaint = new ArrayList<>();

        // initialize database driver
        Class<?> cl = DatabaseImpl.class;

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        try {
            col = DatabaseManager.getCollection(connUri + COLLECTION_ID);

            // get an instance of xquery service
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            // make the service aware of namespaces
            //xqueryService.setNamespace("b", TARGET_NAMESPACE);

            /*String xqueryExpression = "xquery version \"3.1\";\n" +
                    "for $zahtev in collection(\"/db/sample/zahtev/\")\n" +
                    "where fn:contains($zahtev, \"Jojo\")\n" +
                    "return\n" +
                    "    substring-after(base-uri($zahtev), \"zahtev/\")";*/

            // compile and execute the expression
            CompiledExpression compiledXquery = xqueryService.compile(String.format(this.xqueryTextContain, queryStr));
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    resourcesListDTO.complaint.add(res.getContent().toString());

                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return resourcesListDTO;
    }


    public void save (String id, T1 zahtev) throws Exception {
        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        String documentId = id;

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
            JAXBContext context = JAXBContext.newInstance(this.INSTANCE_PATH);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(zahtev, os);

            // link the stream to the XML resource
            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
            System.out.println("[INFO] Done.");

            ByteArrayOutputStream rdfOutputStream = new ByteArrayOutputStream();
            marshaller.marshal(zahtev, rdfOutputStream);

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

    public String[] listComplaints() throws XMLDBException, IllegalAccessException, InstantiationException {
        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        Class<?> cl = DatabaseImpl.class;
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = DatabaseManager.getCollection(connUri + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");

        return col.listResources();
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
        System.out.println("[INFO] Writing the triples to a named graph \"" + NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(connDataEndpoint + NAMED_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, connUpdateEndpoint);
        processor.execute();

        System.out.println("[INFO] End.");
    }
    public String[] listResources() throws XMLDBException, IllegalAccessException, InstantiationException {
        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        Class<?> cl = DatabaseImpl.class;
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = DatabaseManager.getCollection(connUri + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");

        return col.listResources();
    }


    public T1 getOneXML(String id) throws Exception {

        // initialize collection and document identifiers
        String collectionId = this.COLLECTION_ID;
        String documentId = id;

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

        T1 obj = null;

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
                JAXBContext context = JAXBContext.newInstance(this.INSTANCE_PATH);

                Unmarshaller unmarshaller = context.createUnmarshaller();

                obj = (T1) unmarshaller.unmarshal(res.getContentAsDOM());

                System.out.println("[INFO] Showing the document as JAXB instance: ");
                System.out.println(obj);

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

        return obj;
    }
}
