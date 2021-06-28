package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;
/**
 * Classe qui permet d'instancier des objets qui contiennent toutes les marées pour tous les jours pour un port
 *
 */
public class MareesUnPort implements Serializable{
	
	TreeMap<Date,MareesUnJour> mareesUnJourDuPort;
	String nomPort;
		
	/**
	 * Constructeur qui permet l'instanciation d'objets MareesUnPort à partir d'une map de MareesUnJour
	 * @param mareesUnJourDuPort La map qui contient toutes les MareesUnJour du port
	 */
	public MareesUnPort(TreeMap<Date,MareesUnJour> mareesUnJourDuPort) {
		this.mareesUnJourDuPort = mareesUnJourDuPort;
		//Pour définir le nom du port correspondant, on récupère ce nom dans le premier objet contenu dans la map
		this.nomPort = mareesUnJourDuPort.get(new Date(01,06,2021)).getNomPort();
	}
	
	public String toString() {
		String str = nomPort+"\n";
		Set<Date> entrees = mareesUnJourDuPort.keySet();
		for(Date date: entrees) {
			str += mareesUnJourDuPort.get(date).toString();
		}
		return str;
	}
	
	// Getters sur les champs de l'objet
	public String getNomPort() {
		return nomPort;
	}
	
	public TreeMap<Date,MareesUnJour> getMareesUnJourDuPort(){
		return mareesUnJourDuPort;
	}
}
