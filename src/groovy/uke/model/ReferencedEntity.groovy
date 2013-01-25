package uke.model

import org.codehaus.groovy.grails.commons.ApplicationHolder

import static uke.utils.ClassUtil.getSimpleName

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class ReferencedEntity {
    String referenceType
    String referenceId

    transient def entity

    static transients = ['entity']

    static constraints = {
    }

    ReferencedEntity() {

    }

    ReferencedEntity(def entity) {
        setEntity(entity)
    }

    ReferencedEntity(String type, String ref) {
        referenceType = type
        referenceId = ref.toString()
    }

    void setEntity(def entity) {
        if (entity.hasProperty('id') && entity.id) {
            this.entity = entity
            referenceType = getSimpleName(entity.getClass())
            referenceId = entity.id.toString()
        }
        else {
            throw new Exception("Cann't reference unsaved entity. Entity doesn't have an id")
        }
    }

    def getEntity() {
        if (!entity) {
            if (referenceType && referenceId)
                entity = findReference(referenceType, referenceId)
        }
        return entity
    }

    private static def findReference(String type, String ref) {
        def grailsApplication = ApplicationHolder.application

        def klass = grailsApplication.domainClasses.find { getSimpleName it }

        return klass?.clazz.findById(ref)
    }
}
