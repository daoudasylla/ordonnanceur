package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class ListeListener implements ItemListener {
	private Programme fenetrePrincipale;

	public ListeListener(Programme frame){
		fenetrePrincipale=frame;
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		
		if(this.fenetrePrincipale.tachesAperiodiquesPresentes()){
			if(fenetrePrincipale.getListeAperio().getSelectedIndex()==0){
				fenetrePrincipale.getListePeriodiques().setSelectedIndex(0);
				this.fenetrePrincipale.getListePeriodiques().enable();
				//this.fenetrePrincipale.getListePeriodiques().disable();
			}
			else if(fenetrePrincipale.getListeAperio().getSelectedIndex()==2){
				fenetrePrincipale.getListePeriodiques().setSelectedIndex(1);
				this.fenetrePrincipale.getListePeriodiques().disable();
			}
			else {
				this.fenetrePrincipale.getListePeriodiques().enable();
			}
		}
	}

}
