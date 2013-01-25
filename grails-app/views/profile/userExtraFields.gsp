<%@ page import="uke.UserSex" %>
<g:formRemote name="userExtraFields" url="[controller: 'profile', action: 'update']"
              class="form-horizontal"
              onSuccess="jQuery('#userExtraFields').parent().trigger('userExtraFields:success')">
    <legend><g:message code="form.userExtra.legend"/></legend>

    <p><g:message code="form.userExtra.intro"/></p>

    <div class="control-group">
        <label class="control-label" for="inputFirstName"><g:message code="user.firstName.label"/></label>

        <div class="controls">
            <g:textField name="firstName" value="${extra.firstName}" id="inputFirstName"
                         placeholder="${message(code: 'user.firstName.label')}"/>
            <g:textField name="lastName" value="${extra.lastName}" id="inputLastName"
                         placeholder="${message(code: 'user.lastName.label')}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="inputFullName"><g:message code="user.fullName.label"/></label>

        <div class="controls">
            <g:textField name="fullName" value="${extra.fullName}" id="inputFullName"
                         placeholder="${message(code: 'user.fullName.label')}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="inputSex"><g:message code="user.sex.label"/></label>

        <div class="controls">
            <g:select name="sex" from="${UserSex.values()}" id="inputSex"
                      value="${extra.sex}" valueMessagePrefix="user.sex"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="inputBirthday"><g:message code="user.birthday.label"/></label>

        <div class="controls">
            <g:datePicker precision="day" name="birthday" id="inputBirthday" value="${extra?.birthday}"
                          noSelection="['': '']"
                          default="none"
                          years="${2012..1932}"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="inputCountry"><g:message code="user.country.label"/></label>

        <div class="controls">
            <g:countrySelect name="country" id="inputCountry" value="${extra.country}" default="arg"
                             noSelection="['': '']"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLocale"><g:message code="user.locale.label"/></label>

        <div class="controls">
            <g:localeSelect name="locale" id="inputLocale" value="${extra.locale}"/>
        </div>
    </div>
<%--
<div class="control-group">
<label class="control-label" for="inputTimezone"><g:message code="user.timezone.label"/></label>

<div class="controls">
<g:timeZoneSelect name="timezone" id="inputTimezone" value="${extra.timezone}"/>
</div>
</div>

--%>
    <div class="control-group">
        <div class="controls">
            <button type="submit" class="btn"><g:message code="form.userExtra.submit.label"/></button>
        </div>
    </div>
</g:formRemote>