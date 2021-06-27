package modele;

import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class ModeleMareeTable extends DefaultTableModel {
	
	
		public ModeleMareeTable() {
			String intitules[] = new String[2];
			intitules[0] = "heure";
			intitules[1] = "coeficiant";
			setColumnCount(2);
			setColumnIdentifiers(intitules);
			setRowCount(5);
				
		}
		
		public void setValueTableau( String valeur, int l, int c) {
			setValueAt(valeur, l, c);
		}
			
}
