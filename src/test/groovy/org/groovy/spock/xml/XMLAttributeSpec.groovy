package org.groovy.spock.xml
import groovy.util.slurpersupport.Attributes;
import spock.lang.Shared;
import spock.lang.Specification;


class XMLAttributeSpec extends Specification{
	@Shared
	def p="""<person id="99" ssn="555-11-222">John Smith</person>"""
	def "reading attribute using xmlparser"(){
		when:"parser the text using xml parser"
		def person=new XmlParser().parseText(p)
		then:"attribute can be read using [@att] form"
		assert "99" == person["@id"]
	}
	
	def "reading attribute using xml slurper"(){
		when:"parser the text using xml parser"
		def person=new XmlSlurper().parseText(p)
		then:"attribute can be read using [@att] form"
		assert "99" == person["@id"].text()
		and: "selected item is an instance of groovy.util.slurpersupport.Attributes"
		assert person["@id"] instanceof Attributes 
	}
}
