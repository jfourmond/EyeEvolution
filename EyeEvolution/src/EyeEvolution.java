import algorithm.GeneticAlgorithm;
import metiers.Population;

public class EyeEvolution {
	private static int POPULATION_SIZE = 40;
	private static int NB_GENERATIONS = 1000;
	private static double CROSSOVER_RATE = 0.01;
	private static double MUTATION_RATE = 0.01;
	
	public static void main(String[] args) {
		if(args.length != 4)
			throw new IllegalArgumentException("4 arguments nécessaires");
		
		// La taille de la population
		POPULATION_SIZE = Integer.parseInt(args[0]);
		// Le nombre de générations à générer
		NB_GENERATIONS = Integer.parseInt(args[1]);
		// Le taux de Cross-Over est donné en pourcentage
		CROSSOVER_RATE = Double.parseDouble(args[2]) / 100.0;
		// Le taux de Mutation est donné en pourcentage
		MUTATION_RATE = Double.parseDouble(args[3]) / 100.0;
		
		Population population = new Population(POPULATION_SIZE);
		GeneticAlgorithm ga = new GeneticAlgorithm(population, NB_GENERATIONS, CROSSOVER_RATE, MUTATION_RATE);
		ga.run();
	}
}
