package com.xmlproject.project_poverenik.repository;

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
import org.springframework.beans.factory.annotation.Value;

import org.xml.sax.SAXException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ZalbaNaCutanjeRepository {

    private static final String ZALBA_NAMED_GRAPH_URI = "/example/zalbanacutanje/metadata";

    private static final String XML_FILE_PATH = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<ZalbaNaCutanje xmlns=\"http://ftn.uns.ac.rs/xml_zalba_na_cutanje\"\n" +
            " xmlns:opste=\"http://ftn.uns.ac.rs/xml_opste\"\n" +
            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            " xmlns:pred=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate\"\n" +
            " xsi:schemaLocation=\"http://ftn.uns.ac.rs/xml_zalba_na_cutanje file:/C:/Users/Jovana/Desktop/XMLProjekat/koncept/data/zalbanacutanje.xsd\"\n" +
            " about=\"http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic\">\n" +
            "    <Primalac>Primalac0</Primalac>\n" +
            "    <Adresa_primaoca>\n" +
            "        <opste:Ulica>Ulica0</opste:Ulica>\n" +
            "        <opste:Broj>50</opste:Broj>\n" +
            "        <opste:Postanski_broj>50</opste:Postanski_broj>\n" +
            "        <opste:Mesto property=\"pred:mestoPrimaoca\">Mesto0</opste:Mesto>\n" +
            "        <opste:Drzava>Drzava0</opste:Drzava>\n" +
            "    </Adresa_primaoca>\n" +
            "    <Telo_zalbe>\n" +
            "        <Organ>\n" +
            "            <opste:Naziv property=\"pred:Naziv\">Naziv0</opste:Naziv>\n" +
            "            <opste:Adresa>\n" +
            "                <opste:Ulica>Ulica1</opste:Ulica>\n" +
            "                <opste:Broj>50</opste:Broj>\n" +
            "                <opste:Postanski_broj>50</opste:Postanski_broj>\n" +
            "                <opste:Mesto>Mesto1</opste:Mesto>\n" +
            "                <opste:Drzava>Drzava1</opste:Drzava>\n" +
            "            </opste:Adresa>\n" +
            "        </Organ>\n" +
            "        <Razlozi_zalbe>\n" +
            "            <Razlog_zalbe>Razlog_zalbe0</Razlog_zalbe>\n" +
            "            <Razlog_zalbe>Razlog_zalbe1</Razlog_zalbe>\n" +
            "        </Razlozi_zalbe>\n" +
            "        <Datum_podnosenja_zahteva>\n" +
            "            <opste:Datum>Datum0</opste:Datum>\n" +
            "        </Datum_podnosenja_zahteva>\n" +
            "        <Podaci_o_zahtevu_i_informacija>Podaci_o_zahtevu_i_informacija0</Podaci_o_zahtevu_i_informacija>\n" +
            "        <Napomena>Napomena0</Napomena>\n" +
            "    </Telo_zalbe>\n" +
            "    <Dodatne_informacije>\n" +
            "        <opste:Mesto>Mesto2</opste:Mesto>\n" +
            "        <opste:Datum>\n" +
            "            <opste:Datum>Datum1</opste:Datum>\n" +
            "        </opste:Datum>\n" +
            "        <opste:Trazilac>\n" +
            "            <opste:Osoba>\n" +
            "                <opste:Ime>Ime0</opste:Ime>\n" +
            "                <opste:Prezime>Prezime0</opste:Prezime>\n" +
            "            </opste:Osoba>\n" +
            "            <opste:Adresa>\n" +
            "                <opste:Ulica>Ulica2</opste:Ulica>\n" +
            "                <opste:Broj>50</opste:Broj>\n" +
            "                <opste:Postanski_broj>50</opste:Postanski_broj>\n" +
            "                <opste:Mesto>Mesto3</opste:Mesto>\n" +
            "                <opste:Drzava>Drzava2</opste:Drzava>\n" +
            "            </opste:Adresa>\n" +
            "            <opste:Kontakt>Kontakt0</opste:Kontakt>\n" +
            "        </opste:Trazilac>\n" +
            "    </Dodatne_informacije>\n" +
            "</ZalbaNaCutanje>\n";

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

    public ZalbaNaCutanje getOne(String id) throws Exception {

        // initialize collection and document identifiers
        String collectionId = "/db/sample/library";
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


    public void save (ZalbaNaCutanje zalbaNaCutanje) throws Exception {

        // initialize collection and document identifiers
        String collectionId = "/db/sample/library";
        String documentId = "2.xml";
        String filePath = "data/zalbanacutanje.xml";


        System.out.println("\t- collection ID: " + collectionId);
        System.out.println("\t- document ID: " + documentId);
        System.out.println("\t- file path: " + filePath + "\n");

        // initialize database driver
        System.out.println("[INFO] Loading driver class: " + connDriver);
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

            saveRDF();

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

    private void saveRDF() throws Exception {
        String rdfFilePath = "data/rdf/person_metadata.rdf";

        MetadataExtractor metadataExtractor = new MetadataExtractor();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");

        ByteArrayOutputStream b = new ByteArrayOutputStream();

        metadataExtractor.extractMetadata(
                new ByteArrayInputStream(XML_FILE_PATH.getBytes()),
                b);


        // Creates a default model
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(b.toByteArray()),null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Rendering model as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        UpdateRequest request = UpdateFactory.create() ;
        request.add(SparqlUtil.dropAll());

        /*
         * Create UpdateProcessor, an instance of execution of an UpdateRequest.
         * UpdateProcessor sends update request to a remote SPARQL update service.
         */
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, connUpdateEndpoint);
        processor.execute();

        // Creating the first named graph and updating it with RDF data
        System.out.println("[INFO] Writing the triples to a named graph \"" + ZALBA_NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(connDataEndpoint + ZALBA_NAMED_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        processor = UpdateExecutionFactory.createRemote(update, connUpdateEndpoint);
        processor.execute();


        System.out.println("[INFO] End.");
    }

}
