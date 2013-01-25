// configuration for plugin testing - will not be included in the plugin zip

// Added by the Spring Security Core plugin:
/*
grails.plugins.springsecurity.userLookup.userDomainClassName = 'nmn.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'nmn.UserRole'
grails.plugins.springsecurity.authority.className = 'nmn.Role'
grails.plugins.springsecurity.password.algorithm = 'MD5'
//password.algorithm = 'SHA-512'
//useSecurityEventListener = true
*/
grails.gorm.default.constraints = {
    //urlKeyConstraint(nullable: true, unique: true,   blank: false, size: 1..128,matches: '[_-\\w]+')
}

