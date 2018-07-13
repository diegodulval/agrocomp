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
            <c:import url="/WEB-INF/views/templates/cabecalho_logout.jsp"></c:import>
        </c:if>
        <c:if test="${empty usuarioLogin}">
            <c:import url="/WEB-INF/views/templates/cabecalho.jsp"></c:import>
        </c:if>
        <div class="container">
            <h1 class="center">Categorias</h1>
            <a href="<c:url value="/administrador/categorias/novo"/>" class="btn blue"><i class="small material-icons">new</i></a>
            <table class="striped">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Editar</th>
                    <th>Excluir</th>      
                </tr>
                <c:forEach items="${categoriaList}" var="categoria">
                    <tr>
                        <td>${categoria.id}</td>
                        <td>${categoria.nome}</td>
                        <td><a href="<c:url value="/categorias/${categoria.id}/editar"/>" class="btn blue"><i class="small material-icons">edit</i></a> </td>
                        <td> <a href="<c:url value="/categorias/${categoria.id}/deletar"/>" class="btn red"><i class="small material-icons">delete</i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
