-- Insertion des langues
INSERT INTO
    langues (nom_langue, description)
VALUES (
        'Français',
        'Langue française'
    ),
    ('Anglais', 'Langue anglaise'),
    (
        'Espagnol',
        'Langue espagnole'
    ),
    (
        'Allemand',
        'Langue allemande'
    ),
    ('Arabe', 'Langue arabe');

-- Insertion des editeurs
INSERT INTO
    editeurs (nom_editeur)
VALUES ('Gallimard'),
    ('Flammarion'),
    ('Hachette'),
    ('Seuil'),
    ('Robert Laffont'),
    ('Actes Sud'),
    ('Albin Michel'),
    ('Grasset'),
    ('PUF'),
    ('Larousse');

-- Insertion des genres
INSERT INTO
    genres (nom, description)
VALUES (
        'Roman',
        'Œuvre narrative en prose'
    ),
    (
        'Policier',
        'Genre centre sur des enquêtes criminelles'
    ),
    (
        'Science-fiction',
        'Genre imaginaire avec des elements scientifiques'
    ),
    (
        'Fantasy',
        'Genre avec des elements magiques et mythologiques'
    ),
    (
        'Biographie',
        'Recit de la vie d''une personne reelle'
    ),
    (
        'Histoire',
        'Ouvrages sur des evenements historiques'
    ),
    (
        'Poesie',
        'Genre litteraire utilisant le rythme et les images'
    ),
    (
        'Theâtre',
        'Œuvres destinees à être jouees sur scène'
    ),
    (
        'Essai',
        'Ouvrage de reflexion sur un sujet'
    ),
    (
        'Bande dessinee',
        'Combinaison de texte et d''images'
    );

-- Insertion des collections
INSERT INTO
    collections (nom, description, id_editeur)
VALUES (
        'Folio',
        'Collection de poche',
        1
    ),
    (
        'Blanche',
        'Collection litteraire',
        1
    ),
    (
        'GF',
        'Collection classique',
        2
    ),
    (
        'Policier',
        'Romans policiers',
        3
    ),
    (
        'Science-fiction',
        'Romans de SF',
        4
    ),
    (
        'Points',
        'Collection de poche',
        5
    ),
    (
        'Babel',
        'Collection litteraire',
        6
    ),
    (
        'Poche',
        'Livres au format poche',
        7
    ),
    (
        'Pluriel',
        'Collection d''essais',
        8
    ),
    (
        'Classiques',
        'Œuvres classiques',
        9
    );

-- Insertion des auteurs
INSERT INTO
    auteurs (
        nom,
        prenom,
        date_de_naissance
    )
VALUES (
        'Camus',
        'Albert',
        '1913-11-07'
    ),
    (
        'Hugo',
        'Victor',
        '1802-02-26'
    ),
    (
        'Dumas',
        'Alexandre',
        '1802-07-24'
    ),
    ('Zola', 'emile', '1840-04-02'),
    (
        'Flaubert',
        'Gustave',
        '1821-12-12'
    ),
    (
        'Saint-Exupery',
        'Antoine de',
        '1900-06-29'
    ),
    (
        'Orwell',
        'George',
        '1903-06-25'
    ),
    (
        'Rowling',
        'J.K.',
        '1965-07-31'
    ),
    (
        'Tolkien',
        'J.R.R.',
        '1892-01-03'
    ),
    (
        'Martin',
        'George R.R.',
        '1948-09-20'
    ),
    (
        'Lehane',
        'Dennis',
        '1965-08-04'
    ),
    (
        'King',
        'Stephen',
        '1947-09-21'
    ),
    (
        'Nothomb',
        'Amelie',
        '1967-07-13'
    ),
    (
        'Houellebecq',
        'Michel',
        '1956-02-26'
    ),
    (
        'Le Clezio',
        'J.M.G.',
        '1940-04-13'
    );

-- Insertion des livres
INSERT INTO
    livres (
        titre,
        sous_titre,
        date_publication,
        prix,
        id_collection,
        id_genre,
        id_langue
    )
