package org.groovy.basic

import org.codehaus.groovy.runtime.GStringImpl

import static junit.framework.Assert.*
import org.junit.Test

/**
 * Created by cle on 04/06/15.
 */
class GStringTest {
    @Test
    public void testGString(){
        def name="John"
        assertEquals(String.class,name.class)
        assertEquals(String.class,"Hello".class)
        assertTrue("Hello ${name}" instanceof GString)
        assertEquals("Hello John","Hello ${name}".toString())
    }
}
