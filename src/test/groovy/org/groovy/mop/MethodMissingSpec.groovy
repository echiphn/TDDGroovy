package org.groovy.mop

import spock.lang.Shared;
import spock.lang.Specification

class MethodMissingSpec extends Specification{
	@Shared
	MethodMissingImpl service;
	def setup(){
		service=new MethodMissingImpl()
	}
	def "call service with not existing function"(){
		expect:"when serviceimpl is called with unknown function then the manipulated missingMethod will be called"
		assert 'call hello with [World]'==service.hello('World')
	}
	
	def "call not exist property"(){
		expect:"whenn access to a not existing property"
		assert "no property testproperty exist" == service.testproperty
	}
}
