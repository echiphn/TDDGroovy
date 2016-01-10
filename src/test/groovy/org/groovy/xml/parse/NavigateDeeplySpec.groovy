package org.groovy.xml.parse

import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.NodeChildren
import spock.lang.Specification

class NavigateDeeplySpec extends Specification {
	def "navigate deeply with xml spluper"(){
		given:"a xml text"
		def p="""<person id="100">
					<firstname>Jane</firstname>
					<lastname>Doe</lastname>
					<address type="home">
						<street>123 Main St</street>
						<city>Denver</city>
						<state>CO</state>
						<zip>80020</zip>
					</address>
				</person>"""
		when:"parsing this text using xml splurper"
		def person=new XmlSlurper().parseText(p)
		then:"result of navigation using slurper"
		assert "123 Main St" == person.address.street.text()
		and:"person is a node"
		assert person instanceof NodeChild
		and:"type of an element is groovy.util.slurpersupport.NodeChildren"
		assert person.address.street instanceof NodeChildren
		assert person.address instanceof NodeChildren
	}
}
