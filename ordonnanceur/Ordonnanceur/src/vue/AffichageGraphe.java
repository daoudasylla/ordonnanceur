package vue;


import java.util.LinkedList;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import algo.UniteTemps;

import exe.Programme;

import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AffichageGraphe extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private Programme fenetrePrincipale; 
	
	public AffichageGraphe(Programme frame) {
		super();
		this.fenetrePrincipale=frame;
		this.setTitle("Graphe d'ordonnancement");
		
	}
	
	public void initGUI(LinkedList<UniteTemps> liste, int nbTaches, int ppcm) {
		try {
			
			BorderLayout thisLayout = new BorderLayout();
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			
			getContentPane().setLayout(thisLayout);
			getContentPane().removeAll();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			Graphe panel = new Graphe(this.fenetrePrincipale,liste,nbTaches,ppcm);
			getContentPane().add("Center", panel);
			
			setSize(Graphe.LONGUEUR_LIGNE+150, Graphe.xDepart+((nbTaches+1)*Graphe.ESPACEMENT_LIGNE)+50);
			//pack();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
