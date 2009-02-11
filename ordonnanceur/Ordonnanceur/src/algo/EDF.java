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
		this.ordonnancement = null;
		
		this.liste = null;
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
	}
	
	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes(ListeTaches liste)
	{
		
		// On initialise l'ordonnancement que dans le cas où une liste n'est pas déjà passée
		if(this.ordonnancement==null)
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
					periodeTemp += periodeTache;
				}		
				
			}
		
		
		initMap(liste);
	}
		
	private void initOrdonnancement(){	
		//System.out.println("init de l'ordo");
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
	}
	
	
	private void initMap(ListeTaches liste){
		for(Tache t : liste)		
		this.mapTacheUnitesRestantes.put(t, new Integer(0));		
	}
		
	public LinkedList<UniteTemps> executer(ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
			
		//this.liste = tachesPeriodiques;
		this.calculePeriodes(tachesPeriodiques);
		
		
		
		
		Periodique tacheEnCours = null;
		int unitesRestantes = 0; //pour la tache en cours
		
		PriorityQueue<PrioEDF> enAttente = new PriorityQueue<PrioEDF>(); //taches en attentes
		
		//System.out.println("debut EDF ---------------------");		
		for(UniteTemps uniteCourante : this.ordonnancement)
		{
			//System.out.println("Unité en cours : "+uniteCourante.getIdUnite());
			
			for(Tache t :uniteCourante.getPeriodes()){
				
				//System.out.println("ajout en attente de :"+t.getId());
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
						//System.out.println((tacheEnCours.getD()-uniteCourante.getIdUnite())+">"+(tPrio.getD()-uniteCourante.getIdUnite()));
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
				
				// Si une tache n'est pas deja affecté (algo EDL)
				if(uniteCourante.getIdTache()==0){
					//System.out.println("association tache "+tacheEnCours.getId());
					uniteCourante.setIdTache(tacheEnCours.getId());
													
					unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
					this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
					if(unitesRestantes == 0) {//si c'était la dernière unité de temps
						enAttente.remove(new PrioEDF(tacheEnCours,uniteCourante.getIdUnite()));
						tacheEnCours = null;
						
					}
				}
			}
			
		//System.out.println(uniteCourante);
			
			
		}
		
		return this.ordonnancement;


	}

	public void setOrdonnancement(LinkedList<UniteTemps> ordonnancement) {
		this.ordonnancement = ordonnancement;
	}

	
	

	
}
