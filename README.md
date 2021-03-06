# Eye Evolution

## Contributeurs

[<img alt="FOURMOND Jérôme" src="https://avatars2.githubusercontent.com/u/15089371" width="100">](https://github.com/jfourmond) | [<img alt="LEROY Stanislas" src="https://avatars3.githubusercontent.com/u/1542829" width="100">](https://github.com/stanislasleroy) |
------------------------|---------------------------|
[@jfourmond](https://github.com/jfourmond	) | [@stanislasleroy](https://github.com/stanislasleroy)

## Avancement

- [x] Programmation de l'algorithme génétique
- [x] Tester l'exécution
- [x] Choisir la taille de la population, le taux de cross-over et le taux de mutation
- [x] Exécuter à plusieurs reprises l'algorithme avec des graines différents pour le générateur aléatoire
- [x] Sauvegarder l'évolution des paramètres, des grandeurs et de la fitness en fonction de la génération, pour la moyenne de la population et pour le meilleur individu
- [x] Visualiser
- [x] Rediger le rapport

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

- Rayon de courbure initial **&rho;**<sub>c</sub> **= 10000**
- Taille initiale de l'iris **i = 0**
- Angle **&Phi;**<sub>1</sub> initial : **&Phi;**<sub>1</sub> **= 0**
- Indice initial de réfraction au centre de la lentille **n**<sub>0</sub> **= 1.35**

## Algorithme

L'algorithme s'exécute jusqu'à ce que le nombre de générations limite soit atteint.

### Paramètres

- Taille de la population : **40**
- Nombre de générations : **50 000**
- Taux de cross-over : **0.5**
- Taux de mutation : **0.2**
- Graines du générateur aléatoire : **[ 1487422477451, 1487424485848, 1487424573992]**

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

## Execution du programme

Le programme nécessite le répertoire *resources* contenant le fichier *indice_refraction.dat* pour fonctionner.
4 arguments sont à spécifier au programme : 
- la taille de la population
- le nombre de générations
- le taux de cross-over (en pourcentage)
- le taux de mutation (en pourcentage)

Un cinquième argument est optionnel : la graine du générateur aléatoire.

```
	java -jar EyeEvolution.jar [population-size] [generations] [crossover-rate] [mutation-rate] ([seed])
```	

Dans le cas présent, avec les paramètres choisis, l'appel au programme via l'archive Java se fait de la façon suivante (par exemple, avec la première graine du générateur aléatoire) : 

```
	java -jar EyeEvolution.jar 40 50000 50 20 1487422477451
```
	
A l'exécution, le programme produit un fichier au format *csv* contenant les détails suivants pour chaque génération :
- numéro de la generation
- taille de la population
- taux de cross-over
- taux de mutation
- graine du générateur aléatoire
- rayon de courbure moyen
- taille de l'iris moyen
- angle moyen
- indice de réfraction moyen
- fitness moyen
- rayon de courbure du meilleur oeil
- taille de l'iris du meilleur oeil
- angle du meilleur oeil
- indice de réfraction du meilleur oeil
- fitness du meilleur oeil

## Visualisations de l'évolution

### Evolution du rayon de courbure du meilleur oeil en fonction de la génération

![BestEyeCurveRadius](/Rapport/best_eye_curve_radius.jpeg)

### Evolution de la taille de l'iris du meilleur oeil en fonction de la génération

![BestEyeIrisSize](/Rapport/best_eye_iris_size.jpeg)

### Evolution de l'angle du meilleur oeil en fonction de la génération

![BestEyeAngle](/Rapport/best_eye_angle.jpeg)

### Evolution de l'indice de réfraction du meilleur oeil en fonction de la génération

![BestEyeRefractionIndex](/Rapport/best_eye_refraction_index.jpeg)

### Evolution de la profondeur du meilleur oeil en fonction de la génération

![BestEyeDepth](/Rapport/best_eye_depth.jpeg)

### Evolution de l'ouverture du meilleur oeil en fonction de la génération

![BestEyeAperture](/Rapport/best_eye_aperture.jpeg)

### Evolution de l'angle de vue du meilleur oeil en fonction de la génération

![BestEyeSightAngle](/Rapport/best_eye_sight_angle.jpeg)

### Evolution de la fitness du meilleur oeil en fonction de la génération

![BestEyeFitness](/Rapport/best_eye_fitness.jpeg)
