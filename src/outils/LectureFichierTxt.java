package outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import modele.Date;
import modele.HauteurDeMer;
import modele.HauteursDeMerUnJour;
import modele.HauteursDeMerUnPort;
import modele.Maree;
import modele.MareesUnJour;
import modele.MareesUnPort;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
 
/**
 * Classe qui permet de lire les fichier .txt pour en récupérer des objets complets
 */

public class LectureFichierTxt {

	
	/**
	 * Méthode qui lit les marées d'un seul port dans le fichier de données correspondant.
	 * @param nomPort le nom du port duquel on veut extraire les marées.
	 * @return un objet MareesUnPort qui contient toutes les marées enregistrées pour le port
	 */
	public static MareesUnPort lecture (String nomPort) {
		
		//Définition de la map de MaréesUnJour
		TreeMap<Date,MareesUnJour> mareesUnPort = new TreeMap<Date,MareesUnJour>();
		try { 
			//Définition du dossier qui contient les données, puis du tableau les contenants
			File dataFolder = new File("data_ports/datas/");
			File[] dataFiles = dataFolder.listFiles();
			
			//Pour chacun des fichiers du dossier
			for(int i = 0 ; i < dataFiles.length ; i++) {
				//On ne traite que les fichiers qui correspondent au port demandé.
				if(dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[0].equals(nomPort.split("_")[0])) {
					//On définit le buffer, et la ligne lue
					BufferedReader buffer = new BufferedReader (new InputStreamReader ( new FileInputStream (dataFiles[i]), StandardCharsets.UTF_8));
					String ligneLue ; 
					
					//Définition du compteur de lignes
					int j = 1 ;
					MareesUnJour mareesAujd;
					
					//Pour chacune des lignes du fichier
					while ((ligneLue = buffer.readLine()) != null) {
						//Si on est sorti de l'entête du fichier
						if(j>4) {
							//On remplace les caractères qui remplacent des nombres par des nombres (traitement de nombres en aval)
							ligneLue = ligneLue.replaceAll("---", "999");
							ligneLue = ligneLue.replaceAll("--", "999");
							
							//Définition de la date de la marée à partir des données lues dans la ligne
							Date date_marree = new Date(Integer.parseInt(ligneLue.split("	")[0].split("-")[2]),Integer.parseInt(ligneLue.split("	")[0].split("-")[1]),Integer.parseInt(ligneLue.split("	")[0].split("-")[0]));
						
							//Définition des quatre marées à partir des données lues dans la ligne
							Maree maree1 = new Maree("pleine mer",date_marree,ligneLue.split("	")[1].split(":")[0],ligneLue.split("	")[1].split(":")[1],Double.parseDouble(ligneLue.split("	")[2]),Integer.parseInt(ligneLue.split("	")[3]));
							Maree maree2 = new Maree("pleine mer",date_marree,ligneLue.split("	")[4].split(":")[0],ligneLue.split("	")[4].split(":")[1],Double.parseDouble(ligneLue.split("	")[5]),Integer.parseInt(ligneLue.split("	")[6]));
							Maree maree3 = new Maree("basse mer",date_marree,ligneLue.split("	")[7].split(":")[0],ligneLue.split("	")[7].split(":")[1],Double.parseDouble(ligneLue.split("	")[8]));
							Maree maree4 = new Maree("basse mer",date_marree,ligneLue.split("	")[9].split(":")[0],ligneLue.split("	")[9].split(":")[1],Double.parseDouble(ligneLue.split("	")[10]));
							
							//Définition de l'objet MareeUnJour pour le jour lu, avec les quatres marées lues.
							mareesAujd = new MareesUnJour(new ArrayList<Maree>(Arrays.asList(maree1,maree2,maree3,maree4)),nomPort,date_marree);
							
							//Ajout de cette MareeUnJour à la map de marees du port
							mareesUnPort.put(date_marree,mareesAujd);
						}
						j++;
					}
				}
			}

		} // try
		catch (IOException parException) { 
				System.out.println("Erreur dans la lecture du fichier");
		}
		return new MareesUnPort(mareesUnPort);
	}
	

