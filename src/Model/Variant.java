package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Variant {
	private int teams;
	protected List<Rule> rules;
	private String lastErrorMessage;
	
	public Variant( int numPlayers) {
		rules = new ArrayList<Rule>();
		teams = numPlayers;
		lastErrorMessage = "";
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam) {
		int returnVal = Rule.VALID_MOVE;
		
		for (int i = 0; i < rules.size(); i++) {
			returnVal = rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) {
				lastErrorMessage = rules.get(i).getError();
				break;
			}
		}
		return returnVal;
	}
	
	public int getTeamCount(){
		
		return teams;
	}
	public String getError(){
		return lastErrorMessage;
	}
}
