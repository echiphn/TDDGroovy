package org.groovy

/**
 * Created by cle on 04/06/15.
 */
class Employee {
    def sureName
    int old
    private firstName

    def getFirstName() {
        //return is optional
        firstName
    }

    void setFirstName(firstName) {
        this.firstName = firstName
    }

    def getFullName() {
        "${firstName} ${sureName}"
    }
}
