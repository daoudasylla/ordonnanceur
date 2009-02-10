package noyau;

import algo.UniteTemps;

public class Periodique extends Tache{
	
	private int p;
	private int d;
	
	public Periodique(int c, int p, int d) {
		super(c);
		this.p = p;
		this.d = d;
		
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	
	public String toString() {
		
		return "Ci : "+super.getC()+" | Pi : "+this.p+" | Di : "+this.d;
		
	}
	

	
	public boolean equals(Object obj) {

		return super.equals(obj);
	}
	public int hashCode() {		
		return super.getId();
	}
	
	public int getDi(int ut){
		int Di =0;
		while(Di<=ut) Di = Di+d;
		
		return Di;
	}
	

}
