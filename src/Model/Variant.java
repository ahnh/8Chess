package Model;

import java.awt.Point;
import java.util.Stack;

public abstract class Variant {
	private int teams;
	
	public Variant( int numPlayers ) {
		
		teams = numPlayers;
	}

	public abstract int checkMove(Board board, Stack<Move> moves, int currentTeam);
	
	public int getTeamCount(){
		
		return teams;
	}
}
