# Projet de Gestion de Tâches Collaboratif

## 📜 Contexte du Projet
Ce projet vise à développer une application web collaborative de gestion de projets et de tâches, basée sur une application console existante. L'objectif est de créer une solution plus complète, intégrant des fonctionnalités de gestion de projets, de tâches, et d'équipes, tout en utilisant Java et une base de données relationnelle.

## 🎯 Objectifs
- Créer une application web Java avec Servlets, JSP, et JSTL.
- Implémenter les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) pour les projets, tâches, membres et équipes.
- Adapter la base de données existante pour l'intégration dans une application web.
- Développer une interface utilisateur intuitive et responsive avec Bootstrap.
- Appliquer des principes de gestion de projet agile tout au long du développement.
- Utiliser des concepts Java avancés et les bonnes pratiques de développement.

## 📄 Pages Principales
### 1. Page de gestion des projets
- ✅ Liste des projets avec pagination.
- ➕ Création, modification et suppression de projets.
- 🔍 Recherche de projets.
- 📊 Affichage des statistiques basiques du projet (nombre de tâches, membres).

### Page de Gestion des Tâches
- ✅ Liste des tâches par projet avec pagination.
- ➕ Ajout, modification et suppression de tâches.
- 👥 Assignation de tâches aux membres du projet.
- 📈 Suivi de l'avancement des tâches (mise à jour du statut de la tâche : "À faire", "En cours", "Terminée").

### Page de Gestion des Équipes
- ✅ Liste des membres de l'équipe avec pagination.
- ➕ Ajout, modification et suppression d'équipe.
- ➕ Ajout, modification et suppression de membres.
- 📋 Visualisation des tâches assignées par membre.

## 🏗️ Structure des Classes Principales
- **Projet** (nom, description, dateDebut, dateFin, statut : Enum (en préparation, en cours, en pause, terminé, annulé)).
- **Tâche** (titre, description, priorite : Enum (Basse, Moyenne, Haute), statut : Enum (À faire, En cours, Terminée), dateCreation, dateEcheance).
- **Membre** (nom, prenom, email, role : Enum (Chef de projet, Développeur, Designer)).
- **Equipe** (nom).


## ⚙️ Exigences Techniques
![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=java&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005E6C?style=flat-square&logo=mysql&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat-square&logo=bootstrap&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Singleton](https://img.shields.io/badge/Singleton-000000?style=flat-square&logo=github&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-005E6C?style=flat-square&logo=mysql&logoColor=white)
![Servlets](https://img.shields.io/badge/Servlets-000000?style=flat-square&logo=java&logoColor=white)

- Développer avec **Java 8**.
- Travailler avec **Eclipse**.
- Utiliser **Servlet**, **JSP** et **JSTL**.
- Utiliser **Maven** pour la gestion des dépendances.
- Utiliser **Tomcat** comme serveur d'application.
- Créer des tests unitaires avec **JUnit**.
- Utiliser **Bootstrap** pour le design et **FIGMA** pour le maquettage.
- Utiliser le fichier **web.xml** pour la configuration de l'application web. L'utilisation d'annotations pour le mapping URL (comme `@WebServlet`) est interdite dans ce projet.
- Implémenter une architecture **MVC** en couches : présentation, métier, contrôleur, service, DAO, repository, utilitaires.
- Implémenter les design patterns **Repository** et **Singleton**.
- Déployer le fichier **WAR** avec Tomcat sans utiliser Eclipse.
- Ajouter un système de **logging** (**LOGGER**).
- Dans le script SQL, utiliser des **index**, **GRANT**, **UNIQUE**, et **NOT NULL**.
- Adapter la base de données **MySQL** existante.
- Utiliser le pilote **JDBC** pour l'accès aux données.
- Gérer le projet avec **JIRA** en utilisant la méthode **Scrum**.
- Lier correctement **JIRA** avec **Git**.
- Utiliser **Git** avec des branches.
- Mettre en place les validations nécessaires.
- Implémenter la **pagination**.

## 📚 Concepts Java Avancés à Utiliser
- **Java Time API** et **Collection API**.
- **Hashmaps**.
- **Lambda Expressions**.
- **Java Stream API**.

## 📦 Installation et Lancement du Projet
### Prérequis
- **JDK 8+** : Assurez-vous que Java 8 est installé sur votre machine.
- **Maven** : Maven doit être configuré pour gérer les dépendances du projet.
- **Tomcat** : Téléchargez et configurez Apache Tomcat (version 9 ou supérieure).
- **MySQL** : Base de données MySQL pour stocker les projets, tâches, membres, et équipes.

#### Configurer la Base de Données
- Créez une base de données MySQL nommée `taskManager`.
- Importez le fichier SQL situé dans le dossier `resources/db/schema.sql` pour créer les tables et insérer les données initiales.

   1. Cloner le dépôt :
   ```bash git clone https://github.com/Hafsa-Naoufal-Abdelhamid/Gestion_de_Projet_Tache_Equipe ```bash
   
   2. Naviguer vers le dossier du projet :
   ```bash cd votre-projet ```bash
   
   3. Installer les dépendances avec Maven :
   ```bash mvn install ```bash
   

## Collaborateurs
 - Hafsa Elmoatassim billah -> Scrum Master / Backend & Frontend Developer 
 - Abdelhamid lamtioui -> Backend & Frontend Developer 
 - Naoufal Lebrihmi


