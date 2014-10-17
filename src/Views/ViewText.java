package Views;

import java.awt.Point;

import Model.Board;

public class ViewText extends ViewBase {
	
	public ViewText(){
		
		super();
	}
	
	public void displayMessage( String msg ){
		
		System.out.println( msg );
	}
	
	public void display( Board board ){
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String header = "  ";
		for (int j = 0; j < board.getWidth(); j++){
                        header += " ";
			header += alphabet.charAt(j);
		}
		System.out.println(header);
	
		for (int i = 0; i < board.getHeight(); i++){
			// Print Row
			System.out.print(i+1 + " ");
			for (int j = 0; j < board.getWidth(); j++){
				System.out.print(" ");
				System.out.print( board.getTile( new Point(j,i) ) );
			}
			System.out.println();
		}
	}
	
	public void displayOptions( String msg, String[] options ){
		
		System.out.println( msg );
		
		for (int i = 0; i < options.length; i++ ){
			
			System.out.println( options[i] );
		}
	}
	
	public void displayGameOver( int winner, Board board ){
		
		System.out.println( "Congratulations player " + winner + ", you win!" );
		this.display ( board );
	}
}
