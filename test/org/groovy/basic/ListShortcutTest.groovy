package org.groovy.basic

import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by cle on 04/06/15.
 */
class ListShortcutTest {
    @Test
    public void testListShortcut() {
        def languages = ["Python", "Groovy", "Java"]
        assertEquals("ArrayList is implicit class", ArrayList.class, languages.class)
        languages << "Groovy"
        assertEquals("size of the list is incremented", 4, languages.size())
        def setLanguages = ["Python", "Groovy", "Java"] as Set
        assertEquals(3, setLanguages.size())
        setLanguages << "Groovy"
        assertEquals("size of the set is not changed", 3, setLanguages.size())
    }

    @Test
    public void testSort() {
        def languages = ["Python", "Groovy", "Java"]
        languages.sort()
        def expectedLanguages = ["Groovy", "Java", "Python"]
        assertTrue(expectedLanguages.equals(languages))
    }

    @Test
    public void testReverse() {
        def languages = ["Python", "Groovy", "Java"]
        def newLanguages = languages.reverse()
        def expectedLanguages = ["Java", "Groovy", "Python"]
        assertTrue(expectedLanguages.equals(newLanguages))
        assertFalse(expectedLanguages.equals(languages))
        languages.reverse(true)
        assertTrue("mutate reverse", expectedLanguages.equals(languages))
    }

    @Test
    public void testConcatenating() {
        def languages = ["Python", "Groovy", "Java"]
        languages += ["Javascript", "Scala"]
        def expectedLanguages = ["Python", "Groovy", "Java", "Javascript", "Scala"]
        assertTrue(expectedLanguages.equals(languages))
    }

    @Test
    public void testJoin() {
        def languages = ["Python", "Groovy", "Java"]
        def result=languages.join()
        assertEquals("PythonGroovyJava",result)
        assertEquals("Python,Groovy,Java",languages.join(","))
    }

    @Test
    public void testFindAll() {
        def languages = ["Python", "Groovy", "Java"]
        def result=languages.findAll{it.startsWith("P")}
        assertEquals(1,result.size())
        assertEquals("Python",result.get(0))
    }

    @Test
    public void testCollect() {
        def languages = ["Python", "Groovy", "Java"]
        def newLanguages=languages.collect {it +=" programming language"}
        newLanguages.each {assertTrue(it.contains("programming language"))}
    }
}
