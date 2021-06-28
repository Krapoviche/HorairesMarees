package modele;

import java.io.Serializable;
/**
 * Classe qui permet d'instancier des objets correspondants � la hauteur de la mer � une heure
 *
 */

public class HauteurDeMer implements Serializable {
	
	private static final long serialVersionUID = -1181989042963630158L;
	Double hauteur;
	int heure;
	
	/**
	 * Constructeur d'une hauteurDeMer
	 * @param hauteur La hauteur de la mer � l'heure sp�cifi�e en deuxi�me param�tre
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
	 * Getter qui permet de r�cup�rer la hauteur de la mer pour l'objet qui appele la m�thode
	 * @return La hauteur de la mer pour l'objet
	 */
	public Double getHauteur() {
		return hauteur;
	}
}
