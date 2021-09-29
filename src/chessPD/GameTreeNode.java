package chessPD;

import java.util.ArrayList;
import java.util.Random;

public class GameTreeNode 
{
	private GameTreeNode parent;
	private ArrayList<GameTreeNode> children;
	private Board board;
	private Player currentPlayer;
	private Player otherPlayer;
	private int num;
	private int score;
		
	public GameTreeNode(GameTreeNode parent, Player player, Player other, Board board, int numChild)
	{
		this.currentPlayer = player;
		this.otherPlayer = other;
		this.parent = parent;
		this.board = board;
		this.children = new ArrayList<GameTreeNode>();
		this.num = numChild;
	}
	
	public void addChild(GameTreeNode child)
	{
		this.children.add(child);
	}
	
	public void createChildren()
	{
		if(!(this.num > 1))
		{ 
			for(Move move : this.currentPlayer.getMoves(this.board))
			{
				// order must be: create child, make move, add child, create children for child
				
				int newNum = this.num + 1;
				GameTreeNode child = new GameTreeNode(this, this.otherPlayer, this.currentPlayer, this.board.clone(), newNum);
				Piece source = move.getStart().getPiece();
			
				child.getArrayBoard()[move.getStart().getX()][move.getStart().getY()].setPiece(null);
				child.getArrayBoard()[move.getEnd().getX()][move.getEnd().getY()].setPiece(source);
				
				addChild(child);
//				System.out.println("\nGeneration " + child.getNum());
//				child.getBoard().printBoard();
				child.createChildren();
			}
		}
	}
	
	public void evaluate()
	{
		this.num = 0;
		for(Piece piece : this.currentPlayer.getPieces(this.board))
		{
			if(this.currentPlayer.isWhite())
			{
				if(piece instanceof Pawn)
					this.num += 10;
				else if(piece instanceof Knight || piece instanceof Bishop)
					this.num += 30;
				else if(piece instanceof Rook)
					this.num += 50;
				else if(piece instanceof Queen)
					this.num += 90;
				else if(piece instanceof King)
					this.num += 900;
			}
			else
			{
				if(piece instanceof Pawn)
					this.num -= 10;
				else if(piece instanceof Knight || piece instanceof Bishop)
					this.num -= 30;
				else if(piece instanceof Rook)
					this.num -= 50;
				else if(piece instanceof Queen)
					this.num -= 90;
				else if(piece instanceof King)
					this.num -= 900;
			}
		}
	}
	
	// RIGHT NOW this just returns a random move
	public Move getNextMove()
	{
		Random rand = new Random();
		int randInt = rand.nextInt(this.currentPlayer.getMoves(this.board).size() - 1);
		
		return this.currentPlayer.getMoves(this.board).get(randInt);
		
		// tic tac toe version
//		int biggest = -2;
//		GameTreeNode remember = null;
//		for(GameTreeNode child : getChildren())
//		{
//			int currentScore = child.getScore();
//			if(currentScore > biggest)
//			{
//				biggest = currentScore;
//				remember = child;
//			}
//		}
//		return remember.getLastX();
	}
	
	public Position[][] getArrayBoard()
	{
		return this.getBoard().getBoard();
	}

	public GameTreeNode getParent() {
		return parent;
	}

	public void setParent(GameTreeNode parent) {
		this.parent = parent;
	}

	public ArrayList<GameTreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<GameTreeNode> children) {
		this.children = children;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
