package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import algo.Algorithme;
import algo.Background;
import algo.EDF;
import algo.EDL;
import algo.Polling;
import algo.RM;
import algo.UniteTemps;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Ordonnanceur;
import noyau.Periodique;
import noyau.Tache;
import noyau.TachePs;

import exe.Programme;

public class MenuListener implements ActionListener {
	private Programme fenetrePrincipale;

	public MenuListener(Programme frame){
		fenetrePrincipale=frame;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		String commande = arg0.getActionCommand();
		if(commande.equals("testRM")){
				this.fenetrePrincipale.getListeTaches().clear();	
				this.fenetrePrincipale.getListeTaches().add(new Periodique(3,20,20));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,10,10));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,5,5));
				this.fenetrePrincipale.getListePeriodiques().setSelectedIndex(0); //RM
				this.fenetrePrincipale.getListePeriodiques().disable();
				this.fenetrePrincipale.getTextPPCM().setText("20");
		}
		else if(commande.equals("testEDF")){
				this.fenetrePrincipale.getListeTaches().clear();
				this.fenetrePrincipale.getListeTaches().add(new Periodique(1,20,8));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(4,10,10));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,5,4));
				this.fenetrePrincipale.getListePeriodiques().setSelectedIndex(1); //RM
				this.fenetrePrincipale.getTextPPCM().setText("20");
		}	
		else if(commande.equals("testBG")){
				this.fenetrePrincipale.getListeTaches().clear();
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,6,6));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,8,8));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,12,12));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(2,7));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(2,5));
				this.fenetrePrincipale.getListePeriodiques().setSelectedIndex(0); 
				//this.fenetrePrincipale.getListePeriodiques().disable();
				this.fenetrePrincipale.getListeAperio().setSelectedIndex(0); 
				this.fenetrePrincipale.getListeAperio().disable();
				this.fenetrePrincipale.getTextPPCM().setText("30");
		}
		else if(commande.equals("testPS")){
				this.fenetrePrincipale.getListeTaches().clear();
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,6,6));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(1,4,4));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(2,2));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(1,8));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(2,12));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(1,19));
				this.fenetrePrincipale.getListeTaches().add(new TachePs(2,5,5,null));
				this.fenetrePrincipale.getListePeriodiques().setSelectedIndex(0); 
				this.fenetrePrincipale.getListePeriodiques().disable();
				this.fenetrePrincipale.getListeAperio().setSelectedIndex(1); 
				this.fenetrePrincipale.getListeAperio().disable();
				this.fenetrePrincipale.getTextPPCM().setText("24");
		}
		else if(commande.equals("testEDL")){
				this.fenetrePrincipale.getListeTaches().clear();
				this.fenetrePrincipale.getListeTaches().add(new Periodique(3,6,6));
				this.fenetrePrincipale.getListeTaches().add(new Periodique(2,8,8));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(1,3));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(2,9));
				this.fenetrePrincipale.getListeTaches().add(new Aperiodique(1,14));
				this.fenetrePrincipale.getListePeriodiques().setSelectedIndex(1); 
				this.fenetrePrincipale.getListePeriodiques().disable();
				this.fenetrePrincipale.getListeAperio().setSelectedIndex(2); 
				this.fenetrePrincipale.getListeAperio().disable();
				this.fenetrePrincipale.getTextPPCM().setText("24");
		}
		this.fenetrePrincipale.remplirTaches();
		this.fenetrePrincipale.getEnsembleTaches().repaint();
	
	}

}
