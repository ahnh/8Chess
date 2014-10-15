package Model.Variants;

import java.awt.Point;
import java.util.Stack;
import java.util.List;

import Model.Board;
import Model.Variant;
import Model.Move;

public class BugHouse extends Variant {
	public BugHouse(List<Board> boards) {
		super(4);
	}
	
	public int checkMove(Board board, Stack<Move> moves, int currentTeam){
		return 0;
	}

}
