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
	private int currentTeam;
	private String errorMsg;
	private String[][] ruleOptions;
	
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
		moves = new Stack<Move>();
		errorMsg = "";
	}
	
	public int move(Point start, Point end){
		Board board = getActiveBoard();
		Piece piece = board.getTile(start).getPiece();
		int returnVal = -1;
		
		if (piece == null || piece.getTeam() != getCurrentTeam())
			return Rule.INVALID_MOVE;
		
		moves.push(new Move(start, end, piece));
		
		// Check if the piece can make the move
		if (!board.checkMove(moves.peek(), this.getCurrentTeam())){
			errorMsg = activeVariant.getError();
			return Rule.INVALID_MOVE;
		}
		
		// Check that the move doesn't violate any rules or end the game
		returnVal = activeVariant.checkMove(board, moves, this.getCurrentTeam());
		
		if (returnVal == Rule.VALID_MOVE){
                        
			board.move(moves.peek());
            
            returnVal = activeVariant.checkState(board, moves);
            
            if ( returnVal >= Rule.NEEDS_INPUT ){
            	
            	ruleOptions = activeVariant.getRuleOptions();
            }
    
		} else {
			// Invalid so remove
			moves.pop();
		}
        
		return returnVal;
	}
	
	public Board getActiveBoard() {
		// The active board is determined by the active team. 
		// With 2 players per board, the active board will always be currentTeam/2  		
		return this.boards.get( (int)Math.floor((this.currentTeam - 1) / 2));
	}
	
	public String getError(){
		
		return errorMsg;
	}
	
	public String[][] getRuleOptions(){
		
		return ruleOptions;
	}
	
	public int getCurrentTeam() {
		return currentTeam;
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
