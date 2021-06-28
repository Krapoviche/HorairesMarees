package outils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modele.HauteursDeMerUnPort;
import modele.MareesUnPort;
/**
 * Classe qui contient les méthodes statiques qui permettent la transcritption et l'écriture en fichiers objets d'objets
 *
 */
public class EcritureFichierSer {
	/*
	
	 * Méthode qui permet d'écrire un fichier objet à partir d'un objet
	 * @param parFichier Le fichier qu'on souhaite écrire
	 * @param parObjet L'objet qu'on souhaite sérialiser
	
	////CETTE MÉTHODE N'EST PAS UTILISÉ ICI, ELLE A ÉTÉ DÉVELOPPÉE PAR L'ÉQUIPE PEDAGOQIQUE DE L'IUT DE VÉLIZY - DÉPARTEMENT INFORMATIQUE
	
	public static void ecriture (File parFichier, Object parObjet) {
		ObjectOutputStream flux = null;
		// Ouverture du fichier en mode écriture
		try {
			flux = new ObjectOutputStream (new FileOutputStream (parFichier));
			flux.writeObject (parObjet);
			flux.flush ();
			flux.close ();
		}
		catch (IOException parException) {
			System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
			System.exit (1);
		}
	}
	
	*/
	
	/**
	 * Cette méthode est appelée si on souhaite écrire toutes les données du dossier "data_ports/datas/" en fichiers objets (Maree/MareesUnJour/MareesUnPort) dans le dossier "data_ports/datas_ser"
	 */
	public static void ecrireTout() {
		
		//Définition du dossier qui contient les données et de la liste des fichiers de données
		File dataFolder = new File("data_ports/datas/");
		File[] dataFiles = dataFolder.listFiles();
		
		//Pour chaque fichier
		for(int i = 0 ; i < dataFiles.length ; i++) {
			//S'il s'agit bien du premier fichier qui concerne un des ports
			if(dataFiles[i].getName().split("-")[0].equals("06")) {
				
				//On définit le nom du port et on l'initialise à une valeur nulle et on définit une variable de boucle qu'on initialise à 0.
				String nomDuPort = "";
				int compteur = 0;
				
				//On boucle sur chaque sous-mot du nom du fichier (les sous mots étant généré par la division du mot au niveau de ses "_" .Tant qu'on a pas atteint la fin du nom du port dans le nom du fichier, on boucle.
				//Cette partie du code permet de traiter les noms composés de ports
				while(!dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur].equals("2021")) {
					//Si le sous-mot suivant est le dernier mot, on ajoute le mot courant au nom du port
					if(dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur+1].equals("2021"))
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur];
					//Sinon on fait de même mais on rajoute "_" à la suite
					else
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur] + "_";
					compteur++;
				}
				//On lit toutes les marées de tous les fichiers qui contiennent les données du port en question (ici on a 4 fichiers par port)
				MareesUnPort mareesPort = LectureFichierTxt.lecture(nomDuPort);
				
				//On génère un flux de sortie, nul pour le moment
				ObjectOutputStream flux = null;
				try {
					//On génère le flux de sortie sur le fichier serialisé qu'on souhaite créer
					flux = new ObjectOutputStream (new FileOutputStream (new File("data_ports//datas_ser//" + mareesPort.getNomPort() + ".ser")));
					//On écrit l'objet dans le fichier
					flux.writeObject (mareesPort);
					//On ferme le flux
					flux.flush ();
					flux.close ();
				}
				catch (IOException parException) {
					System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
					System.exit (1);
				}
			}
		}

		
	}
	
	/**
	 * Cette méthode permet d'écrire les données du dossier "data_ports/datas" en fichiers objets correspondant. On traitera ici uniquement les fichiers qui n'ont pas encore été traités (les fichiers dont les marées ne sont pas sérialisées).
	 * On l'appelle au démarrage du programme
	 * 
	 */
	
	//Cette méthode fonctionne sensiblement de la même manière que la précédente à la seule différence qu'on compare les dossiers contenant les fichiers de données et les fichiers contenant les fichiers sérialisés 
	//Une fois cette comparaison faite, on ne sérialise que les fichiers qui ne le sont pas encore
	public static void ecrireDemarrage() {

		
		File dataFolderTxt = new File("data_ports/datas/");
		File[] dataFilesTxt = dataFolderTxt.listFiles();
		
		String nomDuPort ="";
		Boolean ecrire = true;

		//Instanctiation du dossier des fichiers sérialisés
		File dataFolderSer= new File("data_ports/datas_ser/");
		File[] dataFilesSer= dataFolderSer.listFiles();
		
		for(int i = 0 ; i < dataFilesTxt.length ; i++) {
			//Définition du nom du port traité
			if(dataFilesTxt[i].getName().split("-")[0].equals("06")) {
				nomDuPort = "";
				int compteur = 0;
				while(!dataFilesTxt[i].getName().substring(3,dataFilesTxt[i].getName().length()).split("_")[compteur].equals("2021")) {
					if(dataFilesTxt[i].getName().substring(3,dataFilesTxt[i].getName().length()).split("_")[compteur+1].equals("2021"))
						nomDuPort+= dataFilesTxt[i].getName().substring(3,dataFilesTxt[i].getName().length()).split("_")[compteur];
					else
						nomDuPort+= dataFilesTxt[i].getName().substring(3,dataFilesTxt[i].getName().length()).split("_")[compteur] + "_";
					compteur++;
				}
			}
			//Définition de si oui ou non on s'apprête à écrire les données sous fichiers serialisés.
			for(int j = 0 ; j < dataFilesSer.length ; j++) {
				if(dataFilesSer[j].getName().split(".ser")[0].equals(nomDuPort)) {
					ecrire = false;
				}
			}
			//Si on doit écrire, alors on le fait
			if(ecrire) {
				MareesUnPort mareesPort = LectureFichierTxt.lecture(nomDuPort);
				ObjectOutputStream flux = null;
				try {
					flux = new ObjectOutputStream (new FileOutputStream (new File("data_ports//datas_ser//" + mareesPort.getNomPort() + ".ser")));
					flux.writeObject (mareesPort);
					flux.flush ();
					flux.close ();
				}
				catch (IOException parException) {
					System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
					System.exit (1);
				}
			}
			ecrire = true;
		}
		
	}
	
	
	public static void ecrireDemarrageHeureParHeure() {
		
		//Definition du tableau de fichiers txt heure/heure
		File dataFolderTxt = new File("data_ports/data_treated/");
		File[] dataFilesTxt = dataFolderTxt.listFiles();
		
		//Définition du tableau de fichiers ser heure/heure
		File dataFolderSer = new File("data_ports/data_hph_ser");
		File[] dataFilesSer = dataFolderSer.listFiles();
		
		//Définition de la variable qui contient le nom du port traité et de celle qui indique si on doit écrire ou non
		String nomPort = null;		
		Boolean ecrire = true;
		
		//Pour chaque fichier du dossier qui contient les fichiers traités en amont (LectureFichierTxt.traitementHeureParHeure)
		if(dataFilesTxt != null) {
			for(int i = 0 ; i < dataFilesTxt.length ; i++) {
				//On définit la nom du port comme le nom du fichier moins son extension
				nomPort = dataFilesTxt[i].getName().split(".txt")[0];
				
				//Pour chaque port qu'on a déjà sérialisé
				for(int j = 0 ; j < dataFilesSer.length ; j++) {
					//Si le port courant est déjà sérialisé alors on ne l'écrit pas
					if(dataFilesSer[j].getName().split(".ser")[0].equals(nomPort)) {
						ecrire = false;
					}
				}
				
				//Si le port courant n'a pas encore été sérialisé (si son .ser n'existe pas)
				if(ecrire) {
					//On l'instancie à partir de son fichier (voir LectureFichierTxt.lectureHauteursDeMerUnPort())
					HauteursDeMerUnPort hauteursPort = LectureFichierTxt.lectureHauteursDeMerUnPort(nomPort);
					ObjectOutputStream flux = null;
					try {
						//Instanciation du flux en sortie
						flux = new ObjectOutputStream (new FileOutputStream (new File("data_ports//data_hph_ser//" + hauteursPort.getPort() + ".ser")));
						
						//On écrit l'objet
						flux.writeObject (hauteursPort);
						
						//Fermeture du flux
						flux.flush ();
						flux.close ();
					}
					catch (IOException parException) {
						System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
						System.exit (1);
					}
				}
				ecrire = true;
				
			}
		}
	}
}
