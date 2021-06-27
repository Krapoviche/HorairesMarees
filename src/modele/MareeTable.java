package modele;

import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class MareeTable extends DefaultTableModel {
	
	String [] intilué = {"Heure", "Coefficient"};;
		public MareeTable() {
			this.setColumnIdentifiers(intilué);
			this.setRowCount(18);
			this.setValueAt("test", 1, 1);
			
			
			
		}
}
