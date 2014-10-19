package Controller;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {
	
	private Scanner scan;
	private String buffer;
	private int inType;
	
	public static final int TEXT_INPUT = 0;
	public static final int GUI_INPUT = 1;
	
	public Player( int inputType ){
		
		scan = new Scanner(System.in);
		buffer = null;
		inType = 0;
		
		if ( !( inputType > 1 || inputType < 0 ) ){
			
			inType = inputType;
		}
	}
	
	// Get input from user through chosen method (during game)
	public String getInput() {
		
		String input = null;
		
		if ( inType == TEXT_INPUT ){
			
			input = scan.nextLine();
		}
		else if ( inType == GUI_INPUT ){
			
			while ( buffer == null ){ 
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
			
			input = buffer;
			buffer = null;
		}
		
		return input.trim();
	}
	
	// For getting console text only (pre game start)
	public static String getTextInput() {
		
		Scanner sc = new Scanner(System.in);
		String input = null;
		
		input = sc.nextLine();
		
		return input.trim();
	}

	public void writeToBuffer( String msg ){
		
		buffer = msg;
	}
}