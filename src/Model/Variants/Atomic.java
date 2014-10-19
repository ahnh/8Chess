package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;
import Model.Variant;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Knight;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import Model.Rules.AtomicWin;
import Model.Rules.Castling;
import Model.Rules.CollisionMove;
import Model.Rules.EnPessant;
import Model.Rules.Pawn_Move;
import Model.Rules.Promotion;

public class Atomic extends Variant {
	public Atomic(List<Board> boards) {
		super(2);

		Board board = new Board();

		initBoard(board);
		boards.add(board);

		// Add Rules
		move_Rules.add(new CollisionMove());
		move_Rules.add(new Castling());
		move_Rules.add(new Pawn_Move());
		move_Rules.add(new EnPessant());

		state_Rules.add(new AtomicWin());
		state_Rules.add(new Promotion());
		state_Rules.add(new Castling());
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam) {

		int returnVal = Rule.VALID_MOVE;
		for (int i = 0; i < move_Rules.size(); i++) {
			returnVal = move_Rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) {
				lastError = move_Rules.get(i).getError();
				break;
			}
		}

		if (returnVal == Rule.VALID_MOVE
				&& EnPessant.moveIsEnPessantAttempt(board, moves)) {
			// En Pessant. Remove the piece that has been captured.
			board.capturePiece(moves.get(moves.size() - 2).getEnd(), null);
		}

		// If Move is valid
		if (returnVal == Rule.VALID_MOVE) {
			// Grab Move
			Move move = moves.peek();

			// Check if a Capture is occuring
			Piece p1 = board.getTile(move.getStart()).getPiece();
			Piece p2 = board.getTile(move.getEnd()).getPiece();

			if (p2 != null) {
				if (p1.getTeam() != p2.getTeam()) {
					// Check Surrounding Piece
					// KillPiece(board, move);
					KillEverything(board, move.getEnd());
				}
			}
		}

		return returnVal;
	}

	private void KillEverything(Board board, Point p) {
		for (int y = p.y - 1; y <= p.y + 1; y++)
			for (int x = p.x - 1; x <= p.x + 1; x++) {
				Point point = new Point(x, y);
				if ((x >= 0 && x < board.getWidth())
						&& (y >= 0 && y < board.getHeight()))
					if ((board.getTile(point).getPiece() != null)
							&& (board.getTile(point).getPiece().getName() != "Pawn")) {
						System.out.println("Killing: " + point);
						board.getTile(point).setPiece(null);
					}

			}

	}

	private void KillPiece(Board board, Move move) {

		Point temp;

		// Left
		temp = new Point(move.getStart().x - 1, move.getStart().y);
		if ((move.getStart().x - 1 > 0)
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Up
		temp = new Point(move.getStart().x, move.getStart().y + 1);
		if ((move.getStart().y + 1 < board.getHeight())
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			// board.capturePiece(temp, null);

			// Down
			temp = new Point(move.getStart().x, move.getStart().y - 1);
		if ((move.getStart().y - 1 > 0)
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Right
		temp = new Point(move.getStart().x + 1, move.getStart().y);
		if ((move.getStart().x + 1 < board.getHeight())
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Top Right
		temp = new Point(move.getStart().x + 1, move.getStart().y + 1);
		if ((move.getStart().x + 1 < board.getWidth())
				&& (move.getStart().y + 1 < board.getHeight())
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Top Left
		temp = new Point(move.getStart().x - 1, move.getStart().y + 1);
		if ((move.getStart().x - 1 > 0)
				&& (move.getStart().y + 1 < board.getHeight())
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Bottom Right
		temp = new Point(move.getStart().x + 1, move.getStart().y - 1);
		if ((move.getStart().x + 1 < board.getWidth())
				&& (move.getStart().y - 1 > 0)
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

		// Bottom Left
		temp = new Point(move.getStart().x - 1, move.getStart().y - 1);
		if ((move.getStart().x - 1 > 0)
				&& (move.getStart().y - 1 > 0)
				&& (board.getTile(temp).getPiece() == null || (board
						.getTile(temp).getPiece().getName() != "Pawn")))
			board.capturePiece(temp, null);

	}

	private void initBoard(Board board) {
		// Team 2
		int team = 2;
		board.getTile(new Point(0, 0)).setPiece(new Rook(team));
		board.getTile(new Point(1, 0)).setPiece(new Knight(team));
		board.getTile(new Point(2, 0)).setPiece(new Bishop(team));
		board.getTile(new Point(3, 0)).setPiece(new Queen(team));
		board.getTile(new Point(4, 0)).setPiece(new King(team));
		board.getTile(new Point(5, 0)).setPiece(new Bishop(team));
		board.getTile(new Point(6, 0)).setPiece(new Knight(team));
		board.getTile(new Point(7, 0)).setPiece(new Rook(team));
		for (int i = 0; i < board.getWidth(); i++) {
			board.getTile(new Point(i, 1)).setPiece(new Pawn(team));
		}

		// Team 1
		team = 1;
		board.getTile(new Point(0, 7)).setPiece(new Rook(team));
		board.getTile(new Point(1, 7)).setPiece(new Knight(team));
		board.getTile(new Point(2, 7)).setPiece(new Bishop(team));
		board.getTile(new Point(3, 7)).setPiece(new Queen(team));
		board.getTile(new Point(4, 7)).setPiece(new King(team));
		board.getTile(new Point(5, 7)).setPiece(new Bishop(team));
		board.getTile(new Point(6, 7)).setPiece(new Knight(team));
		board.getTile(new Point(7, 7)).setPiece(new Rook(team));
		// Pawns
		for (int i = 0; i < board.getWidth(); i++) {
			board.getTile(new Point(i, 6)).setPiece(new Pawn(team));
		}
	}
}
