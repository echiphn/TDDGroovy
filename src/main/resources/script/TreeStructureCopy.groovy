#!/usr/bin/groovy
import groovy.io.FileType;
/**
 * copy tree structure recursively but not the contents
 */
def cli = new CliBuilder(usage:'showdate.groovy -[dh] [path to folder]')
// Create the list of options.
cli.with {
	h longOpt: 'help', 'Show usage information'
	d longOpt: 'dir', args: 1,required:true, argName: 'dir', 'folder to copy. Required argument!'
}

def options = cli.parse(args)
if (!options) {
	return
}
def copyNames(String targetParentAbsolutePath,File file2Copy){
	def newFileAbsolutePath="${targetParentAbsolutePath}/${file2Copy.name}"
	println "create file ${newFileAbsolutePath}"
	def newFileCreated=false
	if(file2Copy.isDirectory()){
		newFileCreated=new File(newFileAbsolutePath).mkdirs()
	}
	if(file2Copy.isFile()){
		newFileCreated=new File(newFileAbsolutePath).createNewFile()
	}
	if(!newFileCreated){
		println "touch '${targetParentAbsolutePath}/${file2Copy.name}' is not valid"
		return
	}
	if(file2Copy.isDirectory()){
		file2Copy.eachFile {File f->copyNames("${targetParentAbsolutePath}/${file2Copy.name}",f)}
	}
}
def File file2Copy = new File(options.d)
if(file2Copy.exists())
	println "file to copy ${options.d}"
else{
	println "file not found ${options.d}"
	return
}
copyNames("/home/cle/work/tmp/Training",file2Copy)

