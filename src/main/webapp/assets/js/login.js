// Animation d'entree
document.addEventListener('DOMContentLoaded', function() {
    const container = document.querySelector('.login-container');
    container.style.opacity = '0';
    container.style.transform = 'translateY(30px)';

    setTimeout(() => {
        container.style.transition = 'all 0.6s ease';
        container.style.opacity = '1';
        container.style.transform = 'translateY(0)';
    }, 100);
});

// Gestion du formulaire
document.getElementById('loginForm').addEventListener('submit', function(e) {
    const button = this.querySelector('.btn-login');
    const btnText = button.querySelector('.btn-text');
    const loading = button.querySelector('.loading');

    // Animation de chargement
    btnText.style.opacity = '0';
    loading.style.display = 'block';
    button.disabled = true;

    // Simulation d'une verification (à remplacer par votre logique)
    setTimeout(() => {
        btnText.style.opacity = '1';
        loading.style.display = 'none';
        button.disabled = false;
    }, 2000);
});

// Animation des champs de saisie
const inputs = document.querySelectorAll('.form-control');
inputs.forEach(input => {
    input.addEventListener('focus', function() {
        this.parentElement.style.transform = 'scale(1.02)';
    });

    input.addEventListener('blur', function() {
        this.parentElement.style.transform = 'scale(1)';
    });
});

// Gestion des erreurs (exemple)
const urlParams = new URLSearchParams(window.location.search);
if (urlParams.get('error')) {
    document.getElementById('error-message').style.display = 'block';
    setTimeout(() => {
        document.getElementById('error-message').style.display = 'none';
    }, 5000);
}