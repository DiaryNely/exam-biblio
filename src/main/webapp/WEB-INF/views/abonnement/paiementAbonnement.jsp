<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Paiement de l'abonnement | Biblio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Frameworks & Icônes -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    
    <!-- Vos feuilles de style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">  <!-- CSS pour le header -->
    
    <style>
        /* CSS SPÉCIFIQUE À CETTE PAGE UNIQUEMENT */
        
        /* --- Importation de la police --- */
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap');

        /* --- Variables de couleur --- */
        :root {
            --primary-color: #007bff;
            --primary-color-light: #e9f5ff;
            --primary-color-border: #bde0ff;
            --secondary-color: #6c757d;
            --bg-light: #f0f2f5;
            --card-bg: #ffffff;
            --text-color: #343a40;
            --text-muted: #6c757d;
            --border-color: #dee2e6;
            --success-color: #198754;
        }

        /* Le style du body est maintenant dans main.css pour être global */

        h5 {
            font-weight: 600;
        }

        /* --- Conteneur de paiement principal --- */
        .payment-container {
            background-color: var(--card-bg);
            border: none;
            border-radius: 16px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .payment-container .card-header {
            background: linear-gradient(135deg, #0d6efd, #0056b3);
            color: white;
            padding: 1.5rem;
            border-bottom: none;
        }
        
        .payment-container .card-body {
            padding: 2rem;
        }

        /* --- Section Adhésion --- */
        .adherent-type-card {
            border: 2px solid var(--border-color);
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            transition: all 0.2s ease-in-out;
        }
        .adherent-type-card:hover {
            border-color: var(--primary-color);
            transform: translateY(-3px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
        }
        .adherent-type-card.active {
            border-color: var(--primary-color);
            background-color: var(--primary-color-light);
            box-shadow: 0 4px 10px rgba(0, 123, 255, 0.2);
            font-weight: 500;
        }

        /* --- Section Récapitulatif --- */
        .order-summary {
            background-color: var(--primary-color-light);
            border: 1px solid var(--primary-color-border);
            border-radius: 8px;
            padding: 1.5rem;
        }
        .order-summary strong { color: #0056b3; }
        #totalAmount {
            font-size: 1.75rem;
            font-weight: 700;
            color: var(--primary-color);
        }

        /* --- Choix de la durée (Badges) --- */
        .duration-badge {
            background-color: #e9ecef;
            color: var(--text-color);
            border: 2px solid transparent;
            border-radius: 50px;
            padding: 8px 16px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.2s ease-in-out;
        }
        .duration-badge:hover { background-color: #dbe2e9; }
        .duration-badge.active {
            background-color: var(--primary-color);
            color: white;
            font-weight: 600;
            box-shadow: 0 2px 8px rgba(0, 123, 255, 0.4);
        }

        /* --- Boutons --- */
        .btn-primary {
            font-weight: 600;
            padding: 0.75rem 1rem;
            transition: all 0.2s ease;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0, 123, 255, 0.3);
        }
        .btn-primary:disabled {
            background-color: #aeb8c4;
            border-color: #aeb8c4;
            cursor: not-allowed;
            transform: none;
            box-shadow: none;
        }
        .btn-outline-secondary { font-weight: 600; }
    </style>
</head>
<body>

    <!-- Inclusion du header -->
    <%@ include file="/WEB-INF/views/elements/header.jsp" %> 

    <!-- Contenu principal de la page -->
    <main class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-7 col-md-9">
                <div class="card payment-container">
                    <div class="card-header">
                        <h3 class="mb-0 text-center">Finaliser votre abonnement</h3>
                    </div>
                    <div class="card-body">

                        <c:if test="${empty adherent}">
                            <div class="mb-4 p-3 border rounded">
                                <h5 class="mb-3">Vous n'êtes pas encore adhérent</h5>
                                <p class="text-muted small">Veuillez choisir votre type d'adhésion avant de continuer :</p>

                                <div class="row g-3" id="adherentTypeOptions">
                                    <c:forEach items="${typesAdherent}" var="typeAdh">
                                        <div class="col-md-6">
                                            <div class="card adherent-type-card p-3" data-typeadh="${typeAdh.idTypeAdh}">
                                                <h5 class="mb-0">${typeAdh.nomType}</h5>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>

                        <div class="alert order-summary">
                            <h5 class="mb-3">Récapitulatif</h5>
                            <p class="mb-2">
                                <strong>Abonnement:</strong> ${typeAbonnement.name}<br>
                                <strong>Prix mensuel:</strong> ${typeAbonnement.prix} €<br>
                                <strong>Durée choisie:</strong> <span id="selectedDurationText">1 mois</span>
                            </p>
                            <hr>
                            <div class="d-flex justify-content-between align-items-center mt-2">
                                <h5 class="mb-0">Total à payer</h5>
                                <span id="totalAmount">${typeAbonnement.prix} €</span>
                            </div>
                        </div>

                        <div class="my-4">
                            <h5 class="mb-3">Choisissez la durée</h5>
                            <div class="d-flex flex-wrap gap-2" id="durationOptions">
                                <span class="badge duration-badge active" data-months="1">1 mois</span>
                                <span class="badge duration-badge" data-months="3">3 mois</span>
                                <span class="badge duration-badge" data-months="6">6 mois</span>
                                <span class="badge duration-badge" data-months="12">1 an</span>
                            </div>
                        </div>

                        <form action="${pageContext.request.contextPath}/abonnements/confirmer-paiement" method="post">
                            <input type="hidden" name="typeAbonnementId" value="${typeAbonnement.idTypeAbonnement}">
                            <input type="hidden" id="durationMonths" name="durationMonths" value="1">
                            <input type="hidden" id="finalPrice" name="finalPrice" value="${typeAbonnement.prix}">
                            <c:if test="${empty adherent}">
                                <input type="hidden" id="adherentTypeId" name="typeAdherentId" value="">
                            </c:if>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary btn-lg" id="payButton" <c:if test="${empty adherent}">disabled</c:if>>
                                    Payer <span id="payButtonAmount">${typeAbonnement.prix}</span> €
                                </button>
                                <a href="${pageContext.request.contextPath}/abonnements" class="btn btn-outline-secondary">
                                    Annuler
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const monthlyPrice = parseFloat("${typeAbonnement.prix}".replace(',', '.'));
            const durationOptions = document.querySelectorAll('#durationOptions .duration-badge');
            const totalAmountElement = document.getElementById('totalAmount');
            const payButtonAmountElement = document.getElementById('payButtonAmount');
            const durationMonthsInput = document.getElementById('durationMonths');
            const finalPriceInput = document.getElementById('finalPrice');
            const selectedDurationText = document.getElementById('selectedDurationText');
            const payButton = document.getElementById('payButton');
            const adherentTypeCards = document.querySelectorAll('.adherent-type-card');
            const adherentTypeIdInput = document.getElementById('adherentTypeId');

            if (adherentTypeCards.length > 0) {
                adherentTypeCards.forEach(card => {
                    card.addEventListener('click', function () {
                        adherentTypeCards.forEach(c => c.classList.remove('active'));
                        this.classList.add('active');
                        const typeId = this.dataset.typeadh;
                        adherentTypeIdInput.value = typeId;
                        payButton.disabled = false;
                    });
                });
            }

            function updatePriceDisplay(months) {
                const totalPrice = monthlyPrice * months;
                const finalPrice = months === 12 ? totalPrice * 0.9 : totalPrice; // Promo 10% sur 1 an
                const roundedPrice = Math.round(finalPrice * 100) / 100;

                totalAmountElement.textContent = `${roundedPrice.toFixed(2)} €`;
                payButtonAmountElement.textContent = roundedPrice.toFixed(2);
                finalPriceInput.value = roundedPrice.toFixed(2);
                
                let durationText = `${months} mois`;
                if (months === 1) durationText = '1 mois';
                if (months === 12) durationText = '1 an';
                selectedDurationText.textContent = durationText;

                durationMonthsInput.value = months;
            }

            durationOptions.forEach(option => {
                option.addEventListener('click', function () {
                    durationOptions.forEach(opt => opt.classList.remove('active'));
                    this.classList.add('active');
                    const months = parseInt(this.dataset.months);
                    updatePriceDisplay(months);
                });
            });

            // Initialiser le prix
            updatePriceDisplay(1);
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>