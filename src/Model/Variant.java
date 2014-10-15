package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Variant {
	private int teams;
	protected List<Rule> rules;
	
	public Variant( int numPlayers) {
		rules = new ArrayList();
		teams = numPlayers;
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam) {
		int returnVal = Rule.VALID_MOVE;
		for (int i = 0; i < rules.size(); i++) {
			returnVal = rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) {
				break;
			}
		}
		return returnVal;
	}
	
	public int getTeamCount(){
		
		return teams;
	}
}
