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
		}
		
		currentTeam = 1; // Players start at 1 - X
		moves = new Stack();
	}
	
	public int move(Point start, Point end){
		Board activeBoard = getActiveBoard();
		
		moves.push(new Move(start, end, activeBoard.getTile(start).getPiece()));
		int returnVal = activeVariant.checkMove(this.getActiveBoard(), moves, this.getCurrentTeam());
		if (returnVal == 0){
			// Valid move
			this.getActiveBoard().move(moves.peek());
		}
		else {
			// Invalid so remove
			moves.pop();
		}
		return returnVal;
	}
	
	public Board getActiveBoard() {
		// The active board is determined by the active team. 
		// With 2 players per board, the active board will always be currentTeam/2  		
		return this.boards.get( (int)Math.floor(this.currentTeam / 2));
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
