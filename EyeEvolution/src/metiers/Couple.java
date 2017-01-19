package metiers;

public class Couple {
	private Eye individual1;
	private Eye individual2;
	
	//	CONSTRUCTEURS
	public Couple(Eye individual1, Eye individual2) {
		this.individual1 = individual1;
		this.individual2 = individual2;
	}
	
	//	GETTERS
	public Eye getIndividual1() { return individual1; }
	
	public Eye getIndividual2() { return individual2; }
	
	//	SETTERS
	public void setIndividual1(Eye individual1) { this.individual1 = individual1; }
	
	public void setIndividual2(Eye individual2) { this.individual2 = individual2; }
	
	// METHODES
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("COUPLE : [ \n");
		sb.append("\t" + individual1 + "\n");
		sb.append("\t" + individual2 + "\n");
		sb.append("]");
		return sb.toString();
	}
}
