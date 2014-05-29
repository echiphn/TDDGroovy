package groovy.org.groovy;

import static org.junit.Assert.*

import org.junit.Test

class NumberTest {

	static final BigDecimal EPSILON = 0.000000001

	@Test
	public void testIntegerExpression() {
		assertEquals(8, 5.plus(3));
		assertEquals(8, 5+3);
		assertEquals(2, 5.minus(3));
		assertEquals(15, 5.multiply(3));
		assertEquals(2, 5.mod(3));
		assertEquals(1.6666666, 5.div(3),0.00001);
	}

	@Test
	public void testFloatExpression() {
		assertEquals(8.0, 5.0.plus(3.0));
		assertEquals(8.0, 5.0+3.0);
		assertEquals(2.0, 5.0.minus(3.0));
		assertEquals(15.00, 5.0.multiply(3.0),EPSILON);
		assertEquals(1.66666666666, 5.0.div(3.0),EPSILON);
	}

	@Test
	public void testMixExpression() {
		assertEquals(8.2, 5.2.plus(3));
		assertEquals(8.2, 5+3.2);
		assertEquals(16, 5.multiply(3.2),EPSILON);
	}

	@Test
	public void testPostIncrement() {
		def x=10;
		def y=x++;
		assertEquals(11,x);
		assertEquals(10,y);
	}

	@Test
	public void testPreIncrement() {
		def x=20;
		def y=++x;
		assertEquals(21,x);
		assertEquals(21,y);
	}
	@Test
	public void testRelationalOperators(){
		assertFalse(5.compareTo(3)<0);
		assertFalse(5.compareTo(3)<=0);
		assertTrue(5.compareTo(3)>0);
		assertTrue(5.compareTo(3)>=0);

		assertFalse(5.equals(3));
		assertEquals(1,5.compareTo(3));

		def age=25;
		def number=25;
		assertTrue(age.equals(number));
	}
}
