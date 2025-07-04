<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nouvel Emprunt</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <style>
        .livre-dispo-item {
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
        }
        .livre-dispo-item:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/elements/header.jsp" %>
    
    <main class="container-fluid mt-4 p-4">
        <div class="row g-5">

            <!-- COLONNE DE GAUCHE : FORMULAIRE -->
            <div class="col-lg-5">
                <div class="card shadow-sm sticky-top" style="top: 80px;">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0"><i class="fas fa-book-medical me-2"></i>Enregistrer un Prêt</h4>
                    </div>
                    <div class="card-body p-4">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                <strong>Erreur :</strong> ${errorMessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success" role="alert">
                                ${successMessage}
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/prets/enregistrer" method="post" id="pretForm">
                            <div class="mb-3">
                                <label for="idAdherent" class="form-label fw-bold">ID Adhérent</label>
                                <input type="number" class="form-control form-control-lg" id="idAdherent" name="idAdherent" placeholder="Ex: 12" required>
                            </div>
                            <div class="mb-4">
                                <label for="idExemplaire" class="form-label fw-bold">ID Exemplaire</label>
                                <input type="number" class="form-control form-control-lg" id="idExemplaire" name="idExemplaire" placeholder="Choisir un livre à droite" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <i class="fas fa-check-circle me-2"></i>Valider le prêt
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- COLONNE DE DROITE : LISTE DES LIVRES DISPONIBLES -->
            <div class="col-lg-7">
                <h3 class="h4 mb-3">Exemplaires Disponibles (${exemplairesDisponibles.size()})</h3>
                <div class="list-group">
                    <c:forEach items="${exemplairesDisponibles}" var="exemplaire">
                        <a href="#" class="list-group-item list-group-item-action livre-dispo-item" 
                           data-id-exemplaire="${exemplaire.idExemplaire}" 
                           onclick="choisirExemplaire(this); return false;">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${exemplaire.livre.titre}</h5>
                                <small class="text-muted">ID Exemplaire : ${exemplaire.idExemplaire}</small>
                            </div>
                            <p class="mb-1">
                                <c:forEach items="${exemplaire.livre.auteurs}" var="auteur" varStatus="loop">
                                    ${auteur.nom}<c:if test="${not loop.last}">, </c:if>
                                </c:forEach>
                            </p>
                            <small>Date d'acquisition : <fmt:formatDate value="${exemplaire.dateAcquisition}" pattern="dd/MM/yyyy"/></small>
                        </a>
                    </c:forEach>
                    <c:if test="${empty exemplairesDisponibles}">
                        <div class="alert alert-warning">Aucun exemplaire n'est actuellement disponible pour le prêt.</div>
                    </c:if>
                </div>
            </div>

        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function choisirExemplaire(element) {
            // Récupérer l'ID de l'exemplaire depuis l'attribut data-*
            const idExemplaire = element.getAttribute('data-id-exemplaire');
            
            // Mettre cette valeur dans le champ du formulaire
            const inputExemplaire = document.getElementById('idExemplaire');
            inputExemplaire.value = idExemplaire;
            
            // Mettre le focus sur le champ de l'adhérent pour la prochaine saisie
            document.getElementById('idAdherent').focus();
        }
    </script>
</body>
</html>