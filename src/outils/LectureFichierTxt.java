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
 * Classe qui permet de lire les fichier .txt pour en r�cup�rer des objets complets
 */

public class LectureFichierTxt {

	
	/**
	 * M�thode qui lit les mar�es d'un seul port dans le fichier de donn�es correspondant.
	 * @param nomPort le nom du port duquel on veut extraire les mar�es.
	 * @return un objet MareesUnPort qui contient toutes les mar�es enregistr�es pour le port
	 */
	public static MareesUnPort lecture (String nomPort) {
		
		//D�finition de la map de Mar�esUnJour
		TreeMap<Date,MareesUnJour> mareesUnPort = new TreeMap<Date,MareesUnJour>();
		try { 
			//D�finition du dossier qui contient les donn�es, puis du tableau les contenants
			File dataFolder = new File("data_ports/datas/");
			File[] dataFiles = dataFolder.listFiles();
			
			//Pour chacun des fichiers du dossier
			for(int i = 0 ; i < dataFiles.length ; i++) {
				//On ne traite que les fichiers qui correspondent au port demand�.
				if(dataFiles[i].getName().substring(3,dataFiles[i].getName().length()).split("_")[0].equals(nomPort.split("_")[0])) {
					//On d�finit le buffer, et la ligne lue
					BufferedReader buffer = new BufferedReader (new InputStreamReader ( new FileInputStream (dataFiles[i]), StandardCharsets.UTF_8));
					String ligneLue ; 
					
					//D�finition du compteur de lignes
					int j = 1 ;
					MareesUnJour mareesAujd;
					
					//Pour chacune des lignes du fichier
					while ((ligneLue = buffer.readLine()) != null) {
						//Si on est sorti de l'ent�te du fichier
						if(j>4) {
							//On remplace les caract�res qui remplacent des nombres par des nombres (traitement de nombres en aval)
							ligneLue = ligneLue.replaceAll("---", "999");
							ligneLue = ligneLue.replaceAll("--", "999");
							
							//D�finition de la date de la mar�e � partir des donn�es lues dans la ligne
							Date date_marree = new Date(Integer.parseInt(ligneLue.split("	")[0].split("-")[2]),Integer.parseInt(ligneLue.split("	")[0].split("-")[1]),Integer.parseInt(ligneLue.split("	")[0].split("-")[0]));
						
							//D�finition des quatre mar�es � partir des donn�es lues dans la ligne
							Maree maree1 = new Maree("pleine mer",date_marree,ligneLue.split("	")[1].split(":")[0],ligneLue.split("	")[1].split(":")[1],Double.parseDouble(ligneLue.split("	")[2]),Integer.parseInt(ligneLue.split("	")[3]));
							Maree maree2 = new Maree("pleine mer",date_marree,ligneLue.split("	")[4].split(":")[0],ligneLue.split("	")[4].split(":")[1],Double.parseDouble(ligneLue.split("	")[5]),Integer.parseInt(ligneLue.split("	")[6]));
							Maree maree3 = new Maree("basse mer",date_marree,ligneLue.split("	")[7].split(":")[0],ligneLue.split("	")[7].split(":")[1],Double.parseDouble(ligneLue.split("	")[8]));
							Maree maree4 = new Maree("basse mer",date_marree,ligneLue.split("	")[9].split(":")[0],ligneLue.split("	")[9].split(":")[1],Double.parseDouble(ligneLue.split("	")[10]));
							
							//D�finition de l'objet MareeUnJour pour le jour lu, avec les quatres mar�es lues.
							mareesAujd = new MareesUnJour(new ArrayList<Maree>(Arrays.asList(maree1,maree2,maree3,maree4)),nomPort,date_marree);
							
							//Ajout de cette MareeUnJour � la map de marees du port
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
	 * Cette m�thode g�n�re des fichiers.txt des hauteurs de mer heure par heure pour tous les ports pour lesquels on a des donn�es d'horaires de mar�es.
	 * Elle traite tous les fichiers et supprime les fichiers qui ne sont pas des .txt
	 */
	public static void traitementHeureParHeure() {
		TreeMap<Date,HauteursDeMerUnJour> hauteursUnPort = new TreeMap<Date,HauteursDeMerUnJour>();
		
		try {
			//d�finition de la liste de fichiers � traiter
			File dataFolder = new File("data_ports/datas_hph/");
			File[] dataFiles = dataFolder.listFiles();
			
			//d�finition de la liste des ports qu'on traite
			File portsTraitesFolder = new File("data_ports/datas_ser/");
			File[] portsTraitesFiles = portsTraitesFolder.listFiles();
			
			//d�finition de la liste des ports qu'on a d�j� trait�s
			File alreadyTreatedFolder = new File("data_ports/data_treated/");
			File[] alreadyTreatedFiles = alreadyTreatedFolder.listFiles();
			
			
			//Pour chacun des fichiers
			for(int i = 0 ; i < dataFiles.length ; i++) {
				
				//D�finition de l'index auquel l'extension du fichier commence
				int indexExt = dataFiles[i].getName().lastIndexOf(".");
				
				//D�finition de l'extension du fichier
				String ext = dataFiles[i].getName().substring(indexExt+1);
				
				//D�finition de l'identifiant du port (premiers caract�res dans les fichiers heure/heure du SHOM)
				String numPort = dataFiles[i].getName().split("_")[0];
				
				//Si le fichier est bien un .txt
				if(ext.equals("txt")) {
					
					//On charge le buffer de lecture des lignes du fichier
					BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(dataFiles[i]),StandardCharsets.UTF_8));
					
					//D�finition des variables n�cessaires, la ligne lue, le nom du port trait�, le num�ro de la ligne trait�e
					String ligneLue;
					String nomPort = null;
					int numLigne = 0;
	


					//On traite la premi�re ligne pour r�cup�rer le nom du port
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
					
					//Si on doit �crire
					if(ecrire) {

						//Alors on lit chaque ligne
						while((ligneLue = buffer.readLine()) != null) {
									
							//On d�finit l'�criture dans le nouveau fichier format�, qui aura le nom du port trait� + ".txt"
							FileWriter myWriter = new FileWriter("data_ports/data_treated/"+nomPort+".txt",true);
									
							//On v�rifie que la ligne trait�e ne fait pas partie de l'ent�te du fichier
							if(!ligneLue.contains("#")) {
								
								//Si cette ligne traite bien de donn�es exp�di�es par la premi�re station, si elle traite bien d'une heure ex : "00h01" ou "11h01" (les fichiers de donn�es commencent tous par un horaire en 01);
								if(ligneLue.split(" ")[1].split(";")[2].equals("1") && ligneLue.split(" ")[1].split(";")[0].split(":")[1].equals("01")) {
											
									//On rajoute la ligne dans le fichier trait�.
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
	 * M�thode qui permet de lire un objet HauteursDeMerUnPort dans les fichiers.txt trait�s � partir du nom du port
	 * @param nomPort Le nom du port trait�
	 * @return l'objet HauteursDeMerUnPort qui contient toutes les hauteurs enregistr�es pour ce port
	 */
	public static HauteursDeMerUnPort lectureHauteursDeMerUnPort(String nomPort) {
		
		
		//D�finition de la map de HauteursDeMerUnJour
		TreeMap<Date,HauteursDeMerUnJour> hauteursUnPort = new TreeMap<Date,HauteursDeMerUnJour>();
		try { 
			//D�finition du dossier qui contient les donn�es, puis du tableau les contenants
			File dataFolder = new File("data_ports/data_treated/");
			File[] dataFiles = dataFolder.listFiles();
			
			//Si le dossier n'est pas vide
			if(dataFiles != null) {
				//Pour chacun des fichiers du dossier
				for(int i = 0 ; i < dataFiles.length ; i++) {
					//On ne traite que le fichier qui correspond au port demand�.
					if(dataFiles[i].getName().split(".txt")[0].equals(nomPort)) {
						//On d�finit le buffer, et la ligne lue
						BufferedReader buffer = new BufferedReader (new InputStreamReader ( new FileInputStream (dataFiles[i]), StandardCharsets.UTF_8));
						String ligneLue ; 
						
						//D�finition du compteur de lignes
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
						
							//D�finition de la date de la mar�e � partir des donn�es lues dans la ligne
							dateTraitee = new Date(Integer.parseInt(date.split("/")[0]),Integer.parseInt(date.split("/")[1]),Integer.parseInt(date.split("/")[2]));
							//D�finition des quatre mar�es � partir des donn�es lues dans la ligne
							HauteurDeMer hauteurDeMer = new HauteurDeMer(Double.parseDouble(hauteur),Integer.parseInt(heure));
							
							//On v�rifie qu'on a pas chang� de date / Java nous obligeant � initialiser nos varibles on est oblig�s d'accepter la premi�re date pour ensuite la modifier (en faire une old_date)
							if(numLigne<1 || dateTraitee.compareTo(oldDate) == 0) {
								hauteursUnJour.put(Integer.parseInt(heure),hauteurDeMer);
							}
							
							//Si on a chang� de date, on enregistre l'objet pour la journ�e compl�te que l'on vient de finir, puis on passe � la journ�e suivante
							else {
								hauteursAujd = new HauteursDeMerUnJour(new TreeMap<Integer,HauteurDeMer>(hauteursUnJour),oldDate);
								hauteursUnPort.put(oldDate, hauteursAujd);
								hauteursUnJour.clear();
								hauteursUnJour.put(Integer.parseInt(heure),hauteurDeMer);
							}
							
	
							//incr�mentations & passage en old
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
		//D�finition de l'objet � renvoyer � partir de la treeMap construite
		return new HauteursDeMerUnPort(hauteursUnPort,nomPort);
	}
}