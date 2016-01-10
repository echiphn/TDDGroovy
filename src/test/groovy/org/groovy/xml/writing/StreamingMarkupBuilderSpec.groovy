package org.groovy.xml.writing

import groovy.xml.StreamingMarkupBuilder

import org.junit.Assert
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.diff.DefaultNodeMatcher
import org.xmlunit.diff.Diff
import org.xmlunit.diff.ElementSelectors

import spock.lang.Specification;

class StreamingMarkupBuilderSpec extends Specification{
	def "simple example how StreamingMarkupBuilder works"(){
		given:"a StreamingMarkupBuilder"
		def StringWriter writer = new StringWriter()
		def builder = new StreamingMarkupBuilder()
		and:"a closure of a person"
		def person={
			PERSON(id:100){
				firstname("Jane")
				LastName("Doe")
			}
		}
		when:"markupbuilder init with xml value"
		builder.bind(person).writeTo(writer)
		then:"compare with existing file. That should be no difference between them"
		def File expectedXmlFile = new File(this.getClass().getResource("/xml/markUpBuilder_result.xml").toURI())
		Diff diff = DiffBuilder.compare(expectedXmlFile.text).withTest(writer.toString())
				.ignoreWhitespace().withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
				.checkForIdentical().build()
		Assert.assertTrue("XML Difference "+diff.toString(),!diff.hasDifferences())
	}

	def "create xml partly using StreamingMarkupBuilder"(){
		given:"a StreamingMarkupBuilder"
		def StringWriter writer = new StringWriter()
		def builder = new StreamingMarkupBuilder()
		and:"a list of persons"
		def person1={
			person(id:100){
				firstname("Jane")
				lastname("Doe")
			}
		}
		def person2={
			person(id:101){
				firstname("Jane")
				lastname("Doe")
			}
		}
		def person3={
			person(id:102){
				firstname("Jane")
				lastname("Doe")
			}
		}
		def persons={
			"person-list"{
				out << person1
				out << person2
				out << person3
			}
		}

		when:"markupbuilder init with xml value"
		builder.bind(persons).writeTo(writer)

		then:"compare to a existing xml string"
		println writer.toString();
		def expectedXml="""
						<person-list>
							<person id='100'>
								<firstname>Jane</firstname><lastname>Doe</lastname>
							</person>
							<person id='101'>
								<firstname>Jane</firstname><lastname>Doe</lastname>
							</person>
							<person id='102'>
								<firstname>Jane</firstname><lastname>Doe</lastname>
							</person>
						</person-list>
						"""
		Diff diff = DiffBuilder.compare(expectedXml).withTest(writer.toString())
				.ignoreWhitespace().withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
				.checkForIdentical().build()
		Assert.assertTrue("XML Difference "+diff.toString(),!diff.hasDifferences())
	}
}
