<%@ page contentType="text/html;charset=UTF-8" %>
<div class="navbar topbar">
    <div class="navbar-inner fill">
        <div class="container">
            <div class="top-identity pull-left">
                <h1><g:link uri="/" class="brand">
                    UkeSoft
                </g:link>
                </h1>
            </div>


            <div class="nav-collapse">
                <form class="navbar-search pull-left" action="">
                    <input type="text" class="search-query span3"
                           data-url="${createLink(controller: 'homeMap', action: 'autocomplete')}"
                           placeholder="Encontrar negocio o promoción">
                </form>

                <ul class="nav pull-right">
                    <li><g:include controller="userLocate" action="showLocation"/></li>
                    <li class="divider-vertical"></li>
                    <sec:ifNotLoggedIn>
                        <li><g:link controller="login" action="index">Acceder</g:link></li>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <social:personAvatar item="${user}" size="tiny"/> ${user?.fullName}
                                <b class="caret"></b></a>

                            <ul class="dropdown-menu">
                                <li>
                                    <social:personLink item="${user}">
                                        Profile
                                    </social:personLink>
                                <li class="divider"></li>
                                <li><g:link controller="logout" action="index">Logout</g:link></li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>
                </ul>
            </div><!-- /.nav-collapse -->
        </div>
    </div><!-- /navbar-inner -->
</div>


<ul class="nav nav-tabs">
    <li><g:link controller="user">Administrar Usuarios</g:link></li>
    <li><g:link controller="wcmPortal">Administrar CMS</g:link></li>
</ul>