import org.codehaus.groovy.grails.commons.GrailsClass
import uke.model.ISearchable
import uke.utils.SpringUtil
import uke.base.services.OriginService
import uke.model.IAuditable
import uke.utils.ClassUtil
import org.codehaus.groovy.grails.commons.GrailsApplication

import static uke.utils.ClassUtil.addEventListener
import static uke.utils.ClassUtil.findAllImplementingInterface
import uke.model.AuditableActions
import org.codehaus.groovy.grails.validation.ConstrainedProperty
import uke.validation.UrlKeyConstraint

class UkeBaseGrailsPlugin {
    def groupId        = 'uke'
    // the plugin version
    def version        = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion  = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn      = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title       = "Nmn Base Plugin" // Headline display name of the plugin
    def author      = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/uke-base"
    def loadAfter     = ['controllers']
    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }



    def doWithSpring = {
        ConstrainedProperty.registerNewConstraint(
            UrlKeyConstraint.URL_KEY_CONSTRAINT,
            UrlKeyConstraint.class);
    }

    def doWithDynamicMethods = { ctx ->
        /*
           Busca todas las clases que implementan la interface ISearchable y le agrega los
           eventos para vincular hibernate con elasticsearch
        */
        findAllImplementingInterface(application.domainClasses, IAuditable).each {domainClass ->

            addEventListener domainClass, 'beforeValidate', {   obj ->
                def originService = (OriginService) SpringUtil.getBean('originService')
                originService.register obj, AuditableActions.created
            }
            addEventListener domainClass, 'beforeInsert', {   obj ->
                def originService = (OriginService) SpringUtil.getBean('originService')
                originService.register obj, AuditableActions.created
            }

            addEventListener domainClass, 'beforeUpdate ', {   obj ->
                def originService = (OriginService) SpringUtil.getBean('originService')
                originService.register obj, AuditableActions.updated
            }
        }

    }



    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