	/**
	 * Cette méthode génère des fichiers.txt des hauteurs de mer heure par heure pour tous les ports pour lesquels on a des données d'horaires de marées.
	 * Elle traite tous les fichiers et supprime les fichiers qui ne sont pas des .txt
	 */
	public static void traitementHeureParHeure() {
		TreeMap<Date,HauteursDeMerUnJour> hauteursUnPort = new TreeMap<Date,HauteursDeMerUnJour>();
		
		try {
			//définition de la liste de fichiers à traiter
			File dataFolder = new File("data_ports/datas_hph/");
			File[] dataFiles = dataFolder.listFiles();
			
			//définition de la liste des ports qu'on traite
			File portsTraitesFolder = new File("data_ports/datas_ser/");
			File[] portsTraitesFiles = portsTraitesFolder.listFiles();
			
			//définition de la liste des ports qu'on a déjà traités
			File alreadyTreatedFolder = new File("data_ports/data_treated/");
			File[] alreadyTreatedFiles = alreadyTreatedFolder.listFiles();
			
			
			//Pour chacun des fichiers
			for(int i = 0 ; i < dataFiles.length ; i++) {
				
				//Définition de l'index auquel l'extension du fichier commence
				int indexExt = dataFiles[i].getName().lastIndexOf(".");
				
				//Définition de l'extension du fichier
				String ext = dataFiles[i].getName().substring(indexExt+1);
				
				//Définition de l'identifiant du port (premiers caractères dans les fichiers heure/heure du SHOM)
				String numPort = dataFiles[i].getName().split("_")[0];
				
				//Si le fichier est bien un .txt
				if(ext.equals("txt")) {
					
					//On charge le buffer de lecture des lignes du fichier
					BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(dataFiles[i]),StandardCharsets.UTF_8));
					
					//Définition des variables nécessaires, la ligne lue, le nom du port traité, le numéro de la ligne traitée
					String ligneLue;
					String nomPort = null;
					int numLigne = 0;
	


					//On traite la première ligne pour récupérer le nom du port
					ligneLue = buffer.readLine();
					nomPort = ligneLue.split("Station : ")[1];
					numLigne +=1;		
					
					
					Boolean ecrire = false;

					
					for(int j = 0 ; j < portsTraitesFiles.length ; j++) {
						if(portsTraitesFiles[j].getName().split(".ser")[0].equals(nomPort)) {
							ecrire = true;
						}
					}
					
					for(int j = 0 ; j < alreadyTreatedFiles.length ; j++) {
						if(alreadyTreatedFiles[j].getName().split(".txt")[0].equals(nomPort)) {
							ecrire = false;
						}
					}
					
					//Si on doit écrire
					if(ecrire) {

						//Alors on lit chaque ligne
						while((ligneLue = buffer.readLine()) != null) {
									
							//On définit l'écriture dans le nouveau fichier formaté, qui aura le nom du port traité + ".txt"
							FileWriter myWriter = new FileWriter("data_ports/data_treated/"+nomPort+".txt",true);
									
							//On vérifie que la ligne traitée ne fait pas partie de l'entête du fichier
							if(!ligneLue.contains("#")) {
								
								//Si cette ligne traite bien de données expédiées par la première station, si elle traite bien d'une heure ex : "00h01" ou "11h01" (les fichiers de données commencent tous par un horaire en 01);
								if(ligneLue.split(" ")[1].split(";")[2].equals("1") && ligneLue.split(" ")[1].split(";")[0].split(":")[1].equals("01")) {
											
									//On rajoute la ligne dans le fichier traité.
									myWriter.append(ligneLue+"\n");
									myWriter.close();
								}
							}
							numLigne++;
						}
					}
				}
				
				//Sinon on le supprime
				else {
					dataFiles[i].delete();
				}
			}
		}
		catch(IOException parException) {
			System.out.println("Erreur dans la lecture du fichier");
		}
	}

	/**
	 * Méthode qui permet de lire un objet HauteursDeMerUnPort dans les fichiers.txt traités à partir du nom du port
	 * @param nomPort Le nom du port traité
	 * @return l'objet HauteursDeMerUnPort qui contient toutes les hauteurs enregistrées pour ce port
	 */
	public static HauteursDeMerUnPort lectureHauteursDeMerUnPort(String nomPort) {
		
		
		//Définition de la map de HauteursDeMerUnJour
		TreeMap<Date,HauteursDeMerUnJour> hauteursUnPort = new TreeMap<Date,HauteursDeMerUnJour>();
		try { 
			//Définition du dossier qui contient les données, puis du tableau les contenants
			File dataFolder = new File("data_ports/data_treated/");
			File[] dataFiles = dataFolder.listFiles();
			
			//Si le dossier n'est pas vide
			if(dataFiles != null) {
				//Pour chacun des fichiers du dossier
				for(int i = 0 ; i < dataFiles.length ; i++) {
					//On ne traite que le fichier qui correspond au port demandé.
					if(dataFiles[i].getName().split(".txt")[0].equals(nomPort)) {
						//On définit le buffer, et la ligne lue
						BufferedReader buffer = new BufferedReader (new InputStreamReader ( new FileInputStream (dataFiles[i]), StandardCharsets.UTF_8));
						String ligneLue ; 
						
						//Définition du compteur de lignes
						int numLigne = 0 ;
						HauteursDeMerUnJour hauteursAujd;
						
						//Pour chacune des lignes du fichier
						Date oldDate = new Date(); 
						TreeMap<Integer,HauteurDeMer> hauteursUnJour = new TreeMap<Integer,HauteurDeMer>();
						while ((ligneLue = buffer.readLine()) != null) {
							
							
							
							Date dateTraitee;
							String date = ligneLue.split(" ")[0];
							String donnees = ligneLue.split(" ")[1];
							String horaire = donnees.split(";")[0];
							String hauteur = donnees.split(";")[1];
							String heure = horaire.split(":")[0];
						
							//Définition de la date de la marée à partir des données lues dans la ligne
							dateTraitee = new Date(Integer.parseInt(date.split("/")[0]),Integer.parseInt(date.split("/")[1]),Integer.parseInt(date.split("/")[2]));
							//Définition des quatre marées à partir des données lues dans la ligne
							HauteurDeMer hauteurDeMer = new HauteurDeMer(Double.parseDouble(hauteur),Integer.parseInt(heure));
							
							//On vérifie qu'on a pas changé de date / Java nous obligeant à initialiser nos varibles on est obligés d'accepter la première date pour ensuite la modifier (en faire une old_date)
							if(numLigne<1 || dateTraitee.compareTo(oldDate) == 0) {
								hauteursUnJour.put(Integer.parseInt(heure),hauteurDeMer);
							}
							
							//Si on a changé de date, on enregistre l'objet pour la journée complète que l'on vient de finir, puis on passe à la journée suivante
							else {
								hauteursAujd = new HauteursDeMerUnJour(new TreeMap<Integer,HauteurDeMer>(hauteursUnJour),oldDate);
								hauteursUnPort.put(oldDate, hauteursAujd);
								hauteursUnJour.clear();
								hauteursUnJour.put(Integer.parseInt(heure),hauteurDeMer);
							}
							
	
							//incrémentations & passage en old
							oldDate = dateTraitee;
							numLigne++;
						}
					}
				}
			}
		} // try
		catch (IOException parException) { 
				System.out.println("Erreur dans la lecture du fichier");
		}
		//Définition de l'objet à renvoyer à partir de la treeMap construite
		return new HauteursDeMerUnPort(hauteursUnPort,nomPort);
	}
}