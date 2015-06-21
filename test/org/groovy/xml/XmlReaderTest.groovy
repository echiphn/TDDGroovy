package org.groovy.xml

import org.junit.Test

import java.nio.file.Paths

import static junit.framework.Assert.assertEquals

/**
 * Created by cle on 14/06/15.
 */
class XmlReaderTest {
    @Test
    public void testReadBooks() {
        def bookPath = Paths.get(this.getClass().getResource("/resources/xml/books.xml").toURI())
        def bookDoc = new XmlSlurper().parse(bookPath.toFile())
        assertEquals("Groovy in Action", bookDoc.book[0].title.toString())
        def foundBooks=bookDoc.book.findAll {it.@isbn=="DEMU9474734"}
        assertEquals(1,foundBooks.size())
        assertEquals("Glen Smith",foundBooks.getAt(0).author[0].toString())
        assertEquals("Peter Ledbrook",foundBooks.getAt(0).author[1].toString())
    }
}
