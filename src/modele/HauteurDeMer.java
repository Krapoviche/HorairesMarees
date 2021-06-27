package modele;

import java.io.Serializable;

public class HauteurDeMer implements Serializable {
	
	Double hauteur;
	int heure;
	
	public HauteurDeMer(Double hauteur, int heure) {
		this.hauteur = hauteur;
		this.heure = heure;
	}
	
	public String toString() {
		return heure + "h " + hauteur;
	}

}
