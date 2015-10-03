package org.groovy.basic.closure


import org.hamcrest.core.IsEqual;

import spock.lang.Specification;
import static org.hamcrest.MatcherAssert.*;
class ClosureFeaturesSpec extends Specification{
	def "A simple declaration of closure"(){
		given: def log=''
		when: (1..10).each {log+=it}
		then: assertThat(log,IsEqual.equalTo('12345678910'))
	}

	class SizeFilter{
		Integer limit
		boolean sizeUpTo(String value){
			return value.size()<=limit
		}
	}
	def "Closure as an already declared method"(){
		given:
		SizeFilter sizefilter6=new SizeFilter(limit:6)
		SizeFilter sizefilter5=new SizeFilter(limit:5)
		Closure sizeUpTo6=sizefilter6.&sizeUpTo
		def words=[
			"long string",
			"medium",
			"short",
			"tiny"
		]
		expect:
		assertThat("medium",IsEqual.equalTo(words.find(sizeUpTo6)))
		assertThat("short",IsEqual.equalTo(words.find(sizefilter5.&sizeUpTo)))
	}

	class MultipleMethod{
		int mysteryMethod(String value){
			return value.length()
		}
		int mysteryMethod(List list){
			return list.size()
		}
		int mysteryMethod(int x,int y){
			return x+y
		}
	}
	def "Runtime overloading resolution of method"(){
		given:
		MultipleMethod instance=new MultipleMethod()
		Closure multiMethod=instance.&mysteryMethod
		expect:
		assertThat(10, IsEqual.equalTo(multiMethod('string arg')))
		assertThat(3, IsEqual.equalTo(multiMethod([
			'list',
			'with three',
			'elements'
		])))
		assertThat(15, IsEqual.equalTo(multiMethod(9,6)))
	}
}
