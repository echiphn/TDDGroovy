package script.util
//#!/usr/bin/groovy

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

send(config,attachments,subject,message)






