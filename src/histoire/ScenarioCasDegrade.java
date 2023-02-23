package histoire;

import personnages.Gaulois;
import villagegaulois.*;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois Naruto = new Gaulois("Naruto",1);
				
		try {
			etal.acheterProduit(-1, Naruto);
		
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		}
}
