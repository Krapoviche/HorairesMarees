package modele;

import java.io.Serializable;

/**
 * classe qui permet d'instancier des objets Maree
 */


public class Maree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6650990913471997769L;
	private String type;
	private String heure;
	private String minute;
	private Double hauteur;
	private int coef;
	private Date date;

	/**
	 * Constructeur qui permet d'instancier un objet Mar�e � partir d'un type de mar�e, une date, un horaire (heure et minute), d'une hauteur et d'un coef
	 * @param type Le type de mar�e
	 * @param date La date de la mar�e
	 * @param heure L'heure de l'horaire de la mar�e
	 * @param minute La minute de l'horaire de la mar�e
	 * @param hauteur La hauteur de la mer au moment de la mar�e
	 * @param coef Le coefficient de mar�e de la mar�e
	 */
	public Maree(String type,Date date, String  heure, String  minute, Double hauteur, int coef) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.coef = coef;
		this.date = date;
		
		//Les donn�es trait�es contenant des donn�es non num�rique l� o� il devrait y en avoir quand on lit des donn�es qui ne sont pas disponibles, la m�thode de lecture remplace ces caract�res non num�riques par "999" afin de les identifier comme donn�es non trait�es.
		//On d�finit des valeurs impossibles pour chaque champ de l'objet pour pouvoir les traiter dans le contr�leur
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}	
	
	/**
	 * Constructeur qui permet d'instancier un objet Mar�e � partir d'un type de mar�e, une date, un horaire (heure et minute), d'une hauteur (Constructeur utilis� pour les basses mar�e)
	 * @param type Le type de mar�e
	 * @param date La date de la mar�e
	 * @param heure L'heure de l'horaire de la mar�e
	 * @param minute La minute de l'horaire de la mar�e
	 * @param hauteur La hauteur de la mer au moment de la mar�e
	 */
	public Maree(String type,Date date, String heure, String minute, Double hauteur) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.date = date;
		coef = -1;
		
		//Les donn�es trait�es contenant des donn�es non num�rique l� o� il devrait y en avoir quand on lit des donn�es qui ne sont pas disponibles, la m�thode de lecture remplace ces caract�res non num�riques par "999" afin de les identifier comme donn�es non trait�es.
		//On d�finit des valeurs impossibles pour chaque champ de l'objet pour pouvoir les traiter dans le contr�leur
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}

	
	public String toString() {
		if(hauteur > -1) {
			if(coef >= 0) {
				return heure + ":" + minute + " � une hauteur de " + hauteur + " son coefficient de marr�e est de " + coef;
			}
			else {
				return heure + ":" + minute + " � une hauteur de " + hauteur +  " son coefficient de marr�e est inconnu ";			
			}
		}
		else {
			return "Pas d'enregistrement";
		}
		
	}
	
	//Getters sur les diff�rents champs de l'objet mar�e
	public Date getDate() {
		return date;
	}
	
	public String getType() {
		return type;
	}
	
	public Double getHauteur() {
		return hauteur;
	}
	
	public String getHoraire() {
		return heure + ":" + minute;
	}
	
	public int getCoef() {
		return coef;
	}
}
