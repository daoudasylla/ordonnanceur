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
	private Iterator<UniteTemps> iterateur;
	private ListeTaches periodiques;
	private ListeTaches aperiodiques;
	private PriorityQueue<PrioEDF> enAttente = null; //taches en attentes
	private UniteTemps uniteCourante;
	private Periodique tacheEnCours = null;
	
	public EDF(int ppcm)
	{
		this.ppcm = ppcm;
		this.ordonnancement = null;
		
		this.liste = null;
		this.mapTacheUnitesRestantes = null;
	}
	
	public HashMap<Tache, Integer> getMapTacheUnitesRestantes(ListeTaches liste) {
		if(this.mapTacheUnitesRestantes.size()==0) this.initMapVide(liste);
		return mapTacheUnitesRestantes;
	}

	public void setMapTacheUnitesRestantes(
			HashMap<Tache, Integer> mapTacheUnitesRestantes) {
		this.mapTacheUnitesRestantes = mapTacheUnitesRestantes;
	}

	/**
	 * Initialise la liste des débuts de période des tâches
	 */
	private void calculePeriodes(ListeTaches liste)
	{
		
		// On initialise l'ordonnancement que dans le cas où une liste n'est pas déjà passée
		if(this.ordonnancement==null)
		initOrdonnancement();			
		
		if(this.mapTacheUnitesRestantes==null)
		initMapVide(liste);
		
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
		
		
		
	}
		
	private void initOrdonnancement(){	
		//System.out.println("init de l'ordo");
		this.ordonnancement = new LinkedList<UniteTemps>();
		for(int i = 0; i < this.ppcm ; i++)
			this.ordonnancement.add(new UniteTemps(i));
	}
	
	
	private void initMapVide(ListeTaches liste){
		System.out.println("init de la map");
		for(Tache t : liste)		
		this.mapTacheUnitesRestantes.put(t, new Integer(0));	
		
		
	}
	
	
	
	private void initMap()
	{				
		for(Tache t : this.periodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
		for(Tache t : this.aperiodiques)		
			this.mapTacheUnitesRestantes.put(t, new Integer(0));
	
	}
	
	public UniteTemps uniteSuivante() {
			
		this.uniteCourante = this.iterateur.next();		
		
		
		
		int unitesRestantes = 0; //pour la tache en cours
		
			
		System.out.println("debut EDF ---------------------");		
		
			System.out.println("Unité en cours : "+uniteCourante.getIdUnite());
			
			for(Tache t :this.uniteCourante.getPeriodes()){
				
				System.out.println("ajout en attente de :"+t.getId());
				this.enAttente.add(new PrioEDF(((Periodique)t),this.uniteCourante.getIdUnite()));
				
				if(this.mapTacheUnitesRestantes.get(t)>0) System.out.println("erreur impossible de finir la tache : "+t.getId()+" "+this.mapTacheUnitesRestantes.get(t));
				else {this.mapTacheUnitesRestantes.put(t,t.getC());
				}
			}
			
			// Si une tache en cours doit etre stoppé car réveil d'une tache prio
			if(this.tacheEnCours!=null){
				Periodique tPrio = this.enAttente.peek().getTache();
				if(tPrio != null){
					
					if((this.tacheEnCours.getDi(this.uniteCourante.getIdUnite())-this.uniteCourante.getIdUnite())>(tPrio.getDi(this.uniteCourante.getIdUnite())-this.uniteCourante.getIdUnite())){
						System.out.println((this.tacheEnCours.getD()-this.uniteCourante.getIdUnite())+">"+(tPrio.getD()-this.uniteCourante.getIdUnite()));
						this.tacheEnCours = tPrio;
					}
				}
			}
			
			//si aucune tache est en cours d'execution on en selectionne une dans la file d'attente
			if(tacheEnCours == null) {
				if(enAttente.peek() != null) {// si des taches dans la file d'attente
					this.tacheEnCours = enAttente.peek().getTache();					 
				} 
			}
			
			
			
			if(tacheEnCours != null) { //si une tache est en cours d'exe
				
				// Si une tache n'est pas deja affecté (algo EDL)
				if(uniteCourante.getTache()==null){
					System.out.println("association tache "+tacheEnCours.getId());
					uniteCourante.setTache(this.tacheEnCours);
													
					unitesRestantes = this.mapTacheUnitesRestantes.get(this.tacheEnCours)-1;
					this.mapTacheUnitesRestantes.put(this.tacheEnCours,unitesRestantes);
					if(unitesRestantes == 0) {//si c'était la dernière unité de temps
						this.enAttente.remove(new PrioEDF(this.tacheEnCours,this.uniteCourante.getIdUnite()));
						this.tacheEnCours = null;
						
					}
				}
			}
			
		System.out.println(uniteCourante);
			
			
		
		
		return this.uniteCourante;


	}

	/*
	public UniteTemps executerInstantT(UniteTemps instantT, ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques)
	{
			
		//this.liste = tachesPeriodiques;		
		this.calculePeriodes(tachesPeriodiques);
		
		
		
		
		Periodique tacheEnCours = null;
		int unitesRestantes = 0; //pour la tache en cours
		
		PriorityQueue<PrioEDF> enAttente = new PriorityQueue<PrioEDF>(); //taches en attentes
		
		
		
		// On recupere la tache qui a été arreté grace à la map
		for(Tache t : 	this.mapTacheUnitesRestantes.keySet())			
			if(this.mapTacheUnitesRestantes.get(t)>0){
				//tacheEnCours=(Periodique)t;
				enAttente.add(new PrioEDF(((Periodique)t),instantT.getIdUnite()));
				System.out.println("ajout de "+t.getId());
			}
			
		
			
		
		//System.out.println("debut EDF ---------------------");		
		UniteTemps uniteCourante = this.ordonnancement.get(this.ordonnancement.indexOf(new UniteTemps(instantT)));
			
			if(uniteCourante.getIdUnite()==instantT.getIdUnite()){
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
							System.out.println("Edf:nouvelle tache en cours : "+tacheEnCours.getId());
						} 
					}
					
					
					
					if(tacheEnCours != null) { //si une tache est en cours d'exe
						
						// Si une tache n'est pas deja affecté (algo EDL)
						if(uniteCourante.getIdTache()==0){
							//System.out.println("association tache "+tacheEnCours.getId());
							uniteCourante.setIdTache(tacheEnCours.getId());
															
							unitesRestantes = this.mapTacheUnitesRestantes.get(tacheEnCours)-1;
							//System.out.println("edf : unites restantes "+unitesRestantes);
							this.mapTacheUnitesRestantes.put(tacheEnCours,unitesRestantes);
							if(unitesRestantes == 0) {//si c'était la dernière unité de temps
								//System.out.println("on retire "+tacheEnCours.getId());
								enAttente.remove(new PrioEDF(tacheEnCours,uniteCourante.getIdUnite()));
								//enAttente.remove();
								tacheEnCours = null;
								
							}
						}
					}
					
				System.out.println(uniteCourante);
					
					
				}
		
		return uniteCourante;


	}*/
	
	public void setOrdonnancement(LinkedList<UniteTemps> ordonnancement) {
		this.ordonnancement = ordonnancement;
	}

	@Override
	public void initialiser(LinkedList<UniteTemps> ordonnancement,
			ListeTaches tachesPeriodiques, ListeTaches tachesAperiodiques) {
		
		this.ordonnancement = ordonnancement;
		this.periodiques = tachesPeriodiques;
		this.aperiodiques = tachesAperiodiques;
		this.iterateur = ordonnancement.iterator();
		this.enAttente = new PriorityQueue<PrioEDF>();		
		this.mapTacheUnitesRestantes = new HashMap<Tache,Integer>();
		this.tacheEnCours=null;
		this.initMap();
		
	}

	

	
	

	
}
