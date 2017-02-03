<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <img width="100%" src="<c:url value="/anuncio/${anuncio.id}/img.jpg"/>">
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
                </div>
            </div>
            <div class="row">                        
                <h5><strong>Perguntas para ${cliente.nome} </strong></h5>
                <hr>
                <div class="card">
                    <div class="card-content">
                        <h6> Escreva uma pergunta ... </h6>
                        <form action="<c:url value="/criar/mensagem"/>" method="post">
                            <div class="form-group">
                                <textarea id="mensagem" class="materialize-textarea" name="mensagem"></textarea>
                                <input type="hidden" id="idAnuncio" name="idAnuncio" value="${anuncio.id}"/>
                            </div>
                            <button type="submit" style="width: 100%"class="btn large btn-default blue">Enviar</button>
                        </form>
                    </div>
                </div>
                <c:if test="${not empty mensagemAnuncioList}">
                    <c:forEach items="${mensagemAnuncioList}" var="msgList">                                    
                        <div class="col s12 m12 l12">
                            <ul class="collection">
                                <li class="collection-item">
                                    <div class="row" style="margin-bottom: 0px;">
                                        <div class="col s10 m10 l11">
                                            <span>Enviado por ${msgList.usuario.nome} em <fmt:formatDate pattern="dd/MM/yyyy" value="${msgList.dataHora}"/> às <fmt:formatDate pattern="HH:mm" value="${msgList.dataHora}" type="time"/></span>                                            
                                        </div>
                                        <div class="col s2 m2 l1">
                                            <c:if test="${anuncio.cliente.id eq usuarioLogin.id}">
                                                <a href="<c:url value="/classificados/anuncio/${anuncio.id}/${msgList.id}/deletar"/>" class="waves-effect waves-light btn-floating red"><i class="tiny material-icons">delete</i></a>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                                <li class="collection-item">
                                    <p style="word-wrap: break-word;" >${msgList.mensagem}</p>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="row">
                <div class="col m8 l8 s 12">
                    <p style="text-align: justify;">Atenção! O AgroComp não se responsabiliza pela venda deste produto é de responsabilidade exclusiva do anunciante. 
                        O AgroComp não realiza intermediação das vendas e compras, trocas ou qualquer tipo de transação feita pelos usuários deste Site, tratando-se de serviço exclusivamente de disponibilização de mídia para divulgação. 
                        A transação é feita diretamente entre as partes interessadas. 
                        Cabe ao consumidor assegurar-se de que o negócio é idôneo antes de realizar qualquer transação. 
                        Ao negociar com qualquer pessoa ou empresa, sugerimos que não efetue qualquer pagamento através de transferência bancária ou outro tipo de pagamento sem a garantia da entrega do bem que está negociando.</p>
                </div>
                <div class="col m4 l4 s12">
                    <div class="card">
                        <div class="card-content">
                            <h7><strong> Anuncio incorreto? </strong></h7>
                            <form action="<c:url value="/reporte/anuncio/${anuncio.id}"/>" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="nome" maxlength="500" name="descricao" placeholder="Descreva o motivo da denuncia.">
                                </div>
                                <button type="submit" style="width: 100%"class="btn large red">Reportar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>