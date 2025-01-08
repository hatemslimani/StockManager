# StockManager - Application de Gestion de Stock

## 📋 Description
StockManager est une application web de gestion de stock développée avec Angular et Spring Boot. Elle permet de gérer efficacement les inventaires, les commandes, les clients et les fournisseurs à travers une interface moderne et intuitive.

## 🖼️ Démonstration

### Interface de connexion
![Login Page](/screenshots/login.png)
*Page de connexion*

### Dashboard principal
![Dashboard](/screenshots/dashboard.png)
*Dashboard avec les statistiques et graphiques*

### Gestion des Utilisateurs
![Users Management](/screenshots/users.png)
*Interface d'administration des utilisateurs et des rôles*

![Update User](/screenshots/update_user.png)
*Modification des informations utilisateur*

### Gestion des Catégories
![Categories Management](/screenshots/categories.png)
*Organisation hiérarchique des catégories de produits*

![Add Category](/screenshots/add_category.png)
*Ajout d'une nouvelle catégorie*

![Update Category](/screenshots/update_category.png)
*Modification d'une catégorie existante*

![Delete Category](/screenshots/delete_category.png)
*Confirmation de suppression d'une catégorie*

### Gestion des Produits
![Products Management](/screenshots/products.png)
*Interface de gestion des produits avec filtres et recherche*

![Add Product](/screenshots/add_product.png)
*Formulaire d'ajout de nouveau produit*

### Gestion des Fournisseurs
![Suppliers Management](/screenshots/suppliers.png)
*Liste des fournisseurs avec leurs informations détaillées*

![Add Supplier](/screenshots/add_supplier.png)
*Ajout d'un nouveau fournisseur*

![Update Supplier](/screenshots/update_supplier.png)
*Modification des informations fournisseur*

### Gestion des Clients
![Clients Management](/screenshots/clients.png)
*Interface de gestion de la clientèle*

![Add Client](/screenshots/add_client.png)
*Ajout d'un nouveau client*

![Update Client](/screenshots/update_client.png)
*Modification des informations client*

### Gestion des Commandes
![Sales Orders](/screenshots/purchase_orders.png)
*Liste des commandes fournisseurs avec statut*

![Sales Orders](/screenshots/sales_orders.png)
*liste des commandes clients et factures*

![Add Sales Order](/screenshots/add_ordre_sale.png)
*Création d'une nouvelle commande de vente*

![Change Sales Order](/screenshots/change_odre_sale.png)
*Modification d'une commande de vente*

![Update Client Orders](/screenshots/update_client_oders.png)
*Mise à jour des commandes client*

![Client Order Details](/screenshots/detalis_client_ordre.png)
*Détails des commandes client*

### Bon de commande
![Purchase Order](/screenshots/purchase_order.png)
*Exemple de bon de commande généré avec JasperReport*

## 🚀 Technologies Utilisées
### Frontend
- Angular 13.2.4
- Bootstrap 4.6
- Font Awesome 6.0.0
- CSS3

### Backend
- Spring Boot
- Java 17
- JasperReport (pour la génération des documents PDF)
- Spring Security (pour l'authentification)

## ✨ Fonctionnalités Principales

### 🔐 Authentification et Sécurité
- Interface de connexion sécurisée
- Gestion des sessions utilisateurs
- Différents niveaux d'accès selon les rôles

### 👥 Gestion des Utilisateurs
- Création et modification des comptes utilisateurs
- Attribution des rôles et permissions
- Réinitialisation des mots de passe

### 🏢 Gestion des Fournisseurs
- Ajout et modification des informations fournisseurs
- Historique des transactions
- Suivi des commandes par fournisseur

### 👨‍👩‍👧‍👦 Gestion des Clients
- Base de données clients
- Historique des achats

### 📦 Gestion des Catégories et Produits
- Organisation hiérarchique des catégories
- Fiche détaillée des produits

### 📥 Gestion des Commandes Achats
- Création de bons de commande
- Suivi des livraisons
- Génération automatique des documents PDF avec JasperReport
- Historique des commandes

### 📤 Gestion des Commandes Ventes
- Création de devis et factures
- Suivi des expéditions
- État des Commandes

## 🛠️ Prérequis
- Node.js (v16 ou supérieure)
- Java JDK 1.8
- Maven
- MySQL/PostgreSQL

## ⚙️ Installation

### Backend
```bash
# Cloner le repository
git clone https://github.com/hatemslimani/StockManager.git

# Accéder au dossier backend
cd StockManager/backend

# Installer les dépendances avec Maven
mvn clean install

# Lancer l'application
mvn spring-boot:run
```

### Frontend
```bash
# Accéder au dossier frontend
cd StockManager/frontend

# Installer les dépendances
npm install

# Lancer l'application
ng serve
```

## 🔧 Configuration
1. Configurer la base de données dans `application.properties`