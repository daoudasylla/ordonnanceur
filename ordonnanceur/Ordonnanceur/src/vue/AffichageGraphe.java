package vue;


import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import algo.UniteTemps;

import exe.Programme;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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
public class AffichageGraphe extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private Programme fenetrePrincipale;
	private JLabel attenteMoy;
	private JLabel jLabel3;
	private JLabel tpsCpu;
	private JLabel jLabel2;
	private JLabel valeurTaux;
	private JLabel jLabel1;
	private JPanel infos;

	public AffichageGraphe(Programme frame) {
		super(frame);
		this.setModal(true);
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
			Graphe panel = new Graphe(this.fenetrePrincipale,liste,nbTaches,ppcm);

			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setSize(326, 235);
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setPreferredSize(new java.awt.Dimension(104, 45));
			{
				infos = new JPanel();
				GridBagLayout infosLayout = new GridBagLayout();
				getContentPane().add(infos, BorderLayout.NORTH);
				infos.setPreferredSize(new java.awt.Dimension(318, 28));
				infosLayout.rowWeights = new double[] {0.1};
				infosLayout.rowHeights = new int[] {7};
				infosLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				infosLayout.columnWidths = new int[] {7, 7, 7, 7, 20, 20};
				infos.setLayout(infosLayout);
				{
					jLabel1 = new JLabel();
					infos.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel1.setText("Taux préemption :");
				}
				{
					valeurTaux = new JLabel();
					infos.add(valeurTaux, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jLabel2 = new JLabel();
					infos.add(jLabel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel2.setText("Tps total cpu :");
				}
				{
					tpsCpu = new JLabel();
					infos.add(tpsCpu, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jLabel3 = new JLabel();
					infos.add(jLabel3, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel3.setText("Attente moy apério :");
				}
				{
					attenteMoy = new JLabel();
					infos.add(attenteMoy, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
			}

			setSize(Graphe.LONGUEUR_LIGNE+150, Graphe.xDepart+((nbTaches+1)*Graphe.ESPACEMENT_LIGNE)+50);
			//pack();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAttenteMoy(String attenteMoy) {
		this.attenteMoy.setText(attenteMoy);
	}

	public void setTpsCpu(String tpsCpu) {
		this.tpsCpu.setText(tpsCpu);
	}

	public void setValeurTaux(String valeurTaux) {
		this.valeurTaux.setText(valeurTaux);
	}

}
