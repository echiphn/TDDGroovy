package org.groovy.basic;

import static org.junit.Assert.*

import org.codehaus.groovy.runtime.GStringImpl
import org.junit.Test

class StringTest {

	@Test
	public void testStringEvaluate() {
		def age=25;
		def expectedString = "My age is 25"
		def actualObject = "My age is ${age}"
		assertTrue(actualObject instanceof GStringImpl);
		assertFalse(expectedString.equals(actualObject));
		assertEquals(expectedString,actualObject.toString());
	}
}
