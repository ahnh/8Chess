package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Variant;

public class Classic extends Variant {
	public Classic(List<Board> boards) {
		super( 2 );
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam){
		return 0;
	}

}
