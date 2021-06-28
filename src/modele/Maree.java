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
	 * Constructeur qui permet d'instancier un objet Marée à partir d'un type de marée, une date, un horaire (heure et minute), d'une hauteur et d'un coef
	 * @param type Le type de marée
	 * @param date La date de la marée
	 * @param heure L'heure de l'horaire de la marée
	 * @param minute La minute de l'horaire de la marée
	 * @param hauteur La hauteur de la mer au moment de la marée
	 * @param coef Le coefficient de marée de la marée
	 */
	public Maree(String type,Date date, String  heure, String  minute, Double hauteur, int coef) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.coef = coef;
		this.date = date;
		
		//Les données traitées contenant des données non numérique là où il devrait y en avoir quand on lit des données qui ne sont pas disponibles, la méthode de lecture remplace ces caractères non numériques par "999" afin de les identifier comme données non traitées.
		//On définit des valeurs impossibles pour chaque champ de l'objet pour pouvoir les traiter dans le contrôleur
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}	
	
	/**
	 * Constructeur qui permet d'instancier un objet Marée à partir d'un type de marée, une date, un horaire (heure et minute), d'une hauteur (Constructeur utilisé pour les basses marée)
	 * @param type Le type de marée
	 * @param date La date de la marée
	 * @param heure L'heure de l'horaire de la marée
	 * @param minute La minute de l'horaire de la marée
	 * @param hauteur La hauteur de la mer au moment de la marée
	 */
	public Maree(String type,Date date, String heure, String minute, Double hauteur) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.date = date;
		coef = -1;
		
		//Les données traitées contenant des données non numérique là où il devrait y en avoir quand on lit des données qui ne sont pas disponibles, la méthode de lecture remplace ces caractères non numériques par "999" afin de les identifier comme données non traitées.
		//On définit des valeurs impossibles pour chaque champ de l'objet pour pouvoir les traiter dans le contrôleur
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}

	
	public String toString() {
		if(hauteur > -1) {
			if(coef >= 0) {
				return heure + ":" + minute + " à une hauteur de " + hauteur + " son coefficient de marrée est de " + coef;
			}
			else {
				return heure + ":" + minute + " à une hauteur de " + hauteur +  " son coefficient de marrée est inconnu ";			
			}
		}
		else {
			return "Pas d'enregistrement";
		}
		
	}
	
	//Getters sur les différents champs de l'objet marée
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
