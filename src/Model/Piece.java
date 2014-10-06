package Model;

public abstract class Piece {
	private String name;
	private String _movable[];
	
	public Piece() {
	}
	
	public String getName()
	{
		return name;
	}
	public String[] getMovable(String movable)
	{
		return _movable;
		
	}
}
