package vue;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


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
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton boutonValider;
	private JTextField textRi;
	private JTextField textCi;

	public AjouterAperiodique(JFrame frame) {
		super(frame);
		this.setTitle("Ajouter une tâche Apériodique");
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Temps d'execution (Ci) :");
				jLabel1.setBounds(12, 27, 129, 19);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Réveil (Ri) :");
				jLabel2.setBounds(12, 61, 62, 14);
			}
			{
				textCi = new JTextField();
				getContentPane().add(textCi);
				textCi.setBounds(170, 26, 59, 21);
			}
			{
				textRi = new JTextField();
				getContentPane().add(textRi);
				textRi.setBounds(170, 58, 59, 21);
			}
			{
				boutonValider = new JButton();
				getContentPane().add(boutonValider);
				boutonValider.setText("Ajouter");
				boutonValider.setBounds(128, 91, 50, 21);
				boutonValider.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						boutonValiderMouseClicked(evt);
					}
				});
			}
			this.setSize(319, 166);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void boutonValiderMouseClicked(MouseEvent evt) {
		
	}

}
