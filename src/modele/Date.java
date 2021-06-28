package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * classe qui permet d'instancier des objets Date
 * Cette classe à été créée par l'équipe pédagogique de l'IUT de Vélizy - Département Informatique.
 */

public class Date implements Serializable,Comparable<Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8498306252442814114L;
	private int jour;
	private int mois;
	private int annee;
	private int jourSemaine;

	/**
	 * Constructeur qui instancie un objet Date
	 * @param jour le jour (de 0 à 30)
	 * @param mois le mois (de 0 à 11)
	 * @param annee l'année
	 */
	public Date(int jour,int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
		jourSemaine = date.get (Calendar.DAY_OF_WEEK);		
		if (jourSemaine == 1)
			  jourSemaine = 7;
		else jourSemaine -= 1; 
	}
	
	
	/**
	 * Constructeur qui instancie un objet Date avec la date courante
	 * 
	 */
	public Date ()   { 
		GregorianCalendar dateAuj = new GregorianCalendar ();
		annee = dateAuj.get (Calendar.YEAR);
		mois = dateAuj.get (Calendar.MONTH) + 1; // janvier = 0, fevrier = 1...
		jour = dateAuj.get (Calendar.DAY_OF_MONTH);
		jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
		if (jourSemaine == 1)
			jourSemaine = 7;
		else jourSemaine -= 1; 
	  }
	
	public String toString() {
		return(jour + "/" +mois +  "/" + annee);
	}
	public String toStringForFile() {
		return(jour + "-" +mois +  "-" + annee);
	}

	
	
	
	
	@Override
	public int compareTo(Date d) {
		if(this.annee == d.annee) {
			if(this.mois == d.mois) {
				if(this.jour == d.jour) {
					return 0;
				}
				else if(this.jour > d.jour) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else if(this.mois > d.mois) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else if(this.annee > d.annee) {
			return 1;
		}
		else {
			return -1;
		}
	}

	public int getAnnee() {
		return annee;
	}
	
	public int getMois() {
		return mois;
	}
	
	public int getJour() {
		return jour;
	}
	public int getJourSemaine() {
		return jourSemaine;
	}


	/**
	 * Retourne la date du lendemain de l'objet date qui l'invoque
	 * @return un objet Date qui correspond à la date du lendemain
	 */
	  public Date dateDuLendemain ()   {	
	    if (jour < dernierJourDuMois(mois,annee))
			     return  new Date (jour+1,mois,annee);
			else if (mois < 12)
					return new Date (1,mois+1,annee);
				 else return new Date (1,1,annee+1);	
	  }  
	  
	  
		/**
		 * Retourne la date de la veille de l'objet date qui l'invoque
		 * @return un objet Date qui correspond à la date de la veille
		 */
	  public Date dateDeLaVeille () {    
		if (jour > 1)
				return  new Date (jour-1,mois,annee);
		else if (mois > 1)
				   return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
			 else return  new Date (31,12,annee-1);
	  }	 
	  
	  
	  /**
	   * Retourne la date du dernier jour du mois passé en paramètre
	   * @param parMois  Le mois
	   * @param parAnnee L'année
	   * @return Le dernier jour du mois
	   */
	  public static int dernierJourDuMois (int parMois, int parAnnee) {
			switch (parMois) {
				 case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
				 case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
				 default : return 31 ;
				}  // switch
		  } 
		  
	  /**
	   * Retourne si l'année passée en paramètre est bissextile ou non
	   * @param parAnnee L'année
	   */
	  private static boolean estBissextile(int parAnnee) {
				return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
		  }
	
}
