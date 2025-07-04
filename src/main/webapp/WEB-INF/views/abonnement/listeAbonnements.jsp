<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Abonnements disponibles</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    /* --- Importation d'une police plus moderne --- */
                    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

                    /* --- Variables de couleur pour un thème cohérent --- */
                    :root {
                        --bs-primary: #0056b3;
                        /* Bleu profond pour la marque */
                        --bs-secondary: #6c757d;
                        --bs-light: #f8f9fa;
                        --bs-dark: #343a40;
                        --highlight-color: #ffc107;
                        /* Jaune/Or pour l'offre populaire */
                        --background-color: #f0f2f5;
                        --card-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
                        --card-shadow-hover: 0 8px 25px rgba(0, 0, 0, 0.12);
                    }

                    /* --- Styles généraux --- */
                    body {
                        font-family: 'Poppins', sans-serif;
                        background-color: var(--background-color);
                    }

                    .page-title {
                        font-weight: 700;
                        color: var(--bs-dark);
                        margin-bottom: 2rem;
                    }

                    /* --- Style des cartes d'abonnement --- */
                    .abonnement-card {
                        border: none;
                        border-radius: 12px;
                        box-shadow: var(--card-shadow);
                        transition: transform 0.3s ease, box-shadow 0.3s ease;
                        display: flex;
                        flex-direction: column;
                        height: 100%;
                        overflow: hidden;
                        /* Important pour le bandeau */
                    }

                    .abonnement-card:hover {
                        transform: translateY(-8px);
                        box-shadow: var(--card-shadow-hover);
                    }

                    .card .card-header {
                        background: none;
                        border-bottom: 1px solid #eee;
                        padding: 1.5rem;
                    }

                    .card .card-header h3 {
                        font-weight: 600;
                        margin: 0;
                        color: var(--bs-primary);
                    }

                    .card .card-body {
                        padding: 1.5rem;
                        flex-grow: 1;
                        /* Permet au body de prendre l'espace restant */
                        display: flex;
                        flex-direction: column;
                    }

                    .price-section {
                        margin: 1.5rem 0;
                    }

                    .price {
                        font-size: 2.5rem;
                        font-weight: 700;
                        color: var(--bs-dark);
                    }

                    .price-currency {
                        font-size: 1.5rem;
                        font-weight: 500;
                        vertical-align: super;
                    }

                    .price-period {
                        color: var(--bs-secondary);
                    }

                    .feature-list {
                        list-style: none;
                        padding: 0;
                        margin-top: auto;
                        /* Pousse la liste vers le bas */
                    }

                    .feature-list li {
                        padding: 0.75rem 0;
                        border-bottom: 1px solid #f0f0f0;
                        display: flex;
                        align-items: center;
                    }

                    .feature-list li:last-child {
                        border-bottom: none;
                    }

                    /* Icône de coche pour les avantages */
                    .feature-list li::before {
                        content: '✓';
                        color: #28a745;
                        font-weight: bold;
                        margin-right: 12px;
                        font-size: 1.2rem;
                    }

                    .card .card-footer {
                        background-color: transparent;
                        border-top: none;
                        padding: 1.5rem;
                    }

                    .btn-subscribe {
                        background-color: var(--bs-primary);
                        color: white;
                        font-weight: 600;
                        padding: 0.75rem 1.5rem;
                        border-radius: 8px;
                        transition: background-color 0.2s ease, transform 0.2s ease;
                    }

                    .btn-subscribe:hover {
                        background-color: #00418a;
                        transform: scale(1.02);
                    }

                    /* --- Style pour la carte mise en avant --- */
                    .highlight-card {
                        transform: scale(1.05);
                        border: 2px solid var(--bs-primary);
                    }

                    .highlight-card:hover {
                        transform: scale(1.08);
                    }

                    /* Bandeau "Populaire" */
                    .popular-badge {
                        position: absolute;
                        top: 20px;
                        right: -35px;
                        background-color: var(--highlight-color);
                        color: var(--bs-dark);
                        padding: 5px 40px;
                        transform: rotate(45deg);
                        font-weight: 600;
                        font-size: 0.8rem;
                        text-transform: uppercase;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                    }

                    /* --- Style de la carte "Abonnement Actuel" --- */
                    .current-abonnement-card {
                        background: linear-gradient(135deg, #e3f2fd, #fafcff);
                        border: 1px solid #bbdefb;
                        border-radius: 12px;
                    }

                    .current-abonnement-card .card-header {
                        background-color: rgba(13, 110, 253, 0.1);
                        border-bottom: 1px solid #bbdefb;
                    }
                </style>
            </head>

            <body>

                <%@ include file="/WEB-INF/views/elements/header.jsp" %>

                    <div class="container py-5">
                        <h1 class="text-center page-title">Nos Offres d'Abonnement</h1>

                        <!-- Messages d'alerte (inchangés) -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">${error}<button
                                    type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${successMessage}<button type="button" class="btn-close" data-bs-dismiss="alert"
                                    aria-label="Close"></button></div>
                        </c:if>

                        <!-- Cartes des offres d'abonnement -->
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 justify-content-center">
                            <c:forEach items="${typesAbonnement}" var="type" varStatus="status">
                                <div class="col">
                                    <div class="card abonnement-card ${status.index == 1 ? 'highlight-card' : ''}">

                                        <!-- Ajout du bandeau "Populaire" pour la carte mise en avant -->
                                        <c:if test="${status.index == 1}">
                                            <div class="popular-badge">Populaire</div>
                                        </c:if>

                                        <div class="card-header">
                                            <h3 class="text-center">${type.name}</h3>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text text-secondary">${type.description}</p>

                                            <div class="text-center price-section">
                                                <span class="price">
                                                    <fmt:formatNumber value="${type.prix}" type="number"
                                                        minFractionDigits="0" maxFractionDigits="0" />
                                                </span>
                                                <span class="price-currency">€</span>
                                                <small class="d-block price-period">/ an</small>
                                            </div>

                                            <ul class="feature-list">
                                                <li>Accès à tous les livres</li>
                                                <c:if test="${type.surplusQuota > 0}">
                                                    <li>+${type.surplusQuota} emprunts simultanés</li>
                                                </c:if>
                                                <c:if test="${type.priorite > 0}">
                                                    <li>Priorité sur les réservations</li>
                                                </c:if>
                                            </ul>
                                        </div>
                                        <div class="card-footer">
                                            <form action="${pageContext.request.contextPath}/abonnements/souscrire"
                                                method="post">
                                                <input type="hidden" name="typeAbonnementId"
                                                    value="${type.idTypeAbonnement}">
                                                <button type="submit" class="btn btn-subscribe w-100">
                                                    Souscrire
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>



                        <script
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>