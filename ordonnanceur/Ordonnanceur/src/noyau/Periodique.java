package noyau;

public class Periodique extends Tache{
	
	private int p;

	public Periodique() {
		super();
		
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	
	public String toString() {
		
		return "Ci : "+super.getC()+" | Pi : "+this.p;
		
	}
	
	
	
	

}
