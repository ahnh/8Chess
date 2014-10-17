package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.*;

import Model.Board;
import Model.Rule;
import java.util.ArrayList;
import java.util.List;

public class Check extends Rule {
	public Check(){
		super();
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
            
                //Grab CurrentMove
		Move currentMove = moves.peek();
                //Find the Piece being Moved
		Piece movingPiece = board.getTile(currentMove.getStart()).getPiece();
                
                //Find out Current Piece's team
                int currentTeam = movingPiece.getTeam();
                
                int maxWidth = board.getWidth();
                int maxHeight= board.getHeight();
                
                Piece comparePiece;
                
                

                
                
                //List of Pieces
                List<Move> enemyList = new ArrayList<Move>(); 

                
                
                //Grab a List of All enemy pieces
                for(int y=0;y<maxHeight;y++)
                    for(int x=0;x<maxWidth;x++)
                    {
                        Tile tile=null;// = board.getTile(point);
                        comparePiece=null;

                        Point point = new Point(x,y);

                        //Assign tile and Check for Null
                        if( (tile=board.getTile(point))!=null)
                            //Assign Piece and Check for Null
                            if( (comparePiece= tile.getPiece())!=null)   
                                //Check if pieces belong on same team
                                if(comparePiece.getTeam()!=movingPiece.getTeam())
                                {
                                //Grab a List of all enemy pieces and set Destination to the moving Piece's destination    
                                Move m = new Move(point,currentMove.getEnd(),comparePiece);
                                enemyList.add(m);
                                }                            
                    }
                
                

                
                // Grab King within Move Class (Easier to grab startlocation, and piece)
                Move playerKing = FindKing(board,currentTeam);
                
                
                
                //If player doesn't have a king, then no need to verify check
                if(playerKing==null)
                    return Rule.VALID_MOVE;
                
                
               

                
                
                
                
                
                //If King is Being Moved
                //Check if the King's Destination can be reached by any piece
                if( movingPiece== playerKing.getPiece())
                    while(enemyList.size()>0)
                    {
                        //Check if piece can move to the destination
                        Move move0 = enemyList.get(0);
                        Piece piece0 = enemyList.get(0).getPiece();
                        if(piece0.checkDestination(move0)) //If this is a Valid Move, then we are in check
                            return Rule.INVALID_MOVE_CHECK;
                        else
                            enemyList.remove(0);
                    }
               
                                
               
                //If King is NOT being moved, check to see if the King is in check
                boolean isCheck=false;
                for(int x=0;x<enemyList.size();x++)
                {
                      
                    
                
                
                
                }
                
                
		return Rule.VALID_MOVE;
		
	}
        
        private Move FindKing(Board board, int team)
        {
            
 
        for(int y=0;y<board.getHeight() ;y++)
            for(int x=0;x<board.getWidth();x++)
            {
                Tile tile=null;
                Piece piece=null;  
                Point point = new Point(x,y);
                
                if( (tile=board.getTile(point))!=null)
                    //Assign Piece and Check for Null
                    if( (piece= tile.getPiece())!=null)   
                        //Check if pieces belong on same team
                        if(piece.getTeam()==team && piece.getName()== "King")
                        {
                        return new Move(point,point,piece);
                        }                            
            }
        return null;
        }
        
}
