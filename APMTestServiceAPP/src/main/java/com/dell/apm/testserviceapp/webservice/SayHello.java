package com.dell.apm.testserviceapp.webservice;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * Created by rxiao on 3/21/14.
 */

@WebService
@SOAPBinding(style = Style.RPC)
public class SayHello {

    public String sayHello(String name) {
        if (name == null) {
            return "Hello";
        }

        return "Hello, " + name + "!";
    }
}
