package metiers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import compare.FitnessCompare;
import random.RandomGen;

public class Population {
	private static final double c = 0.5;
	
	private List<Eye> eyes;
	
	//	CONSTRUCTEURS
	public Population() {
		eyes = new ArrayList<>();
	}
	
	public Population(int size) {
		eyes = new ArrayList<>();
		for(int i=0 ; i<size ; i++)
			eyes.add(new Eye());
	}
	
	//	GETTERS
	public List<Eye> getEyes() { return eyes; }
	
	//	SETTERS
	public void setEyes(List<Eye> population) { this.eyes = population; }
	
	//	METHODES
	/**
	 * Ajoute une individu à la population
	 * @param eye : individu
	 */
	public void add(Eye eye) {
		eyes.add(eye);
	}
	
	/**
	 * Ajoute un couple d'individu à la population
	 * @param couple : couple d'individu
	 */
	public void add(Couple couple) {
		eyes.add(couple.getIndividual1());
		eyes.add(couple.getIndividual2());
	}
	
	/**
	 * Retourne le nombre d'individu
	 * @return le nombre d'individu
	 */
	public int size() { return eyes.size(); }
	
	/**
	 * Calcule la moyenne du rayon de courbure de la population
	 * @return la moyenne du rayon de courbure de la population
	 */
	public double averageCurveRadius() {
		double sumCurveRadius = 0;
		for(Eye eye : eyes)
			sumCurveRadius += eye.getCurveRadius();
		return sumCurveRadius / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de la taille de l'iris de la population
	 * @return la moyenne de la taille de l'iris de la population
	 */
	public double averageIrisSize() {
		double sumIrisSize = 0;
		for(Eye eye : eyes)
			sumIrisSize += eye.getIrisSize();
		return sumIrisSize / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de l'angle de la population
	 * @return la moyenne de l'angle de la population
	 */
	public double averageAngle() {
		double sumAngle = 0;
		for(Eye eye : eyes)
			sumAngle += eye.getAngle();
		return sumAngle / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de l'indice de réfraction de la population
	 * @return la moyenne de l'indice de réfraction de la population
	 */
	public double averageRefractionIndex() {
		double sumRefractionIndex = 0;
		for(Eye eye : eyes)
			sumRefractionIndex += eye.getRefractionIndex();
		return sumRefractionIndex / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de la profondeur de la population
	 * @return la moyenne de la profondeur de la population
	 */
	public double averageDepth() {
		double sumDepth = 0;
		for(Eye eye : eyes)
			sumDepth += eye.getDepth();
		return sumDepth / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de l'ouverture de la population
	 * @return la moyenne de l'ouverture de la population
	 */
	public double averageAperture() {
		double sumAperture = 0;
		for(Eye eye : eyes)
			sumAperture += eye.getAperture();
		return sumAperture / eyes.size();
	}
	
	/**
	 * Calcule la moyenne du ratio de la population
	 * @return la moyenne du ratio de la population
	 */
	public double averageRatio() {
		double sumRatio = 0;
		for(Eye eye : eyes)
			sumRatio += eye.getRatio();
		return sumRatio / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de l'angle de vue de la population
	 * @return la moyenne de l'angle de vue de la population
	 */
	public double averageSightAngle() {
		double sumSightAngle = 0;
		for(Eye eye : eyes)
			sumSightAngle += eye.getSightAngle();
		return sumSightAngle / eyes.size();
	}
	
	/**
	 * Calcule la moyenne de la fonction de fitness de la population
	 * @return la moyenne de la fonction de fitness de la population
	 */
	public double averageFitness() {
		double sumFitness = 0;
		for(Eye eye : eyes)
			sumFitness += eye.getFitness();
		return sumFitness / eyes.size();
	}
	
	/**
	 * Retourne le meilleur individu de la population
	 * @return le meilleur individu de la population
	 */
	public Eye bestEye() {
		Eye bestEye = eyes.get(0);
		double fitness = bestEye.getFitness();
		double f;
		Eye eye;
		for(int i=1 ; i<eyes.size() ; i++) {
			eye = eyes.get(i);
			f = eye.getFitness();
			if(f > fitness) {
				bestEye = eye;
				fitness = f;
			}
		}
		return bestEye;
	}
	
	/**
	 * Selection de deux parents
	 */
	public Couple selection() {
		Eye parent1 = null;
		Eye parent2 = null;
		Collections.sort(eyes, new FitnessCompare());
		// Récupération des probabilités de reproduction
		double percentage[] = new double[eyes.size()];
		for(int i=0 ; i<eyes.size() ; i++) {
			percentage[i] = (eyes.get(i).reproductionProbability(i, eyes.size(), c) * 100) / c;
		}
		int chose[] = new int[100];
		// Création du "camembert"
		int k=0;
		for(int i=0; i<eyes.size() ; i++) {
			long p = Math.round(percentage[i]);
			for(int j=0 ; j<p ; j++) {
				chose[k] = i;
				k++;
			}
		}
		// Sélection
		// Choix du parent 1
		parent1 = eyes.get(chose[RandomGen.nextInt(100)]);
		parent2 = eyes.get(chose[RandomGen.nextInt(100)]);
		while(parent2 == parent1)
			parent2 = eyes.get(chose[RandomGen.nextInt(100)]);
		return new Couple(parent1, parent2);
	}
	
	/**
	 * Retourne deux enfants produits à partir de deux parents
	 * @param couple : Parents
	 * @param crossoverRate : taux de cross-over
	 * @return deux enfants produits à partir de deux parents
	 */
	public Couple reproduction(Couple couple, double crossoverRate) {
		Eye child1 = new Eye(Eye.caracteristic(couple.getIndividual1(), couple.getIndividual2(), crossoverRate));
		Eye chidl2 = new Eye(Eye.caracteristic(couple.getIndividual1(), couple.getIndividual2(), crossoverRate));
		return new Couple(child1, chidl2);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("POPULATION : [\n");
		for(Eye e : eyes) {
			sb.append("\t" + e + "\n");
		}
		sb.append("]");
		return sb.toString();
	}
}
