package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class BoutonPort extends JButton{
	private String nomPort;
	
	
	public BoutonPort(String np) {
		super(np);
		this.nomPort = np;
		this.setSize(new Dimension(200,50));
		this.setPreferredSize(new Dimension(200,50));
		this.setBackground(new Color(255,200,120));
	}
	
	
	public String getPort() {
		return nomPort;
	}
}
