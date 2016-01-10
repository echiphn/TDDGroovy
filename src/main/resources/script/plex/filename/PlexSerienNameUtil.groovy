#!/usr/bin/groovy

import groovy.io.FileType


def processRecursive(File folder,String prefix,String separator,String secondSeparator){
	def supportedVideoTypes=["mp4", "avi", "mkv","m4v","mov"]
	def subFiles=[]
	folder.eachFile(FileType.FILES) {file-> subFiles<<file }

	if(subFiles){
		def processingFiles=[]
		subFiles.each {File file->
			def String extension=findFileExtension(file.getName())
			if(!supportedVideoTypes.contains(extension.toLowerCase())){
				println "${file.getAbsolutePath()} has a file type that is not supported"
			}else{
				processingFiles<<file
			}
		}
		if(!processingFiles){
			println 'no file to process'
		}else{
			processingFiles.each {File file->
				println "processing ${file.getAbsolutePath()} ..."
				def parent=file.getParentFile()
				def String currentName=file.getName()
				def nameWithoutPrefix=currentName
				if(prefix){
					nameWithoutPrefix=currentName.substring(prefix.length(), currentName.length())
				}
				def segments=nameWithoutPrefix.split(separator)
				def seasonNumber=null
				def episodeNumber=null
				def shortTitle=""
				if(!segments[0].isInteger()){
					def season_episodeCombi=segments[0]
					def sub_segments=season_episodeCombi.split(secondSeparator)
					if(!sub_segments[0].isInteger()&&!sub_segments[1].isInteger()){
						println "something wrong with the file ${file.getAbsolutePath()}"
						return
					}else{
						seasonNumber=sub_segments[0]
						episodeNumber=sub_segments[1]
						shortTitle=nameWithoutPrefix.subSequence(seasonNumber.length()+episodeNumber.length()+secondSeparator.length(), nameWithoutPrefix.length())
					}
				}else{
					if(segments[1].isInteger()){
						seasonNumber=segments[0]
						episodeNumber=segments[1]
						shortTitle=nameWithoutPrefix.subSequence(seasonNumber.length()+episodeNumber.length()+2*separator.length(), nameWithoutPrefix.length())
					}else{
						episodeNumber=segments[0]
						def String numberOfBegin=findNumberOfBeginning(parent.getName())
						seasonNumber='00'
						if(numberOfBegin){
							if(numberOfBegin.isInteger()){
								seasonNumber=numberOfBegin
							}
						}
						shortTitle=nameWithoutPrefix.subSequence(episodeNumber.length()+separator.length(), nameWithoutPrefix.length())
					}
				}
				file.renameTo(parent.absolutePath+'/'+prefix+' - s'+seasonNumber+'e'+episodeNumber+' - '+shortTitle)
			}
		}
	}
	folder.eachFile(FileType.DIRECTORIES) {subfolder->processRecursive(subfolder,prefix,separator,secondSeparator)}
}

def findNumberOfBeginning(String name){
	def numbers = extractInts(name)
	numbers.getAt(0)
}

def extractInts( String input ) {
	input.findAll( /\d+/ )
}

def findFileExtension(String filename){
	def extension=""
	if(filename.lastIndexOf(".")!=-1){
		extension=filename.substring(filename.lastIndexOf(".")+1)
	}
	return extension
}