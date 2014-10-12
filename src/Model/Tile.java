package Model;

public class Tile {
	private Piece piece;
	private boolean exist;
	
	public Tile(Piece piece) {
		this.piece = piece;
		this.exist = true;
	}

	@Override
	public String toString() {
		if (piece == null)
		{
			return "_";
		}
		else if (piece != null && exist)
		{
			return piece.getDisplayStr();
		}
		else
		{
			return " ";
		}
	}
}
