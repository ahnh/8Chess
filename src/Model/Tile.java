package Model;

public class Tile {
	private Piece piece;
	private boolean exist;
	
	public Tile(Piece piece) {
		this.piece = piece;
		this.exist = true;
	}

	@Override
	public char toString() {
		if (piece == null)
		{
			return '_';
		}
		else if (piece != null && exist)
		{
			return piece.getDisplayChar();
		}
		else
		{
			return ' ';
		}
	}
}
