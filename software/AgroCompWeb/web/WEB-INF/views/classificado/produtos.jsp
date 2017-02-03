<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="AgroComp">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css"/>">
        <script src="<c:url value="/resources/js/modernizr-2.8.3.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/materialize.css"/>">
        <link href="<c:url value="/resources/css/icon.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
        <title>AgroComp</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
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
        <div class="row">     
            <div class="parallax-container" style="height: 250px">
                <div class="parallax">
                    <img class="img" src="<c:url value="/resources/img/banners/banner1.jpg"/>">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col s12 m3 l3"> 
                    <div class="row">
                        <ul class="collapsible" data-collapsible="expandable">
                            <li>
                                <div class="collapsible-header"><i class="material-icons ">shopping_cart</i>Filtrar por Nome</div>
                                <div class="collapsible-body" style="padding: 20px;">
                                    <form method="get">
                                        <div class="input-field">
                                            <input type="text" name="nome" id="robot" placeholder="Pesquisar produto" value="${produtoBusca}" autocomplete="off">
                                        </div>
                                        <div class="input-field center" style="margin-bottom: 20px">
                                            <button type="submit" class="btn btn-default light-blue darken-4">Filtrar</button>
                                        </div>
                                </div>
                            </li>
                        </ul>      
                    </div>
                    <div class="row">
                        <ul class="collapsible" data-collapsible="expandable">
                            <li>
                                <div class="collapsible-header "><i class="material-icons">shopping_cart</i>Filtrar por Preço</div>
                                <div class="collapsible-body" style="padding: 20px;">
                                    <form method="get" >
                                        <div class="input-field">
                                            <input class="form-control" type="text" name="menor" id="menor" value="${menor}">
                                            <label>De</label>
                                        </div>
                                        <div class="input-field">
                                            <input class="form-control" type="text" name="maior" id="maior" value="${maior}">
                                            <label>Até</label>
                                        </div>
                                        <div class="input-field center" style="margin-bottom: 20px">
                                            <button type="submit" class="btn large btn-default light-blue darken-4">Filtrar</button>
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul> 
                    </div>
                </div>
                <div class="row">       
                    <div class="col s12 m8 l8">  
                        <c:if test="${ empty anuncioList}">
                            <P class="center-align">Nenhum produto encontrado</P>
                            </c:if>
                            <c:if test="${not empty anuncioList}">
                                <c:forEach items="${anuncioList}" var="anuncio">
                                <div class="col m8 l6">
                                    <div class="card sticky-action">
                                        <div class="card-image waves-effect waves-block waves-light">
                                            <c:if test="${ empty anuncio.foto[0]}">
                                                <img class="responsive-img home_img" src="resources/img/temp/agricultor.jpg">
                                            </c:if>
                                            <c:if test="${ not empty anuncio.foto[0]}">
                                                <img class="activator " src="<c:url value="/anuncio/${anuncio.id}/img.jpg"/>">
                                            </c:if>
                                        </div>
                                        <div class="card-content">
                                            <span> <strong>${anuncio.nome}</strong><i class="material-icons right">more_vert</i>
                                                <p>Quantidade: ${anuncio.qtd}</p>
                                                <p>Preço: R$${anuncio.preco}</p>
                                        </div>
                                        <div class="card-reveal">
                                            <span class="card-title grey-text text-darken-4">${anuncio.nome}<i class="material-icons right">close</i></span>
                                            <p>Sobre: ${anuncio.descricao}</p>
                                        </div>
                                        <div class="card-action">
                                            <a href="<c:url value="/classificados/anuncio/${anuncio.id}"/>" style="color: #01579b;">Ver anuncio</a>
                                            <a href="<c:url value="/reporte/anuncio/${anuncio.id}"/>" class="right" style="color: red;"> <i class="material-icons">thumb_down</i></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div> <!-- Fim Produtos 1-->
                </div>
                <div class="row">
                    <div class="col s12 m12 l12 center">
                        <ul class="pagination center" >
                            <c:if test="${(offset - limit)>=0}">
                                <li class="waves-effect"><a href="<c:url value="/classificados?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                                </c:if>
                                <c:if test="${(limit+offset)< count}">
                                <li class="waves-effect"><a href="<c:url value="/classificados?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                                </c:if>
                        </ul>
                    </div>
                </div>
                <div>
                    <p>Produtos disponiveis: ${count}</p>
                </div>
            </div>  <!-- Fim da Row-->
        </div>   
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>        
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
