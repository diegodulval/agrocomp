<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
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
                        <li><a href="<c:url value="/forum"/>">Fórum</a></li>
                        <li><a href="<c:url value="/sobre"/>">Sobre</a></li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <li><a href="<c:url value="/home"/>" class="active">Início</a></li>
                        <li><a href="<c:url value="/classificados"/>">Classificados</a></li>
                        <li><a href="<c:url value="/forum"/>">Fórum</a></li>
                        <li><a href="<c:url value="/sobre"/>">Sobre</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>