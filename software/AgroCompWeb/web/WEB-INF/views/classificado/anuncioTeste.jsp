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
            <div class="parallax-container" style="height: 250px;">
                <div class="parallax">
                    <img class="img" src="<c:url value="/resources/img/banners/bannerAnuncio.jpg"/>">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col m6 s12">
                    <img style="width:100%" class="img-responsive" src="<c:url value="/anuncio/${anuncio.id}/img.jpg"/>">
                </div>
                <div class="col m6 s12">
                    <h4><strong> ${anuncio.nome}</strong></h4>
                    <span class="manufacturer">Anúncio de <strong> ${cliente.nome}</strong></span>
                    <div class="">
                        <h6><strong>Descrição do anúncio</strong></h6>
                        <p> ${anuncio.descricao}</p>
                    </div>
                    <table class="saugroboter-details">
                        <tbody>
                            <tr class="">
                                <th>Telefone: </th>
                                <td><strong>${cliente.telefone}</strong></td>
                            </tr>
                            <tr class="">
                                <th>Localização:</th>
                                <td><strong><a class="underline" rel="nofollow">${cliente.cidade}, ${cliente.estado}</a></strong></td>
                            </tr>
                            <tr class="">
                                <th>Preço:</th>
                                <td><strong><h5><b></b><a class="underline" >R$ ${anuncio.preco}</a></b></h5></strong></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="card">
                        <div class="card-content">
                            <h6><strong> Enviar mensagem ao vendedor: </strong></h6>
                            <form action="<c:url value="/anuncio/msg/${anuncio.id}"/>" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="nome" name="descricao">
                                </div>
                                <button type="submit" style="width: 100%"class="btn large btn-default blue">Enviar</button>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <h6><strong> Reportar anúncio </strong></h6>
                            <form action="<c:url value="/reporte/anuncio/${anuncio.id}"/>" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="nome" name="descricao">
                                </div>
                                <button type="submit" style="width: 100%"class="btn large red">Reportar anúncio</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <p>Atenção! O AgroComp não se responsabiliza pela venda deste produto é de responsabilidade exclusiva do anunciante. 
                O AgroComp não realiza intermediação das vendas e compras, trocas ou qualquer tipo de transação feita pelos usuários deste Site, tratando-se de serviço exclusivamente de disponibilização de mídia para divulgação. 
                A transação é feita diretamente entre as partes interessadas. 
                Cabe ao consumidor assegurar-se de que o negócio é idôneo antes de realizar qualquer transação. 
                Ao negociar com qualquer pessoa ou empresa, sugerimos que não efetue qualquer pagamento através de transferência bancária ou outro tipo de pagamento sem a garantia da entrega do bem que está negociando.</p>
        </div>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>