package org.groovy.ast

import groovy.transform.builder.Builder;
import spock.lang.Specification;
@Builder
class Chemist{
	String first
	String last
	int born
}

class BuilderSpec extends Specification{
	def "a builder will implemented autoamtically"(){
		expect:"a builder"
		def builder=Chemist.builder()
		def c= builder.first("Robert").last("Ivanov").born(1934).build()
		assert "Robert" == c.first
		assert 1934 == c.born
	}
	
}
