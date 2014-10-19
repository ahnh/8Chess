package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;
import Model.Pieces.Bishop;
import Model.Pieces.Knight;
import Model.Pieces.Queen;
import Model.Pieces.Rook;

public class Promotion extends Rule {

	public Promotion() {
		super("Castling is not allowed", new String[][] {
				{ "What piece do you want to promote to?" },
				{ "1. Queen", "2. Rook", "3. Knight", "4. Bishop" } });
	}

	@Override
	public int checkMove(Board board, Stack<Move> moves) {

		Move move = moves.peek();

		for (int i = 0; i < board.getWidth(); i++) {

			// Top of board
			if (board.getTile(new Point(i, 0)).getPiece() != null) {
				if (board.getTile(new Point(i, 0)).getPiece().getName()
						.compareTo("Pawn") == 0) {

					// If move has data about user response, implement it
					if (move.action == Rule.PROMOTION && move.optionSelected) {

						board.getTile(new Point(i, 0)).setPiece(
								getSelectedPiece(move)); // See getSelectedPiece
															// below
					}

					// Return this value regardless if piece has been replaced or not
					return Rule.PROMOTION; 
				}
			}

			// Bottom of board
			if (board.getTile(new Point(i, board.getHeight() - 1)).getPiece() != null) {
				if (board.getTile(new Point(i, board.getHeight() - 1))
						.getPiece().getName().compareTo("Pawn") == 0) {

					if (move.action == Rule.PROMOTION && move.optionSelected) {

						board.getTile(new Point(i, board.getHeight() - 1))
								.setPiece(getSelectedPiece(move));
					}

					return Rule.PROMOTION;
				}
			}
		}

		return Rule.VALID_MOVE;
	}

	public Piece getSelectedPiece(Move move) {

		Piece piece = null;

		if (move.option == 1) {

			piece = new Queen(move.getPiece().getTeam());
		} else if (move.option == 2) {

			piece = new Rook(move.getPiece().getTeam());
		} else if (move.option == 3) {

			piece = new Knight(move.getPiece().getTeam());
		} else if (move.option == 4) {

			piece = new Bishop(move.getPiece().getTeam());
		}

		return piece;
	}
}
