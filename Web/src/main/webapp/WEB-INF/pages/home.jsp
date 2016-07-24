<%--
   Document   : home
   Created on : 18/07/2016, 21:09:55
   Author     : dijalma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Female Voice</title>
        <script src="/assets/javascript/jquery-2.1.4.min.js" type="text/javascript"></script>
        <script src="/assets/javascript/bootstrap.min.js" type="text/javascript"></script>
        <script src="/assets/javascript/jquery-ui.min.js" type="text/javascript"></script>
        <script src="/assets/javascript/maps.js"></script>
        <script src="/assets/javascript/javascript.js"></script>
        <link href="/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="/assets/css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="/assets/css/animate.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    </head>
    <body>
        <header>
            <div id="header_functions">
                <c:if test="${usuariologado eq null}">
                    <button class="btn btn-info" type="button" onclick="cadastrar()">Cadastre-se</button>
                    <button class="btn btn-warning " type="button" onclick="login()">Login</button>
                </c:if>
                <c:if test="${usuariologado != null}">
                    <button class="btn btn-danger" type="button">Logout</button>
                </c:if>
            </div>
            <div id="header_title">
                <h1>Female Voice</h1>
            </div>
        </header>
        <c:if test="${result != null}">
            <div class="body-notification">
                <div id="notification" class="animated hinge">
                    <h4>Notificação</h4>
                    <p>Usuário cadastrado com sucesso!</p>
                </div>
            </div>
        </c:if>
        <c:if test="${usuariologado != null}">
            <div class="body-new-location">
                <button class="btn btn-success btn-sm">Novo denúncia</button>
            </div>
        </c:if>
        <div class="body-address">
            <form class="form-group" onsubmit="carregarNoMapa()" action="#">
                <div class="form-group-sm">
                    <input class="form-control" type="text" placeholder="Busque um endereço" id="address" list="enderecos" autocomplete="on" autofocus=""/>
                    <datalist id="enderecos" >
                    </datalist>
                    <!--                        <div align="right">
                                                <input class="btn btn-default btn-sm" type="button" value="Buscar" onclick="carregarNoMapa()"/>
                                            </div>-->
                </div>
            </form>
        </div>
        <div class="modal-dijalma" id="modal-cadastro">
            <div class="modal-dark"></div>
            <div class="modal-event animated tada">
                <div class="modal-title">
                    <h3>Novo Usuário</h3>
                </div>
                <br><br>
                <div class="modal-sm" style="margin: 0 auto">
                    <form action="/user/new" method="POST">
                        <div class="form-group-sm">
                            <input type="email" name="email" placeholder="Email" class="form-control" autofocus="" required=""/>
                        </div><br>
                        <div class="form-group-sm">
                            <input type="text" name="username" placeholder="Nome de usuário" class="form-control" required=""/>
                        </div><br>
                        <div class="form-group-sm">
                            <input type="password" name="password" placeholder="Senha" class="form-control" required=""/>
                        </div><br>
                        <div align="right">
                            <input type="submit" class="btn btn-primary" value="Cadastrar"/>
                            <input type="button" class="btn btn-danger" value="Cancelar" onclick="cancelarCadastro()"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal-dijalma" id="modal-login">
            <div class="modal-dark"></div>
            <div class="modal-event animated tada">
                <div class="modal-title">
                    <h3>Entrar</h3>
                </div>
                <br><br><br>
                <div class="modal-sm" style="margin: 0 auto">
                    <form action="#" method="POST">
                        <div class="form-group-sm">
                            <input type="text" name="username" placeholder="Nome de usuário" class="form-control" required=""/>
                        </div><br>
                        <div class="form-group-sm">
                            <input type="password" name="password" placeholder="Senha" class="form-control" required=""/>
                        </div><br>
                        <div align="right">
                            <input type="submit" class="btn btn-primary" value="Entrar"/>
                            <input type="button" class="btn btn-danger" value="Cancelar" onclick="cancelarLogin()"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="map"></div>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBNTOq4iU64jIv0QVDx4KLHR-L51XWCQMo&amp;"></script>
    </body>
</html>