package org.groovy.script

/**
 * Created by cle on 21/06/15.
 */
bind=new Binding()
bind.setProperty('name','Groovy language')
shell=new GroovyShell(bind)
shell.evaluate(this.class.getResource("/org/groovy/script/ScriptUseParam.groovy").toURI())