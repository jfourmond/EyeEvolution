package algorithm;
import java.io.IOException;

import metiers.Couple;
import metiers.Eye;
import metiers.Population;
import random.RandomApp;
import writer_and_reader.CSVWriter;

public class GeneticAlgorithm {
	private static final int NB_GENERATIONS = 10;
	
	private CSVWriter csv;
	
	private Population population;
	
	private int size;
	private double crossoverRate;
	private double mutationRate;
	
	//	CONSTRUCTEURS
	public GeneticAlgorithm(Population population, double crossoverRate, double mutationRate) {
		this.population = population;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		
		size = population.size();
		
		try {
			this.csv = new CSVWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//	GETTERS
	public double getCrossoverRate() { return crossoverRate; }
	
	public double getMutationRate() { return mutationRate; }
	
	public Population getPopulation() { return population; }
	
	public int getSize() { return size; }
	
	public CSVWriter getCsv() { return csv; }
	
	//	SETTERS
	public void setCrossoverRate(double crossoverRate) { this.crossoverRate = crossoverRate; }
	
	public void setMutationRate(double mutationRate) { this.mutationRate = mutationRate; }
	
	public void setPopulation(Population population) { this.population = population; }
	
	public void setSize(int size) { this.size = size; }
	
	public void setCsv(CSVWriter csv) { this.csv = csv; }
	
	//	METHODES
	public void run() {
		int nbGenerations = 0;
		Eye bestEye = null;
		while(nbGenerations < NB_GENERATIONS && this.population.size() > 0) {
			bestEye = population.bestEye();
			try {
				csv.writeRow(nbGenerations, crossoverRate, mutationRate,
						this.population.averageCurveRadius(), this.population.averageIrisSize(), this.population.averageAngle(), this.population.averageRefractionIndex(), this.population.averageFitness(),
						bestEye.getCurveRadius(), bestEye.getIrisSize(), bestEye.getAngle(), bestEye.getRefractionIndex(), bestEye.getFitness());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Population population = new Population();
			Couple parents = null;
			Couple children = null;
			int nbChildren = 0;
			int nbMutation = 0;
			while(nbChildren < size) {
				//	SELECTION
				parents = this.population.selection();
				//	REPRODUCTION
				children = this.population.reproduction(parents, crossoverRate);
				//	MUTATION
				if(RandomApp.nextDouble() <= mutationRate) {
					nbMutation++;
					children.getIndividual1().mutate();
					children.getIndividual2().mutate();
				}
				population.add(children);
				nbChildren += 2;
			}
			//	REMPLACEMENT
			setPopulation(population);
			nbGenerations++;
			
			System.out.println("Génération n° : " + nbGenerations);
			System.out.println("\tNOMBRE DE MUTATION : " + nbMutation);
		}
		try {
			csv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
