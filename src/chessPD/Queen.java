package chessPD;

public class Queen extends Piece 
{
	public Queen(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "Q";
		else
			return "q";
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
		// queen cannot jump, is a combo of bishop and rook
		
		// tests rook moves
		if(x == 0)
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
		// tests bishop moves
		else if(Math.abs(x) != Math.abs(y))
			return false;
		else
		{
			// checks if there are any in the way diagonally
			
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