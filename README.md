# Flowable Demo – Processus de demande de rente (CdC)

Ce projet démontre l'utilisation de **Flowable** avec **Spring Boot** pour modéliser, déployer et exécuter des processus
BPMN. Il fournit une API REST pour démarrer des processus et gérer des tâches humaines.

## 🚀 Fonctionnalités

- Déploiement automatique des fichiers BPMN `demandeRenteProcess`
- Exposition d'API REST pour démarrer des processus et lister les tâches
- Exécution de tâches de service Java (`JavaDelegate`)
- Validation manuelle via tâche utilisateur (prête pour UI)
- Base de données H2 en mémoire
- Structure claire pour l'ajout de processus DMN/decision
- Exemple simple d'un processus

## 🧪 Lancer les tests

```bash
./mvnw test
```

## 🖥️ Lancer l'application

```bash
./mvnw spring-boot:run
```

Puis démarrer un processus :

```bash
curl -X POST "http://localhost:8888/process/start?demandeId=CDCG-123456"
```

## 📊 Interfaces disponibles

- H2 Console : [http://localhost:8888/h2-console](http://localhost:8888/h2-console)
- (UI Flowable en option, à venir)

## ✅ Badges

![Java CI](https://github.com/ton-utilisateur/flowable-demo/actions/workflows/maven.yml/badge.svg)

## 📂 Structure du projet

```bash
lowabledemo/
├── src/
│   ├── main/
│   │   ├── java/com/guberan/flowable/demo/
│   │   │   ├── controller/
│   │   │   │   └── ProcessController.java      # Endpoint REST pour lister les tâches et démarrer un processus
│   │   │   ├── delegate/
│   │   │   │   ├── EligibilityCheckDelegate.java    # Delegate Java appelé dans le BPMN (service task)
│   │   │   │   └── PayBenefitDelegate.java
│   │   │   ├── model/
│   │   │   │   └── Person.java                 # Exemple de variable utilisée dans le processus
│   │   │   └── FlowableDemoApplication.java    # Classe principale Spring Boot
│   │   └── resources/
│   │       ├── application.yml                 # Configuration Spring Boot
│   │       └── processes/
│   │           └── odemande_de_rente.bpmn20.xml # Définition BPMN déployée automatiquement
│
├── pom.xml                                     # Dépendances Maven
├── .gitignore                                  # Fichiers exclus de Git
├── README.md                                   # Documentation du projet
└── docker-compose.yml                          # (optionnel)
```

---