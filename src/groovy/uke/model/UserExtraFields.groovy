package uke.model

import uke.UserSex

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 14/08/12
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */

@grails.validation.Validateable
class UserExtraFields {
    String avatar
    Date   birthday

    UserSex sex = UserSex.NOT_SET
    String  locale
    String  timezone

    String firstName
    String lastName
    String fullName

    String country

    static constraints = {
        birthday nullable: true
        locale nullable: true
        timezone nullable: true
        avatar nullable: true
        firstName nullable: true
        lastName nullable: true
        fullName nullable: true
        country nullable: true
    }

    String getFullName() {
        fullName ?: ((firstName ?: '') + ' ' + (lastName ?: '')).trim()
    }
}


