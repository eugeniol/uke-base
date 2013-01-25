package uke.validation

import org.springframework.validation.Errors
import org.codehaus.groovy.grails.validation.AbstractConstraint

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 31/08/12
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
class UrlKeyConstraint extends AbstractConstraint {

    private static final String DEFAULT_NOT_URL_KEY_MESSAGE_CODE = "default.not.urlKey.message";
    public static final String URL_KEY_CONSTRAINT = "urlKey";

    private boolean urlKey;

    public void setParameter(Object constraintParameter) {
        if (!(constraintParameter instanceof Boolean))
            throw new IllegalArgumentException("Parameter for constraint ["
                + URL_KEY_CONSTRAINT + "] of property ["
                + constraintPropertyName + "] of class ["
                + constraintOwningClass + "] must be a boolean value");

        this.urlKey = ((Boolean) constraintParameter).booleanValue();
        super.setParameter(constraintParameter);
    }

    protected void processValidate(Object target, Object propertyValue, Errors errors) {
        if (!validUrlKey(target, propertyValue)) {
            def args = (Object[]) [constraintPropertyName, constraintOwningClass,
                propertyValue]
            super.rejectValue(target, errors, DEFAULT_NOT_URL_KEY_MESSAGE_CODE,
                "not." + URL_KEY_CONSTRAINT, args);
        }
    }

    boolean supports(Class type) {
        return type != null && String.class.isAssignableFrom(type);
    }

    String getName() {
        return URL_KEY_CONSTRAINT;
    }

    boolean validUrlKey(target, propertyValue) {
        propertyValue?.toString().matches(/^[-_\w]+$/)
    }

}
