package com.organ.project_organ.ws.client;
import com.organ.project_organ.ws.hello.Hello;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class HelloClient {

    public void testIt() {
    	
		try {
			//kreiranje web servisa
			URL wsdlLocation = new URL("http://localhost:8081/ws/helloMessage?wsdl");
			QName serviceName = new QName("http://soap.spring.com/ws/hello", "HelloService");
			QName portName = new QName("http://soap.spring.com/ws/hello", "HelloPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			Hello hello = service.getPort(portName, Hello.class);
			
			//poziv web servisa
			String response = hello.sayHi("Mika");
			System.out.println("Response from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloClient client = new HelloClient();
		client.testIt();
    }

}