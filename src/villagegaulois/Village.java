package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,
				String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) return i;
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			Etal[] vendeurs = new Etal[etals.length];
			int nbVendeurs = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit) )
					vendeurs[nbVendeurs++] = etals[i];
			}
			Etal[] vendeurs2 = new Etal[nbVendeurs];
			System.arraycopy(vendeurs, 0, vendeurs2, 0, nbVendeurs);
			return vendeurs2;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois))
					return etals[i];
			}
			return null;
		}
		
		public String afficherMarche() {
			String str = "";
			int nbEtalsVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) str+=etals[i].afficherEtal();
				else nbEtalsVide ++;
			}
			if (nbEtalsVide != 0) str+= "”Il reste" + nbEtalsVide + 
					" étals non utilisés dans le marché.\n";
			
			return str;
		}
		
	}
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		this.marche = new Marche(nbEtals);
		villageois = new Gaulois[nbVillageoisMaximum];
		
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int
			nbProduit) {
		int numEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre ");
		chaine.append(nbProduit);
		chaine.append(" " + produit);
		chaine.append("\nLe vendeur" + vendeur.getNom() + " vend des fleurs a l'etal n°");
		chaine.append(numEtal);
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] vendeurs = marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des fleurs sont : \n");
		for (int i = 0; i < vendeurs.length; i++) {
			chaine.append(" -  " + vendeurs[i].getVendeur().getNom() + "\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}