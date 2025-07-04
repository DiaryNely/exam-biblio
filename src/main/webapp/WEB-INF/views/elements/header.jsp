<%-- /WEB-INF/jspf/header.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="site-header">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <%-- Logo --%>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
                <i class="fas fa-book-open"></i> Biblio
            </a>

            <%-- Bouton "Hamburger" pour le mode mobile --%>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar" aria-controls="mainNavbar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <%-- Liens de navigation --%>
            <div class="collapse navbar-collapse" id="mainNavbar">
                <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.servletPath.contains('/dashboard') ? 'active' : ''}" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.servletPath.contains('/catalogue') ? 'active' : ''}" href="${pageContext.request.contextPath}/catalogue">Catalogue</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.servletPath.contains('/emprunts') ? 'active' : ''}" href="${pageContext.request.contextPath}/emprunts">Mes Emprunts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.servletPath.contains('/abonnements') ? 'active' : ''}" href="${pageContext.request.contextPath}/abonnements">Abonnements</a>
                    </li>
                </ul>

                <%-- Menu Utilisateur (à droite) --%>
                <div class="d-flex align-items-center user-menu">
                    <c:choose>
                        <%-- Si l'utilisateur est connecté --%>
                        <c:when test="${not empty userSession}">
                            <div class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%-- Mettez ici une image de profil si vous en avez, sinon une icône --%>
                                    <img src="https://i.pravatar.cc/40?u=${userSession.id}" alt="Profil" class="profile-picture">
                                    <span>Bonjour, ${userSession.prenom}</span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profil"><i class="fas fa-user-circle"></i> Mon Profil</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
                                </ul>
                            </div>
                        </c:when>

                        <%-- Si l'utilisateur n'est pas connecté --%>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-login">
                                Se Deconnecter
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>
</header>
