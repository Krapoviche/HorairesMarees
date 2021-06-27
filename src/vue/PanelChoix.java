package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.controleur;

public class PanelChoix extends JPanel{
	JScrollPane scrollPanePorts;
	BoutonPort[] boutonsPort;
	JPanel pan =new JPanel();
	public PanelChoix() {
		GridBagConstraints contraintesPan =new GridBagConstraints();
		pan.setLayout(new GridBagLayout());
		File serFolder = new File("data_ports/datas_ser/");
		File[] serFiles = serFolder.listFiles();
		pan.setPreferredSize(new Dimension(serFiles.length*10,20));
		pan.setBackground(new Color(3,34, 76));
		contraintesPan.fill = GridBagConstraints.BOTH;
		setLayout(new BorderLayout());
		
		
		
		boutonsPort = new BoutonPort[serFiles.length];
		for(int i = 0 ; i < serFiles.length ; i++) {
			boutonsPort[i] = new BoutonPort(serFiles[i].getName().split(".ser")[0]);
			contraintesPan.gridy+=1;
			pan.add(boutonsPort [i],contraintesPan);
		}
		this.add(pan);
	}
	
	public BoutonPort[] getBoutonsPort() {
		return boutonsPort;
	}
	
	
	public void recordListener(controleur c) {
		for(int i = 0 ; i < boutonsPort.length ; i++){
			boutonsPort[i].addActionListener(c);
		}
	}
}
