package writer_and_reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class CSVWriter {
	private static final String SEPARATOR = " ; ";
	private static final String NEWLINE_DELIMITER = "\n";
	
	private static final String COLUMN_GENERATION = "generation";
	private static final String COLUMN_CROSSOVER_RATE = "cross-over-rate";
	private static final String COLUMN_MUTATION_RATE = "mutation-rate";
	private static final String COLUMN_AVERAGE_CURVE_RADIUS = "average-curve-radius";
	private static final String COLUMN_AVERAGE_IRIS_SIZE = "average-iris-size";
	private static final String COLUMN_AVERAGE_ANGLE = "average-angle";
	private static final String COLUMN_AVERAGE_REFRACTION_INDEX = "average-refraction-index";
	private static final String COLUMN_AVERAGE_FITNESS = "average-fitness";
	private static final String COLUMN_BEST_EYE_CURVE_RADIUS = "best-eye-curve-radius";
	private static final String COLUMN_BEST_EYE_IRIS_SIZE = "best-eye-iris-size";
	private static final String COLUMN_BEST_EYE_ANGLE = "best-eye-angle";
	private static final String COLUMN_BEST_EYE_REFRACTION_INDEX = "best-eye-refraction-index";
	private static final String COLUMN_BEST_EYE_FITNESS = "best-eye-fitness";
	
	private static final String FILENAME = "_evolution.csv";
	private static final String PATH = "resources/";
	
	private Date date;
	private File file;
	
	private FileWriter fw;
	
	public CSVWriter() throws IOException {
		date = new Date();
		file = new File(PATH + date.getTime() + FILENAME);
		fw = new FileWriter(file);
		writeHeader();
	}
	
	public void writeHeader() throws IOException {
		fw.append(COLUMN_GENERATION + SEPARATOR);
		fw.append(COLUMN_CROSSOVER_RATE + SEPARATOR);
		fw.append(COLUMN_MUTATION_RATE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_CURVE_RADIUS + SEPARATOR);
		fw.append(COLUMN_AVERAGE_IRIS_SIZE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_ANGLE + SEPARATOR);
		fw.append(COLUMN_AVERAGE_REFRACTION_INDEX + SEPARATOR);
		fw.append(COLUMN_AVERAGE_FITNESS + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_CURVE_RADIUS + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_IRIS_SIZE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_ANGLE + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_REFRACTION_INDEX + SEPARATOR);
		fw.append(COLUMN_BEST_EYE_FITNESS + SEPARATOR);
		fw.append(NEWLINE_DELIMITER);
		fw.flush();
	}
	
	public void writeRow(int generation, double crossoverRate, double mutationRate,
			double averageCurveRadius, double averageIrisSize,double averageAngle, double averageRefractionIndex, double averageFitness,
			double bestEyeCurveRadius, double bestEyeIrisSize, double bestEyeAngle, double bestEyeRefractionIndex, double bestEyeFitness) throws IOException {
		
		// Arrondir averageCurveRadius
		averageCurveRadius = round(averageCurveRadius);
		// Arrondir averageIrisSize
		averageIrisSize = round(averageIrisSize);
		// Arrondir averageAngle
		averageAngle = round(averageAngle);
		// Arrondir averageRefractionIndex
		averageRefractionIndex = round(averageRefractionIndex);
		// Arrondir averageFitness
		averageFitness = round(averageFitness);
		// Arrondir bestEye
		bestEyeCurveRadius = round(bestEyeCurveRadius);
		bestEyeIrisSize = round(bestEyeIrisSize);
		bestEyeAngle = round(bestEyeAngle);
		bestEyeRefractionIndex = round(bestEyeRefractionIndex);
		bestEyeFitness = round(bestEyeFitness);
		
		fw.append(generation + SEPARATOR);
		fw.append(crossoverRate + SEPARATOR);
		fw.append(mutationRate + SEPARATOR);
		fw.append(averageCurveRadius + SEPARATOR);
		fw.append(averageIrisSize + SEPARATOR);
		fw.append(averageAngle + SEPARATOR);
		fw.append(averageRefractionIndex + SEPARATOR);
		fw.append(averageFitness + SEPARATOR);
		fw.append(bestEyeCurveRadius + SEPARATOR);
		fw.append(bestEyeIrisSize + SEPARATOR);
		fw.append(bestEyeAngle + SEPARATOR);
		fw.append(bestEyeRefractionIndex + SEPARATOR);
		fw.append(bestEyeFitness + SEPARATOR);
		fw.append(NEWLINE_DELIMITER);
		fw.flush();
	}
	
	private double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd.setScale(3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public void close() throws IOException { fw.close(); }
}
