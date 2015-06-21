package org.groovy.xml

import groovy.xml.XmlUtil
import org.junit.Test

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

import static junit.framework.Assert.assertEquals

/**
 * Created by cle on 21/06/15.
 */
class XmlModifyTest {
    @Test
    public void testModifyXml() {
        def bookPath = Paths.get(this.getClass().getResource("/resources/xml/books2modify.xml").toURI())
        def copiedPath = Paths.get(bookPath.getParent().toAbsolutePath().toString() + '/books2modify_copied.xml')
        Files.copy(bookPath,copiedPath,StandardCopyOption.REPLACE_EXISTING)

        def origFile = bookPath.toFile()
        def books = new XmlParser().parse(origFile)
        assertEquals("Groovy in Action", books.book[0].title[0].value()[0])
        books.book[0].title[0].value()[0] = 'Modified Groovy in Action'
        copiedPath.toFile().withWriter { writer -> new XmlUtil().serialize(books, writer) }
        def copiedBook = new XmlParser().parse(copiedPath.toFile())
        assertEquals("Modified Groovy in Action", copiedBook.book[0].title[0].value()[0])
    }
}
