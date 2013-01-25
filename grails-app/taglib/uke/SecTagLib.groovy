package uke

import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class SecTagLib {
    static namespace = "sec"
    /** Dependency injection for springSecurityService. */
    def    springSecurityService


    def loggedInUserInfo = { attrs, body ->
        if (springSecurityService.isLoggedIn()) {
            def user = springSecurityService.getCurrentUser()
            out << user[attrs.field ?: 'username']
        }
    }
}