package chessPD;

import java.util.ArrayList;

public class Game 
{
	private Player p1;
	private Player p2;
	private Board board;
	private Player currentPlayer;
	private GameStatus status;
	private ArrayList<Move> movesPlayed;
	private ArrayList<Piece> piecesKilled;
	
	public enum GameStatus { ACTIVE, WHITE_WIN, BLACK_WIN, STALEMATE, FORFEIT }
	
	public Game(Player p1, Player p2)
	{
		this.board = new Board();
		this.movesPlayed = new ArrayList<Move>();
		this.piecesKilled = new ArrayList<Piece>();
		this.p1 = p1;
		this.p2 = p2;
		this.initialize();
	}
	
	private void initialize()
	{
		this.setStatus(GameStatus.ACTIVE);
				
		// white always goes first
		if(this.p1.isWhite())
		{
			this.setCurrentPlayer(this.p1);
		}
		else
		{
			this.setCurrentPlayer(this.p2);
		}
		
		//clear list of moves
		this.movesPlayed.clear();
	}
	
	public boolean isEnd()
	{
		// this is the end if the status is not active
		return (this.getStatus() != GameStatus.ACTIVE);
	}
	
	public boolean playerMove(Player player, int startX, int endX, int startY, int endY) throws Exception
	{
		Position startPos = board.getPos(startX, startY);
		Position endPos = board.getPos(endX, endY);
		Move move = new Move(player, startPos, endPos);
		return this.makeMove(move, player);
	}
	
	private boolean makeMove(Move move, Player player) throws Exception
	{
		Piece source = move.getStart().getPiece();
		Piece dest = move.getEnd().getPiece();
		Player other = null;
		if(this.currentPlayer.equals(p1))
			other = p2;
		else if(this.currentPlayer.equals(p2))
			other = p1;
		
		// check if piece is valid
		if(source == null)
			return false;
		
		//check if player is valid
		if(player != this.getCurrentPlayer())
			return false;
		
		// check if piece is valid for player
		if(source.isWhite() != player.isWhite())
			return false;
		
		// check if move is valid
		if(!source.canMove(board, move.getStart(), move.getEnd()))
			return false;
		
		// check if move killed
		if(dest != null)
		{
			dest.setKilled(true);
			move.setpKilled(dest);
			
			//add piece to list of pieces killed
			this.piecesKilled.add(dest);
			
			//if the king is killed, change game status
			if(dest instanceof King)
			{
				if(player.isWhite())
					this.setStatus(GameStatus.WHITE_WIN);
				else
					this.setStatus(GameStatus.BLACK_WIN);
			}
		}
		
		// add move to list
		movesPlayed.add(move);
		
		// actually move the piece
		move.getEnd().setPiece(source);
		move.getStart().setPiece(null);
		source.setPos(move.getEnd());
		
		// checks if the other player is in checkmate after current player has played
		if(this.getStatus() == GameStatus.ACTIVE)
		{
			if(this.isCheckMate(other))
			{
				// if white is in a checkmate then black wins and vice versa
				if(other.isWhite())
					this.setStatus(GameStatus.BLACK_WIN);
				else
					this.setStatus(GameStatus.WHITE_WIN);
				System.out.println("CHECKMATE ");
			}
			else
			{
				// checks for a stalemate
				isStalemate(other);
			}
		}
		
		// change current player to other player
		if(this.getCurrentPlayer().equals(p1))
			this.setCurrentPlayer(p2);
		else
			this.setCurrentPlayer(p1);
		
		return true;
	}
	
	public boolean isStalemate(Player player)
	{
		if(player.getMoves(this.board) == null)
		{
			this.setStatus(GameStatus.STALEMATE);
			return true;
		}
		
		return false;
	}
	
