package outils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import modele.HauteursDeMerUnPort;
import modele.MareesUnPort;

/**
 * Cette classe permet de lire les objets à partir de leurs fichiers sérialisés
 *
 */
public class LectureFichierSer {
	
	/**
	 * Cette méthode permet de lire les objets MareesUnPort écris dans des fichiers sérialisés
	 * @param parFichier Le fichier sérialisé
	 * @return L'objet MareesUnPort lu
	 */
	public static MareesUnPort lecture (File parFichier) {
		ObjectInputStream flux ;
		Object objetLu = null;
		// Ouverture du fichier en mode lecture
		try {
			flux = new ObjectInputStream(new FileInputStream (parFichier));
			objetLu = flux.readObject ();
			flux.close ();
		}
		catch (ClassNotFoundException parException) {
			System.err.println (parException.toString ());
			System.exit (1);
		}
		catch (IOException parException) {
			System.err.println ("Erreur lecture du fichier " + parException.toString ());
			System.exit (1);
		}
		return (MareesUnPort)objetLu ;
	}
	

	
	/**
	 * Cette méthode permet de lire les objets HauteursDeMerUnPort écris dans des fichiers sérialisés
	 * @param parFichier Le fichier sérialisé
	 * @return L'objet HauteursDeMerUnPort lu
	 */
	public static HauteursDeMerUnPort lectureHph(File parFichier) {
		ObjectInputStream flux ;
		Object objetLu = null;
		// Ouverture du fichier en mode lecture
		if(parFichier.exists()) {
			try {
				flux = new ObjectInputStream(new FileInputStream (parFichier));
				objetLu = flux.readObject ();
				flux.close ();
			}
			catch (ClassNotFoundException parException) {
				System.err.println (parException.toString ());
				System.exit (1);
			}
			catch (IOException parException) {
				System.err.println ("Erreur lecture du fichier " + parException.toString ());
				System.exit (1);
			}
			return (HauteursDeMerUnPort)objetLu ;
		}

		else {
			return null;
		}
	}
}
