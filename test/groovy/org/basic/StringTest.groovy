package groovy.org.groovy;

import static org.junit.Assert.*

import org.junit.Ignore;
import org.junit.Test

class StringTest {

	@Test
	public void testStringEvaluate() {
		def age=25;
		assertEquals("My age is 25","My age is ${age}");
	}
}
