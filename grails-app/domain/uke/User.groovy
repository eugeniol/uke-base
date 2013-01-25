package uke

import uke.model.UserExtraFields
import uke.model.AuditProperties
import uke.model.IAuditable

class User implements IAuditable {
    String  username
    String  password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String  email

    @Delegate AuditProperties auditProperties = new AuditProperties()
    @Delegate UserExtraFields extra           = new UserExtraFields()

    def springSecurityService

    static transients  = ['extra', 'springSecurityService', 'auditProperties']
    static constraints = {
        importFrom UserExtraFields
        importFrom AuditProperties
        username blank: false, unique: true
        password blank: false, password: true, nullable: true
        email email: true, nullable: true
    }

    static mapping = {
        password column: '`password`'
        avatar type: 'text'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    String toString() {
        getFullName()
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        if (password?.length() < 32)
            password = springSecurityService.encodePassword(password)
    }

    String getFullName() {
        extra.fullName ?: username
    }
}


