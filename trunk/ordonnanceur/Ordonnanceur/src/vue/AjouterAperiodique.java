package vue;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
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
public class AjouterAperiodique extends javax.swing.JDialog {

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
	private JButton boutonValider;
	private JTextField textRi;
	private JTextField textCi;
	private Programme fenetrePrincipale; 
	private JTabbedPane jTabbedPane1;
	private JPanel Manuel;
	public AjouterAperiodique(Programme frame) {
		super(frame);
		this.fenetrePrincipale=frame;
		initGUI();
		
		this.setTitle("Ajouter une tâche Apériodique");
		this.setLocationRelativeTo(this.getParent());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public JTextField getTextRi() {
		return textRi;
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
				boutonValider.setBounds(113, 112, 72, 21);
				boutonValider.setActionCommand("ajouterAperio");
				boutonValider.addActionListener(new BoutonsListener(this.fenetrePrincipale));
			}
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(0, 0, 311, 85);
				{
					Manuel = new JPanel();
					jTabbedPane1.addTab("Manuel", null, Manuel, null);
					Manuel.setName("Manuel");
					Manuel.setBounds(0, 19, 309, 68);
					Manuel.setLayout(null);
					Manuel.setPreferredSize(new java.awt.Dimension(306, 79));
					{
						jLabel1 = new JLabel();
						Manuel.add(jLabel1);
						jLabel1.setText("Temps d'execution (Ci) :");
						jLabel1.setBounds(12, 8, 129, 19);
					}
					{
						jLabel2 = new JLabel();
						Manuel.add(jLabel2);
						jLabel2.setText("Réveil (Ri) :");
						jLabel2.setBounds(12, 42, 62, 14);
					}
					{
						textCi = new JTextField();
						Manuel.add(textCi);
						textCi.setBounds(170, 7, 59, 21);
					}
					{
						textRi = new JTextField();
						Manuel.add(textRi);
						textRi.setBounds(170, 39, 59, 21);
					}
				}
			}
			this.setSize(319, 175);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void effacerChamps(){
		this.textCi.setText("");
		this.textRi.setText("");
		
	}


}
