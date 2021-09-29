package chessPD;

public class King extends Piece 
{
	//DO CASTLING?
		
	public King(boolean w)
	{
		super(w);
	}
	
	public String toString()
	{
		if(this.isWhite())
			return "K";
		else
			return "k";
	}
	
	public boolean isCheck(Board board)
	{
		int x = this.getPos().getX(), y = this.getPos().getY();
		boolean isProtected = false;
		
		// this also sets isProtected as true if there is a piece in the way that is of the same color
		// if king is protected in that direction, then it can't be in check
		
		// check straight lines from king for x and y
		for(int i = x + 1; i < 8 && !isProtected; i++)
		{
			isProtected = false;
			if(board.getBoard()[i][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[i][y].getPiece() instanceof Rook
						|| board.getBoard()[i][y].getPiece() instanceof Queen)
						&& board.getBoard()[i][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[i][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
		}
		isProtected = false;	// reset isProtected
		for(int i = x - 1; i >= 0 && !isProtected; i--)
		{
			isProtected = false;
			if(board.getBoard()[i][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[i][y].getPiece() instanceof Rook
						|| board.getBoard()[i][y].getPiece() instanceof Queen)
						&& board.getBoard()[i][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[i][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
		}
		isProtected = false;	// reset isProtected
		for(int i = y + 1; i < 8 && !isProtected; i++)
		{
			isProtected = false;
			if(board.getBoard()[x][i].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][i].getPiece() instanceof Rook
						|| board.getBoard()[x][i].getPiece() instanceof Queen)
						&& board.getBoard()[x][i].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][i].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
		}
		isProtected = false;	// reset isProtected
		for(int i = y - 1; i >= 0 && !isProtected; i--)
		{
			isProtected = false;
			if(board.getBoard()[x][i].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][i].getPiece() instanceof Rook
						|| board.getBoard()[x][i].getPiece() instanceof Queen)
						&& board.getBoard()[x][i].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][i].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
		}
		
		// check diagonal lines
		isProtected = false;	// reset isProtected
		x++; 
		y++;	// set x and y so that it is not on the king's space
		while(x < 8 && y < 8 && !isProtected)
		{	
			isProtected = false;
			if(board.getBoard()[x][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][y].getPiece() instanceof Bishop)
						&& board.getBoard()[x][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
			x++;
			y++;
		}
		x = this.getPos().getX();
		y = this.getPos().getY();	// reset x and y
		x++; 
		y--;	// set x and y so that it is not on the king's space
		isProtected = false;	// reset isProtected
		while(x < 8 && y >= 0 && !isProtected)
		{	
			isProtected = false;
			if(board.getBoard()[x][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][y].getPiece() instanceof Bishop)
						&& board.getBoard()[x][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
			x++;
			y--;
		}
		x = this.getPos().getX();
		y = this.getPos().getY();	// reset x and y
		x--; 
		y--;	// set x and y so that it is not on the king's space
		isProtected = false;	// reset isProtected
		while(x >= 0 && y >= 0 && !isProtected)
		{	
			isProtected = false;
			if(board.getBoard()[x][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][y].getPiece() instanceof Bishop)
						&& board.getBoard()[x][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
			x--;
			y--;
		}
		x = this.getPos().getX();
		y = this.getPos().getY();	// reset x and y
		x--; 
		y++;	// set x and y so that it is not on the king's space
		isProtected = false;	// reset isProtected
		while(x >= 0 && y < 8 && !isProtected)
		{	
			isProtected = false;
			if(board.getBoard()[x][y].getPiece() != null)
			{
				// if piece is a rook or queen of the opponent's color, king is in check
				if((board.getBoard()[x][y].getPiece() instanceof Bishop)
						&& board.getBoard()[x][y].getPiece().isWhite() != this.isWhite())
				{
					isProtected = false;
					return true;
				}
				// if piece is same color, king is protected
				else if(board.getBoard()[x][y].getPiece().isWhite() == this.isWhite())
					isProtected = true;
			}
			x--;
			y++;
		}
		x = this.getPos().getX();
		y = this.getPos().getY();	// reset x and y
		
		// checking all possible positions of knights
		// must ensure that array is not out of bounds
		if((x + 2 < 8) && (y + 1 < 8) && board.getBoard()[x + 2][y + 1].getPiece() instanceof Knight 
				&& board.getBoard()[x + 2][y + 1].getPiece().isWhite() != this.isWhite())
			return true;
		if((x + 1 < 8) && (y + 2 < 8) && board.getBoard()[x + 1][y + 2].getPiece() instanceof Knight 
				&& board.getBoard()[x + 1][y + 2].getPiece().isWhite() != this.isWhite())
			return true;
		if((x - 2 >= 0) && (y + 1 < 8) && board.getBoard()[x - 2][y + 1].getPiece() instanceof Knight 
				&& board.getBoard()[x - 2][y + 1].getPiece().isWhite() != this.isWhite())
			return true;
		if((x - 1 >= 0) && (y + 2 < 8) && board.getBoard()[x - 1][y + 2].getPiece() instanceof Knight 
				&& board.getBoard()[x - 1][y + 2].getPiece().isWhite() != this.isWhite())
			return true;
		if((x + 2 < 8) && (y - 1 >= 0) && board.getBoard()[x + 2][y - 1].getPiece() instanceof Knight 
				&& board.getBoard()[x + 2][y - 1].getPiece().isWhite() != this.isWhite())
			return true;
		if((x + 1 < 8) && (y - 2 >= 0) && board.getBoard()[x + 1][y - 2].getPiece() instanceof Knight 
				&& board.getBoard()[x + 1][y - 2].getPiece().isWhite() != this.isWhite())
			return true;
		if((x - 2 >= 0) && (y - 1 >= 0) && board.getBoard()[x - 2][y - 1].getPiece() instanceof Knight 
				&& board.getBoard()[x - 2][y - 1].getPiece().isWhite() != this.isWhite())
			return true;
		if((x - 1 >= 0) && (y - 2 >= 0) && board.getBoard()[x - 1][y - 2].getPiece() instanceof Knight 
				&& board.getBoard()[x - 1][y - 2].getPiece().isWhite() != this.isWhite())
			return true;
		
		// checking all possible positions of pawns
		// must check if white or black since pawns can only move one direction
		if(this.isWhite())
		{
			if((x + 1 < 8) && (y - 1 >= 0) && board.getBoard()[x + 1][y - 1].getPiece() instanceof Pawn 
				&& !board.getBoard()[x + 1][y - 1].getPiece().isWhite())
				return true;
			if((x + 1 < 8) && (y + 1 < 8) && board.getBoard()[x + 1][y + 1].getPiece() instanceof Pawn 
				&& !board.getBoard()[x + 1][y + 1].getPiece().isWhite())
				return true;
		}
		else
		{
			if((x - 1 >= 0) && (y - 1 >= 0) && board.getBoard()[x - 1][y - 1].getPiece() instanceof Pawn 
				&& board.getBoard()[x - 1][y - 1].getPiece().isWhite())
				return true;
			if((x - 1 >= 0) && (y + 1 < 8) && board.getBoard()[x - 1][y + 1].getPiece() instanceof Pawn 
				&& board.getBoard()[x - 1][y + 1].getPiece().isWhite())
				return true;
		}		
				
		return false;
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
		
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());

		// king can only move one x, y, or diagonally
		if((x == 1 && y == 0) || (x == 0 && y == 1) || (x == 1 && y == 1))
			move = true;
		
		return move;
	}
}
