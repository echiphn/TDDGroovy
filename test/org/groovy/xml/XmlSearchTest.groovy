package org.groovy.xml

import org.junit.Test

import java.nio.file.Paths

/**
 * Created by cle on 04/07/15.
 */
class XmlSearchTest {
    static final XML_FILE = Paths.get(this.class.getResource("/resources/xml/xml2search.xml").toURI()).toFile()

    /**
     * Look for any node with a tag name equals 'book' having an available with a value of '5'.
     */
    @Test
    public void testSearchBreadthFirst() {
        def xmldoc = new XmlSlurper().parse(XML_FILE)
        def booksWithAvail = xmldoc.value.books.'*'.findAll { node ->
            node.name() == 'book' && node.@available == '5'
        }
        assert booksWithAvail.size() == 3
        def foundIds = []
        booksWithAvail.each { foundIds << it.@id.text() }
        def expectedIds = ["4", "5", "6"]
        assert expectedIds.equals(foundIds)
        booksWithAvail.every { assert it.author.text() == 'Manuel De Cervantes' }
    }

    @Test
    public void testSearchDepthFirst() throws Exception {
        def xmldoc = new XmlSlurper().parse(XML_FILE)
        def foundId = xmldoc.'**'.find { book ->
            book.author.text() == 'Lewis Carroll'
        }.@id
        assert '3' == foundId.text()
    }
}
