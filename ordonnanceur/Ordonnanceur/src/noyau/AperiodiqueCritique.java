package noyau;

public class AperiodiqueCritique  extends Aperiodique{

	private int d;//delai critique
	public AperiodiqueCritique(int c,int r,int d) {
		super(c,r);
		this.setD(d);
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getD() {
		return d;
	}

}
