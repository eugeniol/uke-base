<%--
  Created by IntelliJ IDEA.
  User: elattanzio
  Date: 10/08/12
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<h1>Bienvenido!!  ${user.fullName}</h1>

<div id="userExtraFieldsContainer" class="alert alert-info">
    <button type="button" class="close" data-dismiss="alert">×</button>
    <g:include action="userExtraFields" params="[user: user]"/>
</div>

<div id="userExtraFieldsSuccess" class="alert alert-success" style="display: none">
    <button type="button" class="close" data-dismiss="alert">×</button>
    <g:message code="form.userExtra.success.label"/>
</div>

<r:script>
    $('#userExtraFieldsContainer').bind('userExtraFields:success', function (ev, data) {
        $(this).hide('slow').nextAll('.alert-success').show('slow');
    });
</r:script>
</body>
</html>