package algo;

import noyau.Aperiodique;


public class PrioEDL implements Comparable{

	private Aperiodique tache;
	public PrioEDL(Aperiodique tache){
		this.tache = tache;
	}
	public int compareTo(Object o) {
		PrioEDL p = (PrioEDL) o;
		if(this.tache.getId() < p.getTache().getId())
			return -1;
		else if(this.tache.getId() > p.getTache().getId())
			return 1;
		return 0;
	}
	public Aperiodique getTache() {
		return tache;
	}
	public void setTache(Aperiodique tache) {
		this.tache = tache;
	}
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		PrioEDL other = (PrioEDL) o;
		return (this.tache.equals(other.tache));
	}

}
