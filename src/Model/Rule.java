package Model;

import java.awt.Point;
import java.util.Stack;

public abstract class Rule {
	public static final int INVALID_MOVE = -1;
	public static final int VALID_MOVE = 0;
	
	
	public static final int GAME_OVER = 1;
	public static final int TIE = 1;        
        
    ///////////////////// Might not be Used, placed here just incase
        public static final int CHECKMATE_TEAM1 = 2;
        public static final int CHECKMATE_TEAM2 = 3;
        public static final int CHECKMATE_TEAM12 = 4;       
        
        public static final int INVALID_MOVE_CHECK =-2;  
	/////////////////////
        
    // Identifiers for states that need additional input
    public static final int NEEDS_INPUT = 10;
    public static final int PROMOTION = 11;
    public static final int CASTLING = 12;
    
    private String errorMsg;
    private String[][] options;
    
	public Rule( String error, String[][] opts ){
		
		errorMsg = error;
		options = opts;
	}
	public abstract int checkMove(Board board, Stack<Move> moves);

    public String getError(){
    	
    	return errorMsg;
    }
    
    public String[][] getOptions(){
    	
    	return options;
    }
}
