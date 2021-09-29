package chessPD;

public class Position 
{
	private Piece piece;
	private int x;
	private int y;
	
	public Position(int x, int y, Piece piece)
	{
		this.setX(x);
		this.setY(y);
		this.setPiece(piece);
		if(this.getPiece() != null)
			this.getPiece().setPos(this);
	}

	public Piece getPiece() 
	{
		return piece;
	}

	public void setPiece(Piece piece) 
	{
		this.piece = piece;
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
	
	public String toString()
	{
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
}
