package chessPD;

public class Pawn extends Piece 
{
	public Pawn(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "P";
		else
			return "p";
	}
	
	private boolean initialPos(Position pos)
	{
		boolean initial = false;
		if(this.isWhite())
		{
			if(pos.getX() == 1)
				initial = true;
		}
		else
		{
			if(pos.getX() == 6)
				initial = true;
		}
		return initial;
	}
	
	public boolean canMove(Board board, Position start, Position end)
	{
		boolean move = false;
		
		//can't move to a space with the same color as me
		if(end.getPiece() != null)
		{
			if(end.getPiece().isWhite() == this.isWhite())
				return false;
		}
		
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		
		// pawn can only move one x, y, or diagonally if killing
		// pawn can only move forward, so must handle by color (position on the board)
		if(this.isWhite())
		{
			// if white, x or x & y will be negative
			if(end.getPiece() != null)
			{
				// tests diagonal killing
				if(!end.getPiece().isWhite() && x == -1 && Math.abs(y) == 1)
					move = true;
			}
			else if(this.initialPos(start))
			{
				// tests for moving two during initial position but can't jump
				if(x == -2 && y == 0 && board.getPieceForPosition(end.getX() - 1, end.getY()) == null)
					move = true;
				else if(x == -1 && y == 0)
					move = true;
			}
			else
			{
				// can only move forward
				if(x == -1 && y == 0)
					move = true;
			}
		}
		else
		{
			// if black, x or x & y will be positive
			if(end.getPiece() != null)
			{
				// tests diagonal killing
				if(end.getPiece().isWhite() && x == 1 && Math.abs(y) == 1)
					move = true;
			}
			else if(this.initialPos(start))
			{
				// tests for moving two during initial position but can't jump
				if(x == 2 && y == 0 && board.getPieceForPosition(end.getX() + 1, end.getY()) == null)
					move = true;
				else if(x == 1 && y == 0)
					move = true;
			}
			else
			{
				// can only move forward
				if(x == 1 && y == 0)
					move = true;
			}
		}
		
		return move;
	}
}