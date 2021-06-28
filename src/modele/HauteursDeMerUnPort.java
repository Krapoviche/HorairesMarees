package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe qui permet d'instancier des objets contenant toutes les hauteurs de mer pour tous les jours pour un port.
 *
 */

public class HauteursDeMerUnPort implements Serializable{
	TreeMap<Date,HauteursDeMerUnJour> hauteursDeMerUnPort;
	String nomPort;
	
	
	/**
	 * Constructeur qui permet d'instancier un objet HauteursDeMerUnPort à partir d'une map d'HauteursDeMerUnJour et d'un port
	 * @param hauteursDeMerUnPort La map des hauteurs de mers de tous les jours correspondant au port passé en deuxième paramètre
	 * @param nomPort Le nom du port en question
	 */
	public HauteursDeMerUnPort(TreeMap<Date,HauteursDeMerUnJour> hauteursDeMerUnPort, String nomPort) {
		
		this.hauteursDeMerUnPort = hauteursDeMerUnPort;
		this.nomPort = nomPort;
	}
	
	/**
	 * Getter qui permet de récupérer une map des HauteursDeMerUnJour pour l'objet correspondant
	 * @return La Map des HauteursDeMerUnJour que contient l'objet appelant
	 */
	public TreeMap<Date,HauteursDeMerUnJour> getHauteursDeMerUnPort(){
		return hauteursDeMerUnPort;
	}
	
	
	/**
	 * Getter qui permet de récupérer le nom du port auquel l'objet appelant correspond
	 * @return le nom du port
	 */
	public String getPort() {
		return nomPort;
	}
	
	public String toString() {
		String str = nomPort + " :";
		Set<Date> keys = hauteursDeMerUnPort.keySet();
		
		for(Date date : keys) {
			str += "\n" + hauteursDeMerUnPort.get(date);
		}
		
		return str;
	}
	
}
