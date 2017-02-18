package algorithm;
import java.io.IOException;

import metiers.Couple;
import metiers.Eye;
import metiers.Population;
import random.RandomGen;
import writer_and_reader.CSVWriter;

public class GeneticAlgorithm {
	private CSVWriter csv;
	
	private Population population;
	
	private int size;
	private int nbGenerations;
	private double crossoverRate;
	private double mutationRate;
	private long seed;
	
	private Eye bestEye;
	
	//	CONSTRUCTEURS
	public GeneticAlgorithm(Population population, int nbGenerations, double crossoverRate, double mutationRate, long seed) {
		this.population = population;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.nbGenerations = nbGenerations;
		this.size = population.size();
		this.bestEye = population.bestEye();
		this.seed = seed;
		
		RandomGen.setSeed(this.seed);
		
		try {
			this.csv = new CSVWriter(this.seed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//	GETTERS
	public double getCrossoverRate() { return crossoverRate; }
	
	public double getMutationRate() { return mutationRate; }
	
	public Population getPopulation() { return population; }
	
	public int getSize() { return size; }
	
	public Eye getBestEye() { return bestEye; }
	
	public long getSeed() { return seed; }
	
	public CSVWriter getCsv() { return csv; }
	
	//	SETTERS
	public void setCrossoverRate(double crossoverRate) { this.crossoverRate = crossoverRate; }
	
	public void setMutationRate(double mutationRate) { this.mutationRate = mutationRate; }
	
	public void setPopulation(Population population) { this.population = population; }
	
	public void setSize(int size) { this.size = size; }
	
	public void setBestEye(Eye bestEye) { this.bestEye = bestEye; }
	
	public void setSeed(long seed) {
		this.seed = seed;
		RandomGen.setSeed(this.seed);
	}
	
	public void setCsv(CSVWriter csv) { this.csv = csv; }
	
	//	METHODES
	/**
	 * Exécute l'algorithme génétique
	 * Edite le meilleur oeil obtenu
	 */
	public void run() {
		int nbGenerations = 0;
		Eye bestEye = null;
		while(nbGenerations < this.nbGenerations && this.population.size() > 0) {
			bestEye = population.bestEye();
			if(bestEye.getFitness() > this.bestEye.getFitness()) this.bestEye = bestEye;
			try {
				csv.writeRow(nbGenerations, size, crossoverRate, mutationRate, seed,
						this.population.averageCurveRadius(), this.population.averageIrisSize(), this.population.averageAngle(), this.population.averageRefractionIndex(), this.population.averageDepth(), this.population.averageAperture(), this.population.averageRatio(), this.population.averageSightAngle(),this.population.averageFitness(),
						bestEye.getCurveRadius(), bestEye.getIrisSize(), bestEye.getAngle(), bestEye.getRefractionIndex(), bestEye.getDepth(), bestEye.getAperture(), bestEye.getRatio(), bestEye.getSightAngle(), bestEye.getFitness());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Population population = new Population();
			Couple parents = null;
			Couple children = null;
			int nbChildren = 0;
			while(nbChildren < size) {
				//	SELECTION
				parents = this.population.selection();
				//	REPRODUCTION
				children = this.population.reproduction(parents, crossoverRate);
				//	MUTATION
				if(RandomGen.nextDouble() <= mutationRate) {
					children.getIndividual1().mutate();
					children.getIndividual2().mutate();
				}
				population.add(children);
				nbChildren += 2;
			}
			//	REMPLACEMENT
			setPopulation(population);
			nbGenerations++;
		}
		try {
			csv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
