package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

/**
 * Classe du modele de la table qui affiche les hauteurs de mer heure par heure
 *
 */
public class ModeleMareeTable extends DefaultTableModel {
	HauteursDeMerUnJour hauteursUnJour;
	
		/**
		 * Constructeur qui permet l'instanciation d'objets à partir d'un objet HauteursDeMerUnJour
		 * @param hauteursUnJour L'objet HauteursDeMerUnJour en question
		 */
		public ModeleMareeTable(HauteursDeMerUnJour hauteursUnJour) {
			
			this.hauteursUnJour = hauteursUnJour;
			
			//Définition de différents champs du modèle (Intitulés, nombre de colonnes ...)
			String[] intitules = {"HEURE","HAUTEUR"};
			setColumnCount(2);
			setColumnIdentifiers(intitules);
				
			//Définition de la liste des clés qui permettra de lire la map de hauteurs de mer
			Set<Integer> keys;
			
			//Si jamais l'objet en paramètre n'est pas nul (Si les données pour ce jour et ce port existent)
			if(hauteursUnJour != null) {
				//On génère le set de clés pour lire la Map de hauteurs de mer
				keys =  hauteursUnJour.getHauteursUnJour().keySet();
				//On définit la taille du tableau en fonction de la taille de la map
				setRowCount(hauteursUnJour.getHauteursUnJour().size());
			}
			//Si les données n'existent pas
			else {
				//Il n'y a donc pas de clé
				keys = null;
				//On affiche un tableau plein
				setRowCount(24);
			}
				
			
			
			
			int compteurLigne = 0;
			//S'il y a des données
			if(keys != null) {
				//Pour chaque entrée de la map
				for(Integer i : keys) {
					//Si on est après 10h
					if(i >= 10) {
						//On rentre les données dans le tableau
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt(hauteursUnJour.getHauteursUnJour().get(i).getHauteur(),compteurLigne,1);
						compteurLigne++;
					}
					//Sinon
					else {
						//On rentre les données dans le tableau
						setValueAt("0"+i+"h00",compteurLigne,0);
						setValueAt(hauteursUnJour.getHauteursUnJour().get(i).getHauteur(),compteurLigne,1);
						compteurLigne++;
					}
				}
			}
			//Sinon
			else {
				//Pour chaque lignes (24 car 24h)
				for(int i = 0 ; i < getRowCount() ; i++) {
					//Si on est après 10h
					if(i >= 10) {
						//On rentre les données indisponibles dans le tableau
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt("Aucune donnée disponible",compteurLigne,1);
						compteurLigne++;
					}
					//Sinon
					else {
						//On rentre les données indisponibles dans le tableau
						setValueAt("0"+i+"h00",compteurLigne,0);
						setValueAt("Aucune donnée disponible",compteurLigne,1);
						compteurLigne++;
					}
				}
			}

			
		}
		
		/**
		 * Méthode qui renvoit la classe de la colonne passée en paramètre
		 * @param col La colonne de laquelle on veut connaître la classe du contenu
		 */
		public Class getColumnClass(int col) {
			if( col == 1 && hauteursUnJour != null) {
				return Double.class;
			}
			else{
				return String.class;
			}
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
}
