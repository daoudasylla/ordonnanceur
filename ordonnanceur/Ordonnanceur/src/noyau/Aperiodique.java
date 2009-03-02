package noyau;

import algo.UniteTemps;

public class Aperiodique  extends Tache{

	private int r;
	public Aperiodique(int c,int r) {
		super(c);
		this.r = r;
	}

	public String toString() {
		
		return "Ci : "+super.getC()+ " Ri :"+this.r;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	 public Object clone() {
		 Aperiodique tmp = new Aperiodique(this.getC(),this.getR());
		    return tmp;
	}

}
