#!/usr/bin/groovy
//adjust video names of inside a folder after plex rules for series type
//https://support.plex.tv/hc/en-us/articles/200220687-Naming-Series-Season-Based-TV-Shows


def cli = new CliBuilder(usage:'VideoNameAdapting -[dh] [path to folder]')
// Create the list of options.
cli.with {
	h longOpt: 'help', 'Show usage information'
	d longOpt: 'dir', args: 1,required:true, argName: 'dir', 'folder to process. Required argument!'
	p longOpt: 'prefix', args: 2, argName: 'prefix', 'prefix of files to process'
	s longOpt: 'separator', args: 3, argName: 'separator', 'separator character that is used to split file names'
	sext longOpt: 'separator_ext', args: 3, argName: 'second separator', 'separator character between season number and episode number'
}

def options = cli.parse(args)
if (!options) {
	return
}
if(options.h){
	cli.usage()
	return
}

def folder2Process=new File(options.d)
if(!folder2Process.exists()){
	println "the folder '${options.d}' doesn't exist"
	return
}
def adaptor=new PlexSerienNameUtil()
def prefix=""
def separator=" "
def secondSeparator=""
if(options.p)
	prefix=options.p
if(options.s)
	separator=options.s
if(options.sext)
	secondSeparator=options.sext
adaptor.processRecursive(folder2Process,prefix,separator,secondSeparator)


