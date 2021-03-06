package metiers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import random.RandomGen;
import writer_and_reader.RefractionIndexReader;

public class Eye {
	//	CONSTANTES
	public static final double W = 1.5;					// Largeur maximale de l'oeil
	public static final double I = Math.pow(Math.E, 6);	// Intensité lumineuse
	
	public static final double eps = 0.01;
	
	public static final RefractionIndexReader rir = new RefractionIndexReader();

	// CONSTANTES DES PLAGES
	// Le rayon de courbure peut varier dans la plage [w/2, 10000]
	public static final double MIN_CURVE_RADIUS = W / 2;
	public static final double MAX_CURVE_RADIUS = 10000;
	
	// La taille de l'iris peut varier dans la plage [0, w/2[
	public static final double MIN_IRIS_SIZE = 0;
	public static final double MAX_IRIS_SIZE = W / 2;

	// L'angle peut varier dans la plage [0, PI/2[
	public static final double MIN_ANGLE = 0;
	public static final double MAX_ANGLE = Math.PI / 2;

	// L'indice de réfraction peut varier dans la plage [1.35, 1.55]
	public static final double MIN_REFRACTION_INDEX = 1.35;
	public static final double MAX_REFRACTION_INDEX = 1.55;

	// ATTRIBUTS
	private double curveRadius; // Rayon de courbure dans la plage [w/2, 10000]
	private double irisSize; // Taille de l'iris dans la plage [0, w/2[
	private double angle; // Angle dans la plage [0, pi/2[
	private double refractionIndex; // Indice de réfraction dans la plage [1.35, 1.55]

	// ATTRIBUTS CALCULES
	private double depth;
	private double aperture;
	private double ratio;
	private double sightAngle;
	private double fitness;

	// CONSTRUCTEURS
	public Eye() {
		curveRadius = 10000;
		irisSize = 0;
		angle = 0;
		refractionIndex = 1.35;

		calculateAttributes();
	}

	public Eye(double curveRadius, double irisSize, double angle, double refractionIndex) {
		this.curveRadius = curveRadius;
		this.irisSize = irisSize;
		this.angle = angle;
		this.refractionIndex = refractionIndex;

		calculateAttributes();
	}

	public Eye(double[] caracteristics) {
		this(caracteristics[0], caracteristics[1], caracteristics[2], caracteristics[3]);
	}

	// GETTERS
	public double getCurveRadius() {
		return curveRadius;
	}

	public double getIrisSize() {
		return irisSize;
	}

	public double getAngle() {
		return angle;
	}

	public double getRefractionIndex() {
		return refractionIndex;
	}

	public double getDepth() {
		return depth;
	}

	public double getAperture() {
		return aperture;
	}

	public double getRatio() {
		return ratio;
	}

	public double getSightAngle() {
		return sightAngle;
	}

	public double getFitness() {
		return fitness;
	}

	// SETTERS avec gestion des plages de valeurs
	/**
	 * Mise à jour du rayon de courbure dans la plage [W/2, 10 000]
	 * @param curveRadius : nouvelle valeur du rayon de courbure
	 */
	public void setCurveRadius(double curveRadius) {
		curveRadius = round(curveRadius);
		if(curveRadius < MIN_CURVE_RADIUS)
			this.curveRadius = MIN_CURVE_RADIUS;
		else if (curveRadius > MAX_CURVE_RADIUS)
			this.curveRadius = MAX_CURVE_RADIUS;
		else
			this.curveRadius = curveRadius;
	}

	/**
	 * Mise à jour de la taille de l'iris dans la plage [0, W/2[
	 * @param irisSize : nouvelle valeur de la taille de l'iris
	 */
	public void setIrisSize(double irisSize) {
		irisSize = round(irisSize);
		if(irisSize < MIN_IRIS_SIZE)
			this.irisSize = MIN_IRIS_SIZE;
		else if (irisSize > MAX_IRIS_SIZE)
			this.irisSize = MAX_IRIS_SIZE - eps;
		else
			this.irisSize = irisSize;
	}

