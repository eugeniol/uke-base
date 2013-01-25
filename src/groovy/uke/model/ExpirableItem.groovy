package uke.model

import groovy.time.Duration

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 11/08/12
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class ExpirableItem {
    Date startDate
    Date endDate
    transient Duration duration

    static constraints = {
        startDate nullable: true
        duration nullable: true
    }

    boolean isExpired() {
        false
    }
}
