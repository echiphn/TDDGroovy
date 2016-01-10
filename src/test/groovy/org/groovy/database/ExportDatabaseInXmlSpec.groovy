package org.groovy.database

import groovy.sql.Sql

import org.junit.Assert
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.diff.DefaultNodeMatcher
import org.xmlunit.diff.Diff
import org.xmlunit.diff.ElementSelectors

import spock.lang.Shared
import spock.lang.Specification

class ExportDatabaseInXmlSpec extends Specification{
	@Shared
	sql=Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")
	def setupSpec(){
		sql.execute("create table addressbook (name varchar, address varchar, city varchar, st varchar,zipcode varchar)")
		sql.execute("""insert into addressbook values ('John Smith', '456 Fleet St', 'Denver', 'CO', '80021'),
					('Jane Doe', '123 Main St', 'Denver', 'CO', '80020'), ('Frank Jones', '345 Center Blvd', 'Omaha', 'NE', '68124')""")
	}
	def "export addressbook in xml"(){
		when:"exporting from db and write in System.out"
		def writer = new StringWriter()
		def xml = new groovy.xml.MarkupBuilder(writer)
		xml.addressBook{
			sql.eachRow("select * from addressbook" ){ row ->
				entry{
					name(row.name)
					addresss(row.address)
					city(row.city)
					state(row.st)
					zipcode(row.zipcode)
				}
			}
		}
		then:"result has to match expected xml text"
		def expectedXml="""
						<addressBook>
						      <entry>
						        <name>John Smith</name>
						        <addresss>456 Fleet St</addresss>
						        <city>Denver</city>
						        <state>CO</state>
						        <zipcode>80021</zipcode>
						      </entry>
						      <entry>
						        <name>Jane Doe</name>
						        <addresss>123 Main St</addresss>
						        <city>Denver</city>
						        <state>CO</state>
						        <zipcode>80020</zipcode>
						      </entry>
						      <entry>
						        <name>Frank Jones</name>
						        <addresss>345 Center Blvd</addresss>
						        <city>Omaha</city>
						        <state>NE</state>
						        <zipcode>68124</zipcode>
						      </entry>
						    </addressBook>
						"""
		Diff diff = DiffBuilder.compare(expectedXml).withTest(writer.toString())
				.ignoreWhitespace().withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
				.checkForIdentical().build()
		Assert.assertTrue("XML Difference "+diff.toString(),!diff.hasDifferences())
	}
}
