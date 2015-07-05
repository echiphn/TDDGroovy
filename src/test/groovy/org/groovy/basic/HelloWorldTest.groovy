package org.groovy.basic

import org.junit.Test

class HelloWorldTest {
	String name;
	String note;

	@Test
	public void testPrintout() {

		def todos = [
			new HelloWorldTest(name:"1", note:"one"),
			new HelloWorldTest(name:"2", note:"two"),
			new HelloWorldTest(name:"3", note:"three")
		]

		todos.each { println "${it.name} ${it.note}" }
	}
}
