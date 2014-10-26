package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;

import java.awt.Point;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;
import org.testng.*;

import Model.Move;
import Model.Piece;
import Model.Pieces.*;


public class TestPieces {
  //Test BK movement	
  @Test
  public void BishopKnightMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			int y2=boardSize/2; //Center Board is used to get all possible directions
			int x2=boardSize/2; //Center Board is used to get all possible directions
			Point start=new Point(x2,y2);
			Point end=new Point(x,y);
			// Check Horizontal
			boolean actual = PieceMove(start,end, new BishopKnight(1));
						
			int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
			int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);
						
			if ((hDifference == 2 && vDifference == 1)
			| (hDifference == 1 && vDifference == 2))
				expected=true;
			else if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y) && (start.x!=end.x && start.y!=end.y)) // Only Diagonals Allowed
				expected =true;
			else
				expected=false;
			  
			Assert.assertEquals(actual,expected,"BishopKnight Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  } 
  }

  //Test QK movement
  @Test
  public void QueenKnightMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			int y2=boardSize/2; //Center Board is used to get all possible directions
			int x2=boardSize/2; //Center Board is used to get all possible directions
			Point start=new Point(x2,y2);
			Point end=new Point(x,y);
			// Check Horizontal
			boolean actual = PieceMove(start,end, new QueenKnight(1));
						
			int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
			int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

			if( x2==x && y2==y)
				expected =false;
			
			else if((hDifference == 2 && vDifference == 1)
			  |(hDifference == 1 && vDifference == 2))
				expected=true;
			  
			  //Rook Movement
			else if((x2-x)==0)
				expected =true;
			else if((y2-y)==0)
				expected =true;
			
			  //Bishop Movement		  
			else if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y)) // Only Diagonals Allowed
				expected =true;
			  
			else
				expected =false;
			  
			Assert.assertEquals(actual,expected,"QueenKnight Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  } 
  }

  //This will use queen movement as testing since the queen can move anywhere the king can,
  //plus the king must move like the queen on the first turn, 2 birds 1 stone.
  @Test
  public void CheshireKingMovement()
  { 
	  //Board Size
	  int boardSize=8;
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end  =new Point(x ,y );
			  boolean actual = PieceMove(start,end, new CheshireKing(1));
			  
			  if( x2==x && y2==y)
				  expected =false;
			  //Rook Movement
			  else if((x2-x)==0)
				  expected =true;
			  else if((y2-y)==0)
				  expected =true;

			  //Bishop Movement		  
			  else if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y)) // Only Diagonals Allowed
				  expected =true;
			  
			  else
				  expected =false;
			  
			  Assert.assertEquals(actual,expected,"King Performed an Illegal Move:("+start.x+","+start.y+"->"+end.x+","+end.y);
		  }
	  
  }  
  //Test K movement
  @Test
  public void KingMovement()
  { 
	  //Board Size
	  int boardSize=8;
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end  =new Point(x ,y );
			  boolean actual = PieceMove(start,end, new King(1));
			  
			  if(x2-x ==0 && y2-y==0)
				  expected = false;			  
			  else if((int)(start.distance(end))==1)
				  expected = true;
			  else if (y2-y==0 && Math.abs(x2-x)<=2)//King CAN move 2 to the left and right on castle
				  expected = true;
			  else
				  expected = false;
			  
			  Assert.assertEquals(actual,expected,"King Performed an Illegal Move:("+start.x+","+start.y+"->"+end.x+","+end.y);
		  }
	  
  }   
  //Test Q movement
  @Test
  public void QueenMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end=new Point(x,y);
			  boolean actual = PieceMove(start,end, new Queen(1));
			  
			  if( x2==x && y2==y)
				  expected =false;
			  //Rook Movement
			  else if((x2-x)==0)
				  expected =true;
			  else if((y2-y)==0)
				  expected =true;

			  //Bishop Movement		  
			  else if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y)) // Only Diagonals Allowed
				  expected =true;
			  
			  else
				  expected =false;
			  
			  Assert.assertEquals(actual,expected,"Rook Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  }
	  
  } 

  //Test K movement
  @Test
  public void KnightMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			int y2=boardSize/2; //Center Board is used to get all possible directions
			int x2=boardSize/2; //Center Board is used to get all possible directions
			Point start=new Point(x2,y2);
			Point end=new Point(x,y);
			// Check Horizontal
			boolean actual = PieceMove(start,end, new Knight(1));
						
			int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
			int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);
						
			if ((hDifference == 2 && vDifference == 1)
			| (hDifference == 1 && vDifference == 2))
				expected=true;
			else
				expected=false;
			  
			Assert.assertEquals(actual,expected,"Knight Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  }
	  
  }
  
  @Test
  public void RookMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end=new Point(x,y);
			  boolean actual = PieceMove(start,end, new Rook(1));
			  
			  if( x2==x && y2==y)
				  expected =false;
			  else if((x2-x)==0)
				  expected =true;
			  else if((y2-y)==0)
				  expected =true;

			  
			  else
				  expected =false;
			  
			  Assert.assertEquals(actual,expected,"Rook Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  }
	  
  }
  
  @Test
  public void RookKnightMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end=new Point(x,y);
			  boolean actual = PieceMove(start,end, new RookKnight(1));
			  
				int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
				int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

				if( x2==x && y2==y)
					expected =false;
				
				else if((hDifference == 2 && vDifference == 1)
				  |(hDifference == 1 && vDifference == 2))
					expected=true;
				
			  else if((x2-x)==0)
				  expected =true;
			  else if((y2-y)==0)
				  expected =true;
			  
			  else
				  expected =false;
			  
			  Assert.assertEquals(actual,expected,"RookKnight Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  }
	  
  }
  @Test
  public void BishopMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	 
	  boolean expected =false; //Initialize
	  
	  //End Locations
	  for(int y=0;y<boardSize;y++)
	  for(int x=0;x<boardSize;x++)		  	
	  {
		  int y2=boardSize/2; //Center Board is used to get all possible directions
		  int x2=boardSize/2; //Center Board is used to get all possible directions
		  Point start=new Point(x2,y2);
	      Point end=new Point(x,y);
		  boolean actual = PieceMove(start,end, new Bishop(1)); 
		  
		  if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y) && (start.x!=end.x && start.y!=end.y)) // Only Diagonals Allowed
			  expected =true;

		  else
			  expected =false;
		  
		  Assert.assertEquals(actual,expected,"Bishop Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
	  }
	    
  }
  
 
  @Test
  public void PawnMovement()
  {
	  //Board Size
	  int boardSize=8;
	  

	  
	  boolean expected =false; //Initialize
	  
	  //End Locations
	  for(int y=0;y<boardSize;y++)
	  for(int x=0;x<boardSize;x++)		  	
	  {
		  int y2=boardSize/2; //Center Board is used to get all possible directions
		  int x2=boardSize/2; //Center Board is used to get all possible directions
		  Point start=new Point(x2,y2);
	      Point end=new Point(x,y);
		  boolean actual = PieceMove(start,end, new Pawn(1)); 
		  
		  //Pawn Logic (Pawns Can Move only vertical, of 1 or 2)
		  if(x2-x!=0) // Horizontal Not Allowed
		  {
			  //Could be Diagonal, if so check is distance ==1
			  if(Math.abs(end.x - start.x) == Math.abs(end.y - start.y) && (int)(start.distance(end))==1)
			  {
				  //Check if moving backwards Team: 1 can't go Neg
				  if(start.y-end.y <0)
					  expected=false;
				  else
					  expected =true;	  
			  }
			  
			  else
			  {
				  expected =false;
			  }
			  
		  }

		  
		  else if((y2-y)>2 || (y2-y)<0) // Pawns Are only Allowed 2 Up
			  expected =false;
		  else if(y-y2==0) //Same Tile
			  expected =false;
		  

		  else
			  expected =true;
		  
		  Assert.assertEquals(actual,expected,"Pawn Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
	  }
	  
	  
  }


  @Test
  public void HobbitPawnMovement()
  { 
	  //Board Size
	  int boardSize=8;
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end  =new Point(x ,y );
			  boolean actual = PieceMove(start,end, new HobbitPawn(1));
			  
			  if(x2-x ==0 && y2-y==0)
				  expected = false;			  
			  else if((int)(start.distance(end))==1)
				  expected = true;

			  else
				  expected = false;
			  
			  Assert.assertEquals(actual,expected,"HobbitPawn Performed an Illegal Move:("+start.x+","+start.y+"->"+end.x+","+end.y);
		  }
	  
  }   
  @Test
  public void StationaryKing()
  { 
	  //Board Size
	  int boardSize=8;
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			  int y2=boardSize/2; //Center Board is used to get all possible directions
			  int x2=boardSize/2; //Center Board is used to get all possible directions
			  Point start=new Point(x2,y2);
		      Point end  =new Point(x ,y );
			  boolean actual = PieceMove(start,end, new StationaryKing(1));
			  
			  expected = false;
			  
			  Assert.assertEquals(actual,expected,"StationaryKing Performed an Illegal Move:("+start.x+","+start.y+"->"+end.x+","+end.y);
		  }
	  
  }  
  
  @Test
  public void JediKnightMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  boolean expected =false; //Initialize
	  //End Locations
	  for(int y=0;y<boardSize;y++)
		  for(int x=0;x<boardSize;x++)		  	
		  {
			int y2=boardSize/2; //Center Board is used to get all possible directions
			int x2=boardSize/2; //Center Board is used to get all possible directions
			Point start=new Point(x2,y2);
			Point end=new Point(x,y);
			// Check Horizontal
			boolean actual = PieceMove(start,end, new JediKnight(1));
						
			int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
			int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

			if( x2==x && y2==y)
				expected =false;
			
			else if((hDifference == 2 && vDifference == 1)
			  |(hDifference == 1 && vDifference == 2))
				expected=true;
			  
			  //Rook Movement
			else if((x2-x)==0 && Math.abs(y2-y)<=3)
				expected =true;
			else if((y2-y)==0 && Math.abs(x2-x)<=3)
				expected =true;
					  
			else
				expected =false;
			  
			Assert.assertEquals(actual,expected,"JediKnight Performed an Illegal Move:("+start.x+","+start.y+  "->"+end.x+","+end.y);
		  } 
  }
  
  
  boolean PieceMove(Point start, Point end, Piece p)
  {
	  //Team = 1
	  
	  Move move3 = new Move(start,end,p);
	  
	  //assert p.checkDestination(move3)==(false);
	  
	  boolean expected=false;
	//  assertTrue(p.checkDestination(move3),"Pawn Move Invalid huzzah");
	  
	  return p.checkDestination(move3);
	  
	 // assertFalse("Pawn Move Invalid huzzah", p.checkDestination(move3));
  }  
}
