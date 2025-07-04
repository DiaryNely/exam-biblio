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

                    <main class="container-fluid p-4 p-md-5">
                        <h1 class="h2 mb-4 dashboard-title">Tableau de Bord</h1>

                        <!-- Ligne des Statistiques Clés (KPIs) -->
                        <div class="row g-4 mb-5">
                            <div class="col-sm-6 col-lg-3">
                                <div class="stat-card primary">
                                    <div class="stat-content">
                                        <h5 class="stat-title">Livres au total</h5>
                                        <div class="stat-number">${nombreTotalLivres}</div>
                                    </div>
                                    <div class="stat-icon"><i class="fas fa-book-journal-whills"></i></div>
                                </div>
                            </div>

                            <div class="col-sm-6 col-lg-3">
                                <div class="stat-card warning">
                                    <div class="stat-content">
                                        <h5 class="stat-title">Emprunts en cours</h5>
                                        <div class="stat-number">${nombreEmpruntsEnCours}</div>
                                    </div>
                                    <div class="stat-icon"><i class="fas fa-hand-holding-hand"></i></div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="stat-card danger">
                                    <div class="stat-content">
                                        <h5 class="stat-title">Livres en retard</h5>
                                        <div class="stat-number">${nombreLivresEnRetard}</div>
                                    </div>
                                    <div class="stat-icon"><i class="fas fa-hourglass-end"></i></div>
                                </div>
                            </div>
                        </div>

                        <!-- Ligne Contenu Principal (Graphique et Listes) -->
                        <div class="row g-4">
                            <!-- Colonne du Graphique -->
                            <div class="col-lg-7">
                                <div class="card content-card">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar"></i>
                                        Livres les plus populaires
                                    </div>
                                    <div class="card-body">
                                        <canvas id="popularBooksChart" style="min-height: 350px;"></canvas>
                                    </div>
                                </div>
                            </div>

                            <!-- Colonne des Derniers Emprunts -->
                            <div class="col-lg-5">
                                <div class="card content-card">
                                    <div class="card-header">
                                        <i class="fas fa-history"></i>
                                        Activité Récente
                                    </div>
                                    <ul class="list-group list-group-flush activity-list">
                                        <c:forEach items="${derniersEmprunts}" var="pret">
                                            <li class="list-group-item">
                                                <div class="list-icon icon-emprunt"><i class="fas fa-book-reader"></i>
                                                </div>
                                                <div class="list-content">
                                                    <strong>${pret.exemplaire.livre.titre}</strong>
                                                    <small>Emprunté par ${pret.adherent.user.prenom}
                                                        ${pret.adherent.user.nom}</small>
                                                </div>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${empty derniersEmprunts}">
                                            <li class="list-group-item text-center">
                                                <small class="text-muted">Aucune activité récente à afficher.</small>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </main>

                    <!-- ... Le reste de votre page reste identique ... -->
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                    <!-- Le script pour le graphique est à l'étape 3 -->
                    <script>
                        document.addEventListener('DOMContentLoaded', function () {
                            // Données pour le graphique (construites avec JSTL)
                            const bookLabels = [<c:forEach items="${livresPopulaires}" var="livre" varStatus="loop">'${livre.titre}'<c:if test="${!loop.last}">, </c:if></c:forEach>];
                            const bookData = [<c:forEach items="${livresPopulaires}" var="livre" varStatus="loop">${livre.nombreEmprunts}<c:if test="${!loop.last}">, </c:if></c:forEach>];

                            const ctx = document.getElementById('popularBooksChart').getContext('2d');

                            // Création d'un dégradé pour les barres
                            const gradient = ctx.createLinearGradient(0, 0, 0, 400);
                            gradient.addColorStop(0, 'rgba(0, 123, 255, 0.8)');
                            gradient.addColorStop(1, 'rgba(0, 123, 255, 0.2)');

                            const popularBooksChart = new Chart(ctx, {
                                type: 'bar',
                                data: {
                                    labels: bookLabels,
                                    datasets: [{
                                        label: 'Nombre d\'emprunts',
                                        data: bookData,
                                        backgroundColor: gradient, // Utilisation du dégradé
                                        borderColor: 'rgba(0, 123, 255, 1)',
                                        borderWidth: 2,
                                        borderRadius: 5, // Coins arrondis pour les barres
                                        hoverBackgroundColor: 'rgba(0, 123, 255, 1)'
                                    }]
                                },
                                options: {
                                    responsive: true,
                                    maintainAspectRatio: false,
                                    plugins: {
                                        legend: {
                                            display: false // On cache la légende, le titre du graphique suffit
                                        },
                                        tooltip: { // Amélioration des info-bulles
                                            backgroundColor: '#343a40',
                                            titleFont: { size: 14, weight: 'bold' },
                                            bodyFont: { size: 12 },
                                            padding: 10,
                                            cornerRadius: 4,
                                            displayColors: false // On cache le petit carré de couleur
                                        }
                                    },
                                    scales: {
                                        y: {
                                            beginAtZero: true,
                                            grid: {
                                                color: '#e9ecef' // Couleur de la grille plus subtile
                                            },
                                            ticks: {
                                                precision: 0 // Assure que l'axe Y n'a que des nombres entiers
                                            }
                                        },
                                        x: {
                                            grid: {
                                                display: false // On cache la grille verticale
                                            }
                                        }
                                    }
                                }
                            });
                        });
                    </script>
            </body>

            </html>