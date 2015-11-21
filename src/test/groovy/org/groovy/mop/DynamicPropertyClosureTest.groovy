package org.groovy.mop;
import spock.lang.Shared
import spock.lang.Specification;

import static org.junit.Assert.*;

import org.junit.Test;


class DynamicPropertyClosureSpec extends Specification {

	def "propertyMissing method is delegated to a closure"(){
		given:"a impl of dynamicproperty closure"
		DynamicPropertyClosure service=new DynamicPropertyClosure()
		expect:"missing property is called, then propertyMissing function is delegated to a closure"
		assert "accessed hello"==service.hello
	}

	def "closure can be changed in run time"(){
		given:"a impl of dynamicproperty closure"
		DynamicPropertyClosure service=new DynamicPropertyClosure()
		and:"closure is changed in runtime"
		service.whatTodo={name -> name.size()}
		when:"not existing property is called"
		def result=service.hello
		then:"behavior of impl will be changed appropriatly"
		assert 5== result
	}
}
