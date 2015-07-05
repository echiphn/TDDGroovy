package org.groovy.basic

import org.codehaus.groovy.runtime.InvokerHelper
import org.junit.Test

import static junit.framework.Assert.assertEquals
import static junit.framework.Assert.assertTrue

/**
 * Created by cle on 27/06/15.
 */
class DefaultGroovyMethodsTest {
    @Test
    public void testInjectMethod() {
        def numbers = [1, 2, 3, 4, 5, 6, 7] as List
        assertEquals(28, numbers.inject(0) { sum, item -> sum + item })
        assertEquals(28, numbers.iterator().inject(0) { sum, item -> sum + item })
    }

    private class ClassWithIterator {
        def min = 0
        def max = 10

        Iterator iterator() {
            def list = []
            for (int i = min; i < max; i++) {
                list << i
            }
            return list.iterator()
        }

        int range() {
            return max - min
        }
    }

    private class ClassWithIteratorOtherForm extends ClassWithIterator {
        def i = min

        Iterator iterator() {
            return [hasNext: { i < max }, next: { i++ }] as Iterator
        }
    }

    @Test
    public void testAsIterator() {
        Iterator<Object> iterator = InvokerHelper.asIterator(new String("laldk"))
        assertEquals(5, iterator.size())

        iterator = InvokerHelper.asIterator(new Integer(3))
        assertEquals(1, iterator.size())

        iterator = InvokerHelper.asIterator(new Integer(3))
        assertTrue(iterator.next() instanceof Integer)

        def instanceOfClassWihtIterator = new ClassWithIterator()
        iterator = InvokerHelper.asIterator(instanceOfClassWihtIterator)
        assertEquals(instanceOfClassWihtIterator.range(), iterator.size())

        instanceOfClassWihtIterator = new ClassWithIteratorOtherForm()
        iterator = InvokerHelper.asIterator(instanceOfClassWihtIterator)
        assertEquals(instanceOfClassWihtIterator.range(), iterator.size())
    }

}
