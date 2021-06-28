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
		 * Constructeur qui permet l'instanciation d'objets � partir d'un objet HauteursDeMerUnJour
		 * @param hauteursUnJour L'objet HauteursDeMerUnJour en question
		 */
		public ModeleMareeTable(HauteursDeMerUnJour hauteursUnJour) {
			
			this.hauteursUnJour = hauteursUnJour;
			
			//D�finition de diff�rents champs du mod�le (Intitul�s, nombre de colonnes ...)
			String[] intitules = {"HEURE","HAUTEUR"};
			setColumnCount(2);
			setColumnIdentifiers(intitules);
				
			//D�finition de la liste des cl�s qui permettra de lire la map de hauteurs de mer
			Set<Integer> keys;
			
			//Si jamais l'objet en param�tre n'est pas nul (Si les donn�es pour ce jour et ce port existent)
			if(hauteursUnJour != null) {
				//On g�n�re le set de cl�s pour lire la Map de hauteurs de mer
				keys =  hauteursUnJour.getHauteursUnJour().keySet();
				//On d�finit la taille du tableau en fonction de la taille de la map
				setRowCount(hauteursUnJour.getHauteursUnJour().size());
			}
			//Si les donn�es n'existent pas
			else {
				//Il n'y a donc pas de cl�
				keys = null;
				//On affiche un tableau plein
				setRowCount(24);
			}
				
			
			
			
			int compteurLigne = 0;
			//S'il y a des donn�es
			if(keys != null) {
				//Pour chaque entr�e de la map
				for(Integer i : keys) {
					//Si on est apr�s 10h
					if(i >= 10) {
						//On rentre les donn�es dans le tableau
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt(hauteursUnJour.getHauteursUnJour().get(i).getHauteur(),compteurLigne,1);
						compteurLigne++;
					}
					//Sinon
					else {
						//On rentre les donn�es dans le tableau
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
					//Si on est apr�s 10h
					if(i >= 10) {
						//On rentre les donn�es indisponibles dans le tableau
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt("Aucune donn�e disponible",compteurLigne,1);
						compteurLigne++;
					}
					//Sinon
					else {
						//On rentre les donn�es indisponibles dans le tableau
						setValueAt("0"+i+"h00",compteurLigne,0);
						setValueAt("Aucune donn�e disponible",compteurLigne,1);
						compteurLigne++;
					}
				}
			}

			
		}
		
		/**
		 * M�thode qui renvoit la classe de la colonne pass�e en param�tre
		 * @param col La colonne de laquelle on veut conna�tre la classe du contenu
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
