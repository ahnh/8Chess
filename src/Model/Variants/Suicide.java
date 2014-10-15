package Model.Variants;

import java.awt.Point;
import java.util.List;

import Model.Board;
import Model.Variant;

public class Suicide extends Variant {
	public Suicide(List<Board> boards) {
		super( 2 );
	}

	public int checkMove(Board board, Point start, Point end, int currentTeam){
		return 0;
	}
}
