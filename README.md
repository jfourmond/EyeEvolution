# Eye Evolution

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