package Model;

public class Tile {
	private Piece piece;
	private boolean exist;
	
	public Tile () {
		exist = true;
	}
	
	public Tile(Piece piece) {
		this.piece = piece;
		this.exist = true;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece newPiece) {
		piece = newPiece;
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
