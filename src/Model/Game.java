package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.awt.Point;

import Model.Variants.*;

public class Game {
	private List<Board> boards;
	private Stack<Move> moves;
	private Variant activeVariant;
	private String errorMessage;
	private int currentTeam;
	
	public Game(int variantSelection) {
		boards = new ArrayList();
		switch(variantSelection){
		case 0:
			activeVariant = new BugHouse(boards);
			break;
		case 1:
			activeVariant = new CheshireCat(boards);
			break;
		case 2:
			activeVariant = new Classic(boards);
			break;
		case 3:
			activeVariant = new JediKnight(boards);
			break;
		case 4:
			activeVariant = new Suicide(boards);
			break;
                    
  		case 5:
			activeVariant = new TestCheck(boards);
			break;                  
		}
		
		currentTeam = 1; // Players start at 1 - X
		moves = new Stack();
		errorMessage = "";
	}
	
	public int move(Point start, Point end){
		Board board = getActiveBoard();
		Piece piece = board.getTile(start).getPiece();
		
		if (piece == null || piece.getTeam() != getCurrentTeam())
			return Rule.INVALID_MOVE;
		
		moves.push(new Move(start, end, piece));
		
		// Check if the piece can make the move
		if (!board.checkMove(moves.peek(), this.getCurrentTeam()))
			return Rule.INVALID_MOVE;
		
		// Check that the move doesn't violate any rules or end the game
		int returnVal = activeVariant.checkMove(board, moves, this.getCurrentTeam());
		if (returnVal == Rule.VALID_MOVE){
			board.move(moves.peek());
		} else if (returnVal == Rule.GAME_OVER){
			board.move(moves.peek());
			// Anything else we need to do?
		} else {
			// Invalid so remove
			moves.pop();
			errorMessage = activeVariant.getError();
		}
		return returnVal;
	}
	
	public Board getActiveBoard() {
		// The active board is determined by the active team. 
		// With 2 players per board, the active board will always be currentTeam/2  		
		return this.boards.get( (int)Math.floor((this.currentTeam - 1) / 2));
	}
	
	public int getCurrentTeam() {
		return currentTeam;
	}

	public String getError(){
		return errorMessage;
	}
	
	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}
	
	public void nextTurn(){
		this.currentTeam++;
		
		if ( this.currentTeam > this.activeVariant.getTeamCount() ){
			currentTeam = 1;
		}
	}

}
