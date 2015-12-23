package script.plex.filename
#!/usr/bin/groovy
def adaptor= new PlexSerienNameUtil()
def result=adaptor.findFileExtension('java.exe')
assert "exe" == result

assert "0" == adaptor.findNumberOfBeginning("0. Introduction")
assert "02" == adaptor.findNumberOfBeginning("02 Backbone.js Fundamentals")
assert "02" == adaptor.findNumberOfBeginning("02\\ Backbone.js Fundamentals")
