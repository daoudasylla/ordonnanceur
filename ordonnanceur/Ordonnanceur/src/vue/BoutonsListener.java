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

public class BoutonsListener implements ActionListener {
	private Programme fenetrePrincipale;

	public BoutonsListener(Programme frame){
		fenetrePrincipale=frame;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		String commande = arg0.getActionCommand();
		if(commande.equals("fenAjoutPerio")){
			this.fenetrePrincipale.getFenAjoutPerio().effacerChamps();
			this.fenetrePrincipale.getFenAjoutPerio().setVisible(true);
		}
		else if(commande.equals("fenAjoutAperio")){
			this.fenetrePrincipale.getFenAjoutAperio().effacerChamps();
			this.fenetrePrincipale.getFenAjoutAperio().setVisible(true);
		}	
		
		else if(commande.equals("validerCreationTachePS")){
			
			try {
				// Recuperation des valeurs	
				Integer pi = Integer.parseInt(this.fenetrePrincipale.getFenCreationPS().getTextPi().getText());
				Integer ci = Integer.parseInt(this.fenetrePrincipale.getFenCreationPS().getTextCapacite().getText());
				Integer di=pi;	
				
				TachePs perio = new TachePs(ci,pi,di,null);
				
				
				this.fenetrePrincipale.getListeTaches().add(perio);
				this.fenetrePrincipale.getDatasListeTaches().add(new String[]{""+perio.getId(),"Périodique (PS)","Capacite="+ci+",Pi="+pi});
				this.fenetrePrincipale.getEnsembleTaches().repaint();
				this.fenetrePrincipale.getFenCreationPS().dispose();
				}
			catch(Exception e){
				this.fenetrePrincipale.showError("Erreur lors de la saisie");
			}
		}	
		
	
		
		else if(commande.equals("ajouterPerio")){
			
			
			/*########################################
			# MANUEL
			#########################################*/
			if(this.fenetrePrincipale.getFenAjoutPerio().getOnglets().getSelectedIndex()==0){
			
				
				try {
					// Recuperation des valeurs	
					Integer ci = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextCi().getText());
					Integer di = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextDi().getText());
					Integer pi = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextPi().getText());
					
					
					Periodique perio = new Periodique(ci,pi,di);
					Integer id = new Integer(perio.getId());
					
					this.fenetrePrincipale.getListeTaches().add(perio);
					this.fenetrePrincipale.getDatasListeTaches().add(new String[]{id.toString(),"Périodique","Ci="+ci+",Di="+di+",Pi="+pi});
					this.fenetrePrincipale.getJScrollPane1().repaint();
					this.fenetrePrincipale.getFenAjoutPerio().dispose();
					}
				catch(Exception e){
					this.fenetrePrincipale.showError("Erreur lors de la saisie");
				}
				
			} else {
			/*########################################
			# AUTO=generation
			#########################################*/
			
				try {
					// Recuperation des valeurs			
					Integer nbTaches = Integer.parseInt(this.fenetrePrincipale.getFenAjoutPerio().getTextNbreTaches().getText());
					Double up = Double.parseDouble(this.fenetrePrincipale.getFenAjoutPerio().getTextUp().getText().replace(",","."));
				
					this.fenetrePrincipale.getGeneration().setNbreTaches(nbTaches);
					ListeTaches liste = this.fenetrePrincipale.getGeneration().genererTachesPeriodiques(up);
					
					for(Tache t:liste){
						Periodique p = (Periodique) t;
						this.fenetrePrincipale.getListeTaches().add(p);
						this.fenetrePrincipale.getDatasListeTaches().add(new String[]{""+p.getId(),"Périodique","Ci="+p.getC()+",Di="+p.getD()+",Pi="+p.getP()});
						
					}
					this.fenetrePrincipale.getEnsembleTaches().revalidate();
					//this.fenetrePrincipale.getJScrollPane1().revalidate();
					this.fenetrePrincipale.getJScrollPane1().repaint();
					
					this.fenetrePrincipale.getFenAjoutPerio().dispose();
					
					
				}
				catch(Exception e){
					this.fenetrePrincipale.showError("Erreur lors de la saisie");
					
				}
				
					
			}
		}
		else if(commande.equals("ajouterAperio")){
			try {
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
		catch(Exception e){
			this.fenetrePrincipale.showError("Erreur lors de la saisie");
			
		}

		}
		else if(commande.equals("supprTache")){
			if(this.fenetrePrincipale.getSelectedTache()==null) this.fenetrePrincipale.showError("Veuillez sélectionner une tache !");
			else if(this.fenetrePrincipale.getDatasListeTaches().size()==0) { }
			else{
				int  id = Integer.parseInt((String) this.fenetrePrincipale.getEnsembleTaches().getValueAt((int)this.fenetrePrincipale.getSelectedTache(), 0));
				
				this.fenetrePrincipale.retirerTache(id);
				this.fenetrePrincipale.getDatasListeTaches().remove((int)this.fenetrePrincipale.getSelectedTache());
				this.fenetrePrincipale.getEnsembleTaches().repaint();
				
				if(!this.fenetrePrincipale.tachesAperiodiquesPresentes()) this.fenetrePrincipale.getListeAperio().disable();
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
				Ordonnanceur ordo=null;
				boolean errors=false;
				
				
				
				/*#####################
				 * PERIODIQUES SEULEMENT
				 #####################*/
				if(!tachesAperioPresentes){
					switch(this.fenetrePrincipale.getListePeriodiques().getSelectedIndex()){
						case 0: // RM
							ordo = new Ordonnanceur(new RM(),this.fenetrePrincipale.getListeTaches());
										
							
						break;
						case 1: // EDF
							ordo = new Ordonnanceur(new EDF(ppcm),this.fenetrePrincipale.getListeTaches());
										
							
							
						break;
					}
				}
				else {
				/*#####################
				 * APERIO + PERIO
				 #####################*/	
					Algorithme algoPeriodique=null;
					Algorithme algoAPeriodique=null;
					switch(this.fenetrePrincipale.getListePeriodiques().getSelectedIndex()){
					case 0: algoPeriodique = new RM(); break;
					case 1: algoPeriodique = new EDF(ppcm); break;
					}
					
					switch(this.fenetrePrincipale.getListeAperio().getSelectedIndex()){
					case 0: algoAPeriodique = new Background(algoPeriodique); break;
					case 1: algoAPeriodique = new Polling(ppcm,algoPeriodique); break;
					case 2: algoAPeriodique = new EDL(ppcm); break;
					}
					
					// Si algo Polling, verif existence tache PS					
					if(algoAPeriodique instanceof Polling){
						if(this.fenetrePrincipale.getTachePS()==null){
							this.fenetrePrincipale.getFenCreationPS().setVisible(true);
							errors=true;
						}
						else {
							this.fenetrePrincipale.getTachePS().setPoll((Polling) algoAPeriodique);
							
						}
					}
					
					if(algoPeriodique instanceof RM && algoAPeriodique instanceof EDL){
						errors = true;
						this.fenetrePrincipale.showError("EDL ne peux fonctionner qu'avec EDF !");
					}
					
					if(!errors)
					ordo = new Ordonnanceur(algoAPeriodique,this.fenetrePrincipale.getListeTaches());
					
					if(!algoAPeriodique.ordonnancable(this.fenetrePrincipale.getListeTaches().getUp(),this.fenetrePrincipale.getListeTaches().getUs(),this.fenetrePrincipale.getListeTaches().getNPerio())) {
						errors = true;
						this.fenetrePrincipale.showError("Algorithme non ordonnancable !");
					}
					
					
					
				}
				if(!errors){
					// On ajuste le nbre de taches à afficher si PS est présent (pas d'aff des aperiodiques, seulement PS)
					int nbreTaches=this.fenetrePrincipale.getListeTaches().size();
					if(this.fenetrePrincipale.getTachePS()!=null || (this.fenetrePrincipale.getListeAperio().getSelectedIndex()==2 && tachesAperioPresentes))
						nbreTaches = this.fenetrePrincipale.nbreTachesPeriodiques();
					
					ordo.ordonnancer(ppcm);	
					this.fenetrePrincipale.getFenAffGraphe().initGUI(ordo.getResult(), nbreTaches, ppcm);
					this.fenetrePrincipale.getFenAffGraphe().setValeurTaux(""+ordo.txPremption());
					this.fenetrePrincipale.getFenAffGraphe().setAttenteMoy(""+ordo.tempsReponseAperiodique());
					this.fenetrePrincipale.getFenAffGraphe().setTpsCpu(""+ordo.tempsCPU());
					this.fenetrePrincipale.getFenAffGraphe().setVisible(true);
					
				}
				
				
			}
		}
	
	}

}
