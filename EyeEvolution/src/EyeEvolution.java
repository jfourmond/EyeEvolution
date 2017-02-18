import algorithm.GeneticAlgorithm;
import metiers.Population;

public class EyeEvolution {
	private static int POPULATION_SIZE = 10;
	private static int NB_GENERATIONS = 1000;
	private static double CROSSOVER_RATE = 0.1;
	private static double MUTATION_RATE = 0.01;
	private static long SEED = System.currentTimeMillis();
	
	public static void main(String[] args) {
		if(args.length == 0) {
			// DO NOTHING
		} else if(args.length != 5 && args.length != 4)
			throw new IllegalArgumentException("4 arguments nécessaires");
		else if(args.length == 4) {
			// La taille de la population
			POPULATION_SIZE = Integer.parseInt(args[0]);
			// Le nombre de générations à générer
			NB_GENERATIONS = Integer.parseInt(args[1]);
			// Le taux de Cross-Over est donné en pourcentage
			CROSSOVER_RATE = Double.parseDouble(args[2]) / 100.0;
			// Le taux de Mutation est donné en pourcentage
			MUTATION_RATE = Double.parseDouble(args[3]) / 100.0;
		} else if(args.length == 5) {
			// La taille de la population
			POPULATION_SIZE = Integer.parseInt(args[0]);
			// Le nombre de générations à générer
			NB_GENERATIONS = Integer.parseInt(args[1]);
			// Le taux de Cross-Over est donné en pourcentage
			CROSSOVER_RATE = Double.parseDouble(args[2]) / 100.0;
			// Le taux de Mutation est donné en pourcentage
			MUTATION_RATE = Double.parseDouble(args[3]) / 100.0;
			// La graine pour le générateur aléatoire
			SEED = Long.parseLong(args[4]);
		}
		
		System.out.println("Exécution Graine : " + SEED);
		Population population = new Population(POPULATION_SIZE);
		GeneticAlgorithm ga = new GeneticAlgorithm(population, NB_GENERATIONS, CROSSOVER_RATE, MUTATION_RATE, SEED);
		ga.run();
		System.out.println("Meilleur oeil : ");
		System.out.println(ga.getBestEye());
	}
}
