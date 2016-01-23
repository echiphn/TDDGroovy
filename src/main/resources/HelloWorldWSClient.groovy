@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.soap.*

def client = new SOAPClient('http://localhost:9999/ws/hello')
def response = client.send() {
	envelopeAttributes "xmlns:ex":"http://predic8.com/groovy-jax/"
	body {
		'ex:getHelloWorldAsString'() { arg0("your name here") }
	}
}

//def response = client.send ("""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gro="http://predic8.com/groovy-jax/">
//   <soapenv:Header/>
//   <soapenv:Body>
//      <gro:getHelloWorldAsString>
//         <arg0>your name here</arg0>
//      </gro:getHelloWorldAsString>
//   </soapenv:Body>
//</soapenv:Envelope>""")
println 'httprequest '+ response.httpRequest.contentAsString
println "greeting " + response.body.toString()