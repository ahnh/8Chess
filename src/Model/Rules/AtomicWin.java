package Model.Rules;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;
import Model.Tile;

public class AtomicWin extends Rule {
	public AtomicWin() {
		super("Checkmate status would be activated", null);
	}

	// Pulls The Team from the Move ontop of MoveStack
	// Checks to see if that TEAM is in checkmate
	@Override
	public int checkMove(Board board, Stack<Move> moves) {

		boolean isT1_checkmate = IsCheckMate(board, 1);
		boolean isT2_checkmate = IsCheckMate(board, 2);

		if (isT1_checkmate && isT2_checkmate)
			return Rule.GAME_OVER_TIE;
		else if (isT1_checkmate) {
			return Rule.GAME_OVER_T1;
		}

		else if (isT2_checkmate) {

			return Rule.GAME_OVER_T2;
		} else {

			return Rule.VALID_MOVE;
		}

	}

	// Two Team Assumption
	boolean IsCheckMate(Board board, int currentTeam) {

		// Find if a King is in Check

		// Grab a List of all Pieces on this Team
		// with all valid move's they can make
		List<Piece> team_Pool = CreatePieceList(board, currentTeam);

		for (int x = 0; x < team_Pool.size(); x++) {
			if (team_Pool.get(x).getName() == "King")
				return false;
		}

		return true;

	}

	// Create list containing all pieces not in team with their moves set to
	// destination
	private List<Piece> CreatePieceList(Board board, int team) {
		CollisionMove collMove = new CollisionMove();

		// Grab a List of All enemy pieces
		Piece comparePiece = null;
		List<Piece> pieceList = new ArrayList<Piece>();
		for (int y = 0; y < board.getHeight(); y++)
			for (int x = 0; x < board.getWidth(); x++) {
				Tile tile = null;// = board.getTile(point);
				comparePiece = null;

				Point point = new Point(x, y);

				// Assign tile and Check for Null
				if ((tile = board.getTile(point)) != null)
					// Assign Piece and Check for Null
					if ((comparePiece = tile.getPiece()) != null)
						// Check if pieces belong on same team
						if (comparePiece.getTeam() == team) {
							// Grab a List of all enemy pieces and set
							// Destination to the moving Piece's destination

							// Cycle through the board's positions setting only
							// valid moves into list
							pieceList.add(comparePiece);
						}
			}
		return pieceList;
	}

}
