package org.groovy.basic

import spock.lang.Specification;

class NamedParameterOrderSpec extends Specification{
	def namedParameter(Map params){
		assert params.a==1
		assert params.b==2
		assert params.c==3
		true
	}
	def "We can pass parameters in any order"(){
		expect: "We can pass parameters in any order"
		namedParameter(a:1,b:2,c:3)
		namedParameter(b:2,c:3,a:1)
		namedParameter(a:1,b:2,c:3)
	}
	def namedParamsMethod2(Map params, String param2, String param3) {
		assert params.a == 1
		assert params.b == 2
		assert params.c == 3
		assert param2 == "param2"
		assert param3 == "param3"
		true
	}
	def "We can mix named and regular parameter in any order"(){
		expect: "We can mix named and regular parameter in any order"
		namedParamsMethod2(a:1, b:2, c:3, "param2", "param3")
		namedParamsMethod2("param2", b:2, "param3", c:3, a:1)
		namedParamsMethod2(c:3, "param2", a:1, "param3", b:2)
	}
}
