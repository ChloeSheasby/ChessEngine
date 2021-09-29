package chessPD;

public class Knight extends Piece 
{
	public Knight(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "N";
		else
			return "n";
	}
	
	public boolean canMove(Board board, Position start, Position end)
	{		
		//can't move to a space with the same color as me
		if(end.getPiece() != null)
		{
			if(end.getPiece().isWhite() == this.isWhite())
				return false;
		}
		
		// knights goes in an L shape, any combination will only multiply to 2
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		return x * y == 2;
	}
}