package org.groovy.mop

class MethodMissingImpl {
	def methodMissing(String name,Object args){
		"call $name with $args"
	}
	
	def propertyMissing(String propName){
		"no property $propName exist"
	}
}
