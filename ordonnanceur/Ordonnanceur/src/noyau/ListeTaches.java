package noyau;

import java.util.LinkedList;

public class ListeTaches extends LinkedList<Tache>{

	public Tache getTacheById(int id) {
		for(Tache t : this) {
			if(t.getId() == id)
				return t;
		}
		return null;
	}
	public String toString(){
		
		String tmp = new String();
		for(Tache t : this)
			tmp += "\n"+t;
		
		return tmp;
	}
	public double getUp() {
		
		double up = 0;
		Periodique temp;
		for(Tache t : this) {
			//System.out.println("size"+this.size()+(!(t instanceof TachePs) && (t instanceof Periodique)));
			if(!(t instanceof TachePs) && (t instanceof Periodique)) {
				temp = (Periodique)t;
				up += (((double)temp.getC())/((double)temp.getP()));
			}
		}
		return up;
	}
	public double getUs() {
		
		double us = 0;
		TachePs temp;
		for(Tache t : this) {
			//System.out.println("size"+this.size()+(!(t instanceof TachePs) && (t instanceof Periodique)));
			if((t instanceof TachePs)) {
				temp = (TachePs)t;
				us = (((double)temp.getCapacite())/((double)temp.getP()));
			}
		}
		return us;
	}
	public int getNPerio() {
		int nb = 0;
		for(Tache t : this) {
			//System.out.println("size"+this.size()+(!(t instanceof TachePs) && (t instanceof Periodique)));
			if(!(t instanceof TachePs) && (t instanceof Periodique)) {
				nb++;
			}
		}
		return nb;
	}
}
