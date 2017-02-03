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
        <c:import url="/WEB-INF/views/templates/cabecalho_logout.jsp"></c:import>
            <div class="row">
                <div class="parallax-container" style="height: 250px;">
                    <div class="parallax">
                        <img class="img" src="<c:url value="/resources/img/banners/bannerAnuncio.jpg"/>">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col s12 l6 m6">
                    <div class="card-content">
                        <img id="output" style="width:100%" class="img-responsive"/>
                    </div>

                    <c:if test="${ not empty anuncio.id}">
                        <img class=" img-responsive" style="width:100%" src="<c:url value="/anuncio/${anuncio.id}/img.jpg"/>"/>
                    </c:if>
                </div>
                <div class="col m6 l6 s12">
                    <div class="row">
                        <div class="col s12 m12">
                            <div class="card">
                                <div class="card-content">   
                                    <form method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="nome">Anúncio:</label>
                                            <input type="text" class="form-control" id="nome" name="nome" maxlength="100" value="${anuncio.nome}" placeholder="${errors.nome}">
                                        </div>
                                        <div class="form-group">
                                            <label for="descricao">Descrição:</label>
                                            <input type="text" class="form-control" id="sobre" name="descricao" maxlength="500" value="${anuncio.descricao}">
                                        </div>
                                        <div class="form-group">
                                            <label for="quantidade">Quantidade:</label>
                                            <input type="text" class="form-control" id="quantidade" name="qtd" value="${anuncio.qtd}" placeholder="${errors.qtd}" onkeypress='return SomenteNumero(event)'>
                                        </div>
                                        <div class="form-group">
                                            <label for="preco">Preço:</label>
                                            <input type="text" class="form-control" id="preco" name="preco" value="${anuncio.preco}" placeholder="${errors.preco}" onkeypress='return SomenteNumero(event)'>
                                        </div>
                                        <div class="form-group">
                                            <select name="id_categoria">
                                                <option value="0" p disabled selected>Categoria</option>
                                                <c:forEach items="${categoriaList}" var="categoria">
                                                    <option value="${categoria.id}" <c:if test="${categoria.nome eq anuncio.categoria.nome}"> selected </c:if>>${categoria.nome}</option>
                                                </c:forEach>
                                            </select>

                                            <div class="file-field input-field">
                                                <div class="btn small light-blue darken-4"">
                                                    <span> <i class="material-icons" >airplay</i></span>
                                                    <input type="file"  name="foto" onchange="carregaImagem(event)" value="${anuncio.foto} ">                                                   
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        </br>
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <button type="submit" style="width:100%" class="btn large btn-default blue">${operacao}</button>
                                            </div>   
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col m12 s12 l12">
                    <p class="text-justify" ><strong>Atenção!</strong> O AgroComp não se responsabiliza pela venda deste produto é de responsabilidade exclusiva do anunciante. 
                        O AgroComp não realiza intermediação das vendas e compras, trocas ou qualquer tipo de transação feita pelos usuários deste Site, tratando-se de serviço exclusivamente de disponibilização de mídia para divulgação. 
                        A transação é feita diretamente entre as partes interessadas. 
                        Cabe ao consumidor assegurar-se de que o negócio é idôneo antes de realizar qualquer transação. 
                        Ao negociar com qualquer pessoa ou empresa, sugerimos que não efetue qualquer pagamento através de transferência bancária ou outro tipo de pagamento sem a garantia da entrega do bem que está negociando.
                    </p>
                </div>
            </div>
        </div>

        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>