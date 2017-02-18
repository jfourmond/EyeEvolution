package writer_and_reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CSVWriter {
	private static final String SEPARATOR = " ; ";
	private static final String NEWLINE_DELIMITER = "\n";
	
	private static final String COLUMN_GENERATION = "generation";
	private static final String COLUMN_POPULATION_SIZE = "population-size";
	private static final String COLUMN_CROSSOVER_RATE = "cross-over-rate";
	private static final String COLUMN_MUTATION_RATE = "mutation-rate";
	private static final String COLUMN_SEED = "seed";
	
	private static final String COLUMN_AVERAGE_CURVE_RADIUS = "average-curve-radius";
	private static final String COLUMN_AVERAGE_IRIS_SIZE = "average-iris-size";
	private static final String COLUMN_AVERAGE_ANGLE = "average-angle";
	private static final String COLUMN_AVERAGE_REFRACTION_INDEX = "average-refraction-index";
	private static final String COLUMN_AVERAGE_DEPTH = "average-depth";
	private static final String COLUMN_AVERAGE_APERTURE = "average-aperture";
	private static final String COLUMN_AVERAGE_RATIO = "average-ratio";
	private static final String COLUMN_AVERAGE_SIGHT_ANGLE = "average-sight-angle";
	private static final String COLUMN_AVERAGE_FITNESS = "average-fitness";
	
	private static final String COLUMN_BEST_EYE_CURVE_RADIUS = "best-eye-curve-radius";
	private static final String COLUMN_BEST_EYE_IRIS_SIZE = "best-eye-iris-size";
	private static final String COLUMN_BEST_EYE_ANGLE = "best-eye-angle";
	private static final String COLUMN_BEST_EYE_REFRACTION_INDEX = "best-eye-refraction-index";
	private static final String COLUMN_BEST_EYE_DEPTH = "best-eye-depth";
	private static final String COLUMN_BEST_EYE_APERTURE = "best-eye-aperture";
	private static final String COLUMN_BEST_EYE_RATIO = "best-eye-ratio";
	private static final String COLUMN_BEST_EYE_SIGHT_ANGLE = "best-eye-sight-angle";
	private static final String COLUMN_BEST_EYE_FITNESS = "best-eye-fitness";
	
	private static final String PATH = "resources/";
	private static final String FILENAME = "_evolution";
	private static final String EXTENSION = ".csv";
	
	private long seed;
	private File file;
	
	private FileWriter fw;
	
	public CSVWriter(long seed) throws IOException {
		this.seed = seed;
		
		file = new File(PATH + this.seed + FILENAME + EXTENSION);
		int n=1;
		while(file.exists()) {
			file = new File(PATH + this.seed + FILENAME + "_" + n + EXTENSION);
			n++;
		}
		fw = new FileWriter(file);
		writeHeader();
	}
	
	/**
	 * Ecriture des Headers
	 * @throws IOException
	 */
	private void writeHeader() throws IOException {
		fw.append(COLUMN_GENERATION + SEPARATOR);
		fw.append(COLUMN_POPULATION_SIZE + SEPARATOR);
		fw.append(COLUMN_CROSSOVER_RATE + SEPARATOR);
		fw.append(COLUMN_MUTATION_RATE + SEPARATOR);
		fw.append(COLUMN_SEED + SEPARATOR);
		
		fw.append(COLUMN_AVERAGE_CURVE_RADIUS + SEPARATOR);
		fw.append(COLUMN_AVERAGE_IRIS_SIZE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_ANGLE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_REFRACTION_INDEX + SEPARATOR);
		fw.append(COLUMN_AVERAGE_DEPTH + SEPARATOR);
		fw.append(COLUMN_AVERAGE_APERTURE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_RATIO + SEPARATOR);
		fw.append(COLUMN_AVERAGE_SIGHT_ANGLE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_FITNESS + SEPARATOR);
		
		fw.append(COLUMN_BEST_EYE_CURVE_RADIUS + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_IRIS_SIZE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_ANGLE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_REFRACTION_INDEX + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_DEPTH + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_APERTURE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_RATIO + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_SIGHT_ANGLE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_FITNESS);
		fw.append(NEWLINE_DELIMITER);
		fw.flush();
	}
	
	/**
	 * Ecriture d'une ligne avec les paramètres suivants :
	 * @param generation : numéro de la génération
	 * @param populationSize : taille de la population
	 * @param crossoverRate : taux de cross-over
	 * @param mutationRate : taux de mutation
	 * @param seed : graine du générateur aléatoire
	 * @param averageCurveRadius : rayon de courbure moyen de la génération
	 * @param averageIrisSize : taille de l'iris moyen de la génération
	 * @param averageAngle : angle moyen de la génération
	 * @param averageRefractionIndex : indice de réfraction moyen de la génération
	 * @param averageFitness : fitness moyen de la génération
	 * @param bestEyeCurveRadius : rayon de courbure du meilleur oeil de la génération
	 * @param bestEyeIrisSize : taille de l'iris du meilleur oeil de la génération
	 * @param bestEyeAngle : angle du meilleur oeil de la génération
	 * @param bestEyeRefractionIndex : indice de réfraction du meilleur oeil de la génération
	 * @param bestEyeFitness : fitness du meilleur oeil de la génération
	 * @throws IOException
	 */
	public void writeRow(int generation, int populationSize, double crossoverRate, double mutationRate, long seed,
			double averageCurveRadius, double averageIrisSize,double averageAngle, double averageRefractionIndex, double averageDepth, double averageAperture, double averageRatio, double averageSightAngle, double averageFitness,
			double bestEyeCurveRadius, double bestEyeIrisSize, double bestEyeAngle, double bestEyeRefractionIndex, double bestEyeDepth, double bestEyeAperture,double bestEyeRatio, double bestEyeSightAngle, double bestEyeFitness) throws IOException {
		
		// Arrondir les valeurs moyennes
		averageCurveRadius = round(averageCurveRadius);
		averageIrisSize = round(averageIrisSize);
		averageAngle = round(averageAngle);
		averageRefractionIndex = round(averageRefractionIndex);
		averageDepth = round(averageDepth);
		averageAperture = round(averageAperture);
		averageRatio = round(averageRatio);
		averageSightAngle = round(averageSightAngle);
		averageFitness = round(averageFitness);
		
		// Arrondir bestEye
		bestEyeCurveRadius = round(bestEyeCurveRadius);
		bestEyeIrisSize = round(bestEyeIrisSize);
		bestEyeAngle = round(bestEyeAngle);
		bestEyeRefractionIndex = round(bestEyeRefractionIndex);
		bestEyeDepth = round(bestEyeDepth);
		bestEyeAperture = round(bestEyeAperture);
		bestEyeRatio = round(bestEyeRatio);
		bestEyeSightAngle = round(bestEyeSightAngle);
		bestEyeFitness = round(bestEyeFitness);
		
		fw.append(generation + SEPARATOR);
		fw.append(populationSize + SEPARATOR);
		fw.append(crossoverRate + SEPARATOR);
		fw.append(mutationRate + SEPARATOR);
		fw.append(seed + SEPARATOR);
		
		fw.append(averageCurveRadius + SEPARATOR);
		fw.append(averageIrisSize + SEPARATOR);
		fw.append(averageAngle + SEPARATOR);
		fw.append(averageRefractionIndex + SEPARATOR);
		fw.append(averageDepth + SEPARATOR);
		fw.append(averageAperture + SEPARATOR);
		fw.append(averageRatio + SEPARATOR);
		fw.append(averageSightAngle + SEPARATOR);
		fw.append(averageFitness + SEPARATOR);
		
		fw.append(bestEyeCurveRadius + SEPARATOR);
		fw.append(bestEyeIrisSize + SEPARATOR);
		fw.append(bestEyeAngle + SEPARATOR);
		fw.append(bestEyeRefractionIndex + SEPARATOR);
		fw.append(bestEyeDepth +SEPARATOR);
		fw.append(bestEyeAperture + SEPARATOR);
		fw.append(bestEyeRatio + SEPARATOR);
		fw.append(bestEyeSightAngle + SEPARATOR);
		fw.append(bestEyeFitness + "");
		
		fw.append(NEWLINE_DELIMITER);
		fw.flush();
	}
	
	/**
	 * Arrondit le double en paramètre à trois décimales
	 * @param value : double à arrondir
	 * @return le double arrondi à trois décimales
	 */
	private double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd.setScale(3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public void close() throws IOException { fw.close(); }
}
