package modele;

import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class MareeTable extends DefaultTableModel {

		public MareeTable() {
			String [] intilué = {"Heure", "Hauteur", "Coefficient"};
			this.setColumnCount(intilué.length);
			this.setColumnIdentifiers(intilué);
			this.setRowCount(5);
			
			
		}
}
