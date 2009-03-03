package vue;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import exe.Programme;


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
public class AjouterPeriodique extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton boutonValider;
	private JTextField textDi;
	private JTextField textPi;
	private JTextField textCi;
	private Programme fenetrePrincipale;
	private JLabel jLabel5;
	private JTextField textUp;
	private JTextField textNbreTaches;
	private JLabel jLabel4;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTabbedPane onglets;


	public AjouterPeriodique(Programme frame) {
		super(frame);
		this.fenetrePrincipale=frame;
		initGUI();
		
		this.setTitle("Ajouter une tâche Périodique");
		this.setLocationRelativeTo(this.getParent());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JTextField getTextDi() {
		return textDi;
	}

	public JTextField getTextPi() {
		return textPi;
	}

	public JTextField getTextCi() {
		return textCi;
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
			}
			{
				boutonValider = new JButton();
				getContentPane().add(boutonValider);
				boutonValider.setText("Ajouter");
				boutonValider.setBounds(113, 133, 75, 21);
				boutonValider.setActionCommand("ajouterPerio");
				boutonValider.addActionListener(new BoutonsListener(this.fenetrePrincipale));
				
			}
			{
				jPanel1 = new JPanel();
				jPanel1.setName("Manuel");
				jPanel1.setBounds(2, 22, 304, 96);
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("Temps d'execution (Ci) :");
					jLabel1.setBounds(10, 5, 129, 19);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("Période (Pi) :");
					jLabel2.setBounds(10, 39, 62, 14);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("DeadLine (Di) :");
					jLabel3.setBounds(10, 70, 71, 14);
				}
				{
					textCi = new JTextField();
					jPanel1.add(textCi);
					textCi.setBounds(168, 4, 59, 21);
				}
				{
					textPi = new JTextField();
					jPanel1.add(textPi);
					textPi.setBounds(168, 36, 59, 21);
				}
				{
					textDi = new JTextField();
					jPanel1.add(textDi);
					textDi.setBounds(168, 67, 59, 21);
				}
			}
			onglets = new JTabbedPane();
			
			//jPanel1.setPreferredSize(new java.awt.Dimension(300, 99));
			onglets.addTab("Manuel", jPanel1);
			{
				jPanel2 = new JPanel();
				onglets.addTab("Automatique", null, jPanel2, null);
				jPanel2.setLayout(null);
				{
					jLabel4 = new JLabel();
					jPanel2.add(jLabel4);
					jLabel4.setText("Nombre de tâches :");
					jLabel4.setBounds(10, 6, 94, 14);
				}
				{
					textNbreTaches = new JTextField();
					jPanel2.add(textNbreTaches);
					textNbreTaches.setBounds(167, 4, 59, 20);
				}
				{
					jLabel5 = new JLabel();
					jPanel2.add(jLabel5);
					jLabel5.setText("Facteur up (flottant) :");
					jLabel5.setBounds(10, 36, 106, 14);
				}
				{
					textUp = new JTextField();
					jPanel2.add(textUp);
					textUp.setBounds(167, 33, 59, 20);
				}
			}
			getContentPane().add(onglets);
			onglets.setBounds(0, 0, 311, 127);

			//onglets.setOpaque(true);
			
			this.setSize(319, 199);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTabbedPane getOnglets() {
		return onglets;
	}

	public JTextField getTextUp() {
		return textUp;
	}

	public JTextField getTextNbreTaches() {
		return textNbreTaches;
	}

	public void effacerChamps(){
		this.textCi.setText("");
		this.textDi.setText("");
		this.textPi.setText("");
	}

}
