package modele;

import java.io.Serializable;
/**
 * Classe qui permet d'instancier des objets correspondants à la hauteur de la mer à une heure
 *
 */

public class HauteurDeMer implements Serializable {
	
	private static final long serialVersionUID = -1181989042963630158L;
	Double hauteur;
	int heure;
	
	/**
	 * Constructeur d'une hauteurDeMer
	 * @param hauteur La hauteur de la mer à l'heure spécifiée en deuxième paramètre
	 * @param heure L'heure (uniquement heure)
	 */
	public HauteurDeMer(Double hauteur, int heure) {
		this.hauteur = hauteur;
		this.heure = heure;
	}
	
	public String toString() {
		return heure + "h " + hauteur;
	}

	/**
	 * Getter qui permet de récupérer la hauteur de la mer pour l'objet qui appele la méthode
	 * @return La hauteur de la mer pour l'objet
	 */
	public Double getHauteur() {
		return hauteur;
	}
}
