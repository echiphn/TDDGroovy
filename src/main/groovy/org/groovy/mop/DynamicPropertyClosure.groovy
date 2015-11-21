package org.groovy.mop

class DynamicPropertyClosure {
	Closure whatTodo={name-> "accessed $name"}
	def propertyMissing(String name){
		whatTodo(name)
	}
}