	public boolean isCheckMate(Player player) throws Exception
	{
		int startX = player.getKing(this.board).getPos().getX();
		int startY = player.getKing(this.board).getPos().getY();

		if(!player.getKing(this.board).isCheck(this.board) || !isStalemate(player))
			return false;
		
		// checks if the king can move to any spot
		for(int i = 0; i < this.getPossibleMovesForPiece(startX, startY).size(); i++)
		{			
			// this would check if any that the possible moves that the king would make would make a check
			int endX = this.getPossibleMovesForPiece(startX, startY).get(i).getX();
			int endY = this.getPossibleMovesForPiece(startX, startY).get(i).getY();
			this.testMove(player, startX, endX, startY, endY);
			
			// if the king can move to another spot that isn't in check, then this is not a checkmate
			if(!player.getKing(this.board).isCheck(this.board))
			{
				this.undoRecentMove();
				return false;
			}
			
			this.undoRecentMove();
		}
		
		// checks if any pieces can move to a spot to protect king
		for(Piece piece : player.getPieces(this.board))
		{
			// do a move for each piece and check if the king is still in check
			startX = piece.getPos().getX();
			startY = piece.getPos().getY();
			for(int i = 0; i < this.getPossibleMovesForPiece(startX, startY).size(); i++)
			{			
				// this would check if any that the possible moves that the king would make would make a check
				int endX = this.getPossibleMovesForPiece(startX, startY).get(i).getX();
				int endY = this.getPossibleMovesForPiece(startX, startY).get(i).getY();
				this.testMove(player, startX, endX, startY, endY);
				
				// if any piece can move to another spot that takes the king out of check, then this is not a checkmate
				if(!player.getKing(this.board).isCheck(this.board))
				{
					this.undoRecentMove();
					return false;
				}
				
				this.undoRecentMove();
			}
		}
		
		return true;
	}
	
	public void testMove(Player player, int startX, int endX, int startY, int endY) throws Exception
	{
		Position startPos = board.getPos(startX, startY);
		Position endPos = board.getPos(endX, endY);
		Move move = new Move(player, startPos, endPos);
		Piece source = move.getStart().getPiece();
		Piece dest = move.getEnd().getPiece();
		
		// check if move killed
		if(dest != null)
		{
			dest.setKilled(true);
			move.setpKilled(dest);
			
			//add piece to list of pieces killed
			this.piecesKilled.add(dest);
		}
		
		// add move to list
		movesPlayed.add(move);
		
		// actually move the piece
		move.getEnd().setPiece(source);
		move.getStart().setPiece(null);
		source.setPos(move.getEnd());
		
		// don't know if this is necessary
//		this.board.getBoard()[move.getStart().getX()][move.getStart().getY()].setPiece(null);
//		this.board.getBoard()[move.getEnd().getX()][move.getEnd().getY()].setPiece(source);
	}
	
	public void undoRecentMove()
	{
		Move move = this.movesPlayed.get(this.movesPlayed.size() - 1);
		Piece source = move.getEnd().getPiece();
		Piece dest = move.getpKilled();

		if(dest != null)
		{
			dest.setKilled(false);
			move.setpKilled(null);
			this.getPiecesKilled().remove(this.getPiecesKilled().size() - 1);
			dest.setPos(move.getEnd());
		}
		
		// move pieces back
		source.setPos(move.getStart());
		this.board.getBoard()[move.getStart().getX()][move.getStart().getY()].setPiece(source);
		this.board.getBoard()[move.getEnd().getX()][move.getEnd().getY()].setPiece(dest);
		
		// remove the move from the list
		this.movesPlayed.remove(this.movesPlayed.size() - 1);
	}
	
	public void printBoard()
	{
		this.board.printBoard();
	}
	
	public void evaluateGameStatus()
	{
		if(this.getStatus() == GameStatus.WHITE_WIN)
			System.out.println(this.getWhitePlayer().name + " WINS!!!");
		else if(this.getStatus() == GameStatus.BLACK_WIN)
			System.out.println(this.getBlackPlayer().name + " WINS!!!");
		else if(this.getStatus() == GameStatus.STALEMATE)
			System.out.println("Stalemate. No one wins.");
		// add a forfeit section
	}
	
	public ArrayList<Position> getPossibleMovesForPiece(int x, int y)
	{
		Piece piece = this.board.getPieceForPosition(x, y);
		ArrayList<Position> possibleMoves = new ArrayList<Position>();
		possibleMoves = piece.calcPossibleMoves(this.board);
		return possibleMoves;
	}
	
	public Player getOtherPlayer()
	{
		if(this.currentPlayer.equals(this.getP1()))
			return this.getP2();
		else if(this.currentPlayer.equals(this.getP2()))
			return this.getP1();
		return null;
	}
		
	public Player getWhitePlayer()
	{
		if(this.getP1().isWhite())
			return this.getP1();
		else
			return this.getP2();
	}
	
	public Player getBlackPlayer()
	{
		if(!this.getP1().isWhite())
			return this.getP1();
		else
			return this.getP2();
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public ArrayList<Move> getMovesPlayed() {
		return movesPlayed;
	}

	public void setMovesPlayed(ArrayList<Move> movesPlayed) {
		this.movesPlayed = movesPlayed;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Piece> getPiecesKilled() {
		return piecesKilled;
	}

	public void setPiecesKilled(ArrayList<Piece> piecesKilled) {
		this.piecesKilled = piecesKilled;
	}
	
}
