package com.xmlproject.project_poverenik.ws.izvestaj;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;


@WebServiceClient(name = "IzvestajService", 
                  wsdlLocation = "classpath:wsdl/Izvestaj.wsdl",
                  targetNamespace = "http://soap.spring.com/ws/report") 
public class IzvestajService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.spring.com/ws/report", "IzvestajService");
    public final static QName IzvestajPort = new QName("http://soap.spring.com/ws/report", "IzvestajPort");
    static {
        URL url = IzvestajService.class.getClassLoader().getResource("wsdl/Izvestaj.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(IzvestajService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/Izvestaj.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public IzvestajService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IzvestajService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IzvestajService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public IzvestajService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public IzvestajService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public IzvestajService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "IzvestajPort")
    public IzvestajInterface getIzvestajPort() {
        return super.getPort(IzvestajPort, IzvestajInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IzvestajInterface
     */
    @WebEndpoint(name = "IzvestajPort")
    public IzvestajInterface getIzvestajPort(WebServiceFeature... features) {
        return super.getPort(IzvestajPort, IzvestajInterface.class, features);
    }

}
