package chessPD;

public class Board 
{
	private Position[][] board = new Position[8][8];
	
	public Board()
	{
		this.createBoard();
	}
	
	public Position[][] getBoard()
	{
		return this.board;
	}
	
	public Position getPos(int x, int y) throws Exception
	{
		if(x < 0 || x > 7 || y < 0 || y > 7)
			throw new Exception("Index out of bounds");
		
		return board[x][y];
	}
	
	public void createBoard()
	{
		//create white pieces, which are in two lines at the top
		board[0][0] = new Position(0, 0, new Rook(true));
		board[0][1] = new Position(0, 1, new Knight(true));
		board[0][2] = new Position(0, 2, new Bishop(true));
		board[0][3] = new Position(0, 3, new King(true));
		board[0][4] = new Position(0, 4, new Queen(true));
		board[0][5] = new Position(0, 5, new Bishop(true));
		board[0][6] = new Position(0, 6, new Knight(true));
		board[0][7] = new Position(0, 7, new Rook(true));

		board[1][0] = new Position(1, 0, new Pawn(true));
		board[1][1] = new Position(1, 1, new Pawn(true));
		board[1][2] = new Position(1, 2, new Pawn(true));
		board[1][3] = new Position(1, 3, new Pawn(true));
		board[1][4] = new Position(1, 4, new Pawn(true));
		board[1][5] = new Position(1, 5, new Pawn(true));
		board[1][6] = new Position(1, 6, new Pawn(true));
		board[1][7] = new Position(1, 7, new Pawn(true));

		//create black pieces, which are in two lines at the bottom
		board[7][0] = new Position(7, 0, new Rook(false));
		board[7][1] = new Position(7, 1, new Knight(false));
		board[7][2] = new Position(7, 2, new Bishop(false));
		board[7][3] = new Position(7, 3, new King(false));
		board[7][4] = new Position(7, 4, new Queen(false));
		board[7][5] = new Position(7, 5, new Bishop(false));
		board[7][6] = new Position(7, 6, new Knight(false));
		board[7][7] = new Position(7, 7, new Rook(false));

		board[6][0] = new Position(6, 0, new Pawn(false));
		board[6][1] = new Position(6, 1, new Pawn(false));
		board[6][2] = new Position(6, 2, new Pawn(false));
		board[6][3] = new Position(6, 3, new Pawn(false));
		board[6][4] = new Position(6, 4, new Pawn(false));
		board[6][5] = new Position(6, 5, new Pawn(false));
		board[6][6] = new Position(6, 6, new Pawn(false));
		board[6][7] = new Position(6, 7, new Pawn(false));
		
		//create rest of positions as null
		for(int i = 2; i < 6; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				board[i][j] = new Position(i, j, null);
			}
		}
	}
	
	public Board clone()
	{
		Board copy = new Board();
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 8; x++)
			{
				copy.board[x][y] = new Position(x, y, this.board[x][y].getPiece());
			}
		}
		return copy;
	}
	
	public boolean isGameComplete()
	{
		int check = 0;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(this.board[i][j].getPiece() instanceof King)
					check++;
			}
		}
		
		if(check != 2)
			return true;
		else
			return false;
	}
	
	public Piece getPieceForPosition(int x, int y)
	{
		if(this.board[x][y] != null)
			return board[x][y].getPiece();
		return null;
	}
	
	public void printBoard()
	{
		System.out.println("    A B C D E F G H");
		System.out.println();
		for(int i = 0; i < 8; i++)
		{
			System.out.print((i+1) + "   ");
			for(int j = 0; j < 8; j++)
			{
				if(!(board[i][j].getPiece() == null))
					System.out.print(board[i][j].getPiece().toString() + " ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}
}
