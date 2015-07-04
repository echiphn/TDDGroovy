package org.groovy.basic

/**
 * Created by cle on 05/07/15.
 */
/**
 * function accepts closure. This function calls the closure twice
 * @param closure
 * @return
 */
def tellFortunes(closure) {
    Date date = new Date("09/20/2012")
//closure date, "Your day is filled with ceremony"
//closure date, "They're features, not bugs"
// We can curry to avoid sending date repeatedly
    postFortune = closure.curry(date)
    //first call of curried closure
    postFortune "Your day is filled with ceremony"
    //second call of curried closure
    postFortune "They're features, not bugs"
}

tellFortunes() {
    date, fortune ->
        println "Fortune for ${date} is '${fortune}'"
}

