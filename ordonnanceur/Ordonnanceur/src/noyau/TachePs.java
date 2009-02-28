package noyau;

import algo.Polling;

public class TachePs extends Periodique {

	private Polling poll =null;
	
	public TachePs(int c, int p, int d, Polling poll) {
		super(c, p, d);
		this.poll=poll;
	}

	
	public int getC(){
		int tpsTemp = this.poll.tpsExecAperiodique();
		//System.out.println("cpac :"+poll.getCapaciteServer());
		if(poll.getCapaciteServer()>tpsTemp) return tpsTemp;
		else return poll.getCapaciteServer();
		
	}
	
	public int getCapacite(){
		return super.getC();
	}
}
