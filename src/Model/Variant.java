package Model;

import java.awt.Point;

public abstract class Variant {
	
	private int teams;
	
	public Variant( int numPlayers ) {
		
		teams = numPlayers;
	}

	public abstract int checkMove(Board board, Point start, Point end, int currentTeam);
	
	public int getTeamCount(){
		
		return teams;
	}
}
