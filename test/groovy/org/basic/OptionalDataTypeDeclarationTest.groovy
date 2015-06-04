package groovy.org.basic

import org.junit.Test

import static junit.framework.Assert.assertEquals

/**
 * Created by cle on 04/06/15.
 */
class OptionalDataDeclarationTest {

    @Test
    public void testDataTypeDeclaration() {
        def w = "hello"
        assertEquals(w.class, String.class)
        println('test success')
    }
}
