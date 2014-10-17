package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Variant {
	private int teams;
	protected List<Rule> move_Rules;
	protected List<Rule> state_Rules;
	public Variant( int numPlayers) {
		move_Rules = new ArrayList();
		state_Rules = new ArrayList();
                
		teams = numPlayers;
	}

	public int checkMove(Board board, Stack<Move> moves, int currentTeam) {
		int returnVal = Rule.VALID_MOVE;
		for (int i = 0; i < move_Rules.size(); i++) {
			returnVal = move_Rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) {
				break;
			}
		}
		return returnVal;
	}
        
	public int checkState(Board board, Stack<Move> moves) 
        {
		int returnVal = Rule.VALID_MOVE;
		for (int i = 0; i < state_Rules.size(); i++) 
                {
			returnVal = state_Rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) 
                        {
				break;
			}
		}
		return returnVal;
	}
        
	public int getTeamCount(){
		
		return teams;
	}
}
