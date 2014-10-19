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
		super("Move places you in check", null);
	}
	
        //Invalidates moves based on if the player is in check
        
        public boolean isKingInCheck(Board board, int currentTeam)
        {
                    List<Move> enemyList;
                    Rule CollisionMove= new CollisionMove();
                    
                    // Grab King within Move Class (Easier to grab startlocation, and piece)
                    Move playerKing = FindKing(board,currentTeam);   
                
                    if(playerKing!=null)
                    {
                        //Verify if any piece can move to kings location
                        playerKing = FindKing(board,currentTeam);

                        //Check if any piece can reach playerking

                        //Create Enemy List from board of all enemy pieces with their destination set on the king
                        enemyList=CreateEnemyList(board,currentTeam,playerKing.getStart());

                        boolean isCheck=false;

                        for(int x=0;x<enemyList.size() && isCheck==false; x++)
                        {
                            Move move0 = enemyList.get(x);                      
                            Stack moveStack = new Stack();
                            moveStack.add(move0);   

                            if(move0.getPiece().getName()=="Pawn")
                            {
                            if(move0.getPiece().checkDestination(move0) && ((move0.getStart().x != move0.getEnd().x)&&(move0.getStart().y != move0.getEnd().y)) )                          
                                isCheck=true;
                            }
                            else
                            {
                                if(((CollisionMove.checkMove(board,  moveStack))==Rule.VALID_MOVE) && enemyList.get(x).getPiece().checkDestination(move0))
                                {
                                    isCheck=true;
                                }
                            }


                        }
                        return isCheck;
                    }
                    else 
                        return false; //No king, can't be in check
                    
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
                
                

                
                
 

                

                
                // Grab King within Move Class (Easier to grab startlocation, and piece)
                Move playerKing = FindKing(board,currentTeam);
                
                               
                //If player doesn't have a king, then no need to verify check
                if(playerKing==null)
                    return Rule.VALID_MOVE;
                
                
               

                

                Rule CollisionMove= new CollisionMove();
                List<Move> enemyList;
                
    

                    //Make a copy of the piece at the Destination

                    //Copy Needed Contents
                    Piece orig_destination = board.getTile(currentMove.getEnd()).getPiece();


                    //Overwrite the piece at destination
                    board.getTile(currentMove.getEnd()).setPiece(movingPiece);
                    board.getTile(currentMove.getStart()).setPiece(null);

                    //See if this invalidates Check



                    //Verify if any piece can move to kings location
                    playerKing = FindKing(board,currentTeam);
                    
                    //Check if any piece can reach playerking

                    //Create Enemy List from board of all enemy pieces with their destination set on the king
                    enemyList=CreateEnemyList(board,currentTeam,playerKing.getStart());

                    boolean isCheck=false;
                    for(int x=0;x<enemyList.size() && isCheck==false; x++)
                    {
                        Move move0 = enemyList.get(x);
                        Stack moveStack = new Stack();
                        moveStack.add(move0);   

                        if(move0.getPiece().getName()=="Pawn")
                        {
                            
                            if(move0.getPiece().checkDestination(move0) && ((move0.getStart().x != move0.getEnd().x)&&(move0.getStart().y != move0.getEnd().y)) )
                            {
                                isCheck=true;
                            }
                            
                        }
                        else
                        {
                            if(((CollisionMove.checkMove(board,  moveStack))==Rule.VALID_MOVE) && enemyList.get(x).getPiece().checkDestination(move0))
                            {

                               // System.out.println("HES CHECKING YOU "+enemyList.get(x).getPiece().getName());
                                isCheck=true;
                            }
                        }


                    }
                
                

                    //Undo Changes
                    board.getTile(currentMove.getStart()).setPiece(movingPiece);                
                    board.getTile(currentMove.getEnd()).setPiece(orig_destination);   

                    if(isCheck)
                    return Rule.INVALID_MOVE;
                    else
                    return Rule.VALID_MOVE;
                
                
                
                
		
		
	}
        //Create list containing all pieces not in team with their moves set to destination 
        private List<Move> CreateEnemyList(Board board, int team, Point destination)
        {
            //Grab a List of All enemy pieces
            Piece comparePiece=null;
            List<Move> enemyList = new ArrayList<Move>();
            for(int y=0;y<board.getHeight();y++)
                for(int x=0;x<board.getWidth();x++)
                {
                    Tile tile=null;// = board.getTile(point);
                    comparePiece=null;

                    Point point = new Point(x,y);

                    //Assign tile and Check for Null
                    if( (tile=board.getTile(point))!=null)
                        //Assign Piece and Check for Null
                        if( (comparePiece= tile.getPiece())!=null)   
                            //Check if pieces belong on same team
                            if(comparePiece.getTeam()!=team)
                            {
                            //Grab a List of all enemy pieces and set Destination to the moving Piece's destination    
                            Move m = new Move(point,destination,comparePiece);
                            enemyList.add(m);
                            }                            
                }    
            return enemyList;
        }
        
        
        protected Move FindKing(Board board, int team)
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
