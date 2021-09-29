package chessPD;

import java.util.ArrayList;

public abstract class Piece 
{
	private boolean killed = false;
	private boolean white = false;
	private Position pos;
	private ArrayList<Position> possibleMoves;
		
	public Piece(boolean w)
	{
		this.setWhite(w);
		possibleMoves = new ArrayList<Position>();
	}
	
	public boolean isKilled() 
	{
		return killed;
	}

	public void setKilled(boolean killed) 
	{
		this.killed = killed;
	}

	public boolean isWhite() 
	{
		return white;
	}

	public void setWhite(boolean white) 
	{
		this.white = white;
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public ArrayList<Position> getPossibleMoves() {
		return possibleMoves;
	}
	
	public void setPossibleMoves(ArrayList<Position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	// calculates all possible moves (positions) from current position
	public ArrayList<Position> calcPossibleMoves(Board board)
	{
		this.possibleMoves = new ArrayList<Position>();
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				Position end = new Position(i, j, board.getPieceForPosition(i, j));
				if(this.canMove(board, this.getPos(), end))
					this.possibleMoves.add(end);
			}
		}
		
		return this.possibleMoves;
	}

	public abstract String toString();
	
	public abstract boolean canMove(Board board, Position start, Position end);
}
