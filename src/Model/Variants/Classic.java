package Model.Variants;

import java.awt.Point;
import java.util.List;

import Model.Board;
import Model.Variant;

public class Classic extends Variant {
	public Classic(List<Board> boards) {
		super( 2 );
	}

	public int checkMove(Board board, Point start, Point end, int currentTeam){
		return 0;
	}

}
