package org.groovy.spock.xml

import groovy.util.slurpersupport.NodeChildren;
import spock.lang.Shared;
import spock.lang.Specification

class ParseWithNameSpaceSpec extends Specification{
	@Shared
	def xmlText="""
					<p:person
						xmlns:p="http://somewhere.org/person"
						xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xsi:schemaLocation="http://somewhere.org/person
						http://somewhere.org/person.xsd"
						id="99" >
						<p:firstname>John</p:firstname>
						<p:last-name>Smith</p:last-name>
					</p:person>
				"""
	def "how to parse xml with a namespace using xmlparser"(){
		when:"parsing the xml text with xmlparser"
		def person=new XmlParser().parseText(xmlText)
		then:"try to find person firstname without namespace will returns empty array"
		assert [] == person.firstname
		and:"try to find person firstname with correct namespace"
		def namespace = new groovy.xml.Namespace("http://somewhere.org/person")
		assert "John" == person[namespace.firstname].text()
		and:"firstname is a instance of NodeList"
		assert person[namespace.firstname] instanceof groovy.util.NodeList;
	}
	
	def "how to parse xml with namespace using XMLSlurper"(){
		when:"parsing the xml text with xmlslurper"
		def person = new XmlSlurper().parseText(xmlText)
		then:"XMLSlurper ignores namespace at default"
		assert "John" == person.firstname.text()
		and:"firstname is a instance of NodeChildren"
		assert person.firstname instanceof NodeChildren
	}
	
	def "parse xml with a namespace and without a namespace"(){
		given:"a xml text with different namespace elements in its body"
		def xmlText="""
					<item xmlns:product="urn:somecompany:products"
						xmlns:vendor="urn:somecompany:vendors" >
						<product:name>iPhone</product:name>
						<vendor:name>Apple</vendor:name>
						<quantity>1</quantity>
					</item>
					"""
		when:"parsing the xml text using XMLSlurper"
		def item= new XmlSlurper().parseText(xmlText)
		then:"access same xml element will return all elements regardless of namespace"
		assert "iPhoneApple" == item.name.text()
		and:"access using namespaces"
		def ns=[product:"urn:somecompany:products",vendor:"urn:somecompany:vendors"]
		item.declareNamespace(ns)
		assert "iPhone" == item.'product:name'.text()
		assert "Apple" == item.'vendor:name'.text()
	}
}
