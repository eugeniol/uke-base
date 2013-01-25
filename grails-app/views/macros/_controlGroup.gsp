<g:set var="inline" value=""/>
<g:hasErrors bean="${bean}" field="${fieldAttrs?.name}">
    <g:set var="inline">
        <div class="help-block">
            <g:renderErrors bean="${bean}" field="${fieldAttrs?.name}"/>
        </div>
    </g:set>
</g:hasErrors>
<div class="${this.'class'?.plus(' ')}${inline ? 'error ' : ''}control-group">
    <g:if test="${label}">
        <label class="control-label" for="${fieldAttrs?.id}">
            <g:message code="${label}"/>
        </label>
    </g:if>
    <div class="controls${label ? '' : ' no-label'}">
        <%=body + inline%>
    </div>
</div>
