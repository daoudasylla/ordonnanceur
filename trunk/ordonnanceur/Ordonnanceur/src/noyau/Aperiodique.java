package noyau;

public class Aperiodique  extends Tache{

	private int r;
	public Aperiodique(int c,int r) {
		super(c);
		this.r = r;
	}

	public String toString() {
		
		return "Ci : "+super.getC();
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	

}
