package View;

import java.util.List;

import Model.Board;

public class ViewText extends View {
	
	public ViewText(){
		
		super();
	}
	
	public void displayMessage( String msg ){
		
		System.out.println( msg );
	}
	
	public void display( List<Board> board ){
		
		
	}
	
	public void displayGameOver( String player ){
		
		System.out.println( "Congratulations " + player + ", you win!" );
	}
}
