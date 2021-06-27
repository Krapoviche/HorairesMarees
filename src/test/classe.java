//<<<<<<< HEAD
//s
//=======
package test;

import java.io.File;
import java.util.ArrayList;

import modele.Date;
import modele.MareesUnJour;
import modele.MareesUnPort;
import outils.EcritureFichierSer;
import outils.LectureFichierSer;
import outils.LectureFichierTxt;

public class classe {
	public static void main (String args[]) {
		/*
		File dataFolder = new File("data_ports/datas/");
		File[] dataFiles = dataFolder.listFiles();
		ArrayList <ArrayList<MareesUnJour>> mareesTotal = new ArrayList<ArrayList<MareesUnJour>>();
		
		for(int i = 0 ; i < dataFiles.length ; i++) {
			mareesTotal.add(outils.LectureFichierTxt.lecture(dataFiles[i]));
		}
		
		for(ArrayList<MareesUnJour> mareesUnPort : mareesTotal) {
			for(MareesUnJour mareesUnJour : mareesUnPort) {
				File dir = new File("data_ports/datas_ser/"+mareesUnJour.getNomPort()+"/");
				dir.mkdir();
				EcritureFichierSer.ecriture(new File("data_ports/datas_ser/"+mareesUnJour.getNomPort()+"/"+ mareesUnJour.getDate().toStringForFile()+".ser"),(Object) mareesUnJour);
			}
		}*/

		
		/*
		File dataFolder = new File("data_ports/datas/");
		File[] dataFiles = dataFolder.listFiles();
		for(int i = 0 ; i < dataFiles.length ; i++) {
			if(dataFiles.)
		}
		
		File dataFolder = new File("data_ports/datas_ser/");
		File[] portFolders = dataFolder.listFiles();
		File[][] dataPorts = new File[portFolders.length][31]; 
		MareesUnJour mar;
		MareesUnPort por;
		ArrayList<MareesUnJour> mareesUnJourList = new ArrayList<MareesUnJour>();
		ArrayList<MareesUnPort> mareesUnPortList = new ArrayList<MareesUnPort>(); 
		
		for(int i = 0 ; i < portFolders.length ; i++) {
			dataPorts[i] = portFolders[i].listFiles();
		}
		for(int i = 0 ; i < portFolders.length ; i++) {
			for(int j = 0 ; j< 31; j++) {
				if(dataPorts[i][j] != null) {
					mar = ((MareesUnJour) LectureFichierSer.lecture(dataPorts[i][j]));
					mareesUnJourList.add(mar);
				}
			}
			mareesUnPortList.add(new MareesUnPort(mareesUnJourList));
		}
		
		for(MareesUnPort port : mareesUnPortList) {
			System.out.println(port.getMareesUnJourDuPort().toString());
		}
		
		ArrayList <ArrayList<MareesUnJour>> mareesTotal = new ArrayList<ArrayList<MareesUnJour>>();
		
		for(int i = 0 ; i < dataFiles.length ; i++) {
			mareesTotal.add(outils.LectureFichierTxt.lecture(dataFiles[i]));
		}*/
		
		//System.out.println(LectureFichierSer.lecture(new File("data_ports//datas_ser//TOULON.ser")).getMareesUnJourDuPort().get(new Date(7,9,2021)));
		//LectureFichierTxt.traitementHeureParHeure();
		EcritureFichierSer.ecrireDemarrageHeureParHeure();
		System.out.println(LectureFichierSer.lectureHph(new File("data_ports//data_hph_ser//DUNKERQUE.ser")).toString());
	}
}
//>>>>>>> branch 'master' of https://github.com/Krapoviche/projdeouf
