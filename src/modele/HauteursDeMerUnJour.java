package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe qui permet d'instancier des objets contenant les hauteurs de mer heure par heure pour un jour.
 *
 */
public class HauteursDeMerUnJour implements Serializable{
	TreeMap<Integer,HauteurDeMer> hauteursUnJour;
	Date date;
	
	/**
	 * Constructeur qui permet d'instancier les objets HauteursDeMerUnJour à partir d'une map de HauteursDeMer et d'une date
	 * @param hauteursUnJour La map des HauteursDeMer qui correspondent à la date spécifiée en deuxième paramètre
	 * @param date La date à laquelle l'objet et la map correspondent.
	 */
	public HauteursDeMerUnJour(TreeMap<Integer,HauteurDeMer> hauteursUnJour,Date date) {
		this.hauteursUnJour = hauteursUnJour;
		this.date = date;
	}
	
	/**
	 * Getter qui permet de récupérer la map des hateurs de mer de l'objet
	 * @return La map correspondant
	 */
	public TreeMap<Integer,HauteurDeMer> getHauteursUnJour(){
		return hauteursUnJour;
	}
	
	/**
	 * Getter qui permet de récupérer la date à laquelle l'objet correspond.
	 * @return Un objet Date correspondant
	 */
	public Date getDate() {
		return date;
	}
	
	public String toString() {
		String str = date.toString() + " :";
		Set<Integer> keys = hauteursUnJour.keySet();
		
		for(Integer i : keys) {
			str += "\n" + hauteursUnJour.get(i).toString();
		}
		
		return str;
	}
	
}
