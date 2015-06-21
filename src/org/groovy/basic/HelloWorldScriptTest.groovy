#!/usr/bin/groovy
package org.groovy.basic

// a simple script to execute. This script can be called using shebang after groovy is defined in /usr/bin/
if (args) {
    println "Hello ${args[0]}, may Groovy be with you."
    return 0
}else{
    println "Usage: your name"
    return 1
}
