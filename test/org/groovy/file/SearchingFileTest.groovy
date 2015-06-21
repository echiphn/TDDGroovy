package org.groovy.file

import org.junit.Test

/**
 * Created by cle on 21/06/15.
 */
class SearchingFileTest {
    @Test
    public void testSearchGroovyfile(){
        def bind=new Binding()
        bind.setProperty('dir','/home/cle/work/workspace/TDDGroovy')
        def shell=new GroovyShell(bind)
        def result=shell.evaluate(this.class.getResource('/org/groovy/file/SearchingGroovyFiles.groovy').text) as List
        assert result !=null
        assert result.contains(this.class.simpleName+'.groovy') == true
        assert result.contains('SearchingGroovyFiles.groovy') == true
    }

}
