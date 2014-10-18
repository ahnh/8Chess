package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Variant;
import Model.Pieces.*;
import Model.Rules.*;

public class TestCastling extends Variant {
	public TestCastling(List<Board> boards) {

		super(2);
		Board board = new Board();
		initBoard(board);
		boards.add(board);

		// Add Rules
		move_Rules.add(new CollisionMove());
		move_Rules.add(new Check());
		move_Rules.add(new Castling());

		state_Rules.add(new CheckMate());
		//state_Rules.add(new Castling());

	}

	private void initBoard(Board board) {
		TestCastling1(board);

	}

	void TestCastling1(Board board) {
		// Team 1
		int team = 2;
		board.getTile(new Point(0,0)).setPiece(new Rook(team));
		board.getTile(new Point(4,0)).setPiece(new King(team));
		board.getTile(new Point(7,0)).setPiece(new Rook(team));
		
		// Team 2
		team = 1;
		board.getTile(new Point(0,7)).setPiece(new Rook(team));
		board.getTile(new Point(4,7)).setPiece(new King(team));
		board.getTile(new Point(7,7)).setPiece(new Rook(team));
	}
}
