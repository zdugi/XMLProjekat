package com.mailserver.MailServer.ws.mail;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;


@WebServiceClient(name = "MailService", 
                  wsdlLocation = "classpath:wsdl/Mail.wsdl",
                  targetNamespace = "http://soap.spring.com/ws/mail") 
public class MailService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.spring.com/ws/mail", "MailService");
    public final static QName MailPort = new QName("http://soap.spring.com/ws/mail", "Mailort");
    static {
        URL url = MailService.class.getClassLoader().getResource("wsdl/Mail.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(MailService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/Mail.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public MailService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MailService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MailService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public MailService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public MailService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public MailService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "MailPort")
    public MailInterface getMailPort() {
        return super.getPort(MailPort, MailInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IzvestajInterface
     */
    @WebEndpoint(name = "MailPort")
    public MailInterface getMailPort(WebServiceFeature... features) {
        return super.getPort(MailPort, MailInterface.class, features);
    }

}
