<html>

<head>
    <meta name="layout" content="application"/>
    <title><g:message code='spring.security.ui.login.title'/></title>
</head>

<body>

<div class="login s2ui_center ui-corner-all" style='text-align:center;'>
    <div class="login-inner">
        <form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
            <div class="sign-in">
                <h1>Acceder</h1>

                <table>
                    <tr>
                        <td>
                            <oauth:connect provider="facebook"
                                           elementId="facebook-connect-link">
                                Ingresar con Facebook
                            </oauth:connect>

                        </td>
                    </tr>
                    <tr>
                        <td><input name="j_username" id="username" size="20"
                                   value="<g:message code='spring.security.ui.login.username'/>"/></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="j_password" id="password" size="20"
                                   value="<g:message code='spring.security.ui.login.password'/>"/></td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me"
                                   checked="checked"/>
                            <label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label> |
                            <span class="forgot-link">
                                <g:link controller='register' action='forgotPassword'><g:message
                                        code='spring.security.ui.login.forgotPassword'/></g:link>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2' class="login-row">

                            <input type="submit" class="btn btn-success btn-large" value="Ingresar"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2' class="register-row">
                            <label>¿No tienes cuenta?</label>
                            <s2ui:linkButton class="btn btn-info right" elementId='register' controller='register'
                                             messageCode='spring.security.ui.login.register'/>
                    </tr>

                </table>

            </div>
        </form>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('#username').focus();
    });
</script>

</body>
</html>