<g:if test="${items}">
    <ul class="nav nav-list">
        <g:if test="header"><li class="nav-header">${header}</li></g:if>
        <g:each in="${items}" var="item">
            <g:if test="${item == '-'}">
                <li class="divider"></li>
            </g:if>
            <g:else>
                <g:set var="url" value="${g.createLink(item + [params: params])}"/>
                <li class="${url == request.forwardURI ? 'active' : ''}"><g:link url="${url}">
                    <i class="${url == request.forwardURI ? 'icon-white ' : ''} ${item.icon}"></i> ${item.label}</g:link></li>
            </g:else>
        </g:each>
    </ul>
</g:if>