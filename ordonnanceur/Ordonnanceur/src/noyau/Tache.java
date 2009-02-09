package noyau;

public abstract class Tache{

	private int c;
	static private int nextId = 1;
	private int id;
	
	public Tache(int c)
	{
		this.id = Tache.nextId++;
		this.c = c;
	}
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	abstract public String toString();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int hashCode() {		
		return id;
	}
	
	
	
}
