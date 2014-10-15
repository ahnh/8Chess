package View;

import Model.Board;

public abstract class View {
	
	public View(){
		
		
	}
	
	// General display game state update
	public abstract void display( Board board );
	
	// Give a message to the player
	public abstract void displayMessage( String msg );
	
	// Alter view to a game over state
	public abstract void displayGameOver( int winner, Board board );
}
