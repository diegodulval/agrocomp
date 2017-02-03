<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="AgroComp">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="<c:url value="/resources/js/modernizr-2.8.3.min.js"/>"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
        <!-- Materialize e Icons -->
        <link rel="stylesheet" href="<c:url value="/resources/css/materialize.css"/>">
        <link href="<c:url value="/resources/css/icon.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
        <title>AgroComp</title>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="<c:url value="/resources/js/html5shiv.min.js"/>"></script>
          <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
        <![endif]-->
       <!-- Favicons-->
        <link rel="icon" href="<c:url value="/resources/img/apple-touch-icon.png"/>" sizes="32x32">
        <!-- Favicons-->
        <link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/img/apple-touch-icon.png"/>">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" href="<c:url value="/resources/img/apple-touch-icon.png"/>">
    </head>
    <body>
        <c:if test="${not empty  usuarioLogin}">
            <c:import url="/WEB-INF/views/templates/cabecalho_relatorios.jsp"></c:import>
        </c:if>
        <c:if test="${empty usuarioLogin}">
            <c:import url="/WEB-INF/views/templates/cabecalho.jsp"></c:import>
        </c:if>
        <div class="slider">
            <ul class="slides">
                <li>
                    <img style="opacity: 0.5" src="<c:url value = "/resources/img/banners/agricultura-familiar-2.jpg"/>"> <!-- random image -->
                    <div class="caption center-align">
                        <h3>Bem vindo ao seu perfil ${cliente.nome}!</h3>
                        <h5 class="light grey-text text-lighten-3">AgroComp</h5>
                    </div>
                </li>
                <li>
                    <img src="<c:url value = "/resources/img/banners/agricultura_familiar.jpg"/>"> <!-- random image -->
                    <div class="caption right-align">
                        <h3>AgroComp</h3>
                        <h5 class="light grey-text text-lighten-3">Soluções para o homem do campo</h5>
                    </div>
                </li>
                <li>
                    <img style="opacity: 0.5" src="<c:url value = "/resources/img/banners/lavradores.jpg"/>"> <!-- random image -->
                    <div class="caption center-align">
                        <h5>“Quem ama, algumas vezes precisa ter a sabedoria de um agricultor, que sabe que nenhuma planta cresce e dá frutos da noite para o dia. É preciso muitos cuidados, dedicação e a crença de que após determinado período, caso pragas e intempéries não assolem a plantação, será feita uma boa colheita.” </h5>
                        <h6 class="light grey-text text-lighten-3">(Augusto Branco)</h6>
                    </div>
                </li>
            </ul>
        </div>                  
        <div class="container">
            <div class="row">
                <div class="card" style="margin: 20px">
                    <div class="card-content">
                        <h5 class="center"><strong> Gestão de contéudo </strong></h5>
                        <div class="row" style="margin: 20px">
                            <div class="col m12 s12 l3">
                                <a href="<c:url value="/administrador/clientes"/>" class="btn blue"  style="width: 100%; margin-top: 5px">Clientes do sistema</a>
                            </div>

                            <div class="col m12 s12 l3">
                                <a href="<c:url value="/administrador/reportes"/>" class="btn blue"  style="width: 100%; margin-top: 5px">Reportes</a>
                            </div>

                            <div class="col m12 s12 l3">
                                <a href="<c:url value="/administrador/categorias"/>" class="btn blue"  style="width: 100%; margin-top: 5px">Categorias</a>
                            </div>
                            <div class="col m12 s12 l3">
                                <a href="<c:url value="/administrador/noticias"/>" class="btn blue"  style="width: 100%; margin-top: 5px">Notícias</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
