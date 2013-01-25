package uke.base.services

import uke.User
import uke.model.IAuditable
import uke.model.AuditableActions

class OriginService {

    static transactional = false
    static scope         = "singleton"


    private User getCurrentUser() {
        uke.utils.SpringUtil.getBean('springSecurityService')?.currentUser
    }

    def register(IAuditable entity) {
        register(entity, AuditableActions.created)
    }

    def register(IAuditable entity, AuditableActions action) {
        if (entity.hasProperty('auditProperties'))
            entity = entity.auditProperties

        if (getCurrentUser() != null)
            entity.user = getCurrentUser()

        if (!entity.created)
            entity.created = new Date()
        else
            entity.edited = new Date()

        return entity
    }

}