VALUES (
        'L''etranger',
        '',
        '1942-06-19',
        6.50,
        1,
        1,
        1
    ),
    (
        'Les Miserables',
        '',
        '1862-01-01',
        12.90,
        2,
        1,
        1
    ),
    (
        'Le Comte de Monte-Cristo',
        '',
        '1844-01-01',
        15.20,
        3,
        1,
        1
    ),
    (
        'Germinal',
        '',
        '1885-03-01',
        9.90,
        4,
        1,
        1
    ),
    (
        'Madame Bovary',
        '',
        '1857-01-01',
        8.70,
        5,
        1,
        1
    ),
    (
        'Le Petit Prince',
        '',
        '1943-04-06',
        7.30,
        6,
        1,
        1
    ),
    (
        '1984',
        '',
        '1949-06-08',
        10.50,
        7,
        3,
        2
    ),
    (
        'Harry Potter à l''ecole des sorciers',
        '',
        '1997-06-26',
        14.90,
        8,
        4,
        1
    ),
    (
        'Le Seigneur des Anneaux',
        'La Communaute de l''Anneau',
        '1954-07-29',
        18.00,
        9,
        4,
        1
    ),
    (
        'Le Trône de Fer',
        'Le Donjon rouge',
        '1996-08-01',
        16.50,
        10,
        4,
        1
    ),
    (
        'Shutter Island',
        '',
        '2003-04-15',
        11.20,
        1,
        2,
        1
    ),
    (
        'Ça',
        '',
        '1986-09-15',
        13.75,
        2,
        2,
        1
    ),
    (
        'Stupeur et tremblements',
        '',
        '1999-01-01',
        9.40,
        3,
        1,
        1
    ),
    (
        'Les Particules elementaires',
        '',
        '1998-08-01',
        12.30,
        4,
        1,
        1
    ),
    (
        'Desert',
        '',
        '1980-01-01',
        10.80,
        5,
        1,
        1
    );

-- Association des auteurs aux livres
INSERT INTO
    auteur_livre (id_livre, id_auteur)
VALUES (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);

-- Insertion des rayons
INSERT INTO
    rayons (nom_rayon)
VALUES ('Litterature française'),
    ('Litterature etrangère'),
    ('Policier/Thriller'),
    ('Science-fiction/Fantasy'),
    ('Biographies'),
    ('Histoire'),
    ('Poesie/Theâtre'),
    ('Essais'),
    ('Bandes dessinees'),
    ('Jeunesse');

-- Insertion des etagères
INSERT INTO
    etageres (
        nom_etagere,
        nbr_niveau,
        id_rayon
    )
VALUES ('A-C', 5, 1),
    ('D-F', 5, 1),
    ('G-L', 5, 1),
    ('M-P', 5, 1),
    ('Q-Z', 5, 1),
    ('A-E', 5, 2),
    ('F-K', 5, 2),
    ('L-R', 5, 2),
    ('S-Z', 5, 2),
    ('Policier A-L', 5, 3),
    ('Policier M-Z', 5, 3),
    ('SF A-M', 5, 4),
    ('SF N-Z', 5, 4);

-- Insertion des localisations
INSERT INTO
    localisations (nom_zone, niveau, id_etagere)
VALUES ('Section A', 1, 1),
    ('Section B', 2, 1),
    ('Section C', 3, 1),
    ('Section D', 1, 2),
    ('Section E', 2, 2),
    ('Section F', 3, 2),
    ('Section G', 1, 3),
    ('Section H', 2, 3),
    ('Section I', 3, 3),
    ('Section J', 1, 4),
    ('Section K', 2, 4),
    ('Section L', 3, 4),
    ('Section M', 1, 5),
    ('Section N', 2, 5),
    ('Section O', 3, 5);

-- Insertion des exemplaires
INSERT INTO
    exemplaires (
        date_acquisition,
        id_localisation,
        id_livre
    )
VALUES ('2020-01-15', 1, 1),
    ('2020-01-15', 1, 1),
    ('2019-05-22', 2, 2),
    ('2019-05-22', 2, 2),
    ('2019-05-22', 2, 2),
    ('2018-11-10', 3, 3),
    ('2018-11-10', 3, 3),
    ('2021-02-28', 4, 4),
    ('2021-02-28', 4, 4),
    ('2021-02-28', 4, 4),
    ('2021-02-28', 4, 4),
    ('2017-09-05', 5, 5),
    ('2017-09-05', 5, 5),
    ('2022-03-14', 6, 6),
    ('2022-03-14', 6, 6),
    ('2022-03-14', 6, 6),
    ('2019-07-19', 7, 7),
    ('2019-07-19', 7, 7),
    ('2020-12-01', 8, 8),
    ('2020-12-01', 8, 8),
    ('2020-12-01', 8, 8),
    ('2020-12-01', 8, 8),
    ('2018-04-30', 9, 9),
    ('2018-04-30', 9, 9),
    ('2021-06-15', 10, 10),
    ('2021-06-15', 10, 10),
    ('2021-06-15', 10, 10),
    ('2019-10-08', 11, 11),
    ('2019-10-08', 11, 11),
    ('2020-08-25', 12, 12),
    ('2020-08-25', 12, 12),
    ('2020-08-25', 12, 12),
    ('2021-01-12', 13, 13),
    ('2021-01-12', 13, 13),
    ('2022-02-18', 14, 14),
    ('2022-02-18', 14, 14),
    ('2017-12-05', 15, 15),
    ('2017-12-05', 15, 15);

