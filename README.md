# 📚 Merry Bookstore

Merry Bookstore est une application mobile Android développée avec **Kotlin** et **Jetpack Compose**. Elle permet de consulter un catalogue de livres, voir les détails de chaque livre, gérer un panier, s'inscrire/se connecter et suivre ses commandes.

## 🧩 Fonctionnalités principales

- 📖 Affichage du catalogue de livres
- 🔍 Vue détaillée avec titre, auteur, type, description, stock
- 🛒 Gestion de panier
- 🧑‍💻 Authentification des utilisateurs (login/inscription)
- 🗂️ Sauvegarde des données via Room
-  💳 Paiement par carte simulé (démo)
- 🔄 Stock mis à jour automatiquement après validation du paiement
- 🔌 Récupération des données depuis une API Express locale
- 🌐 Internationalisation (prévue)
- 🎨 Thème esthétique : *Dark Academia*

## 🛠️ Technologies utilisées

- Kotlin & Jetpack Compose
- Hilt (injection de dépendances)
- Retrofit (appels API REST)
- Room (base de données locale)
- Express.js (API locale avec fichier `books.json`)
- Material3 (Design moderne)

## 🚀 Lancer le projet

### Backend (API Express)
```bash
npm install -g nodemon
nodemon server.js
## 📷 Captures d’écran de l'application

Les captures d’écran prises lors des tests de l’application se trouves dans depot server dans public /captures
## Structure du projet

Le code source est organisé en deux dépôts Git distants distincts :

- **Backend**  
  Le backend (API serveur) se trouve dans le dépôt :  
  [https://github.com/MERIEM9994/my-kotline-server-api.git](https://github.com/MERIEM9994/my-kotline-server-api.git)  
  Branche utilisée : `master`

- **Frontend**  
  Le frontend (application Android Kotlin) est hébergé dans le dépôt :  
  [https://github.com/MERIEM9994/TEST-KOTLINE-BIBLIO.git](https://github.com/MERIEM9994/TEST-KOTLINE-BIBLIO.git)  
  Branche utilisée : `main`



