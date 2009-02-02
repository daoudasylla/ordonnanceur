package noyau;
import java.util.Random;
public class Generation {
	
	private double[] tab; 
	private double up;
	private int nbre;
	public static int coeff = 100000;
	
	    public int entier(int min,int max){
	        return (int) Math.floor(Math.random()*(max+1-min)+min);
	    }
	    /**
	     * @param args
	     */
	    public void Generation(double up,int nbre){
	        
	       this.up = up;
	       this.nbre = nbre;
	        
	       this.tab = new double[this.nbre];
	        
	       up*=Generation.coeff;
	       
	        int total=0;
	        for(int i=0;i<nbre-1;i++){
	            
	            int min = (int)((up/(nbre-i))*0.5);
	            int max = (int)((up/(nbre-i))*1.5);
	            int rdm = entier(min,max);
	            up = (up-rdm);
	            total+=rdm;
	            tab[i] = Math.floor(rdm/Generation.coeff);
	            System.out.println(rdm);
	            
	        }
	        
	        tab[nbre-1] = Math.floor(up/Generation.coeff);
	        
	        
	        total+=up;
	        System.out.println("total"+total);

	    
	}
	    
	    
	    public ListeTaches genererTachesPeriodiques(){
	    	
	    	ListeTaches liste = new ListeTaches();
	    	
	    	
	    	for(int i=0;i<this.nbre-1;i++){
	    		
	    		Periodique t = new Periodique();
	    		// Definition du Ci
	    		int Ci= (int) Math.floor(Math.random()*10);
	    		// Definition du Pi
	    		int Pi = (int) Math.floor(Ci/tab[i]);
	    		
	    		t.setC(Ci);
	    		t.setP(Pi);
	    		liste.addTache(t);
	    		System.out.println(t);
	    	}
	    	
	    	return liste;
	    	
	    }

	
}
