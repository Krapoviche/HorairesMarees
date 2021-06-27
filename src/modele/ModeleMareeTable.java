package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class ModeleMareeTable extends DefaultTableModel {
	
	
		public ModeleMareeTable(HauteursDeMerUnJour hauteursUnJour) {
			String[] intitules = {"heure","hauteur"};
			setColumnCount(2);
			setColumnIdentifiers(intitules);
				
			Set<Integer> keys;
			
			if(hauteursUnJour != null) {
				keys =  hauteursUnJour.getHauteursUnJour().keySet();
				setRowCount(hauteursUnJour.getHauteursUnJour().size());
			}
			else {
				keys = null;
				setRowCount(0);
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

			
		}
		
}
