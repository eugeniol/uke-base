package uke.model

import uke.utils.CountryUtils

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 31/08/12
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class AddressInput {
    String street
    String city
    String state
    String postalCode
    String country

    static constraints = {
        street nullable: true
        city nullable: true
        state nullable: true
        country nullable: true
        postalCode nullable: true
    }


    @Override
    String toString() {

        [street, city, state, CountryUtils.getCountryName(country)].findAll {it != null}.join(', ')
    }
}
