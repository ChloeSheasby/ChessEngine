package chessUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import chessPD.Game;
import chessPD.Player;
import chessPD.Player.PlayerType;

@SuppressWarnings("serial")
public class NewPanel extends JPanel 
{
	private DefaultComboBoxModel<String> player1Type;
	private DefaultComboBoxModel<String> player2Type;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public NewPanel(JFrame currentFrame) 
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

		JLabel lblChess = new JLabel("New Game");
		lblChess.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		lblChess.setBounds(400, 25, 250, 60);
		add(lblChess);
		
		JLabel lblSelectPlayers = new JLabel("Select players:");
		lblSelectPlayers.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblSelectPlayers.setBounds(410, 97, 200, 30);
		add(lblSelectPlayers);
		
		JLabel lblWhite = new JLabel("White");
		lblWhite.setBounds(345, 188, 72, 16);
		add(lblWhite);
		
		player1Type = new DefaultComboBoxModel<String>();
		player1Type.addElement("Player 1");
		player1Type.addElement("CPU");
		player1Type.setSelectedItem("Player 1");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(player1Type);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("CPU"))
				{
					textField.setEditable(false);
					textField.setText("BB-8");
				}
				else
				{
					textField.setEditable(true);
					textField.setText("");
				}
			}
		});
		comboBox.setBounds(391, 184, 130, 27);
		add(comboBox);
		
		JLabel lblBlack = new JLabel("Black");
		lblBlack.setBounds(345, 228, 72, 16);
		add(lblBlack);
		
		player2Type = new DefaultComboBoxModel<String>();
		player2Type.addElement("Player 2");
		player2Type.addElement("CPU");
		player2Type.setSelectedItem("Player 2");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox_1 = new JComboBox(player2Type);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem().equals("CPU"))
				{
					textField_1.setEditable(false);
					textField_1.setText("R2-D2");
				}
				else
				{
					textField_1.setEditable(true);
					textField_1.setText("");
				}
			}
		});
		comboBox_1.setBounds(391, 224, 130, 27);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(533, 183, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(533, 223, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerType pt1, pt2;
				if(comboBox.getSelectedItem().equals("Player 1"))
					pt1 = PlayerType.HUMAN;
				else
					pt1 = PlayerType.COMPUTER;
				
				if(comboBox_1.getSelectedItem().equals("Player 2"))
					pt2 = PlayerType.HUMAN;
				else
					pt2 = PlayerType.COMPUTER;
				
				Player p1 = new Player(true, pt1, textField.getText());
				Player p2 = new Player(false, pt2, textField_1.getText());
				
				Game game = new Game(p1, p2);

				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new GamePanel(currentFrame, game));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewGame.setBounds(449, 313, 117, 45);
		add(btnNewGame);
	}

}
