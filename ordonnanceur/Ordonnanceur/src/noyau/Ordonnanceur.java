package noyau;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import algo.Algorithme;
import algo.UniteTemps;

public class Ordonnanceur {
	private ListeTaches liste;
	private Algorithme algo;
	private LinkedList<UniteTemps> result;
	private ListeTaches tachesPeriodiques;
	private ListeTaches tachesAperiodiques;
	
	
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
	public double tempsReponseAperiodique()
	{
		if(this.result != null)
		{
			double tempsRep = 0;
			boolean trouve = false;
			for(Tache t : this.tachesAperiodiques) {//pour toute les taches apériodiques
				for(UniteTemps u : this.result) {
					if(u.getIdTache() == t.getId() && !trouve) {//quand la tache est trouvée
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
	
	public int nombrePremption() {
		int nbrePremp = 0;
		Tache tacheEncours = null;
		HashMap ci = new HashMap<Tache,Integer>();
		for(Tache t : this.liste)
			ci.put(t,0);
		int ciCourant = 0;
		if(this.result != null) {
			for(UniteTemps u : this.result) {
				//pour chaque UT, s'il ne s'agit pas d'un temps creux ...
				if(u.getIdTache() != 0) {
					if(tacheEncours != null) {//si la tache est déjà en cours d'exe
						System.out.println("---");
						System.out.println("tache courante:"+tacheEncours.getId());
						if(u.getIdTache() == tacheEncours.getId()) {
							ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
							if(tacheEncours.getC() == ++ciCourant) {
								System.out.println("ci:"+tacheEncours.getC());
								ci.put(tacheEncours, 0);
								tacheEncours = null;
							}
							else
								ci.put(tacheEncours, ciCourant);
								
						}
						else {
							tacheEncours = this.liste.getTacheById(u.getIdTache());
							//ciCourant = 0;
							nbrePremp++;
							ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
							if(tacheEncours.getC() == ++ciCourant) {
								tacheEncours = null;
								//ciCourant = 0;
								ci.put(tacheEncours, 0);
							}
							else
								ci.put(tacheEncours, ciCourant);
						}
					}
					else if(tacheEncours == null) { //s'il s'agit d'une nouvelle tâche
						tacheEncours = this.liste.getTacheById(u.getIdTache());
						ciCourant = ((Integer)ci.get(tacheEncours)).intValue();
						if(tacheEncours.getC() == ++ciCourant) {
							tacheEncours = null;
							//ciCourant = 0;
							ci.put(tacheEncours, 0);
						}
						else
							ci.put(tacheEncours, ciCourant);
					}
				}
				//System.out.println("---");
				System.out.println(u);
				System.out.println(nbrePremp);
				System.out.println("---\n\n");
			}
			
		}
		return nbrePremp;
		
	}
	public String toString(){
		String tmp="Affichage du résultat de l'ordonnanceur\n";
		for(UniteTemps ut : this.result){
			tmp += ut+"\n";
		}
		return tmp;
	}
}
