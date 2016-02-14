package org.groovy.ast

import groovy.transform.Canonical;
import spock.lang.Specification;

@Canonical
class Person{
	String first
	String last
}

class CanonicalSpec extends Specification{
	def "canonical entity has constructors"(){
		given:"a canonical instance"
		Person p=new Person(first:'Thomas',last:'Huth')
		Person p2=new Person('Thomas','Huth')
		expect:"toString is automatically implemnted"
		assert 'Thomas' == p.first
		assert 'Huth' == p.last
		assert 'Thomas' == p2.first
		assert 'Huth' == p2.last
		and:"equals function is also implemented be default"
		assert p == p2
		and:"also the hashCode funtion is implemented"
		assert p.hashCode() == p2.hashCode()
	}
}
