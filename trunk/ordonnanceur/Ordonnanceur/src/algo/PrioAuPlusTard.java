package algo;


import noyau.Periodique;


public class PrioAuPlusTard implements Comparable{

	private Periodique tache;
	public static UniteTemps ut = null;
	public PrioAuPlusTard(Periodique tache,UniteTemps ut){
		this.tache = tache;
		PrioAuPlusTard.ut=ut;
	}
	public int compareTo(Object o) {
		PrioAuPlusTard p = (PrioAuPlusTard) o;
		int tempsActuel = PrioAuPlusTard.ut.getIdUnite();
		if(this.tache.getDi(tempsActuel) > p.getTache().getDi(tempsActuel))
			return -1;
		else if(this.tache.getDi(tempsActuel) < p.getTache().getDi(tempsActuel))
			return 1;
		return 0;
	}
	public Periodique getTache() {
		return tache;
	}
	public void setTache(Periodique tache) {
		this.tache = tache;
	}
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		PrioAuPlusTard other = (PrioAuPlusTard) o;
		return (this.tache.equals(other.tache));
	}

}
