package outils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modele.HauteursDeMerUnPort;
import modele.MareesUnPort;
/**
 * Classe qui contient les m�thodes statiques qui permettent la transcritption et l'�criture en fichiers objets d'objets
 *
 */
public class EcritureFichierSer {
	/*
	
	 * M�thode qui permet d'�crire un fichier objet � partir d'un objet
	 * @param parFichier Le fichier qu'on souhaite �crire
	 * @param parObjet L'objet qu'on souhaite s�rialiser
	
	////CETTE M�THODE N'EST PAS UTILIS� ICI, ELLE A �T� D�VELOPP�E PAR L'�QUIPE PEDAGOQIQUE DE L'IUT DE V�LIZY - D�PARTEMENT INFORMATIQUE
	
	public static void ecriture (File parFichier, Object parObjet) {
		ObjectOutputStream flux = null;
		// Ouverture du fichier en mode �criture
		try {
			flux = new ObjectOutputStream (new FileOutputStream (parFichier));
			flux.writeObject (parObjet);
			flux.flush ();
			flux.close ();
		}
		catch (IOException parException) {
			System.err.println ("Probleme a l�ecriture\n" + parException.toString ());
			System.exit (1);
		}
	}
	
	*/
	
	/**
	 * Cette m�thode est appel�e si on souhaite �crire toutes les donn�es du dossier "data_ports/datas/" en fichiers objets (Maree/MareesUnJour/MareesUnPort) dans le dossier "data_ports/datas_ser"
	 */
	public static void ecrireTout() {
		
		//D�finition du dossier qui contient les donn�es et de la liste des fichiers de donn�es
		File dataFolder = new File("data_ports/datas/");
		File[] dataFiles = dataFolder.listFiles();
		
		//Pour chaque fichier
		for(int i = 0 ; i < dataFiles.length ; i++) {
			//S'il s'agit bien du premier fichier qui concerne un des ports
			if(dataFiles[i].getName().split("-")[0].equals("06")) {
				
				//On d�finit le nom du port et on l'initialise � une valeur nulle et on d�finit une variable de boucle qu'on initialise � 0.
				String nomDuPort = "";
				int compteur = 0;
				
				//On boucle sur chaque sous-mot du nom du fichier (les sous mots �tant g�n�r� par la division du mot au niveau de ses "_" .Tant qu'on a pas atteint la fin du nom du port dans le nom du fichier, on boucle.
				//Cette partie du code permet de traiter les noms compos�s de ports
				while(!dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur].equals("2021")) {
					//Si le sous-mot suivant est le dernier mot, on ajoute le mot courant au nom du port
					if(dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur+1].equals("2021"))
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur];
					//Sinon on fait de m�me mais on rajoute "_" � la suite
					else
						nomDuPort+= dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[compteur] + "_";
					compteur++;
				}
				//On lit toutes les mar�es de tous les fichiers qui contiennent les donn�es du port en question (ici on a 4 fichiers par port)
				MareesUnPort mareesPort = LectureFichierTxt.lecture(nomDuPort);
				
				//On g�n�re un flux de sortie, nul pour le moment
				ObjectOutputStream flux = null;
				try {
					//On g�n�re le flux de sortie sur le fichier serialis� qu'on souhaite cr�er
					flux = new ObjectOutputStream (new FileOutputStream (new File("data_ports//datas_ser//" + mareesPort.getNomPort() + ".ser")));
					//On �crit l'objet dans le fichier
					flux.writeObject (mareesPort);
					//On ferme le flux
					flux.flush ();
					flux.close ();
				}
				catch (IOException parException) {
					System.err.println ("Probleme a l�ecriture\n" + parException.toString ());
					System.exit (1);
				}
			}
		}

		
	}
	
	/**
	 * Cette m�thode permet d'�crire les donn�es du dossier "data_ports/datas" en fichiers objets correspondant. On traitera ici uniquement les fichiers qui n'ont pas encore �t� trait�s (les fichiers dont les mar�es ne sont pas s�rialis�es).
	 * On l'appelle au d�marrage du programme
	 * 
	 */
	
	//Cette m�thode fonctionne sensiblement de la m�me mani�re que la pr�c�dente � la seule diff�rence qu'on compare les dossiers contenant les fichiers de donn�es et les fichiers contenant les fichiers s�rialis�s 
	//Une fois cette comparaison faite, on ne s�rialise que les fichiers qui ne le sont pas encore
	public static void ecrireDemarrage() {

		
		File dataFolderTxt = new File("data_ports/datas/");
		File[] dataFilesTxt = dataFolderTxt.listFiles();
		
		String nomDuPort ="";
		Boolean ecrire = true;

		//Instanctiation du dossier des fichiers s�rialis�s
		File dataFolderSer= new File("data_ports/datas_ser/");
		File[] dataFilesSer= dataFolderSer.listFiles();
		
		for(int i = 0 ; i < dataFilesTxt.length ; i++) {
			//D�finition du nom du port trait�
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
			//D�finition de si oui ou non on s'appr�te � �crire les donn�es sous fichiers serialis�s.
			for(int j = 0 ; j < dataFilesSer.length ; j++) {
				if(dataFilesSer[j].getName().split(".ser")[0].equals(nomDuPort)) {
					ecrire = false;
				}
			}
			//Si on doit �crire, alors on le fait
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
					System.err.println ("Probleme a l�ecriture\n" + parException.toString ());
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
		
		//D�finition du tableau de fichiers ser heure/heure
		File dataFolderSer = new File("data_ports/data_hph_ser");
		File[] dataFilesSer = dataFolderSer.listFiles();
		
		//D�finition de la variable qui contient le nom du port trait� et de celle qui indique si on doit �crire ou non
		String nomPort = null;		
		Boolean ecrire = true;
		
		//Pour chaque fichier du dossier qui contient les fichiers trait�s en amont (LectureFichierTxt.traitementHeureParHeure)
		if(dataFilesTxt != null) {
			for(int i = 0 ; i < dataFilesTxt.length ; i++) {
				//On d�finit la nom du port comme le nom du fichier moins son extension
				nomPort = dataFilesTxt[i].getName().split(".txt")[0];
				
				//Pour chaque port qu'on a d�j� s�rialis�
				for(int j = 0 ; j < dataFilesSer.length ; j++) {
					//Si le port courant est d�j� s�rialis� alors on ne l'�crit pas
					if(dataFilesSer[j].getName().split(".ser")[0].equals(nomPort)) {
						ecrire = false;
					}
				}
				
				//Si le port courant n'a pas encore �t� s�rialis� (si son .ser n'existe pas)
				if(ecrire) {
					//On l'instancie � partir de son fichier (voir LectureFichierTxt.lectureHauteursDeMerUnPort())
					HauteursDeMerUnPort hauteursPort = LectureFichierTxt.lectureHauteursDeMerUnPort(nomPort);
					ObjectOutputStream flux = null;
					try {
						//Instanciation du flux en sortie
						flux = new ObjectOutputStream (new FileOutputStream (new File("data_ports//data_hph_ser//" + hauteursPort.getPort() + ".ser")));
						
						//On �crit l'objet
						flux.writeObject (hauteursPort);
						
						//Fermeture du flux
						flux.flush ();
						flux.close ();
					}
					catch (IOException parException) {
						System.err.println ("Probleme a l�ecriture\n" + parException.toString ());
						System.exit (1);
					}
				}
				ecrire = true;
				
			}
		}
	}
}
