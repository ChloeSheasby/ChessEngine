package chessPD;

public class Rook extends Piece 
{
	public Rook(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "R";
		else
			return "r";
	}
	
	public boolean canMove(Board board, Position start, Position end)
	{		
		//can't move to a space with the same color as me
		if(end.getPiece() != null)
		{
			if(end.getPiece().isWhite() == this.isWhite())
				return false;
		}
		
		int x = start.getX() - end.getX();
		int y = start.getY() - end.getY();
		// rook cannot move diagonally and cannot jump
		
		// if position would move diagonally, that's not allowed
		if(x != 0 && y != 0)
		{
			return false;
		}
		// either x or y would be 0, this handles all directions
		else if(x == 0)
		{
			if(y > 0)
			{
				for(int i = start.getY() - 1; i >= end.getY(); i--)
				{
					// if there is a piece in the way, return false
					if(board.getBoard()[start.getX()][i].getPiece() != null && i != end.getX())
						return false;
				}
			}
			else if(y < 0)
			{
				for(int i = start.getY() + 1; i <= end.getY(); i++)
				{
					// if there is a piece in the way, return false
					if(board.getBoard()[start.getX()][i].getPiece() != null && i != end.getX())
						return false;
				}
			}
		}
		else if(y == 0)
		{
			if(x > 0)
			{
				for(int i = start.getX() - 1; i >= end.getX(); i--)
				{
					// if there is a piece in the way, return false
					if(board.getBoard()[i][start.getY()].getPiece() != null && i != end.getX())
						return false;
				}
			}
			else if(x < 0)
			{
				for(int i = start.getX() + 1; i <= end.getX(); i++)
				{
					// if there is a piece in the way, return false
					if(board.getBoard()[i][start.getY()].getPiece() != null && i != end.getX())
						return false;
				}
			}
		}
		
		return true;
	}
}