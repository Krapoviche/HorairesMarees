package modele;

import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class MareeTable extends DefaultTableModel {
	
	String [] intilu� = {"Heure", "Coefficient"};;
		public MareeTable() {
			this.setColumnIdentifiers(intilu�);
			this.setRowCount(18);
			this.setValueAt("test", 1, 1);
			
			
			
		}
}
