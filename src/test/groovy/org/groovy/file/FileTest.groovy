package org.groovy.file

import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

import static org.junit.Assert.assertEquals

/**
 * Created by cle on 05/06/15.
 */
class FileTest {


    @Test
    public void testReadFile() {
        Path path = Paths.get(this.getClass().getResource("/testDataFiles/testfile.txt").toURI())
        def line
        path.toFile().withReader { line = it.readLine() }
        assertEquals("first content", line)
    }

    @Test
    public void testReadFileToString() {
        Path path = Paths.get(this.getClass().getResource("/testDataFiles/testfile.txt").toURI())
        assertEquals("first content", path.toFile().text)
    }
    /*
    print out line and number of line
     */
    @Test
    public void testReadlineWithNumber(){
        Path path=Paths.get(this.class.getResource("/testDataFiles/testfile2").toURI())
        path.toFile().eachLine { line,numberOfLine ->
            println "In the line $numberOfLine: $line"
        }
    }
}
