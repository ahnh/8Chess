package Views;

import Model.Board;

public abstract class ViewBase {
	
	public ViewBase(){}
	
	// General display game state update
	public abstract void display( Board board );
	
	// Give a message to the player through main window
	public abstract void displayMessage( String msg );
	
	// Display options to choose from a player
	public abstract void displayOptions( String msg, String[] options );
	
	// Alter view to a game over state
	public abstract void displayGameOver( int winner, Board board );
}
