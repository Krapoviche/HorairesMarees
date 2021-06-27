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
	 * constructeur des objets Maree avec un coef
	 * @param type, date, heure, minute, hauteur, coef de la maree
	 */
	public Maree(String type,Date date, String  heure, String  minute, Double hauteur, int coef) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.coef = coef;
		this.date = date;
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}	
	/**
	 * constructeur des objets Maree sans coef
	 * @param type, Date, heure, minute, hauteur de la maree
	 */
	public Maree(String type,Date date, String heure, String minute, Double hauteur) {
		this.type = type;
		this.heure = heure;
		this.minute = minute;
		this.hauteur = hauteur;
		this.date = date;
		coef = -1;
		if(Integer.parseInt(heure) == 999) {
			this.heure = "-1" ;this.minute = "-1"; this.hauteur = -1.00;this.coef = -1;
		}
	}
	/**
	 * affichage d'un objet Maree
	 * si il y a une hauteur enregistré
	 * si il y a un coef
	 * @return le date à heure  : minute la mer était en type à une hauteur de hauteur son coefficient de marée est de coef, de type stringh
	 * si il n'y a pas de coef
	 * @return le date à heure  : minute la mer était en type à une hauteur de hauteur, de type stringh
	 * si il n'y a pas de hauteur enregistré
	 * @return pas d'enregistrement, de type stringh
	 */
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
