package Model;

import java.awt.Point;

public abstract class Rule {
	public Rule(){ }
	public abstract int checkMove(Board board, Point start, Point end);
}
