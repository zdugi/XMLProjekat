package com.organ.project_organ.ws.zahtev;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;


@WebServiceClient(name = "ZahtevService", 
                  wsdlLocation = "classpath:wsdl/Zahtev.wsdl",
                  targetNamespace = "http://soap.spring.com/ws/request") 
public class ZahtevService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.spring.com/ws/request", "ZahtevService");
    public final static QName ZahtevPort = new QName("http://soap.spring.com/ws/reuquest", "ZahtevPort");
    static {
        URL url = ZahtevService.class.getClassLoader().getResource("wsdl/Zahtev.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZahtevService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/Zahtev.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public ZahtevService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZahtevService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZahtevService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ZahtevService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZahtevService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZahtevService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "ZahtevPort")
    public ZahtevInterface getZahtevPort() {
        return super.getPort(ZahtevPort, ZahtevInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IzvestajInterface
     */
    @WebEndpoint(name = "ZahtevPort")
    public ZahtevInterface getZahtevPort(WebServiceFeature... features) {
        return super.getPort(ZahtevPort, ZahtevInterface.class, features);
    }

}
