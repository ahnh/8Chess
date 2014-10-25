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
import Model.Pieces.Pawn;


public class TestNG {
	
  @Test
  public void f()
  {
	 
  }
  
  @Test
  public void PawnMovement()
  {
	  //Board Size
	  int boardSize=8;
	  
	  //Vertical Values
	  
	  boolean expected =false; //Initialize
	  
	  //Start
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
