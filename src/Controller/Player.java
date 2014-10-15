package Controller;

import java.util.Scanner;

public class Player {
	
	Scanner scan;
	int inType;
	
	public static int TEXT_INPUT = 0;
	public static int GUI_INPUT = 1;
	
	public Player( int inputType ){
		
		scan = new Scanner(System.in);
		
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
			
			// Extract gui input here
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
}
