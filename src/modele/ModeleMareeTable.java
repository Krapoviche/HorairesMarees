package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class ModeleMareeTable extends DefaultTableModel {
	HauteursDeMerUnJour hauteursUnJour;
	
		public ModeleMareeTable(HauteursDeMerUnJour hauteursUnJour) {
			this.hauteursUnJour = hauteursUnJour;
			String[] intitules = {"HEURE","HAUTEUR"};
			setColumnCount(2);
			setColumnIdentifiers(intitules);
				
			Set<Integer> keys;
			
			if(hauteursUnJour != null) {
				keys =  hauteursUnJour.getHauteursUnJour().keySet();
				setRowCount(hauteursUnJour.getHauteursUnJour().size());
			}
			else {
				keys = null;
				setRowCount(24);
			}
				
			
			
			
			int compteurLigne = 0;
			if(keys != null) {
				for(Integer i : keys) {
					if(i >= 10) {
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt(hauteursUnJour.getHauteursUnJour().get(i).getHauteur(),compteurLigne,1);
						compteurLigne++;
					}
					else {
						setValueAt("0"+i+"h00",compteurLigne,0);
						setValueAt(hauteursUnJour.getHauteursUnJour().get(i).getHauteur(),compteurLigne,1);
						compteurLigne++;
					}
				}
			}
			else {
				for(int i = 0 ; i < getRowCount() ; i++) {
					if(i >= 10) {
						setValueAt(i+"h00",compteurLigne,0);
						setValueAt("Aucune donnée disponible",compteurLigne,1);
						compteurLigne++;
					}
					else {
						setValueAt("0"+i+"h00",compteurLigne,0);
						setValueAt("Aucune donnée disponible",compteurLigne,1);
						compteurLigne++;
					}
				}
			}

			
		}
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
