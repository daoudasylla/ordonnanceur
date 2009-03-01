
package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;



public class RunMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Programme inst = new Programme();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setFenetres();
			}
		});
	}

}
