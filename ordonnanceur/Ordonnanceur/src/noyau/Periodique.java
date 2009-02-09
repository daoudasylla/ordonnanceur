package noyau;

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
	
	
	
	

}