	/**
	 * Mise à jour de l'angle dans la plage [0, pi / 2[
	 * @param angle : nouvelle valeur de l'angle
	 */
	public void setAngle(double angle) {
		angle = round(angle);
		if(angle < MIN_ANGLE)
			this.angle = MIN_ANGLE;
		else if (angle > MAX_ANGLE)
			this.angle = MAX_ANGLE - eps;
		else
			this.angle = angle;
	}

	/**
	 * Mise à jour de l'indice de réfraction dans la plage [1.35, 1.55]
	 * @param refractionIndex : nouvelle valeur de l'indice de réfraction
	 */
	public void setRefractionIndex(double refractionIndex) {
		refractionIndex = round(refractionIndex);
		if(refractionIndex < MIN_REFRACTION_INDEX)
			this.refractionIndex = MIN_REFRACTION_INDEX;
		else if (refractionIndex > MAX_REFRACTION_INDEX)
			this.refractionIndex = MAX_REFRACTION_INDEX;
		else {
			BigDecimal bd = new BigDecimal(refractionIndex);
			bd = bd.setScale(3, RoundingMode.HALF_UP);
			refractionIndex = bd.doubleValue();
			this.refractionIndex = refractionIndex;
		}
	}

	// METHODES
	/**
	 * Calcule des attributs profondeur, ouverture, rapport (rayon / distance
	 * focale), angle de vue et fitness
	 */
	public void calculateAttributes() {
		depth();
		aperture();
		ratio();
		sightAngle();
		fitness();
	}

	/**
	 * Calcule la profondeur de l'oeil
	 */
	public void depth() {
		depth = 0;
		if (curveRadius > W / 2)
			depth = curveRadius - Math.sqrt(Math.pow(curveRadius, 2) - (Math.pow(W, 2) / 4));
		else if(curveRadius == W/2)
			depth = (W / 2) * (1 + Math.sin(angle));
	}

	/**
	 * Calcule l'ouverture de l'oeil
	 */
	public void aperture() {
		aperture = 0;
		if (curveRadius > W / 2)
			aperture = W - 2 * irisSize;
		else
			aperture = W * Math.cos(angle) - 2 * irisSize;
	}

	/**
	 * Met à jour le rapport entre le rayon de la lentille et sa distance focale
	 */
	public void ratio() {
		ratio = Eye.rir.r1(refractionIndex);
	}

	/**
	 * Calcule de l'angle de vue
	 */
	public void sightAngle() {
		double res = 0;
		if (getRefractionIndex() == 1.35)
			res = 2 * Math.atan(aperture / (2 * depth));
		else if (getRefractionIndex() > 1.35) {
			double term_1 = (Math.pow(ratio, 2) * aperture) / (2 * depth);
			double term_2 = Math.sqrt(1 + Math.pow(ratio, 2) - ((Math.pow(ratio, 2) * Math.pow(aperture, 2)) / (4 * Math.pow(depth, 2))));
			double num = term_1 - term_2;
			double denum = 1 + Math.pow(ratio, 2);
			res = 2 * Math.asin(num / denum);
			if(Double.isNaN(res)) res = 0;
		}
		sightAngle = res;
	}

	/**
	 * Calcule de la fonction de fitness
	 */
	public void fitness() {
		if(isViable()) {
			if(refractionIndex == 1.35)
				fitness = 0.375 * (depth / aperture) * Math.sqrt(Math.log(0.746 * Math.pow(aperture, 2) * Math.sqrt(I)));
			else if(refractionIndex > 1.35)
				fitness = 1 / sightAngle;
		} else
			fitness = 0;
	}
	
