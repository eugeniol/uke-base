package uke.utils

import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.beans.BeansException

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 26/06/12
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class SpringUtil {

    public static ApplicationContext getCtx() {
        return getApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return (ApplicationContext) ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        try {
            return (T) getApplicationContext().getBean(beanName);
        } catch (BeansException ex) {
            println ex
            return null
        }
    }

}
