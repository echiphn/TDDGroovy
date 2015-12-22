package org.groovy.script

import spock.lang.Specification


class ParseScriptSpec extends Specification{
	def "parsing script in a script instance to reuse"(){
		given:"a formula"
		def monthly="amount*(rate/12) / (1-(1+rate/12)**-numberOfMonths)"
		and:"a shell"
		def shell=new GroovyShell()
		and:"a script is parsed"
		def script=shell.parse(monthly)

		when:"the script is called"
		script.binding=bindingObj
		def result = script.run() 
		then:"the result of the script has to matcht with expected result"
		expectedResult==result
		
		where:
		bindingObj||expectedResult
		new Binding(amount: 185000,rate: 3.50/100,numberOfMonths: 300)||926.1536089487843
		new Binding(amount: 154000,rate: 3.75/100,numberOfMonths: 240)||913.0480050387338
		
	}
}
