package Model;

import java.util.List;
import java.awt.Point;

import Model.Variants.*;

public class Game {
	private List<Board> boards;
	private Variant activeVariant;
	private int currentTeam;
	
	public Game(int variantSelection) {
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
	}
	
	public int move(Point start, Point end){
		int returnVal = activeVariant.checkMove(this.getActiveBoard(), start, end, this.getCurrentTeam());
		if (returnVal == 0){
			// Valid move
			this.getActiveBoard().move(start, end);
		}
		return returnVal;
	}
	
	public Board getActiveBoard() {
		// The active board is determined by the active team. 
		// With 2 players per board, the active board will always be currentTeam/2  		
		return this.boards.get(Math.floorDiv(this.currentTeam, 2));
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
