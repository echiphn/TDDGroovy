package org.groovy.basic

class HelloWorldTest {
	String name;
	String note;
	public static void main(String[] args) {

		def todos = [
			new HelloWorldTest(name:"1", note:"one"),
			new HelloWorldTest(name:"2", note:"two"),
			new HelloWorldTest(name:"3", note:"three")
		]

		todos.each { println "${it.name} ${it.note}" }
	}
}
