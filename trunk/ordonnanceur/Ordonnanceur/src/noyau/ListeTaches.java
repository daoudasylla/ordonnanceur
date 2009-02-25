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
}
