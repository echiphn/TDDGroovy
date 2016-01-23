package org.groovy.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace = "http://predic8.com/groovy-jax/")
@SOAPBinding(style = Style.RPC)
public interface HelloWorld {
	@WebMethod
	public String getHelloWorldAsString(String name);
}
