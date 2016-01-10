package org.groovy.spock.xml.writing

import groovy.xml.MarkupBuilder

import org.junit.Assert
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.diff.DefaultNodeMatcher
import org.xmlunit.diff.Diff
import org.xmlunit.diff.ElementSelectors

import spock.lang.Specification

class MarkupBuilderSpec extends Specification{
	def "writing a object in xml string using MarkupBbuilder"(){
		given:"a markupbuilder"
		def StringWriter writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		when:"markupbuilder init with xml value"
		xml.PERSON(id:100){
			firstname("Jane")
			LastName("Doe")
		}
		then:"compare with existing file. That should be no difference between them"
		def File expectedXmlFile = new File(this.getClass().getResource("/xml/markUpBuilder_result.xml").toURI())
		Diff diff = DiffBuilder.compare(expectedXmlFile.text).withTest(writer.toString())
		.ignoreWhitespace().withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
		.checkForIdentical().build()
		Assert.assertTrue("XML Difference "+diff.toString(),!diff.hasDifferences())
	}
	
	def "writing xml with namespace"(){
		given:"a markupbuilder"
		def StringWriter writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		
		when:"markupbuilder init with xml value and namespace"
		def params=["xmlns:product":"urn:somecompany:products","xmlns:vendor":"urn:somecompany:vendors","id":99]
		xml.person(params){
			firstname("Jane")
			lastname("Doe")
		}
		then:"compare with existing file. That should be no difference between them"
		def File expectedXmlFile = new File(this.getClass().getResource("/xml/markUpBuilder_namespace_result.xml").toURI())
		Diff diff = DiffBuilder.compare(expectedXmlFile.text).withTest(writer.toString())
		.ignoreWhitespace().withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
		.checkForIdentical().build()
		Assert.assertTrue("XML Difference "+diff.toString(),!diff.hasDifferences())
	}
}
