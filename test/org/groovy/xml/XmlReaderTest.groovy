package org.groovy.xml

import groovy.util.slurpersupport.GPathResult
import groovy.xml.StreamingMarkupBuilder
import org.junit.Test

import java.nio.file.Paths

import static junit.framework.Assert.assertEquals
import static junit.framework.Assert.assertTrue

/**
 * Created by cle on 14/06/15.
 */
class XmlReaderTest {
    @Test
    public void testReadBooks_slurper() {
        def bookPath = Paths.get(this.getClass().getResource("/resources/xml/books.xml").toURI())
        def bookDoc = new XmlSlurper().parse(bookPath.toFile())
        assertTrue(bookDoc instanceof GPathResult)
        assertEquals("Groovy in Action", bookDoc.book[0].title.text())
        def foundBooks = bookDoc.book.findAll { it.@isbn == "DEMU9474734" }
        assertEquals(1, foundBooks.size())
        assertEquals("Glen Smith", foundBooks.getAt(0).author[0].toString())
        assertEquals("Peter Ledbrook", foundBooks.getAt(0).author[1].toString())
    }

    @Test
    public void testReadBooks_xmlparser() {
        def bookPath = Paths.get(this.getClass().getResource("/resources/xml/books.xml").toURI())
        def bookDoc = new XmlParser().parse(bookPath.toFile())
        assertTrue(bookDoc instanceof Node)
        assertEquals("Groovy in Action", bookDoc.book[0].title[0].value()[0])
        def foundBooks = bookDoc.book.findAll { it.@isbn == "DEMU9474734" }
        assertEquals(1, foundBooks.size())
        assert foundBooks.getAt(0).author[0] instanceof groovy.util.Node
        assertEquals("Glen Smith", foundBooks.getAt(0).author[0].value()[0])
        assertEquals("Peter Ledbrook", foundBooks.getAt(0).author[1].value()[0])
    }

    @Test
    public void testReadBooks_twoParser() {
        def xmlText = '''
                <music>
                  <tune artist="Simon and Garfunkel">
                    <title>The 59th Street Bridge Song</title>
                  </tune>
                  <tune artist="The Mamas and The Papas">
                    <title>Monday Monday</title>
                  </tune>
                  <tune artist="Shivaree">
                    <title>Goodnight Moon</title>
                  </tune>
                </music>
                '''
        def parseResult = new XmlParser().parseText(xmlText)
        def slurpResult = new XmlSlurper().parseText(xmlText)
        assertEquals("Simon and Garfunkel", parseResult.tune[0].'@artist')
        assertEquals("Simon and Garfunkel", slurpResult.tune[0].'@artist'[0].text())

        assertEquals("number of children in first level", 3, parseResult.'*'.size())
        assertEquals("number of children in first level", 3, slurpResult.'*'.inject(0) { x, y -> x + 1 })
        println parseResult.'**'.size()
        println slurpResult.'**'.inject(0) { x, y -> x + 1 }
        def output = new StringWriter()
        def smkp
        smkp = new StreamingMarkupBuilder()
        output << smkp.bind { out << slurpResult.tune[0] }
        def reRead
        reRead = new XmlSlurper().parseText(output.toString())
        println slurpResult.tune[0] == reRead
    }
}
