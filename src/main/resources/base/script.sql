CREATE TABLE auteurs (
    id_auteur SERIAL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_de_naissance DATE,
    PRIMARY KEY (id_auteur)
);

CREATE TABLE langues (
    id_langue SERIAL,
    nom_langue VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id_langue)
);

CREATE TABLE editeurs (
    id_editeur SERIAL,
    nom_editeur VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_editeur)
);

CREATE TABLE genres (
    id_genre SERIAL,
    nom VARCHAR(100) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id_genre)
);

CREATE TABLE collections (
    id_collection SERIAL,
    nom VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    id_editeur INTEGER NOT NULL,
    PRIMARY KEY (id_collection),
    FOREIGN KEY (id_editeur) REFERENCES editeurs (id_editeur)
);

CREATE TABLE type_adherent (
    id_type_adh SERIAL,
    nom_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_type_adh)
);

CREATE TABLE config_biblio (
    id_config SERIAL,
    cle VARCHAR(50) NOT NULL,
    valeur INTEGER NOT NULL,
    PRIMARY KEY (id_config)
);

CREATE TABLE rayons (
    id_rayon SERIAL,
    nom_rayon VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_rayon)
);

CREATE TABLE type_pret (
    id_type_pret SERIAL,
    nom_emprunt VARCHAR(50) NOT NULL,
    descriprtion VARCHAR(100),
    PRIMARY KEY (id_type_pret)
);

CREATE TABLE jour_ferie (
    id_jour_ferie SERIAL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id_jour_ferie)
);

CREATE TABLE type_abonnement (
    id_type_abonnement SERIAL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    prix NUMERIC(10, 2) NOT NULL,
    surplus_quota INTEGER,
    priorite INTEGER,
    PRIMARY KEY (id_type_abonnement)
);

CREATE TABLE quota (
    id_quota SERIAL,
    nbr_max INTEGER NOT NULL,
    duree_max TIME,
    id_type_adh INTEGER NOT NULL,
    PRIMARY KEY (id_quota),
    FOREIGN KEY (id_type_adh) REFERENCES type_adherent (id_type_adh)
);

CREATE TABLE users (
    id_user SERIAL,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(50),
    adresse VARCHAR(50),
    date_naissance DATE NOT NULL,
    PRIMARY KEY (id_user)
);

CREATE TABLE type_status (
    id_type_status SERIAL,
    libelle VARCHAR(50),
    PRIMARY KEY (id_type_status)
);

CREATE TABLE livres (
    id_livre SERIAL,
    titre VARCHAR(50) NOT NULL,
    sous_titre VARCHAR(50),
    date_publication DATE NOT NULL,
    prix NUMERIC(10, 2),
    id_collection INTEGER NOT NULL,
    id_genre INTEGER NOT NULL,
    id_langue INTEGER NOT NULL,
    PRIMARY KEY (id_livre),
    FOREIGN KEY (id_collection) REFERENCES collections (id_collection),
    FOREIGN KEY (id_genre) REFERENCES genres (id_genre),
    FOREIGN KEY (id_langue) REFERENCES langues (id_langue)
);

CREATE TABLE adherents (
    id_adherent SERIAL,
    numero VARCHAR(100) NOT NULL,
    date_inscription DATE NOT NULL,
    id_user INTEGER NOT NULL,
    id_type_adh INTEGER NOT NULL,
    PRIMARY KEY (id_adherent),
    FOREIGN KEY (id_user) REFERENCES users (id_user),
    FOREIGN KEY (id_type_adh) REFERENCES type_adherent (id_type_adh)
);

CREATE TABLE etageres (
    id_etagere SERIAL,
    nom_etagere VARCHAR(50) NOT NULL,
    nbr_niveau INTEGER NOT NULL,
    id_rayon INTEGER NOT NULL,
    PRIMARY KEY (id_etagere),
    FOREIGN KEY (id_rayon) REFERENCES rayons (id_rayon)
);

CREATE TABLE abonnement (
    id_abonnement SERIAL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    id_adherent INTEGER NOT NULL,
    id_type_abonnement INTEGER NOT NULL,
    PRIMARY KEY (id_abonnement),
    FOREIGN KEY (id_adherent) REFERENCES adherents (id_adherent),
    FOREIGN KEY (id_type_abonnement) REFERENCES type_abonnement (id_type_abonnement)
);

CREATE TABLE penalite (
    id_penalite SERIAL,
    commentaire VARCHAR(100),
    date_debut DATE NOT NULL,
    date_fin VARCHAR(50) NOT NULL,
    id_adherent INTEGER NOT NULL,
    PRIMARY KEY (id_penalite),
    FOREIGN KEY (id_adherent) REFERENCES adherents (id_adherent)
);

