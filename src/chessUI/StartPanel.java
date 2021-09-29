package chessUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@SuppressWarnings("serial")
public class StartPanel extends JPanel 
{
	/**
	 * Create the panel.
	 */
	public StartPanel(JFrame currentFrame) 
	{
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);

		JLabel lblChess = new JLabel("Welcome to Chess!");
		lblChess.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		lblChess.setBounds(250, 100, 500, 69);
		add(lblChess);
	}

}
