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
            <div class="parallax-container">
                <div class="parallax">
                    <img class="img-responsive" src="<c:url value="/resources/img/banners/header-bg.jpg"/>">
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col s12 m6 l6">
                            <div class="card hoverable ">
                                <div class="row">
                                    <div class="col s12">
                                        <ul class="tabs">
                                            <li class="tab col s3"><a <c:if test="${empty errors}">class="active"</c:if> href="#Login">Entrar</a></li>
                                            <li class="tab col s3"><a <c:if test="${not empty errors}">class="active"</c:if>href="#Cadastro">Cadastrar-se</a></li>
                                            </ul>
                                        </div>
                                        <div id="Login" class="col s12">
                                            <div class="card-content white-text">
                                                <span class="card-title center ">Acesse o Seu Perfil</span>
                                                <form method="post" action="<%=request.getContextPath()%>/login">
                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <i class="material-icons prefix iconAcesso">account_circle</i>
                                                        <input name="email" id="email" type="email"  maxlength="100" class=" iconAcesso validate">
                                                        <label for="email" >Email</label>
                                                    </div>
                                                    <div class="input-field col s12">
                                                        <i class="material-icons prefix iconAcesso">vpn_key</i>
                                                        <input name="senha" id="icon_telephone" type="password" maxlength="30"class=" iconAcesso validate">
                                                        <label for="icon_telephone">Senha</label>
                                                    </div>
                                                    <span class="blue-text text-darken-2 ">${loginFailed}</span>
                                                </div>
                                                <div class="card-action center">
                                                    <button class="btn waves-effect waves-light light-blue darken-4" type="submit">Entrar
                                                        <i class="material-icons right">perm_identity</i>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div id="Cadastro" class="col s12">
                                        <div class="card-content white-text">
                                            <span class="card-title center ">Digite seus dados </span>
                                            <form method="post" action="<%=request.getContextPath()%>/cadastro">
                                                <div class="row">
                                                    <div class="input-field col s12 m6">
                                                        <i class="material-icons prefix iconAcesso">account_circle</i>
                                                        <input name="nome" id="icon_prefix" type="text" maxlength="30" placeholder="${errors.nome}" class="iconAcesso validate">
                                                        <label for="icon_prefix">Nome completo</label>
                                                    </div>
                                                    <div class="input-field col s12 m6">
                                                        <i class="material-icons prefix iconAcesso">vpn_key</i>
                                                        <input id="icon_telephone" type="password" name="senha" maxlength="30"  placeholder="${errors.senha}" class="iconAcesso validate">
                                                        <label for="icon_telephone">Senha</label>
                                                    </div>
                                                    <div class="input-field col s12">
                                                        <i class="material-icons prefix iconAcesso">account_circle</i>
                                                        <input id="email" type="email" name="email" placeholder="${errors.email}" class=" iconAcesso validate">
                                                        <label for="email" data-error="wrong"  maxlength="100"  data-success="right">Email</label>
                                                    </div>
                                                </div>

                                                <div class="card-action center">
                                                    <button class="btn waves-effect waves-light light-blue darken-4" type="submit" name="action">Cadastrar
                                                        <i class="material-icons right">perm_identity</i>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <div class="container">
            <div class="row">
                <h5 style="margin-top: 20px;">Categorias </h5>
                <hr>
            </div>
            <!-- CATEGORIAS -->
            <div class="row">
                <c:forEach items="${categoriaList}" var="categoria">                      
                    <div class="col s12 l3 m3 card" style="text-align: center">     
                        <div>
                            <c:if test="${categoria.nome eq 'Agricultura'}">
                                <img src="<c:url value="/resources/img/categorias/agricultura.png"/>" class="responsive-img" style="margin-top: 20px;">
                            </c:if>
                            <c:if test="${categoria.nome eq 'Pecuária'}">
                                <img src="<c:url value="/resources/img/categorias/pecuaria.png"/>" class="responsive-img" style="margin-top: 20px;">
                            </c:if>
                            <c:if test="${categoria.nome eq 'Serviços'}">
                                <img src="<c:url value="/resources/img/categorias/servico.png"/>" class="responsive-img" style="margin-top: 20px;margin-bottom: 9px;">
                            </c:if>
                            <c:if test="${categoria.nome eq 'Artesanato'}">
                                <img src="<c:url value="/resources/img/categorias/artesanato.png"/>" class="responsive-img" style="margin-top: 20px;margin-bottom: 12px;">
                            </c:if>
                        </div>
                        <h5><a href="<c:url value="/classificados?categoria=${categoria.nome}&id=${categoria.id}"/>">${categoria.nome}</a></h5>

                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <h5 style="margin-top: 20px;">Ultimas Noticias </h5>
                <hr>
            </div>
            <div class="row">
                <c:forEach items="${noticiaList}" var="noticia">
                    <div class="row card">
                        <div class="col m3 s4 thumbnail">
                            <img class="responsive-img home_img" src="resources/img/temp/agricultor.jpg">
                        </div>
                        <div class="col m9 s8">
                            <h5><a href="${noticia.link}" target=" _blank">${noticia.titulo}</a></h5>
                            <p class="">${noticia.descricao} </p>

                            <div class="col m3 l3">
                                <div class="social_icons">
                                    <!-- Para adicionar um link para o compartilhamento nas redes sociais e só adptar o link abaixo com as informaçoes do anuncio -->
                                    <a href="http://www.facebook.com/sharer.php?u=${noticia.link}" target=" _blank"><i class="fa fa-facebook fa-2x"></i></a>
                                    <a href="http://twitter.com/home?status=http%3A%2F%2Fwww.roboterwelt.de%2Fmagazin%2Fsichler-pcr-3550-uv-der-saugroboter-mit-uv-lampe+Sichler+PCR+3550+UV+%E2%80%93+Der+Saugroboter+mit+UV-Lampe" target=" _blank"><i class="fa fa-twitter fa-2x"></i></a>
                                    <a href="https://www.linkedin.com/shareArticle?mini=true&amp;url=http%3A%2F%2Fwww.roboterwelt.de%2Fmagazin%2Fsichler-pcr-3550-uv-der-saugroboter-mit-uv-lampe&amp;title=Sichler+PCR+3550+UV+%E2%80%93+Der+Saugroboter+mit+UV-Lampe&amp;summary=Staubsaugroboter+mit+UV-Lampe+sind+die+Ausnahme+auf+dem+hei%C3%9F+umk%C3%A4mpften+Markt+der+saugenden+Roboter.+Der+PCR+3550+UV+von+Sichler+ist+einer+davon.+Wir+haben+ihn+getestet.+&amp;source=Share on LinkedIn" ><i class="fa fa-linkedin fa-2x"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col s12 m12 l12 center">
                    <ul class="pagination">
                        <c:if test="${(offset - limit)>=0}">
                            <li class="waves-effect"><a href="<c:url value="/home?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                            </c:if>
                            <c:if test="${(limit+offset)< count}">
                            <li class="waves-effect"><a href="<c:url value="/home?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                            </c:if>
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
