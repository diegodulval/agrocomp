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

        <script>
            $('.btnExcluir').click(function () {
                var aux = $(this).attr("aux");
                $('#btnConfirma').attr('href', aux);
            });
        </script>

    </head>
    <body>      
        <c:import url="/WEB-INF/views/templates/cabecalho_logout.jsp"></c:import>       
            <div class="slider">
                <ul class="slides" >

                    <li>
                        <img style="opacity: 0.5" src="<c:url value = "/resources/img/banners/agricultura-familiar-2.jpg"/>"> <!-- random image -->
                    <div class="caption center-align">
                        <h3>Bem vindo ao seu perfil ${usuarioLogin.nome}!</h3>
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
                <div class="col s12 m12">
                    <div class="card horizontal">
                        <div class="card-stacked">
                            <div class="card-content">   
                                <h4 class="center-align">Meu perfil </h4>
                                <form method="post">
                                    <div class="form-group">
                                        <label for="nome">Nome:</label>
                                        <input type="text" class="form-control" id="nome" maxlength="30" name="nome" value="${usuarioLogin.nome}">
                                    </div>
                                    <div class="form-group">
                                        <label for="sobre">Sobre:</label>
                                        <input type="text" class="form-control" id="sobre" maxlength="500" name="sobre"value="${usuarioLogin.sobre}">
                                    </div>
                                    <div class="form-group">
                                        <label for="Telefone">Telefone:</label>
                                        <input type="text" class="form-control" id="Telefone" name="telefone" maxlength="20" value="${usuarioLogin.telefone}">
                                    </div>
                                    <div class="form-group">
                                        <label for="estado">Estado:</label>
                                        <input type="text" class="form-control" id="estado" name="estado" maxlength="25" value="${usuarioLogin.estado}">
                                    </div>
                                    <div class="form-group">
                                        <label for="cidade">Cidade:</label>
                                        <input type="text" class="form-control" id="cidade" name="cidade" maxlength="100" value="${usuarioLogin.cidade}">
                                    </div>
                                    <div class="row">
                                        <div class="col s12">
                                            <p>Email: ${usuarioLogin.email}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s12">
                                            <button type="submit" class="center-align btn light-blue darken-4"  style="width: 100%; text"><span class="center-align">Atualizar informações</span></button>
                                        </div>
                                        <div class="col s12" style="margin-top: 10px;">
                                            <a href="#modalAlterarSenha" data-target="modal" class="modal-trigger center-align btn light-blue darken-4" style="width: 100%;">
                                                Alterar senha</a>
                                        </div>
                                        <div class="col s12" style="margin-top: 10px;">
                                            <a href="<c:url value="/meuperfil/anuncios/novo"/>" class="center-align btn light-blue darken-4" style="width: 100%;" >Criar anúncio</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:if test="${not empty anuncioList}">
                    <div class="row">
                        <h4 class="center-align">Meus anuncios: ${count} </h4>
                        <hr>
                    </div>
                    <div class="row">
                        <div class="col s12 m12">
                            <ul>
                                <c:forEach items="${anuncioList}" var="anuncio">
                                    <li class="col l4 m6 s12">
                                        <div class="card">
                                            <div class="card-image img-responsive">
                                                <img src="<c:url value="/anuncio/${anuncio.id}/img.jpg"/>">
                                            </div>
                                            <div class="row" style="padding: 20px;">
                                                <h4 class="center">${anuncio.nome}</h4>
                                                <h6 class="col s6"> Preço:</h6>
                                                <h6 class="col s6"><span>R$ ${anuncio.preco}</h6> 
                                                <span class="col s6"> Quantidade:</span>
                                                <span class="col s6"><span> ${anuncio.qtd}</span> 
                                            </div>
                                            <div class="card-action card-content">
                                                <a href="<c:url value="/anuncios/${anuncio.id}/editar"/>" class="btn blue"><i class="small material-icons">input</i></a>
                                                <a href="#modelExcluir" data-target="modal1" class="btn modal-trigger red"><i class="small material-icons">delete</i></a>
                                            </div>
                                        </div>
                                    </li>
                                    <div id="modelExcluir" class="modal">
                                        <div class="modal-content">
                                            <h4>Excluir Anuncio</h4>
                                            <p>Tem certeza que deseja exluir esse anuncio ?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="<c:url value="/anuncios/${anuncio.id}/deletar"/>" class=" modal-action modal-close waves-effect waves-green btn-flat red">Excluir</a>
                                            <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat blue">Cancelar</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <ul class="pagination center" >
                        <c:if test="${(offset - limit)>=0}">
                            <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                            </c:if>
                            <c:if test="${(limit+offset)< count}">
                            <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                            </c:if>
                    </ul>
                </c:if>
            </div>
            <div id="modalAlterarSenha" class="modal">
                <div class="modal-content">
                    <h4>Alterar senha</h4>
                    <form method="post" action="<c:url value="/novasenha"/>">
                        <div class="form-group" >
                            <label for="Nova Senha">Nova senha:</label>
                            <input type="password" class="form-control" id="Nova Senha" name="senha">
                        </div>
                        <div class="form-group">
                            <label for="Confirmar Nova Senha">Confirmar nova senha:</label>
                            <input type="password" class="form-control" id="Confirmar Nova Senha" name="senha2">
                        </div>
                        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat red right" style="color: #FFF; margin-left: 10px;">Cancelar</a>
                        <input type="submit" class="btn btn-default blue right" value="Alterar">
                    </form>
                </div>
                </br>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/rodape.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appAgrocomp.js"/>"></script>
    </body>
</html>
