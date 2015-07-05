package org.groovy.file

import groovy.io.FileType
import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

import static org.junit.Assert.assertTrue

/**
 * Created by cle on 21/06/15.
 */
class SearchingFileTest {
    @Test
    public void testSearchGroovyfile() {
        def bind = new Binding()
        bind.setProperty('dir', '/home/cle/work/workspace/TDDGroovy')
        def shell = new GroovyShell(bind)
        def result = shell.evaluate(new File("src/main/groovy/org/groovy/file/SearchingGroovyFiles.groovy").text) as List
        assert result != null
        assert result.contains(this.class.simpleName + '.groovy') == true
        assert result.contains('SearchingGroovyFiles.groovy') == true
    }


    @Test
    public void testEachfile() {
        def testfiles=[]
        Path path = Paths.get(this.class.getResource("/").toURI())
        def rootTestResource = path.toFile()
        assert rootTestResource.exists() == true
        rootTestResource.eachFile(FileType.FILES){ file -> testfiles << file.getName()}
        assert testfiles.isEmpty() == false
        assertTrue (testfiles.contains("testfile.txt"))
        assertTrue (testfiles.contains("testfile2"))

        def subDirs=[]
        rootTestResource.eachFile(FileType.DIRECTORIES){ file -> subDirs << file.getName()}
        assert subDirs.isEmpty() == false
        assertTrue (subDirs.contains("xml"))
    }

}
