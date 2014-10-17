package Model;

import java.awt.Point;
import java.util.Stack;

public abstract class Rule {
	public static final int INVALID_MOVE = -1;
	public static final int VALID_MOVE = 0;
	public static final int GAME_OVER = 1;
    public static final int INVALID_MOVE_CHECK =-2;
	
    private String errorMsg;
    
	public Rule( String error ){
		
		errorMsg = error;
	}
	public abstract int checkMove(Board board, Stack<Move> moves);
	
	public String getError(){
		
		return errorMsg;
	}
}
