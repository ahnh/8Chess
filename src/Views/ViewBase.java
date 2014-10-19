package Views;

import Model.Board;

public abstract class ViewBase {

	public ViewBase() {
	}

	// General display game state update
	public abstract boolean display(Board board);

	// Give a message to the player through main window
	public abstract boolean displayMessage(String msg);

	// Display options to choose from a player
	public abstract boolean displayOptions(String msg, String[] options);

	// Alter view to a game over state
	public abstract boolean displayGameOver(int winner, Board board);
}
