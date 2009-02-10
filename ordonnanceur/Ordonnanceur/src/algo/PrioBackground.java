package algo;

import noyau.Aperiodique;

public class PrioBackground implements Comparable<Object>{

	private Aperiodique tache;
	public PrioBackground(Aperiodique tache){
		this.tache = tache;
	}
	public int compareTo(Object o) {
		PrioBackground p = (PrioBackground) o;
		if(this.tache.getR() < p.getTache().getR())
			return -1;
		else if(this.tache.getR() > p.getTache().getR())
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
		PrioBackground other = (PrioBackground) o;
		return (other.tache.equals(this.tache));
	}

}
