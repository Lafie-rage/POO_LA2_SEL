# Compte rendu de TP de POO4 : Persistence de données

## Introduction

L'objectif de ce document est de détailler les actions réalisées lors de la réalisation du TP de POO4 sur la persistence des données.

Le sujet est disponible sous le nom "**2021-2022 - LA2 - POO - Persistance de données - Support de TP.pdf**".

## Question 1

Le code réalisé pour les entitées se trouve dans le package **data.model** du module **main**. Chaque table est associé à une classe portant le **même nom, traduit en anglais**, avec le suffix "**DbEntity**".

## Question 2

Pour la partie de test, j'ai souhaité respecter une architecture DAO/repository pour la récupération des données.
Chaque entité est récupérée en BDD à l'aide du DAO qui ne fait rien de plus que récupérer les données brutes.
Ensuite, le repository associé récupérera les données, par le biais du DAO, en ajoutant de la logique métier dessus.

Lors des tests ou affichages, il est donc primordial qu'on passe par des appels aux repository pour récupérer les données.

Les cas de tests se trouvent dans le package **data** du module **test.java** et portent le **nom de la table traduit en anglais** et le prefix **Test**.

## Question 3

Pour créer un héritage entre des entités depuis l'entités membres, j'ai créer les classes définits dans le packaqge **data.model.member** et passer la classe **MemberDbEntity** en abstraite.
J'ai également dû mettre à jour les cas de tests puisque je ne pouvais plus tuiliser la classe MemberDbEntity directement lors de l'instation d'objets.

## Question 4

## Contributors
- Corentin Destrez ([Lafie-rage](https://github.com/Lafie-rage))

