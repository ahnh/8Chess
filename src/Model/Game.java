package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.Variants.*;

public class Game {
	private List<Board> boards;
	private Stack<Move> moves;
	private Variant activeVariant;
	private int currentTeam;
	private String errorMsg;
	private String[][] ruleOptions;

	public Game(int variantSelection) {
		boards = new ArrayList();
		switch (variantSelection) {
		case 1:
			activeVariant = new Classic(boards);
			break;
		case 2:
			activeVariant = new Absorption(boards);
			break;
		case 3:
			activeVariant = new CheshireCat(boards);
			break;
		case 4:
			activeVariant = new Suicide(boards);
			break;
		case 5:
			activeVariant = new JediKnightChess(boards);
			break;
		case 6:
			activeVariant = new Atomic(boards);
			break;
		case 7:
			activeVariant = new Hobbit(boards);
			break;
		case 8:
			//activeVariant = new StationaryKingChess(boards);
			break;
		case 9:
			activeVariant = new TestCheck(boards);
			break;
		}

		currentTeam = 1; // Players start at 1 - X
		moves = new Stack<Move>();
		errorMsg = "";
	}

	public int move(Point start, Point end) {
		Board board = getActiveBoard();
		Tile tile = null;
		Piece piece = null;; 
		int returnVal = -1;
		
		tile = board.getTile(start);
		
		if ( tile == null ){
			
			errorMsg = "Invalid coordinates entered";
			return Rule.INVALID_MOVE;
		}
		
		piece = tile.getPiece();
		
		if (piece == null ){
			
			errorMsg = "No piece in the starting position";
			return Rule.INVALID_MOVE;
		}
		else if ( piece.getTeam() != getCurrentTeam() ){
			
			errorMsg = "Piece is not on your team";
			return Rule.INVALID_MOVE;
		}

		moves.push(new Move(start, end, piece));

		// Check if the piece can make the move
		if (!board.checkMove(moves.peek(), this.getCurrentTeam())) {
			errorMsg = "Piece can not move that way";
			return Rule.INVALID_MOVE;
		}

		// Check that the move doesn't violate any rules or end the game
		returnVal = activeVariant
				.checkMove(board, moves, this.getCurrentTeam());

		if (returnVal == Rule.VALID_MOVE) {

			board.move(moves.peek());

			returnVal = activeVariant.checkState(board, moves);

			if (returnVal >= Rule.NEEDS_INPUT) {

				ruleOptions = activeVariant.getRuleOptions();
			}

		} else {
			// Invalid so remove
			moves.pop();
			errorMsg = activeVariant.getError();
		}

		return returnVal;
	}

	public Board getActiveBoard() {
		// The active board is determined by the active team.
		// With 2 players per board, the active board will always be
		// currentTeam/2
		return this.boards.get((int) Math.floor((this.currentTeam - 1) / 2));
	}

	public String getError() {

		return errorMsg;
	}

	public String[][] getRuleOptions() {

		return ruleOptions;
	}

	public int getCurrentTeam() {
		return currentTeam;
	}

	public int completeAction(int response, int action) {

		Move move = moves.peek();
		move.action = action;
		move.option = response;
		move.optionSelected = true;

		return activeVariant.checkState(getActiveBoard(), moves);
	}

	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}

	public void nextTurn() {
		this.currentTeam++;

		if (this.currentTeam > this.activeVariant.getTeamCount()) {
			currentTeam = 1;
		}
	}

}
