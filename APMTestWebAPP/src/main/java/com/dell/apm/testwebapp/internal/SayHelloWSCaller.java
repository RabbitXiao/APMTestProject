package com.dell.apm.testwebapp.internal;

import com.dell.apm.testwebapp.internal.webservice.client.SayHello;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by rxiao on 3/21/14.
 */
public class SayHelloWSCaller {
    private URL wsdlURL;

    public SayHelloWSCaller(URL wsdlURL) {
        this.wsdlURL = wsdlURL;
    }

    public String sayHello(String content) {
        QName qName = new QName("http://webservice.testserviceapp.apm.dell.com/",
                "SayHelloService");
        Service service = Service.create(wsdlURL, qName);
        SayHello helloService = service.getPort(SayHello.class);
        return helloService.sayHello(content);
    }

}