	/**
	 * Teste si l'oeil courant est viable
	 * @return <code>true</code> si l'oeil est viable, <code>false</code> viable sinon
	 */
	public boolean isViable() {
		if (((angle != 0) && curveRadius != (W / 2)))
			return false;
		else if ((angle != 0) && (irisSize > (W * Math.cos(angle) / 2)))
			return false;
		else if(refractionIndex == 1.35 && angle == 0 && irisSize > (0.5 * (W - Math.sqrt(Math.E / (0.746 * Math.sqrt(I))))))
			return false;
		else if(refractionIndex == 1.35 && angle != 0 && irisSize > (0.5 * (W * Math.cos(angle) - Math.sqrt(Math.E / (0.746 * Math.sqrt(I))))))
			return false;
		else if (refractionIndex != 1.35 && (depth > (ratio * aperture / 2) || (depth < (aperture / 2))))
			return false;
		else
			return true;
	}

	/**
	 * Calcule la probabilité de reproduction
	 * @param rank : rank de l'indivu dans la population
	 * @param n : taille de la population
	 * @return la probabilité de reproduction
	 */
	public double reproductionProbability(int rank, int n, double c) {
		if (!isViable())
			return 0;
		else
			return ((c - 1) / (Math.pow(c, n) - 1)) * Math.pow(c, n - rank);
	}

	/**
	 * Retourne les caractéristiques sous la forme d'un tableau de
	 * {@link Double}
	 * 
	 * @return les caractéristiques sous la forme d'un tableau de {@link Double}
	 */
	public double[] caracteristics() {
		double caracteristics[] = new double[4];
		caracteristics[0] = curveRadius;
		caracteristics[1] = irisSize;
		caracteristics[2] = angle;
		caracteristics[3] = refractionIndex;
		return caracteristics;

	}

	/**
	 * Met à jour les caractéristiques à partir du tableau des caractéristiques
	 * 
	 * @param caracteristics : les caractéristiques à utiliser pour mettre à jour l'individu
	 */
	public void setCaracteristics(double[] caracteristics) {
		setCurveRadius(caracteristics[0]);
		setIrisSize(caracteristics[1]);
		setAngle(caracteristics[2]);
		setRefractionIndex(caracteristics[3]);

		calculateAttributes();
	}

	/**
	 * Mute aléatoirement une caractéristique de l'oeil courant à base d'un {@link Double} gaussien aléatoire
	 */
	public void mutate() {
		double[] caracteristics = caracteristics();
		int c = RandomGen.nextInt(4);
		caracteristics[c] += RandomGen.nextGaussian();
		setCaracteristics(caracteristics);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Rayon de courbure : " + curveRadius + "\t");
		sb.append("Taille de l'iris : " + irisSize + "\t");
		sb.append("Angle : " + angle + "\t");
		sb.append("Indice de réfraction : " + refractionIndex + "\n\t");
		sb.append("Profondeur : " + depth + "\t");
		sb.append("Ouverture : " + aperture + "\t");
		sb.append("Ratio : " + ratio + "\t");
		sb.append("Angle de vue : " + sightAngle + "\t");
		sb.append("Fitness : " + fitness);
		return sb.toString();
	}

	/**
	 * Arrondi le double passé en paramètre à trois décimales après la virgule
	 * @param value : le double à arrondir
	 * @return le double arrondi à trois décimales après la virgule
	 */
	private double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd.setScale(3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	//	METHODES DE CLASSE
	/**
	 * Retourne des caractéristiques aléatoires en fonction des caractéristiques
	 * des parents
	 * @param e1 : parent 1
	 * @param e2 : parent 2
	 * @param crossoverRate : taux de cross-over
	 * @return des caractéristiques aléatoires en fonction des caractéristiques
	 *         des parents
	 */
	public static double[] caracteristic(Eye e1, Eye e2, double crossoverRate) {
		double caracteristics[] = new double[4];
		double c1[] = e1.caracteristics();
		double c2[] = e2.caracteristics();
		// Choix 1
		caracteristics[0] = (RandomGen.nextDouble() < crossoverRate) ? c1[0] : c2[0];
		caracteristics[1] = (RandomGen.nextDouble() < crossoverRate) ? c1[1] : c2[1];
		caracteristics[2] = (RandomGen.nextDouble() < crossoverRate) ? c1[2] : c2[2];
		caracteristics[3] = (RandomGen.nextDouble() < crossoverRate) ? c1[3] : c2[3];
		return caracteristics;
	}
}
