package modele;
import java.util.TreeSet;
import java.util.Collection;


/**
 * Classe qui g�n�re un TreeSet qui contient toutes les dates d'un mois (contient �galement les dates hors mois qui appartiennent aux semaines du mois trait�)
 * Cette classe � �t� cr��e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 *
 */
public class CalendrierDuMois{	
  
	private Collection <Date> treeSetDate;	  
	/**
	 * Le constructeur qui instancie un objet CalendrierDuMois
	 * @param mois Le mois demand�
	 * @param annee L'ann�e demand�e
	 * Ce constructeur � �t� cr�� par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
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