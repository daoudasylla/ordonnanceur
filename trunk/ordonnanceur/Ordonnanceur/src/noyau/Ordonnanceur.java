package noyau;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import algo.Algorithme;
import algo.Background;
import algo.EDF;
import algo.EDL;
import algo.Polling;
import algo.RM;
import algo.UniteTemps;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private LinkedList<UniteTemps> result;
	private ListeTaches tachesPeriodiques;
	private ListeTaches tachesAperiodiques;
	private int ppcm;
	
	
	//private int ppcm;
	public Ordonnanceur(Algorithme algo,ListeTaches liste){
		this(algo);
		this.liste = liste;

	}
	public Ordonnanceur(Algorithme algo){
		this.algo = algo;
		this.tachesPeriodiques = new ListeTaches();
		this.tachesAperiodiques = new ListeTaches();
		//this.ppcm = ppcm;
	}
	public void setListe(ListeTaches liste) {
		this.liste = liste;
	}
	public void ordonnancer(int ppcm)
	{
		//if(this.algo.ordonnancable(this.liste.getUp(),this.liste.getUs(),this.liste.getNPerio())) {
			
			this.ppcm = ppcm;
			// On remplit les deux listes
			for(Tache t : liste) {
				if(t instanceof Periodique) {
					this.tachesPeriodiques.add(t);
				}
				else
					this.tachesAperiodiques.add(t);
			}
			
			this.result = this.calculePeriodes(ppcm);
			
			
			
			this.algo.initialiser(this.result, this.tachesPeriodiques, this.tachesAperiodiques);
			for(int i = 0;i < ppcm;i++)
				this.algo.uniteSuivante();
		//this.result = this.algo.executer(tachesPeriodiques,tachesAperiodiques);
		//}
		
	}
	private LinkedList<UniteTemps> calculePeriodes(int ppcm)
	{
		LinkedList<UniteTemps> result = new LinkedList<UniteTemps>();
		int periodeTemp, periodeTache;
		Tache tacheTemp = null;
		for(int i = 0; i < ppcm ; i++)
			result.add(new UniteTemps(i));
		for(Tache t : this.tachesPeriodiques)
		
		{
			periodeTemp = 0;
			tacheTemp = t;
			periodeTache = ((Periodique)tacheTemp).getP();
			while(periodeTemp < ppcm)
			{
				result.get(result.indexOf(new UniteTemps(periodeTemp))).ajouterPeriode(tacheTemp);
				//this.periodes[periodeTemp-1][tacheTemp.getId()] = true;
				periodeTemp += periodeTache;
			}
		
			//this.mapTacheUnitesRestantes.put(t, new Integer(0));
		}
		return result;
	}
	public LinkedList<UniteTemps> getResult() {
		return result;
	}
	public void setResult(LinkedList<UniteTemps> result) {
		this.result = result;
	}
	public double tempsReponseAperiodique()
	{
		if(this.result != null)
		{
			double tempsRep = 0;
			boolean trouve = false;
			for(Tache t : this.tachesAperiodiques) {//pour toute les taches apériodiques
				for(UniteTemps u : this.result) {
					if(u.getTache() != null && u.getTache().equals(t) && !trouve) {//quand la tache est trouvée
						tempsRep += (u.getIdUnite() - ((Aperiodique) t).getR()); //on calcul son temps de réponse
						trouve = true;
					}
				}
				trouve = false;
			}
			return (tempsRep / this.tachesAperiodiques.size());
		}
		return 0;
		
	}
	public int tempsCPU() {
		int nbreTpsCreux = 0;
		if(this.result != null) {
			for(UniteTemps u : this.result) {
				if(u.getTache() == null) {
					nbreTpsCreux++;
				}
			}
			return this.ppcm - nbreTpsCreux;	
		}
		return 0;
	}
	public double txPremption() {
		double nbrePremp = 0;
		double nbreTache = 0;
		Tache tacheEncours = null;
		HashMap<Tache,Integer> ci = new HashMap<Tache,Integer>();
		for(Tache t : this.liste)
			ci.put(t,0);
		int ciCourant = 0;
		if(this.result != null) {
			for(UniteTemps u : this.result) {
				//pour chaque UT, s'il ne s'agit pas d'un temps creux ...
				if(u.getTache() != null) {
					if(tacheEncours != null) {//si la tache est déjà en cours d'exe
						//System.out.println("---");
						//System.out.println("tache courante:"+tacheEncours.getId());
						if(u.getTache().equals(tacheEncours)) {
							ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
							if(tacheEncours.getC() == ++ciCourant) {
								//System.out.println("ci:"+tacheEncours.getC());
								ci.put(tacheEncours, 0);
								//System.out.println("a-tache en cours:"+tacheEncours.getId()+" ut:"+u.getIdUnite()+" ci:"+ciCourant);
								tacheEncours = null;
								nbreTache++; //la tache est finis on la comptabilise
							}
							else
								ci.put(tacheEncours, ciCourant);
								
						}
						else {
							tacheEncours = this.liste.getTacheById(u.getTache().getId());
							//ciCourant = 0;
							nbrePremp++;
							ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
							if(tacheEncours.getC() == ++ciCourant) {
								//System.out.println("c-tache en cours:"+tacheEncours.getId()+" ut:"+u.getIdUnite()+" ci:"+ciCourant);
								ci.put(tacheEncours, 0);
								tacheEncours = null;
								//ciCourant = 0;
								nbreTache++;
								
							}
							else
								ci.put(tacheEncours, ciCourant);
						}
					}
					else if(tacheEncours == null) { //s'il s'agit d'une nouvelle tâche
						tacheEncours = this.liste.getTacheById(u.getTache().getId());
						ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
						if(tacheEncours.getC() == ++ciCourant) {
							//System.out.println("b-tache en cours:"+tacheEncours.getId()+" ut:"+u.getIdUnite()+" ci:"+ciCourant);
							ci.put(tacheEncours, 0);
							tacheEncours = null;
							//ciCourant = 0;
							
							nbreTache++;
						}
						else
							ci.put(tacheEncours, ciCourant);
					}
				}
				//System.out.println("---");
				//System.out.println(u);
				//System.out.println(nbrePremp);
				//System.out.println("---\n\n");
			}
			return nbrePremp/nbreTache;
		}
		//System.out.println("nb tache:"+nbreTache);
		return 0;
		
	}
	public String toString(){
		String nomAlgo="";
		if(algo instanceof RM) nomAlgo="RM";
		else if(algo instanceof EDF) nomAlgo="EDF";
		else if(algo instanceof Polling) nomAlgo="Polling";
		else if(algo instanceof Background) nomAlgo="Background";
		else if(algo instanceof EDL) nomAlgo="EDL";

		String tmp="Affichage du résultat de l'ordonnanceur pour l'algo "+nomAlgo+"\n";
		for(UniteTemps ut : this.result){
			tmp += ut+"\n";
		}
		return tmp;
	}
}
