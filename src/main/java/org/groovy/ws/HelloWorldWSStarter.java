package org.groovy.ws;

import javax.xml.ws.Endpoint;

public class HelloWorldWSStarter {
	public static void main(String []args){
		Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
	}
}
