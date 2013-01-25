package uke.model

import uke.User

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
interface IAuditable {
    User             user
    Date             created
    Date             edited
    AuditableActions action
}

