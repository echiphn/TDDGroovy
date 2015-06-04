package groovy.org.basic;

import static org.junit.Assert.*

import org.junit.Test

class RegularExpressionTest {

	@Test
	public void testMatchOperator() {
		assertTrue("abc" ==~ /abc/);
		assertTrue("abc" ==~ 'abc');
		assertFalse("abcabc" ==~ 'abc');
		assertTrue("Starts with a, 1 character, ends with c","abc" ==~ /^a.c/);
		assertTrue("Starts with a, 2 characters","abc" ==~ /^a../);
		assertTrue("zero or more characters, ends with c","abc" ==~ /.*c$/);
		assertTrue("zero or more characters, ends with c","c" ==~ /.*c$/);
	}

	@Test
	public void testFindOperator() {
		assertTrue("abc" ==~ /abc/);
		assertTrue("abc" ==~ 'abc');
		assertFalse("abcabc" ==~ 'abc');
		assertTrue("Starts with a, 1 character, ends with c","abc" ==~ /^a.c/);
		assertTrue("Starts with a, 2 characters","abc" ==~ /^a../);
		assertTrue("zero or more characters, ends with c","abc" ==~ /.*c$/);
		assertTrue("zero or more characters, ends with c","c" ==~ /.*c$/);
	}
}
