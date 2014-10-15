package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Variant;
import Model.Rules.*;

public class Classic extends Variant {
	public Classic(List<Board> boards) {
		super( 2 );
		
		boards.add(new Board());
		
		// Add Rules
		rules.add(new Castling());
	}
}
