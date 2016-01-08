package script.util

def cli = new CliBuilder(usage:'SendingMail -[dh] [path to folder]')
// Create the list of options.
cli.with {
	h longOpt: 'help', 'Show usage information'
	acc longOpt: 'account-config', args: 1,required:true, argName: 'account-config', 'config file for account information'
	att longOpt: 'attachments',args:2,  argName: 'attachments', 'paths of attachments, seperated using comma'
	sub longOpt: 'subject', args:3, argName: 'subject', 'subject of the mail'
	msg longOpt: 'message', args:4, argName: 'message', 'body of the mail'
}

def options = cli.parse(args)
if (!options) {
	return
}
if(options.h){
	cli.usage()
	return
}

def accountConfig=new File(options.acc)
if(!accountConfig.exists()){
	println "the account configuration file '${options.acc}' doesn't exist"
	return
}
def attachments=[]
if(options.att){
	def items=options.att.split (",")
	items.each{it->
		def file=new File(it)
		if(file.exists()){
			attachments<<file
		}else{
			println "attachment ${it} doesn't exist"
		}
	}
}
def subject=""
def message=""
if(options.sub)
subject=options.sub
if(options.msg)
message=options.msg
ConfigObject config = new ConfigSlurper().parse(accountConfig.toURL())

def binding = new Binding()
binding.putAt("config", config)
binding.putAt("attachments", attachments)
binding.putAt("subject", subject)
binding.putAt("message", message)
def shell=new GroovyShell(binding)
shell.parse(new File("SendingMail.groovy")).run()
