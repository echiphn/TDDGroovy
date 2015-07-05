package script

/**
 * Created by cle on 21/06/15.
 */
bind=new Binding('Big Groovy','second name')

shell=new GroovyShell(bind)
/**
 * Groovy script doesn't know about resources in classpath. It needs absolute path
 */
result=shell.evaluate(new File("../../../../src/main/groovy/org/groovy/basic/HelloWorldScriptTest.groovy"))
assert result == 0
