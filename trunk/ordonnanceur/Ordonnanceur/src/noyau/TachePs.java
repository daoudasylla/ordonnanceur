package noyau;

import algo.Polling;

public class TachePs extends Periodique {

	private Polling poll =null;
	
	public TachePs(int c, int p, int d, Polling poll) {
		super(c, p, d);
		this.poll=poll;
	}

	
	public int getC(){
		
		return this.poll.tpsExecAperiodique();
	}
}
