<g:if test="${path}">
    <ul class="breadcrumb">
        <g:each in="${path}" var="item" status="ix">
            <li${ix == path.size() - 1 ? 'class="active"' : ' '}>
                <a href="${item.url}">${item.label}</a>
                <g:if test="${!(ix == path.size() - 1)}">
                    <span class="divider">/</span>
                </g:if>
            </li>
        </g:each>
    </ul>
</g:if>