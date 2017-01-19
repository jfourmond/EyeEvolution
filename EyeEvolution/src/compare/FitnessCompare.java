package compare;

import java.util.Comparator;

import metiers.Eye;

public class FitnessCompare implements Comparator<Eye> {
	@Override
	public int compare(Eye o1, Eye o2) {
		return Double.compare(o1.getFitness(), o2.getFitness());
	}
}
