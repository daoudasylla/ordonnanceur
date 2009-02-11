package algo;


import java.util.LinkedList;
import java.util.PriorityQueue;

import noyau.Aperiodique;
import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class EDL implements Algorithme{
	
	private LinkedList<UniteTemps> ordonnancement;
	private int ppcm;
	private ListeTaches liste;
	
	public EDL(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = null;
		
		this.liste = null;
		
	}
	
	private void initOrdonnancement(){	
		System.out.println("init de l'ordo EDL");
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes(ListeTaches liste)
	{
		initOrdonnancement();	
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		for(Tache t : liste)
		
		{
			periodeTemp = 0;
			tacheTemp = t;
			periodeTache = ((Periodique)tacheTemp).getP();
			while(periodeTemp < this.ppcm)
			{
				this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(periodeTemp))).ajouterPeriode(tacheTemp);
				//this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTache;
			}
		
		
		}
	}
	
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
	
	this.calculePeriodes(tachesPeriodiques);
	

	
	PriorityQueue<PrioEDL> enAttente = new PriorityQueue<PrioEDL>(); //taches en attentes	
	
	boolean dejaAffecte=false;
	int unitesRestantes =0;
	Tache tacheEnCours=null;
	// On associe toutes les taches aperiodiques aux unites de temps
	for(UniteTemps uniteCourante : this.ordonnancement)
	{
		
		if(unitesRestantes>0){
			dejaAffecte=true;
			uniteCourante.setIdTache(tacheEnCours.getId());
			unitesRestantes--;
		}
		
		// On verifie qu'il n'y a pas de tache dans la lsite d'attente
		if(enAttente.peek()!=null && unitesRestantes==0){
			dejaAffecte=true;
			
			// On affecte la tache en attente a l'unite en cours
			tacheEnCours = enAttente.remove().getTache();
			uniteCourante.setIdTache(tacheEnCours.getId());
			unitesRestantes = tacheEnCours.getC();
		}
		
		for(Tache ape : tachesAperiodiques)
		{			
			
			if(uniteCourante.getIdUnite()==((Aperiodique) ape).getR()){
				
				// Si tache deja affecté dans liste d'attente on mets dans la liste d'attente
				if(dejaAffecte) enAttente.add(new PrioEDL(((Aperiodique) ape)));
				else {
					uniteCourante.setIdTache(ape.getId());
					dejaAffecte=true;
					tacheEnCours = ape;
					unitesRestantes = tacheEnCours.getC()-1;
				}
				
			}
		}
		
		dejaAffecte=false; // On remet a jour notre variable booleenne
	}
	
	
	/*for(UniteTemps uniteCourante : this.ordonnancement)
	{
		System.out.println(uniteCourante);
	}*/
	
	// On appel EDF pour continuer le travail
	EDF edf = new EDF(this.ppcm);
	edf.setOrdonnancement(this.ordonnancement); // on affecte notre liste
	
	this.ordonnancement = edf.executer(tachesPeriodiques, tachesAperiodiques);
	
	return this.ordonnancement;
		
	}
}
