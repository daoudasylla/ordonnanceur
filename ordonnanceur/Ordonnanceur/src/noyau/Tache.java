package noyau;

public abstract class Tache  implements Creneau{

	int c;
	static private int nextId = 0;
	private int id;
	
	public Tache(int c)
	{
		this.id = Tache.nextId++;
	}
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	abstract public String toString();
	
}
