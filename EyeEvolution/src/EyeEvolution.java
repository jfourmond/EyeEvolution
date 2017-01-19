import algorithm.GeneticAlgorithm;
import metiers.Population;

public class EyeEvolution {
	private static double CROSSOVER_RATE = 0.01;
	private static double MUTATION_RATE = 0.01;
	
	public static void main(String[] args) {
		Population population = new Population(40);
		GeneticAlgorithm ga = new GeneticAlgorithm(population, CROSSOVER_RATE, MUTATION_RATE);
		ga.run();
	}
}
