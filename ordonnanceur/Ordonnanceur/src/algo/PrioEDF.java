package algo;

import noyau.Periodique;

public class PrioEDF implements Comparable{

	private Periodique tache;
	private static int uniteActuelle;
	
	public PrioEDF(Periodique tache, int unite){
		this.tache = tache;
		PrioEDF.uniteActuelle = unite;
	}
	public int compareTo(Object o) {
		PrioEDF p = (PrioEDF) o;
		if((this.tache.getDi(this.uniteActuelle)-this.uniteActuelle) < (p.getTache().getDi(this.uniteActuelle)-this.uniteActuelle))
			return -1;
		else if((this.tache.getDi(this.uniteActuelle)-this.uniteActuelle) > (p.getTache().getDi(this.uniteActuelle)-this.uniteActuelle))
			return 1;
		return 1;
	}
	
	public int getUniteActuelle() {
		return uniteActuelle;
	}
	public void setUniteActuelle(int uniteActuelle) {
		this.uniteActuelle = uniteActuelle;
	}
	public Periodique getTache() {
		return tache;
	}
	public void setTache(Periodique tache) {
		this.tache = tache;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrioEDF other = (PrioEDF) obj;
		return (this.tache.equals(other.tache));
	}
	
}
