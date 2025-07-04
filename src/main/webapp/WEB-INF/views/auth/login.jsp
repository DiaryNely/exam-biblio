<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion | Biblio</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/login.css" >
</head>
<body>
<div class="login-container">
    <div class="logo-section">
        <div class="logo">
            <i class="fas fa-book-open"></i>
        </div>
        <h1 class="title">Biblio</h1>
        <p class="subtitle">Systeme de Gestion de Bibliotheque</p>
    </div>

    <div id="error-message" class="error-message" style="display: none;">
        <i class="fas fa-exclamation-triangle"></i>
        Email ou mot de passe incorrect
    </div>

    <form id="loginForm" action="/login" method="post">
        <div class="form-group">
            <label for="email">Adresse Email</label>
            <div class="input-wrapper">
                <input type="email" id="email" name="email" class="form-control" placeholder="votre.email@exemple.com" required>
                <i class="fas fa-envelope"></i>
            </div>
        </div>

        <div class="form-group">
            <label for="password">Mot de Passe</label>
            <div class="input-wrapper">
                <input type="password" id="password" name="password" class="form-control" placeholder="****" required>
                <i class="fas fa-lock"></i>
            </div>
        </div>

        <button type="submit" class="btn-login">
            <span class="btn-text">Se Connecter</span>
            <div class="loading">
                <div class="spinner"></div>
            </div>
        </button>
    </form>

    

    <div class="divider">
        <span>Fonctionnalites</span>
    </div>

    <div class="features">
        <div class="feature-item">
            <i class="fas fa-shield-alt"></i>
            <span>Connexion securisee</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-search"></i>
            <span>Recherche avancee de livres</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-chart-bar"></i>
            <span>Suivi des emprunts</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-users"></i>
            <span>Gestion des membres</span>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/login.js" ></script>
</body>
</html>