# Eye Evolution

## Avancement

- [x] Programmation de l'algorithme génétique
- [x] Tester l'exécution
- [ ] Choisir la taille de la population, le taux de cross-over et le taux de mutation
- [ ] Exécuter à plusieurs reprises l'algorithme avec des graines différents pour le générateur aléatoire
- [ ] Sauvegarder l'évolution des paramètres, des grandeurs et de la fitness en fonction de la génération, pour la moyenne de la population et pour le meilleur individu
- [ ] Visualiser
- [ ] Rediger le rapport

## Objectif

L'objectif est de programmer un algorithme génétique permettant de faire évoluer une population d'"individus", où chaque "individu" code les paramètres d'un oeil, d'après la modélisation suivante :

![Modélisation](/Rapport/modelisation.png)

## Constantes

- Tout au long de l'évolution, la largeur maximale de l'oeil (distance AB) reste égale à **W = 1.5** cm,
- L'intensité lumineuse **I** sera prise égale à **e**<sup>6</sup>.

## Paramètres à faire évoluer
- Rayon de courbure **&rho;**<sub>c</sub> : peut varier dans la plage **[W/2, 10000]**
- Taille de l'iris **i** : peut varier dans la plage **[0, W/2[**
- Angle **&Phi;**<sub>1</sub> : peut varier dans la plage **[0, &pi;/2[**
- Indice de réfraction **n**<sub>0</sub> au centre de la lentille : peut varier dans la plage **[1.35, 1.55]**

## Valeurs initiales

- Rayon de courbure initial **&rho;**<sub>c</sub>** = 10000**
- Taille initiale de l'iris **i = 0**
- Angle **&Phi;**<sub>1</sub> initial : **&Phi;**<sub>1</sub>** = 0**
- Indice initial de réfraction au centre de la lentille **n**<sub>0</sub>** = 1.35**

## Algorithme

L'algorithme s'exécute jusqu'à ce que le nombre de générations limite soit atteint.

### Paramètres

- Taille de la population :
- Nombre de générations :
- Taux de cross-over :
- Taux de mutation : 

### Processus de sélection

1. Tri de la population en fonction de leur valeur de fitness
2. Récupération des probabilités de reproduction
3. Création du "camembert" en fonction de la probabilité de reproduction
4. Choix aléatoire de deux parents en fonction du camembert

### Processus de reproduction

L'enfant détiendra les caractéristiques aléatoires en fonction des caractéristiques des parents et du taux de cross-over.

### Processus de mutation

Les enfants produits muteront en fonction du taux de mutation. Une seule caractéristique de l'enfant sera modifié (addition) par un double gaussien aléatoire.

### Processus de remplacement

Les étapes de sélection, de reproduction et de remplacement sont effectuées jusqu'à ce que la population soit entièrement renouvelée.