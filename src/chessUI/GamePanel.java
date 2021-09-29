package chessUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import chessPD.Game;
import chessPD.Position;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private JLabel[][] pieces = new JLabel[8][8];
	private int xAdj, yAdj;
	
	/**
	 * Create the panel.
	 */
	
	// NEED TO USE TRANSFER HANDLER??
	public GamePanel(JFrame currentFrame, Game game) 
	{	
		setLayout(null);
		
		layeredPane = new JLayeredPane();
		currentFrame.add(layeredPane);
		layeredPane.setPreferredSize(new Dimension(600,600));
		
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setBounds(306, 96, 600, 600);
		add(chessBoard);
		
		this.initializeBoard(game);
		
		JLabel lblChess = new JLabel("Chess");
		lblChess.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblChess.setBounds(450, 6, 130, 73);
		add(lblChess);
		
		JLabel lblPlayer = new JLabel("Player 1: ");
		lblPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer.setBounds(67, 90, 77, 16);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2:");
		lblPlayer_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer_1.setBounds(67, 118, 61, 16);
		add(lblPlayer_1);
		
		JLabel label = new JLabel(game.getP1().name);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label.setBounds(140, 90, 93, 16);
		add(label);
		
		JLabel label_1 = new JLabel(game.getP2().name);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label_1.setBounds(140, 118, 93, 16);
		add(label_1);
	}

	private void initializeBoard(Game game)
	{
		// set colors and put pieces (strings) on chess board
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				JPanel square = new JPanel(new BorderLayout());
				chessBoard.add(square);
				pieces[i][j] = new JLabel();
				
				if(i % 2 == 0)
				{
					if(j % 2 == 0)
						square.setBackground(Color.GRAY);
					else
						square.setBackground(Color.WHITE);
				}
				else
				{
					if(j % 2 == 0)
						square.setBackground(Color.WHITE);
					else
						square.setBackground(Color.GRAY);
				}
				
				if(game.getBoard().getBoard()[i][j].getPiece() != null)
				{	
					if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("K"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WKing.png"));
						pieces[i][j].setName("WKing");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("k"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BKing.png"));
						pieces[i][j].setName("BKing");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("Q"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WQueen.png"));
						pieces[i][j].setName("WQueen");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("q"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BQueen.png"));
						pieces[i][j].setName("BQueen");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("N"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WKnight.png"));
						pieces[i][j].setName("WKnight");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("n"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BKnight.png"));
						pieces[i][j].setName("BKnight");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("B"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WBishop.png"));
						pieces[i][j].setName("WBishop");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("b"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BBishop.png"));
						pieces[i][j].setName("BBishop");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("R"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WRook.png"));
						pieces[i][j].setName("WRook");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("r"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BRook.png"));
						pieces[i][j].setName("BRook");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("P"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("WPawn.png"));
						pieces[i][j].setName("WPawn");
					}
					else if(game.getBoard().getBoard()[i][j].getPiece().toString().contentEquals("p"))
					{
						pieces[i][j] = new JLabel(new ImageIcon("BPawn.png"));
						pieces[i][j].setName("BPawn");
					}
				}
				square.add(pieces[i][j]);
				pieces[i][j].addMouseListener(new PieceListener(game));
				pieces[i][j].addMouseMotionListener(new PieceListener(game));
			}
		}
	}
	
	private class PieceListener implements MouseListener, MouseMotionListener
	{
		private Game game;
		public PieceListener(Game game)
		{
			this.game = game;
		}
		public void mouseClicked(MouseEvent e)
		{
			JLabel src = (JLabel) e.getSource();
			this.resetBoard();
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(game.getCurrentPlayer().isWhite())
					{
						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'W')
						{
							for(Position pos : game.getBoard().getBoard()[i][j].getPiece().calcPossibleMoves(game.getBoard()))
							{
								int x = pos.getX();
								int y = pos.getY();
								pieces[x][y].setBackground(Color.BLUE);
								pieces[x][y].setOpaque(true);
							}
						}
					}
					else
					{
						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'B')
						{
							for(Position pos : game.getBoard().getBoard()[i][j].getPiece().calcPossibleMoves(game.getBoard()))
							{
								int x = pos.getX();
								int y = pos.getY();
								pieces[x][y].setBackground(Color.BLUE);
								pieces[x][y].setOpaque(true);
							}
						}
					}
				}
			}
		}
		public void resetBoard()
		{
			// resets backgrounds on board
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					pieces[i][j].setBackground(null);
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) 
		{
//			JLabel src = (JLabel) e.getSource();
//
//			for(int i = 0; i < 8; i++)
//			{
//				for(int j = 0; j < 8; j++)
//				{
//					if(game.getCurrentPlayer().isWhite())
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'W')
//						{
//							Point parentLoc = src.getParent().getLocation();
//							xAdj = parentLoc.x - e.getX();
//							yAdj = parentLoc.y - e.getY();
//							pieces[i][j].setLocation(e.getX() + xAdj, e.getY() + yAdj);
//							layeredPane.add(pieces[i][j], JLayeredPane.DRAG_LAYER);
//							layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//						}
//					}
//					else
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'B')
//						{
//
//						}
//					}
//				}
//			}
//			repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) 
		{
//			JLabel src = (JLabel) e.getSource();
//
//			for(int i = 0; i < 8; i++)
//			{
//				for(int j = 0; j < 8; j++)
//				{
//					if(game.getCurrentPlayer().isWhite())
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'W')
//						{
//							int x = e.getX() + xAdj;
//							int maxX = layeredPane.getWidth() - pieces[i][j].getWidth();
//							x = Math.min(x, maxX);
//							x = Math.max(x, 0);
//							
//							int y = e.getY() + yAdj;
//							int maxY = layeredPane.getHeight() - pieces[i][j].getHeight();
//							y = Math.min(y, maxY);
//							y = Math.max(y, 0);
//							
//							pieces[i][j].setLocation(x, y);
//						}
//					}
//					else
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'B')
//						{
//
//						}
//					}
//				}
//			}	
//			repaint();
		}
		@Override
		public void mouseReleased(MouseEvent e) 
		{
//			JLabel src = (JLabel) e.getSource();
//
//			for(int i = 0; i < 8; i++)
//			{
//				for(int j = 0; j < 8; j++)
//				{
//					if(game.getCurrentPlayer().isWhite())
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'W')
//						{
//							layeredPane.setCursor(null);
//							pieces[i][j].setVisible(false);
//							layeredPane.remove(pieces[i][j]);
//							pieces[i][j].setVisible(true);
//							
//							int maxX = layeredPane.getWidth() - pieces[i][j].getWidth();
//							int x = Math.min(e.getX(), maxX);
//							x = Math.max(x, 0);
//							
//							int maxY = layeredPane.getHeight() - pieces[i][j].getHeight();
//							int y = Math.min(e.getY(), maxY);
//							y = Math.max(y, 0);
//							
//							Component com = chessBoard.findComponentAt(x, y);
//							
////							pieces[i][j].setLocation(e.getX() + xAdj, e.getY() + yAdj);
////							JPanel panel = (JPanel) chessBoard.getComponent(i + j);
////							panel.remove(pieces[i][j]);
////							JPanel end = (JPanel) chessBoard.getComponentAt(e.getX(), e.getY());
////							end.add(pieces[i][j]);
//							
//							if(com instanceof JLabel)
//							{
//								Container parent = com.getParent();
//								parent.remove(0);
//								parent.add(pieces[i][j]);
//								parent.validate();
//								repaint();
//							}
//							else
//							{
//								Container parent = (Container) src;
//								parent.add(pieces[i][j]);
//								parent.validate();
//								repaint();
//							}
//						}
//					}
//					else
//					{
//						if(src == pieces[i][j] && pieces[i][j].getName().charAt(0) == 'B')
//						{
//
//						}
//					}
//				}
//			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
