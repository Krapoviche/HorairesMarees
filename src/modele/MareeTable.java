package modele;

import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class MareeTable extends DefaultTableModel {

		public MareeTable() {
			String [] intilu� = {"Heure", "Hauteur", "Coefficient"};
			this.setColumnCount(intilu�.length);
			this.setColumnIdentifiers(intilu�);
			this.setRowCount(5);
			
			
		}
}