-- Insertion des types d'abonnement
INSERT INTO
    type_abonnement (
        name,
        description,
        prix,
        surplus_quota,
        priorite
    )
VALUES (
        'Basique',
        'Abonnement standard avec accès aux livres courants',
        20.00,
        0,
        3
    ),
    (
        'Etudiant',
        'Abonnement reduit pour etudiants avec justificatif',
        15.00,
        0,
        3
    ),
    (
        'Premium',
        'Abonnement avec accès aux nouveautes et ouvrages speciaux',
        35.00,
        2,
        1
    ),
    (
        'Famille',
        'Abonnement pour famille (jusqu''à 4 membres)',
        50.00,
        3,
        2
    ),
    (
        'Senior',
        'Abonnement reduit pour les plus de 65 ans',
        12.00,
        0,
        4
    ),
    (
        'Enfant',
        'Abonnement pour les moins de 12 ans',
        10.00,
        0,
        4
    ),
    (
        'Chercheur',
        'Abonnement special avec accès etendu aux ouvrages de reference',
        40.00,
        5,
        1
    ),
    (
        'Corporate',
        'Abonnement pour entreprises (jusqu''à 10 employes)',
        120.00,
        10,
        1
    ),
    (
        'Vacances',
        'Abonnement temporaire de 3 mois',
        25.00,
        0,
        4
    ),
    (
        'Luxe',
        'Abonnement haut de gamme avec services premium',
        75.00,
        5,
        1
    );

INSERT INTO
    type_adherent (nom_type)
VALUES ('Etudiant'),
    ('Enseignant'),
    ('Personnel'),
    ('Public');

INSERT INTO
    type_status (libelle)
VALUES ('En attente'), -- Pour les abonnements/paiements en attente de traitement
    ('Actif'), -- Pour les abonnements actifs
    ('Expire'), -- Pour les abonnements arrives à expiration
    ('Suspendu'), -- Pour les abonnements temporairement suspendus
    ('Annule'), -- Pour les abonnements annules
    ('Paiement accepte'), -- Pour les paiements valides
    ('Paiement refuse'), -- Pour les paiements refuses
    ('Paiement en attente'), -- Pour les paiements en cours de traitement
    ('Prêt en cours'), -- Pour les prêts actifs
    ('Retard'), -- Pour les prêts en retard
    ('Perdu'), -- Pour les livres perdus
    ('Rendu'), -- Pour les prêts retournes
    ('Reserve'), -- Pour les livres reserves
    ('Disponible'), -- Pour les exemplaires disponibles
    ('Indisponible');
-- Pour les exemplaires indisponibles



INSERT INTO users (nom, prenom, email, password, date_naissance, adresse, type_user)
VALUES (
    'Dupont',
    'Jean',
    'admin@biblio.com',
    'admin123',  -- IMPORTANT: Voir la note de sécurité ci-dessous
    '1985-05-20',
    '1 Rue de la Paix, 75001 Paris',
    1  -- 1 pour Administrateur
);




ALTER TABLE quota 
ALTER COLUMN duree_max TYPE INTEGER 
USING 0;



-- 1. Quota pour les "Étudiants"
-- Règle : 5 livres pour 21 jours (3 semaines)
INSERT INTO quota (id_type_adh, nbr_max, duree_max) 
VALUES (
    (SELECT id_type_adh FROM type_adherent WHERE nom_type = 'Etudiant'), 
    5, 
    21
);

-- 2. Quota pour les "Enseignants"
-- Règle : 15 livres pour 60 jours (2 mois)
INSERT INTO quota (id_type_adh, nbr_max, duree_max) 
VALUES (
    (SELECT id_type_adh FROM type_adherent WHERE nom_type = 'Enseignant'), 
    15, 
    60
);

-- 3. Quota pour le "Personnel"
-- Règle : 10 livres pour 30 jours (1 mois)
INSERT INTO quota (id_type_adh, nbr_max, duree_max) 
VALUES (
    (SELECT id_type_adh FROM type_adherent WHERE nom_type = 'Personnel'), 
    10, 
    30
);

-- 4. Quota pour le "Public"
-- Règle : 3 livres pour 14 jours (2 semaines)
INSERT INTO quota (id_type_adh, nbr_max, duree_max) 
VALUES (
    (SELECT id_type_adh FROM type_adherent WHERE nom_type = 'Public'), 
    3, 
    14
);