CREATE TABLE paiement (
    id_paiement SERIAL,
    validated_at DATE,
    id_abonnement INTEGER NOT NULL,
    PRIMARY KEY (id_paiement),
    FOREIGN KEY (id_abonnement) REFERENCES abonnement (id_abonnement)
);

CREATE TABLE paiement_details (
    id_paiement_detaiil SERIAL,
    date_paiement DATE,
    montant_donne NUMERIC(10, 2),
    retour NUMERIC(10, 2),
    id_user INTEGER NOT NULL,
    id_paiement INTEGER NOT NULL,
    PRIMARY KEY (id_paiement_detaiil),
    FOREIGN KEY (id_user) REFERENCES users (id_user),
    FOREIGN KEY (id_paiement) REFERENCES paiement (id_paiement)
);

CREATE TABLE localisations (
    id_localisation SERIAL,
    nom_zone VARCHAR(50),
    niveau INTEGER NOT NULL,
    id_etagere INTEGER NOT NULL,
    PRIMARY KEY (id_localisation),
    FOREIGN KEY (id_etagere) REFERENCES etageres (id_etagere)
);

CREATE TABLE exemplaires (
    id_exemplaire SERIAL,
    status VARCHAR(50) ENUM(
        'disponible',
        'emprunté',
        'perdu',
        'réparé'
    ) DEFAULT 'disponible',
    date_acquisition DATE NOT NULL,
    id_localisation INTEGER NOT NULL,
    id_livre INTEGER NOT NULL,
    PRIMARY KEY (id_exemplaire),
    FOREIGN KEY (id_localisation) REFERENCES localisations (id_localisation),
    FOREIGN KEY (id_livre) REFERENCES livres (id_livre)
);

CREATE TABLE reservations (
    id_reservation SERIAL,
    date_reservation DATE NOT NULL,
    motif VARCHAR(100),
    actif INTEGER NOT NULL DEFAULT 1,
    id_exemplaire INTEGER NOT NULL,
    id_adherent INTEGER NOT NULL,
    PRIMARY KEY (id_reservation),
    FOREIGN KEY (id_exemplaire) REFERENCES exemplaires (id_exemplaire),
    FOREIGN KEY (id_adherent) REFERENCES adherents (id_adherent)
);

CREATE TABLE prets (
    id_pret SERIAL,
    date_emprunt DATE NOT NULL,
    date_fin_prevue DATE NOT NULL,
    date_remise VARCHAR(50),
    id_type_pret INTEGER NOT NULL,
    id_adherent INTEGER NOT NULL,
    id_exemplaire INTEGER NOT NULL,
    PRIMARY KEY (id_pret),
    FOREIGN KEY (id_type_pret) REFERENCES type_pret (id_type_pret),
    FOREIGN KEY (id_adherent) REFERENCES adherents (id_adherent),
    FOREIGN KEY (id_exemplaire) REFERENCES exemplaires (id_exemplaire)
);

CREATE TABLE auteur_livre (
    id_livre INTEGER,
    id_auteur INTEGER,
    PRIMARY KEY (id_livre, id_auteur),
    FOREIGN KEY (id_livre) REFERENCES livres (id_livre),
    FOREIGN KEY (id_auteur) REFERENCES auteurs (id_auteur)
);

CREATE TABLE adh_livre (
    id_livre INTEGER,
    id_type_adh INTEGER,
    PRIMARY KEY (id_livre, id_type_adh),
    FOREIGN KEY (id_livre) REFERENCES livres (id_livre),
    FOREIGN KEY (id_type_adh) REFERENCES type_adherent (id_type_adh)
);

CREATE TABLE status_pret (
    id_pret INTEGER,
    id_type_status INTEGER,
    PRIMARY KEY (id_pret, id_type_status),
    FOREIGN KEY (id_pret) REFERENCES prets (id_pret),
    FOREIGN KEY (id_type_status) REFERENCES type_status (id_type_status)
);

CREATE TABLE abonnement_status (
    id_abonnement INTEGER,
    id_type_status INTEGER,
    PRIMARY KEY (id_abonnement, id_type_status),
    FOREIGN KEY (id_abonnement) REFERENCES abonnement (id_abonnement),
    FOREIGN KEY (id_type_status) REFERENCES type_status (id_type_status)
);

CREATE TABLE paiement_status (
    id_paiement INTEGER,
    id_type_status INTEGER,
    PRIMARY KEY (id_paiement, id_type_status),
    FOREIGN KEY (id_paiement) REFERENCES paiement (id_paiement),
    FOREIGN KEY (id_type_status) REFERENCES type_status (id_type_status)
);