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
                    <img src="http://thespiritscience.net/wp-content/uploads/2016/06/Research-Center-Main-Housing-2-1024x576.jpg">
                </div>
            </div>
        </article>
        <div class="container">
            <div class="row">
                <div class="col s12 m12 l12">
                    <ul id="issues-collection" class="collection">
                        <li class="collection-item">
                            <div class="row">
                                <div class="col s9">
                                    <h5>
                                        ${discussao.titulo} 
                                    </h5>
                                </div>
                                <div class="col s3">
                                    <h7 style="">por ${discussao.cliente.nome}  às ${discussao.dataHora}</h7>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12">
                                    <h6 style="margin-top: 20px;">${discussao.pergunta} </h6>
                                </div>
                            </div>
                        </li>

                    </ul>
                    <ul id="issues-collection" class="collection">
                        <li class="collection-item"> 
                            <h5>
                                <strong>Respostas </strong>
                            </h5>
                        </li>
                        <c:if test="${not empty mensagemList}">
                            <c:forEach items="${mensagemList}" var="mensagem">
                                <li class="collection-item text-right" > 
                                    <div class="col s12">
                                        <h7 style="">por ${mensagem.cliente.nome} - DD/MM/AA às 01:28 </h7>
                                    </div>
                                    <h6 style="margin-top: 20px;">${mensagem.mensagem} </h6>
                                </li>
                            </c:forEach> 
                        </c:if>
                        <c:if test="${ empty mensagemList}">
                            <li class="collection-item"> 
                                <div class="col s12">
                                    <p> Nenhuma resposta para essa pergunta</p>
                                </div>
                            </c:if>
                    </ul>
                    <c:if test="${not empty usuarioLogin}">
                        <ul id="issues-collection" class="collection">
                            <form method="post">
                                <li class="collection-item"> 
                                    <label for="textarea1">Resposta</label>
                                    <textarea id="textarea1" class="materialize-textarea"  name="resposta" style="height: 300px;">${errors.resposta}</textarea>
                                </li>
                                <li class="collection-item">
                                    <input type="submit" id="EnviarPergunta" class="btn blue">
                                </li>
                            </form>
                        </ul>
                    </c:if>
                </div>  
            </div>          
        </div>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
