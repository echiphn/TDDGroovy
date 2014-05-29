package groovy.org.basic;

import static org.junit.Assert.*

import org.groovy.Employee
import org.junit.Test

class EmployeeTest {

	/**
	 *  Employee doesn't have to have setter and getter method!
	 */
	@Test
	public void testGetterSetter() {
		Employee emp=new Employee();
		emp.setName("test");
		assertEquals("test",emp.getName());
	}
}
