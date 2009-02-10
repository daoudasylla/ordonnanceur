package algo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


import noyau.ListeTaches;
import noyau.Periodique;
import noyau.Tache;

public class EDF implements Algorithme{
	
	private LinkedList<UniteTemps> ordonnancement;
	private ListeTaches liste;
	private int ppcm;
	private HashMap<Tache,Integer> mapTacheUnitesRestantes;
	
	
	public EDF(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
		this.liste = null;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes()
	{
		
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		for(Tache t : this.liste)
		
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
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
		}
	}
	public LinkedList<UniteTemps> executer(ListeTaches liste)
	{
		this.liste = liste;
		//UniteTemps uniteCourante = null;
		Periodique tacheEnCours = null;
		int unitesRestantes = 0; //pour la tache en cours
		
		PriorityQueue<PrioEDF> enAttente = new PriorityQueue<PrioEDF>(); //taches en attentes
		this.calculePeriodes();
		
		
		for(UniteTemps uniteCourante : this.ordonnancement)
		{
			for(Tache t :uniteCourante.getPeriodes()){
				enAttente.add(new PrioEDF(((Periodique)t),uniteCourante.getIdUnite()));
				
				if(this.mapTacheUnitesRestantes.get(t)>0) System.out.println("erreur impossible de finir la tache : "+t.getId()+" "+this.mapTacheUnitesRestantes.get(t));
				else {mapTacheUnitesRestantes.put(t,t.getC());
				}
			}
			
			// Si une tache en cours doit etre stoppé car réveil d'une tache prio
			if(tacheEnCours!=null){
				Periodique tPrio = enAttente.peek().getTache();
				if(tPrio != null){
					
					if((tacheEnCours.getDi(uniteCourante.getIdUnite())-uniteCourante.getIdUnite())>(tPrio.getDi(uniteCourante.getIdUnite())-uniteCourante.getIdUnite())){
						tacheEnCours = tPrio;
					}
				}
			}
			
			//si aucune tache est en cours d'execution on en selectionne une dans la file d'attente
			if(tacheEnCours == null) {
				if(enAttente.peek() != null) {// si des taches dans la file d'attente
					tacheEnCours = enAttente.peek().getTache();					 
				} else {
					uniteCourante.setIdTache(0);
					
				}
			}
			
			
			
			if(tacheEnCours != null) { //si une tache est en cours d'exe
				uniteCourante.setIdTache(tacheEnCours.getId());
												
				unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
				this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
				if(unitesRestantes == 0) {//si c'était la dernière unité de temps
					enAttente.remove(new PrioEDF(tacheEnCours,uniteCourante.getIdUnite()));
					tacheEnCours = null;
					
				}
			}
			
		
			
			
		}
		
		return this.ordonnancement;


	}
	

	
}
