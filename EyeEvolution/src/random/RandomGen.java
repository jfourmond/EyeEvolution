package random;
import java.util.Random;

public class RandomGen {
	private static Random rand= new Random(System.currentTimeMillis());
	
	public static void setSeed(long seed) {
		rand.setSeed(seed);
	}
	
	public static int nextInt(int bound) {
		return rand.nextInt(bound);
	}
	
	public static double nextDouble() {
		return rand.nextDouble();
	}
	
	public static double nextGaussian() {
		return rand.nextGaussian();
	}
}
