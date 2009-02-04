package noyau;

import java.util.LinkedList;

public class ListeTaches extends LinkedList<Tache>{

	
	public String toString(){
		
		String tmp = new String();
		for(Tache t : this)
			tmp += "\n"+t;
		
		return tmp;
	}
}
