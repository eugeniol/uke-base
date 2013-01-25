<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title><g:layoutTitle default="Ukesoft"/> | ukesoft.com</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Mobile viewport optimized: j.mp/bplateviewport -->
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <r:require module="modernizr"/>
    <r:require module="bootstrap"/>
    <%--

    <script>CSS_PATH = '${request.contextPath}/css/libs';
    JSCLASS_PATH = '${request.contextPath}/js/libs/src'</script>

    <r:require module="jsClass"/>
    --%>

    <g:layoutHead/>

    <r:layoutResources/>
</head>


<body onload="${pageProperty(name: 'body.onload')}" class="${pageProperty(name: 'body.class')}">

<header class="container">
    <g:include action="header" controller="layout"/>
</header>

<div class="container">
    <g:if test="${flash.message}">
        <div class="alert">
            <button class="close" data-dismiss="alert">×</button>
            ${flash.message}
        </div>
    </g:if>

    <g:layoutBody/>
</div>

<footer>
    ukesoft.com
</footer>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Localizando&hellip;"/>
</div>


<r:layoutResources/>
<s2ui:layoutResources module='spring-security-ui'/>
</body>
</html>