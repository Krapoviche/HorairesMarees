package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class ModeleMareeTable extends DefaultTableModel {
	
	
		public ModeleMareeTable(HauteursDeMerUnJour hauteursUnJour) {
			String[] intitules = {"heure","hauteur"};
			setColumnCount(2);
			setColumnIdentifiers(intitules);
			setRowCount(24);
				
			
			Set<Integer> keys =  hauteursUnJour.getHauteursUnJour().keySet();
			
			
			
			
			int compteurLigne = 0;
			for(Integer i : keys) {
				if(compteurLigne >= 10) {
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
