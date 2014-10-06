package Model;


import java.awt.Point;

public class Piece {
	
	private int color;
	private int type;
	private String name;
	private Point coords;
	
	public Piece(int color, int type, Point coords) {
		this.color = color;
		this.type = type;
		this.coords = coords;
		setName();
	}
	
	private void setName() {
		if (getColor() == 0)
		{
			name = "W";
		}
		else 
		{
			name = "B";
		}
		
		switch(getType())
		{
			case 0: 
				this.name += "K";
				break;
			case 1:
				this.name += "Q";
				break;
			case 2:
				this.name += "R";
				break;
			case 3:
				this.name += "N";
				break;
			case 4:
				this.name += "B";
				break;
			case 5:
				this.name += "P";
				break;
		}
		
	}

	private int getType() {
		return type;
	}

	private int getColor() {
		return color;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPieceName()
	{
		String name = getName();
		if (name.charAt(0) == 'B')
		{
			name = name.toLowerCase();
		}
		
		return String.valueOf(name.charAt(1));
	}
}
