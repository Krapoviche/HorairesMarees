package outils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modele.HauteursDeMerUnPort;
import modele.MareesUnPort;

public class EcritureFichierSer {
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
	public static void ecrireTout() {
		
		
		File dataFolder = new File("data_ports/datas/");
		File[] dataFiles = dataFolder.listFiles();
		for(int i = 0 ; i < dataFiles.length ; i++) {
			if(dataFiles[i].getName().split("-")[0].equals("06")) {
				String nomDuPort = "";
				int compteur = 0;
				while(!dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur].equals("2021")) {
					if(dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur+1].equals("2021"))
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur];
					else
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur] + "_";
					compteur++;
				}
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
		}

		
	}
	
	
	public static void ecrireDemarrage() {

		
		File dataFolderTxt = new File("data_ports/datas/");
		File[] dataFilesTxt = dataFolderTxt.listFiles();
		
		String nomDuPort ="";
		Boolean ecrire = true;

		File dataFolderSer= new File("data_ports/datas_ser/");
		File[] dataFilesSer= dataFolderSer.listFiles();
		
		
		for(int i = 0 ; i < dataFilesTxt.length ; i++) {
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
			
			for(int j = 0 ; j < dataFilesSer.length ; j++) {
				if(dataFilesSer[j].getName().split(".ser")[0].equals(nomDuPort)) {
					ecrire = false;
				}
			}
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
