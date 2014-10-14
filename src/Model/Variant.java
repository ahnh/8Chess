package Model;

import java.awt.Point;

public abstract class Variant {
	public Variant() {
	}

	public abstract int checkMove(Board board, Point start, Point end, int currentTeam);
}
