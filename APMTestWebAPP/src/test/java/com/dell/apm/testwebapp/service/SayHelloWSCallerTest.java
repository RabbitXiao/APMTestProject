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
        /*SayHelloWSCaller caller = new SayHelloWSCaller(new URL("http://localhost:8988/APMTestWebApp/service/HelloWs?wsdl"));
        String result = caller.sayHello("Tom");
        assertEquals("content should equals", result, "Hello, Tom!");   */
    }
}
