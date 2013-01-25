package uke.model

import uke.User

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class AuditProperties implements IAuditable, ISearchable {
    User             user
    Date             created
    Date             edited
    AuditableActions action


    static constraints = {
        user nullable: true
        created nullable: true
        edited nullable: true
        action nullable: true
    }

    @Override
    Map toSearchableMap() {
        [
            created: created,
            edited: edited,
            user: user ? [
                id: user.id,
                username: user.username,
                fullName: user.fullName
            ] : null
        ]
    }
}

