<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="Agrocomp">
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
        <!-- For Windows Phone -->

        <!-- CORE CSS-->
        <link href="<c:url value="/resources/css/materialize.css"/>" type="text/css" rel="stylesheet" media="screen,projection">
    </head>
    <body>
        <c:if test="${not empty  usuarioLogin}">
            <c:import url="/WEB-INF/views/templates/cabecalho_logout.jsp"></c:import>
        </c:if>
        <c:if test="${empty usuarioLogin}">
            <c:import url="/WEB-INF/views/templates/cabecalho.jsp"></c:import>
        </c:if>
        <article>
            <div class="parallax-container" style="height: 250px;">
                <div class="parallax">
                    <img class="img" src="<c:url value="/resources/img/banners/bannerForum.jpg"/>">
                </div>
            </div>
        </article>
        <div class="container">
            <div class="row">   
                <div class="col s12 m12 l12">
                    <h5 style="margin-top: 20px;">Forum </h5>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col m12 l12 s12">
                    <form>
                        <div class="input-field">
                            <input type="search" name="pesquisaDiscussao" required >
                            <label for="search"><i class="material-icons">search</i></label>
                            <i class="material-icons">close</i>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col s12 m6 l6">
                    <ul id="issues-collection" class="collection">
                        <form method="post">
                            <li class="collection-item"> 
                                <label for="titulo">Assunto da pergunta</label>
                                <input type="text" id="titulo" name="titulo" length="200" maxlength="200" value="${titulo}" placeholder="${errors.titulo}">
                            </li>
                            <li class="collection-item"> 
                                <label for="textarea1">Faça uma Pergunta</label>
                                <textarea id="textarea1" name="pergunta" class="materialize-textarea" maxlength="500" length="500" style="height: 300px;">${errors.pergunta} ${pergunta}</textarea>
                            </li>
                            <li class="collection-item">
                                <input type="submit" id="EnviarPergunta" class="btn blue">
                            </li>
                        </form>
                    </ul>
                    <ul id="issues-collection" class="collection">
                        <li class="collection-item"> 
                            <h5>
                                Minhas Discussões 
                            </h5>
                        </li>
                        <c:forEach items="${minhaDiscussaoList}" var="minhaDiscussao">
                            <li class="collection-item">
                                <div class="row">
                                    <div class="col s10">
                                        <a class="divNaoRespondida boxBg0" title="${minhaDiscussao.titulo}" href="<c:url value="/forum/discussao/${minhaDiscussao.id}"/>">
                                            <span>${minhaDiscussao.dataHora}<br>
                                            </span>
                                            ${minhaDiscussao.titulo} 
                                        </a>
                                    </div>
                                    <div class="col s2">
                                        <a class="btn-floating btn-flat waves-effect waves-light blue"><i class="material-icons">forum</i></a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="col s12 m6 l6">
                    <ul id="issues-collection" class="collection">
                        <li class="collection-item">
                            <h5>Perguntas </h5>
                        </li>
                        <c:forEach items="${discussaoList}" var="discussao">
                            <li class="collection-item">
                                <div class="row">
                                    <div class="col s12">
                                        <p class="collections-title"><strong>${discussao.titulo}</strong></p>
                                        <p class="collections-content">${discussao.pergunta} </p>
                                    </div> 
                                </div>

                                <div class="row">
                                    <div class="col s6 l6">
                                        <a class="waves-effect waves-light" href="<c:url value="/forum/discussao/${discussao.id}"/>"><i class="material-icons left">forum</i>Responder</a>
                                    </div>
                                    <div class="col s6 l6">
                                        <a class="waves-effect waves-light" href="<c:url value="/forum/discussao/${discussao.id}"/>"><i class="material-icons left">textsms</i>Nº de respostas: ${discussao.numMensagem}</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>   
            </div>
        </div>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
