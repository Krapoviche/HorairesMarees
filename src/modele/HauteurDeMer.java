package modele;

import java.io.Serializable;

public class HauteurDeMer implements Serializable {
	
	private static final long serialVersionUID = -1181989042963630158L;
	Double hauteur;
	int heure;
	
	public HauteurDeMer(Double hauteur, int heure) {
		this.hauteur = hauteur;
		this.heure = heure;
	}
	
	public String toString() {
		return heure + "h " + hauteur;
	}

	public Double getHauteur() {
		return hauteur;
	}
}
