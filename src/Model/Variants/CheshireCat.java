package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Variant;
import Model.Move;

public class CheshireCat extends Variant {
	public CheshireCat(List<Board> boards) {
		super(2);
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam){
		return 0;
	}

}
