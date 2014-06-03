package groovy.org.basic;

import static org.junit.Assert.*
import groovy.json.JsonSlurper

import org.junit.Test

class NativeJSONSupportTest {

	@Test
	public void testJSONParser() {
		def slurper = new JsonSlurper();
		def result = slurper.parseText('{"person":{"name":"John Doe","age":40,"cars":["bmw","ford"]}}');
		assertEquals("John Doe",result.person.name);
		assertEquals(40,result.person.age);
		assertEquals(2,result.person.cars.size());
		assertEquals("bmw",result.person.cars[0]);
	}
}
