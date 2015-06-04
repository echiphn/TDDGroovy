package groovy.org.basic

import org.groovy.Employee
import org.junit.Assert
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class EmployeeTest {

    /**
     *  Employee doesn't have to have setter and getter method!
     */
    @Test
    public void testGetterSetter() {
        Employee emp = new Employee()
        emp.setSureName("test")
        emp.old = 20
        emp.setFirstName('firstname')
        assertEquals("test", emp.getSureName())
        assertEquals(20, emp.getOld())
        assertEquals('firstname', emp.getFirstName())
        assertEquals('firstname test',emp.getFullName().toString())
    }
}
