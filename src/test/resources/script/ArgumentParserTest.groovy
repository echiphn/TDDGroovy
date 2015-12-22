#!/usr/bin/groovy
import java.text.DateFormat
import java.text.SimpleDateFormat


def cli = new CliBuilder(usage:'showdate.groovy -[chflms] [date] [prefix]')
// Create the list of options.
cli.with {
	h longOpt: 'help', 'Show usage information'
	c longOpt: 'format-custom', args: 1, argName: 'format', 'Format date with custom format defined by "format"'
	f longOpt: 'format-full',   'Use DateFormat#FULL format'
	l longOpt: 'format-long',   'Use DateFormat#LONG format'
	m longOpt: 'format-medium', 'Use DateFormat#MEDIUM format (default)'
	s longOpt: 'format-short',  'Use DateFormat#SHORT format'
}

if(!args){
	println cli.usage()
	return
}

def options = cli.parse(args)
if (!options) {
	println "\nInvalid command line, exiting..."
	println cli.getUsage()
	return
}
if(options.h){
	cli.usage()
	return
}

// Determine formatter.
def df = DateFormat.getDateInstance(DateFormat.MEDIUM)  // Defeault.
if (options.f) {  // Using short option.
	df = DateFormat.getDateInstance(DateFormat.FULL)
} else if (options.'format-long') {  // Using long option.
	df = DateFormat.getDateInstance(DateFormat.LONG)
} else if (options.'format-medium') {
	df = DateFormat.getDateInstance(DateFormat.MEDIUM)
} else if (options.s) {
	df = DateFormat.getDateInstance(DateFormat.SHORT)
} else if (options.'format-custom') {
	df = new SimpleDateFormat(options.c)
}

// Handle all non-option arguments.
def prefix = ''  // Default is empty prefix.
def date = new Date()  // Default is current date.
def extraArguments = options.arguments()
if (extraArguments) {
	date = new Date().parse(extraArguments[0])
	// The rest of the arguments belong to the prefix.
	if (extraArguments.size() > 1) {
		prefix = extraArguments[1..-1].join(' ')
	}
}
println "$prefix${df.format(date)}"


