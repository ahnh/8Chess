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
//		this.coords = coords; // maybe just change to two ints row and col
		setName();
	}
	
	private void setName() {
		switch(getType())
		{
			case 0: 
				this.name = "K";
				break;
			case 1:
				this.name = "Q";
				break;
			case 2:
				this.name = "R";
				break;
			case 3:
				this.name = "N";
				break;
			case 4:
				this.name = "B";
				break;
			case 5:
				this.name = "P";
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
		if (getColor() == 1)
		{
			name = name.toLowerCase();
		}
		
		return name;
	}
}