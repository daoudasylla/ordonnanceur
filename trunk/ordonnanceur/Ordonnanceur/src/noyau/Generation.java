package noyau;
public class Generation {
	
	private double[] tab; 
	private double up;
	private int nbre;
	public static int coeff = 100000;
	
	    public int entier(int min,int max){
	        return (int) Math.floor(Math.random()*(max+1-min)+min);
	    }

		public Generation(){
		    	    
		}
		    
	    
	    public void setNbreTaches(int nbre) {
			this.nbre = nbre;
		}

		public ListeTaches genererTachesPeriodiques(double up){
	    	
	    	
    	  this.up = up;    	
    	  this.tab = new double[this.nbre];
    	  ListeTaches liste = new ListeTaches();

	      // On augmente le up pour limiter   
	       up=up*Generation.coeff;
	      
	        int total=(int)(up);
	        for(int i=0;i<nbre-1;i++){
	            
	            int min = (int)((up/(nbre-i))*0.5);
	            int max = (int)((up/(nbre-i))*1.5);
	            int rdm = entier(min,max);
	            up = (up-rdm);	           
	           
	           
	            this.tab[i] = 1.0*(((double)rdm/(double)total));
	           
	            
	        }
	        
	        this.tab[nbre-1] = 1.0*(((double)(up)/(double)total));
	    
	    	
	    	
	    	
	    	for(int i=0;i<this.nbre;i++){
	    		
	    		
	    		// Definition du Ci
	    		int Ci= (int) Math.floor(Math.random()*10)+1;
	    		// Definition du Pi
	    		int Pi = (int) Math.floor(Ci/tab[i]);
	    		Periodique t = new Periodique(Ci,Pi,Pi);
	    		/*t.setC(Ci);
	    		t.setP(Pi);*/
	    		liste.add(t);
	    		//System.out.println(t);
	    	}
	    	
	    	return liste;
	    	
	    }

	
}
