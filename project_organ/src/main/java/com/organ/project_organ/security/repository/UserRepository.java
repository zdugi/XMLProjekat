package com.organ.project_organ.security.repository;


import com.organ.project_organ.model.xml_korisnik.Korisnik;
import com.organ.project_organ.pojo.ComplaintsListDTO;
import com.organ.project_organ.repository.Repository;
import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import java.util.ArrayList;

@Component
public class UserRepository extends Repository<Korisnik> {

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

    public static final String findByUsernameQr = "findUserByUsername.rq";
    public static final String findByEmailQr = "findUserByEmail.rq";

    public UserRepository(String graphURI, String collectionId, String instancePath, String xqueryTextContain) {
        super(graphURI, collectionId, instancePath, xqueryTextContain);
    }

    public Korisnik getOne(String id) throws Exception {
        //TODO check getOnXML
        return super.getOneXML(id);
    }

    /*

    public Korisnik findByUsername(String username) {
        XPathQueryService queryService = ConnectionProperties.getXPathService(collection);
        try {

            queryService.setNamespace("",ConnectionProperties.USERS_NAMESPACE);
            ResourceSet result = queryService.query("//Users/User[UserName=\""+username+"\"]");

            JAXBContext jaxbContext = JAXBContext.newInstance(ConnectionProperties.PACKAGE_PATH + ConnectionProperties.USERS_PACKAGE);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ResourceIterator i = result.getIterator();
            Resource res = i.nextResource();
            
            return (User) unmarshaller.unmarshal(new StringReader(res.getContent().toString()));
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public void save (String id, Korisnik korisnik) throws Exception {
        // initialize collection and document identifiers
        super.save(id,korisnik);
    }

    public String findByUsername(String queryStr) throws IllegalAccessException, InstantiationException, XMLDBException {
        ComplaintsListDTO resourcesListDTO = new ComplaintsListDTO();
        resourcesListDTO.complaint = new ArrayList<>();

        String id = "";

        // initialize database driver
        Class<?> cl = DatabaseImpl.class;

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        Collection col = null;

        String textContainQuery = "xquery version \"3.1\";\n" +
                "for $korisnik in collection(\"/db/sample/korisnik/\")\n" +
                "where fn:contains(lower-case($korisnik), lower-case(\"%s\"))\n" +
                "return\n" +
                "    substring-after(base-uri($korisnik), \"korisnik/\")";

        try {
            col = DatabaseManager.getCollection(connUri + "/db/sample/korisnik");

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
            CompiledExpression compiledXquery = xqueryService.compile(String.format(textContainQuery, queryStr));
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    System.out.println(res.getContent());
                    id = res.getContent().toString();

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

        return id;
    }





}
