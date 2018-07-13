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
        <div class="section white">
            <div class="container">
                <div class="row">

                    <div class="col m5 s12">                        
                        <form method="post">
                            <div class="form-group">
                                <label for="nome">Nome da categoria:</label>
                                <input type="text" class="form-control" id="nome" name="nome" value="${categoria.nome}">
                            </div>
                            <button type="submit" class="btn large btn-default">Criar</button>
                        </form>                         
                    </div>
                </div>
            </div>
            <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
            <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
            <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
            <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>