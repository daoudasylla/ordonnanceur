package algo;

import noyau.Periodique;

public class PrioEDF implements Comparable{

	private Periodique tache;
	private int uniteActuelle;
	
	public PrioEDF(Periodique tache, int uniteActuelle){
		this.tache = tache;
		this.uniteActuelle = uniteActuelle;
	}
	public int compareTo(Object o) {
		PrioEDF p = (PrioEDF) o;
		if((this.tache.getD()-this.uniteActuelle) < (p.getTache().getD()-this.uniteActuelle))
			return -1;
		else if((this.tache.getD()-this.uniteActuelle) > (p.getTache().getD()-this.uniteActuelle))
			return 1;
		return 0;
	}
	
	public Periodique getTache() {
		return tache;
	}
	public void setTache(Periodique tache) {
		this.tache = tache;
	}

}
