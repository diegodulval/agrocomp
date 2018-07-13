<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <ul id="relatoriomain" class="dropdown-content">
        <li><a href="<c:url value="/usuario/pdf"/>" target="_blank">Clientes</a></li>
        <li class="divider"></li>
        <li><a href="<c:url value="/anuncio/pdf"/>" target="_blank">Anúncios</a></li>
        <li class="divider"></li>
        <li><a href="<c:url value="/reporte/pdf"/>" target="_blank">Reportes</a></li>
    </ul>
    <nav>
        <div class="nav-wrapper light-blue darken-4">
            <div class="container">
                <div class="row">
                    <a href="<c:url value="/home"/>" class="brand-logo">
                        <img src="<c:url value="/resources/img/Logo_AgroComp.png"/>"/> 
                    </a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="<c:url value="/home"/>">Início</a></li>
                        <li><a href="<c:url value="/classificados"/>">Classificados</a></li>                        
                        <li>
                            <a class="dropdown-button" href="#!" data-activates="relatoriomain">Relatórios<i class="material-icons right">arrow_drop_down</i></a>
                        </li>
                        <li><a href="<c:url value="/forum"/>">Fórum</a></li>
                        <li><a href="<c:url value="/sobre"/>">Sobre</a></li>
                        <li><a href="<c:url value="/logout"/>">Logout</a></li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <li><a href="#" class="active">Início</a></li>
                        <li><a href="/agrocomp/classificados">Classificados</a></li>
                        <li>
                            <a class="dropdown-button" href="<c:url value="#!"/>" data-activates="relatorio">Relatórios<i class="material-icons right">arrow_drop_down</i></a>
                        </li>
                        <li><a href="#">Fórum</a></li>
                        <li><a href="/agrocomp/sobre">Sobre</a></li>
                        <li><a href="<c:url value="/logout"/>">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>