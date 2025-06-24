# Flowable Demo – Processus de demande de rente (CdC)

Ce projet montre comment modéliser et exécuter un processus BPMN avec Flowable dans un contexte inspiré de la **Centrale
de compensation CdC (Genève)**.

## 🚀 Fonctionnalités

- Processus BPMN `demandeRenteProcess`
- API REST pour démarrer une demande
- Exécution de tâches de service Java (`JavaDelegate`)
- Validation manuelle via tâche utilisateur (prête pour UI)
- Base de données H2 en mémoire

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

- H2 Console : [http://localhost:8888/h2-console](http://localhost:8080/h2-console)
- (UI Flowable en option, à venir)

## ✅ Badges

![Java CI](https://github.com/ton-utilisateur/flowable-demo/actions/workflows/maven.yml/badge.svg)

## 📂 Structure du projet

```bash
src/
├── main/java/com/guberan/flowable/demo
│   ├── FlowableDemoApplication.java
│   ├── controller/ProcessController.java
│   ├── delegate/VerifyRequestDelegate.java
│   └── delegate/PayBenefitDelegate.java
├── resources/
│   ├── processes/demande_de_rente.bpmn20.xml
│   └── application.yml
└── test/java/com/example/flowabledemo
    └── FlowableProcessTest.java
```

---