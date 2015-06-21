package org.groovy.basic

/**
 * Created by cle on 21/06/15.
 */
bind=new Binding('Big Groovy','second name')

shell=new GroovyShell(bind)
result=shell.evaluate(this.class.getResource('/org/groovy/basic/HelloWorldScriptTest.groovy').toURI())
assert result == 0
