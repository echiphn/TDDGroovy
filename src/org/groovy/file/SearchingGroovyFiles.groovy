package org.groovy.file

/**
 * Created by cle on 21/06/15.
 */

def dir2Search = new File(dir)
def result=[]
dir2Search.traverse { File file ->
    if (file.name.endsWith('.groovy')) {
        result << file.name
    }
}
return result