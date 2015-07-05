package script

/**
 * Created by cle on 21/06/15.
 */
bind=new Binding()
bind.setProperty('name','Groovy language')
shell=new GroovyShell(bind)
shell.evaluate(new File("../../../../src/main/groovy/org/groovy/script/ScriptUseParam.groovy"))
