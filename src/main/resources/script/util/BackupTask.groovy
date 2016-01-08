package script.util

def scriptBase="/home/pi/scripts"
def cli = new CliBuilder(usage:'BackupTask -[h src target]')
// Create the list of options.
cli.with {
	h longOpt: 'help', 'Show usage information'
	src longOpt: 'source', args: 1,required:true, argName: 'source', 'source folder'
	target longOpt: 'target',args:2,required:true,  argName: 'target', 'target folder'
}

def options = cli.parse(args)
if (!options) {
	return
}
if(options.h){
	cli.usage()
	return
}
def source = options.src
def target = options.target
def Process proc="rsync -uvr --delete-after ${source} ${target}".execute()
def backupFile = new File("backup_result.txt")
FileOutputStream fos = new FileOutputStream(backupFile)
fos << proc.in
proc.out.close()
proc.waitForOrKill(0)
def attachments=[backupFile]

ConfigObject config = new ConfigSlurper().parse(new File("${scriptBase}/credentials.txt").toURL())

def binding = new Binding()
binding.putAt("config", config)
binding.putAt("attachments", attachments)
binding.putAt("subject", "Backup process finished")
binding.putAt("message", "Backup process for ${source} is finished")

def scriptEngine = new GroovyScriptEngine(scriptBase)
scriptEngine.run("SendingMail.groovy",binding)

