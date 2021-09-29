package chessPD;

public class Bishop extends Piece 
{
	public Bishop(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "B";
		else
			return "b";
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
		// bishop can only move diagonally and cannot jump
		
		// checks if bishop is not moving diagonally
		if(x == 0 || y == 0)
			return false;
		// bishop must move the same number of x and y values
		else if(Math.abs(x) != Math.abs(y))
			return false;
		else
		{
			// checks if there are any in the way diagonally
			// must handle all directions, so cannot start with absolute values
			
			if(x < 0 && y > 0)
			{
				int i = start.getX() + 1, j = start.getY() - 1;
				while(i <= end.getX() && j >= end.getY())
				{
					if(board.getBoard()[i][j].getPiece() != null && i != end.getX() && j != end.getY())
						return false;
					i++;
					j--;
				}
			}
			else if(x < 0 && y < 0)
			{
				int i = start.getX() + 1, j = start.getY() + 1;
				while(i <= end.getX() && j <= end.getY())
				{
					if(board.getBoard()[i][j].getPiece() != null && i != end.getX() && j != end.getY())
						return false;
					i++;
					j++;
				}
			}
			else if(x > 0 && y > 0)
			{
				int i = start.getX() - 1, j = start.getY() - 1;
				while(i >= end.getX() && j >= end.getY())
				{
					if(board.getBoard()[i][j].getPiece() != null && i != end.getX() && j != end.getY())
						return false;
					i--;
					j--;
				}
			}
			else if(x > 0 && y < 0)
			{
				int i = start.getX() - 1, j = start.getY() + 1;
				while(i >= end.getX() && j <= end.getY())
				{
					if(board.getBoard()[i][j].getPiece() != null && i != end.getX() && j != end.getY())
						return false;
					i--;
					j++;
				}
			}
		}
		
		return true;
	}
}