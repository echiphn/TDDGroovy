package script.util
#!/usr/bin/groovy

@GrabConfig( systemClassLoader = true )
@Grab(group='org.apache.ant', module='ant-javamail', version='1.9.6')
@Grab( group = 'javax.mail', module = 'mail', version = '1.4.7' )
@Grab( group = 'javax.activation', module = 'activation', version = '1.1.1' )

def send(config, attachments,subject,msg) {

	assert config

	def ant = new AntBuilder()
	def attPaths=""
	String filesString = ""
	if(attachments!=null && attachments.size()>0){
		for (int i = 0; i < attachments.size(); i++) {
			filesString += attachments.get(i).canonicalPath
			if (attachments.size() > 1 && i < attachments.size() -1)
			filesString += ","
		}
	}
	def mailParams = [
		mailhost: "smtp.gmail.com",
		subject: subject,
		messagemimetype: "text/plain",
		user: config.user,
		password: config.password,
		enableStartTLS: "true",
		ssl: "true",
		mailport: "465",
		message:msg,
	]
	if(filesString){
		mailParams.putAt("files", filesString)
	}

	ant.mail( mailParams ) {
		from( address: config.from )
		to( address: config.to )
	}
}

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
send(config,attachments,subject,message)






