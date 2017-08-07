package com.dell.apm.testwebapp.service;

import com.dell.apm.testwebapp.internal.SayHelloWSCaller;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by rxiao on 3/21/14.
 */
public class SayHelloWSCallerTest {
    @Test
    public void testSayHello() throws MalformedURLException {
        /*
        String wsdlURL = "http://10.154.10.138:9080/APMTestServiceAPP/webservice/sayHello?wsdl";
        //String wsdlURL = "http://localhost:8780/APMTestServiceAPP/webservice/sayHello?wsdl";
        SayHelloWSCaller caller = new SayHelloWSCaller(new URL(wsdlURL));
        String result = caller.sayHello("Tom");
        assertEquals("content should equals", result, "Hello, Tom!");
        */
    }
}
