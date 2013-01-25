package uke.model

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 31/08/12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class ContactInformation {
    String firstName
    String lastName
    String email
    String phone
    static constraints = {
        firstName nullable: true
        lastName nullable: true
        phone nullable: true
        email email: true, nullable: true
    }
}
