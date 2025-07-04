<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Biblio</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/elements/header.jsp" %> 

    <main class="container-fluid p-4">
        <h1 class="h3 mb-4">Tableau de Bord</h1>

        <!-- Ligne des Statistiques Clés (KPIs) -->
        <div class="row g-4 mb-4">
            <div class="col-md-6 col-lg-3">
                <div class="stat-card primary">
                    <div class="stat-icon"><i class="fas fa-book"></i></div>
                    <h5>Livres au total</h5>
                    <div class="stat-number">${nombreTotalLivres}</div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="stat-card success">
                    <div class="stat-icon"><i class="fas fa-users"></i></div>
                    <h5>Adhérents Actifs</h5>
                    <div class="stat-number">${nombreAdherentsActifs}</div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="stat-card warning">
                    <div class="stat-icon"><i class="fas fa-hand-holding"></i></div>
                    <h5>Emprunts en cours</h5>
                    <div class="stat-number">${nombreEmpruntsEnCours}</div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="stat-card danger">
                    <div class="stat-icon"><i class="fas fa-clock"></i></div>
                    <h5>Livres en retard</h5>
                    <div class="stat-number">${nombreLivresEnRetard}</div>
                </div>
            </div>
        </div>

        <!-- Ligne Contenu Principal (Graphique et Listes) -->
        <div class="row g-4">
            <!-- Colonne du Graphique -->
            <div class="col-lg-8">
                <div class="card content-card">
                    <div class="card-header">Livres les plus populaires</div>
                    <div class="card-body">
                        <canvas id="popularBooksChart"></canvas>
                    </div>
                </div>
            </div>

            <!-- Colonne des Derniers Emprunts -->
            <div class="col-lg-4">
                <div class="card content-card">
                    <div class="card-header">Derniers Emprunts</div>
                    <ul class="list-group list-group-flush">
                        <c:forEach items="${derniersEmprunts}" var="pret">
                            <li class="list-group-item">
                                <strong>${pret.exemplaire.livre.titre}</strong><br>
                                <small class="text-muted">
                                    par ${pret.adherent.user.prenom} ${pret.adherent.user.nom} 
                                    le <fmt:formatDate value="${pret.dateEmprunt}" type="date" pattern="dd/MM/yyyy"/>
                                </small>
                            </li>
                        </c:forEach>
                        <c:if test="${empty derniersEmprunts}">
                            <li class="list-group-item text-center">
                                <small class="text-muted">Aucun emprunt récent.</small>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Données pour le graphique (construites avec JSTL)
            const bookLabels = [<c:forEach items="${livresPopulaires}" var="livre" varStatus="loop">'${livre.titre}'<c:if test="${!loop.last}">, </c:if></c:forEach>];
            const bookData = [<c:forEach items="${livresPopulaires}" var="livre" varStatus="loop">${livre.nombreEmprunts}<c:if test="${!loop.last}">, </c:if></c:forEach>];

            const ctx = document.getElementById('popularBooksChart').getContext('2d');
            const popularBooksChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: bookLabels,
                    datasets: [{
                        label: 'Nombre d\'emprunts',
                        data: bookData,
                        backgroundColor: 'rgba(0, 123, 255, 0.6)',
                        borderColor: 'rgba(0, 123, 255, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: { y: { beginAtZero: true } },
                    plugins: { legend: { display: false } }
                }
            });
        });
    </script>
</body>
</html>