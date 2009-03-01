package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import algo.EDF;
import algo.RM;

import noyau.Aperiodique;
import noyau.Ordonnanceur;
import noyau.Periodique;

import exe.Programme;

public class BoutonsListener implements ActionListener {
	private Programme fenetrePrincipale;

	public BoutonsListener(Programme frame){
		fenetrePrincipale=frame;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		String commande = arg0.getActionCommand();
		if(commande.equals("fenAjoutPerio")){
			this.fenetrePrincipale.getFenAjoutPerio().setVisible(true);
		}
		else if(commande.equals("fenAjoutAperio")){
			this.fenetrePrincipale.getFenAjoutAperio().setVisible(true);
		}	
		else if(commande.equals("ajouterPerio")){
			// Recuperation des valeurs			
			Integer ci = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextCi().getText());
			Integer di = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextDi().getText());
			Integer pi = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextPi().getText());
			
			
			Periodique perio = new Periodique(ci,di,pi);
			Integer id = new Integer(perio.getId());
			
			this.fenetrePrincipale.getListeTaches().add(perio);
			this.fenetrePrincipale.getDatasListeTaches().add(new String[]{id.toString(),"Périodique","Ci="+ci+",Di="+di+",Pi="+pi});
			this.fenetrePrincipale.getEnsembleTaches().repaint();
			this.fenetrePrincipale.getFenAjoutPerio().dispose();
		}
		else if(commande.equals("ajouterAperio")){
			// Recuperation des valeurs			
			Integer ci = Integer.parseInt(this.fenetrePrincipale.getFenAjoutAperio().getTextCi().getText());
			Integer ri = Integer.parseInt(this.fenetrePrincipale.getFenAjoutAperio().getTextRi().getText());
			
			
			Aperiodique aperio = new Aperiodique(ci,ri);
			Integer id = new Integer(aperio.getId());
			
			this.fenetrePrincipale.getListeTaches().add(aperio);
			this.fenetrePrincipale.getDatasListeTaches().add(new String[]{id.toString(),"Apériodique","Ci="+ci+",Ri="+ri});
			this.fenetrePrincipale.getEnsembleTaches().repaint();
			this.fenetrePrincipale.getFenAjoutAperio().dispose();
		}
		else if(commande.equals("supprTache")){
			if(this.fenetrePrincipale.getSelectedTache()==null) this.fenetrePrincipale.showError("Veuillez sélectionner une tache !");
			else {
				int  id = (Integer) this.fenetrePrincipale.getEnsembleTaches().getValueAt((int)this.fenetrePrincipale.getSelectedTache(), 0);
				
				this.fenetrePrincipale.retirerTache(id);
				this.fenetrePrincipale.getDatasListeTaches().remove((int)this.fenetrePrincipale.getSelectedTache());
				this.fenetrePrincipale.getEnsembleTaches().repaint();
			}
		}
		else if(commande.equals("lancerSimulation")){
			if(this.fenetrePrincipale.getDatasListeTaches().size()==0) this.fenetrePrincipale.showError("Veuillez entrer une tache !");
			else if(this.fenetrePrincipale.getTextPPCM().getText().equals("") || this.fenetrePrincipale.getTextPPCM().getText().equals("0"))  this.fenetrePrincipale.showError("Veuillez entrer le PPCM !");
		
			else{
				// Verif qu'il y a des aperio
				boolean tachesPerioPresentes = this.fenetrePrincipale.tachesPeriodiquesPresentes();
				boolean tachesAperioPresentes = this.fenetrePrincipale.tachesAperiodiquesPresentes();
				int ppcm = Integer.parseInt(this.fenetrePrincipale.getTextPPCM().getText());			
				Ordonnanceur ordo;
				// On lance la simulation avec les algorithmes demandées
				if(!tachesAperioPresentes){
					switch(this.fenetrePrincipale.getListePeriodiques().getSelectedIndex()){
						case 0: // RM
							ordo = new Ordonnanceur(new RM(),this.fenetrePrincipale.getListeTaches());
							this.fenetrePrincipale.setOrdo(ordo);
						break;
						case 1: // EDF
							ordo = new Ordonnanceur(new EDF(ppcm),this.fenetrePrincipale.getListeTaches());
							this.fenetrePrincipale.setOrdo(ordo);
						break;
					}
				}
				
				
			}
		}
	
	}

}
