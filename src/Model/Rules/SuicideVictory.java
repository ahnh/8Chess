package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;

public class SuicideVictory extends Rule {

	public SuicideVictory() {

		super("Victory achieved", null);
	}

	public int checkMove(Board board, Stack<Move> moves) {

		int team1pieces = 0;
		int team2pieces = 0;

		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {

				Piece piece = board.getTile(new Point(j, i)).getPiece();

				if (piece != null) {
					if (piece.getTeam() == 1) {

						team1pieces++;
					} else if (piece.getTeam() == 2) {

						team2pieces++;
					}
				}
			}
		}

		if (team1pieces == 0) {

			return Rule.GAME_OVER_T2;
		} else if (team2pieces == 0) {

			return Rule.GAME_OVER_T1;
		}

		return Rule.VALID_MOVE;
	}
}
