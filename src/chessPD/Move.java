package chessPD;

public class Move 
{
	private Player player;
	private Position start;
	private Position end;
	private Piece pMoved;
	private Piece pKilled;
	
	public Move(Player player, Position start, Position end)
	{
		this.setPlayer(player);
		this.setStart(start);
		this.setEnd(end);
		this.setpMoved(start.getPiece());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}

	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

	public Piece getpMoved() {
		return pMoved;
	}

	public void setpMoved(Piece pMoved) {
		this.pMoved = pMoved;
	}

	public Piece getpKilled() {
		return pKilled;
	}

	public void setpKilled(Piece pKilled) {
		this.pKilled = pKilled;
	}
}


