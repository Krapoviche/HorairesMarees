package modele;
import java.util.TreeSet;
import java.util.Collection;


/**
 * Classe qui génère un TreeSet qui contient toutes les dates d'un mois (contient également les dates hors mois qui appartiennent aux semaines du mois traité)
 * Cette classe à été créée par l'équipe pédagogique de l'IUT de Vélizy - Département Informatique.
 *
 */
public class CalendrierDuMois{	
  
	private Collection <Date> treeSetDate;	  
	/**
	 * Le constructeur qui instancie un objet CalendrierDuMois
	 * @param mois Le mois demandé
	 * @param annee L'année demandée
	 * Ce constructeur à été créé par l'équipe pédagogique de l'IUT de Vélizy - Département Informatique.
	 */
	public CalendrierDuMois ( int mois, int annee) {			   
		treeSetDate = new TreeSet <Date> ();
		Date date = new Date (1,mois,annee);
		int indiceJour = date.getJourSemaine() ;
		for (int x = indiceJour ; x!=0 ; x--) {	    	 
			treeSetDate.add(date);	    	
	    	date = date.dateDeLaVeille();
	    }	    
	    date = new Date (2,mois,annee);
	    indiceJour = indiceJour % 7 ;
	    while (date.getMois () == mois) {
	    	while(indiceJour<7) {
	    		treeSetDate.add(date);
	    		date = date.dateDuLendemain();
	    		indiceJour++ ;
	    	} 
	    	indiceJour=0;
	    }    
	}
 
	/**
	 * Getter sur la collection de dates que contient l'objet
	 * @return la collection de dates
	 */
	public Collection <Date> getDates() {	
		return treeSetDate;	
	}	

	public String toString () {
		return treeSetDate.toString();
	}
}