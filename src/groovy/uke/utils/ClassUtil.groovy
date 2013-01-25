package uke.utils

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.commons.GrailsClass
import uke.model.IAuditable
import uke.base.services.OriginService
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 26/06/12
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class ClassUtil {
    /**
     * Busca todas las clases que implementan la interface type
     * @param classes
     * @param type
     * @return
     */
    static GrailsClass[] findAllImplementingInterface(GrailsClass[] classes, Class type) {
        classes.findAll {                   dc ->
            def domainClass = dc.getClazz()
            domainClass.interfaces.find { it == IAuditable}
        }
    }

    static void addEventListener(DefaultGrailsDomainClass domainClass, String event, Closure action) {
        def savedBeforeValidate = domainClass.metaClass.getMetaMethod(event, [] as Class[])
        domainClass.metaClass[event] = {
            // New afterInsert() logic here
            // println "$domainClass $event $delegate"
            action(delegate)
            if (savedBeforeValidate)
                savedBeforeValidate.invoke(delegate)
        }
    }

    static String getSimpleName(Class klass) {
            klass.name.split('\\.').last().split('_').first().replaceAll(/([a-zA-Z])(?=[A-Z])/, '$1-').toLowerCase()
    }

    static findClassBySimpleName(List classes, String simpleName) {
        classes.find { simpleName.equals(getSimpleName(it))}
    }

}
