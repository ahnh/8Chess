package View;

import Model.Board;

public class ViewText extends View {
	
	public ViewText(){
		
		super();
	}
	
	public void displayMessage( String msg ){
		
		System.out.println( msg );
	}
	
	public void display( Board board ){
		
		board.printBoard();
	}
	
	public void displayGameOver( int winner, Board board ){
		
		System.out.println( "Congratulations player " + winner + ", you win!" );
		this.display ( board );
	}
}
