package groovy.org.basic

import static org.junit.Assert.*
import org.junit.Test

/**
 * Created by cle on 04/06/15.
 */
class SafeDereferencingTest {
    @Test(expected = NullPointerException.class)
    public void testNotSafeDereferencing(){
        String s=null
        s.size()
    }
    @Test
    public void testSafeDereferencing(){
        String s=null
        assertEquals(null,s?.size())
    }
}
