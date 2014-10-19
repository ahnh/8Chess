package Model.Variants;

import java.awt.Point;
import java.util.List;

import Model.Board;
import Model.Variant;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Knight;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import Model.Rules.Check;
import Model.Rules.CheckMate;
import Model.Rules.CollisionMove;

public class TestCheck extends Variant {
	public TestCheck(List<Board> boards) {

		super(2);
		Board board = new Board();
		initBoard(board);
		boards.add(board);

		// Add Rules
		move_Rules.add(new CollisionMove());
		// rules.add(new Castling());

		move_Rules.add(new Check());

		state_Rules.add(new CheckMate());
	}

	private void initBoard(Board board) {
		TestCheck3(board);

	}

	// King Not in Check, Surrounded by Pieces

	// Board doesn't need updating, just needs to check if any enemy piece can
	// get to the king
	// if he moves to his desired location
	void TestCheck1(Board board) {
		// Team 1
		int team = 1;
		board.getTile(new Point(4, 0)).setPiece(new King(team));

		// Team 2
		team = 2;
		board.getTile(new Point(5, 1)).setPiece(new Rook(team));
		board.getTile(new Point(1, 2)).setPiece(new Bishop(team));
	}

	// Piece is blocking check,
	// Moving king behind piece should be allowed
	void TestCheck2(Board board) {
		// Team 1
		int team = 1;
		board.getTile(new Point(4, 0)).setPiece(new King(team));
		board.getTile(new Point(5, 1)).setPiece(new Pawn(team));
		board.getTile(new Point(0, 0)).setPiece(new Queen(team));

		// Team 2
		team = 2;
		board.getTile(new Point(5, 4)).setPiece(new Rook(team));
		board.getTile(new Point(5, 5)).setPiece(new Queen(team));
		board.getTile(new Point(3, 3)).setPiece(new Knight(team));
	}

	// Testing Checkmate claus
	void TestCheck3(Board board) {
		// Team 1
		int team = 1;
		board.getTile(new Point(4, 5)).setPiece(new King(team));

		// Team 2
		team = 2;
		board.getTile(new Point(6, 4)).setPiece(new Rook(team));
		board.getTile(new Point(2, 7)).setPiece(new Rook(team));
		board.getTile(new Point(5, 0)).setPiece(new Rook(team));
		board.getTile(new Point(3, 0)).setPiece(new Rook(team));
		// board.getTile(new Point(5,4)).setPiece(new Rook(team));
	}
}
