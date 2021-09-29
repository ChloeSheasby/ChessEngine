package chessPD;

import java.util.ArrayList;

public class Player 
{
	public boolean white;
	public String name;
	public PlayerType type;
	public ArrayList<Piece> pieces;
	public ArrayList<Move> moves;
	public enum PlayerType { HUMAN, COMPUTER };
		
	public Player(boolean w, PlayerType type, String name)
	{
		this.setWhite(w);
		this.type = type;
		this.setName(name);
	}
	
	public boolean isWhite() {
		return white;
	}
	public void setWhite(boolean white) {
		this.white = white;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public ArrayList<Move> getMoves(Board board)
	{
		this.moves = new ArrayList<Move>();
		
		for(Piece piece : this.getPieces(board))
		{
			for(int i = 0; i < piece.calcPossibleMoves(board).size(); i++)
			{
				int endX = piece.calcPossibleMoves(board).get(i).getX();
				int endY = piece.calcPossibleMoves(board).get(i).getY();
				Move move = new Move(this, piece.getPos(), piece.calcPossibleMoves(board).get(i));
				if(board.getPieceForPosition(endX, endY) != null)
					move.setpKilled(board.getPieceForPosition(endX, endY));
				this.moves.add(move);
			}
		}
		
		return this.moves;
	}
	
	public ArrayList<Piece> getPieces(Board board)
	{	
		this.pieces = new ArrayList<Piece>();
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if((board.getPieceForPosition(i, j) instanceof King || board.getPieceForPosition(i, j) instanceof Queen
					|| board.getPieceForPosition(i, j) instanceof Bishop || board.getPieceForPosition(i, j) instanceof Knight
					|| board.getPieceForPosition(i, j) instanceof Rook || board.getPieceForPosition(i, j) instanceof Pawn)
						&& board.getPieceForPosition(i, j).isWhite() == this.isWhite())
					this.pieces.add(board.getPieceForPosition(i, j));
			}
		}
		return this.pieces;
	}

	public King getKing(Board board)
	{
		this.getPieces(board);
		for(Piece piece : this.getPieces(board))
		{
			if(piece instanceof King)
				return (King) piece;
		}
		
		return null;	// should only return null if king is dead, and game would be over
	}
}

