package writer_and_reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RefractionIndexReader {
	private static final String FILENAME = "resources/indice_refraction.dat";
	
	private HashMap<Double, Double> values;
	private int colR1;
	private int colVitreous;
	
	//	CONSTRUCTEURS
	public RefractionIndexReader() {
		values = new HashMap<>();
		read();
	}
	
	/**
	 * Lecture du fichier "indice_refraction.data"
	 */
	private void read() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			String sCurrentLine;
			// Lecture de la première ligne
			sCurrentLine = br.readLine();
			sCurrentLine = sCurrentLine.trim();
			String headers[] = sCurrentLine.split("(\\s)+");
			for(int i=0; i<headers.length ; i++) {
				String header = headers[i];
				// Récupération des colonnes
				if(header.equals("r1")) colR1 = i;
				else if(header.equals("n0vitreous")) colVitreous = i;
			}
			// Récupération des valeurs
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.trim();
				String line[] = sCurrentLine.split("(\\s)+");
				// System.out.println(line[colR1] + " " + line[colVitreous]);
				values.put(Double.parseDouble(line[colVitreous]),
						Double.parseDouble(line[colR1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne le rapport entre le rayon de la lentille et sa distance focale
	 * en fonction de l'indice de réfraction
	 * @param refractionIndex : indice de réfraction
	 * @return le rapport entre le rayon de la lentille et sa distance focale 
	 * en fonction de l'indice de réfraction
	 * @throws Exception
	 */
	public double r1(double refractionIndex) {
		return values.get(refractionIndex);
	}
}
