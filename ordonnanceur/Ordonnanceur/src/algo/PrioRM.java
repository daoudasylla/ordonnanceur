package algo;

import noyau.Periodique;

public class PrioRM implements Comparable{

	private Periodique tache;
	public PrioRM(Periodique tache){
		this.tache = tache;
	}
	public int compareTo(Object o) {
		PrioRM p = (PrioRM) o;
		if(this.tache.getP() < p.getTache().getP())
			return -1;
		else if(this.tache.getP() > p.getTache().getP())
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
