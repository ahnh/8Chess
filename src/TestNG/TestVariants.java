package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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


public class TestVariants {
	
	@BeforeTest
	public void testBeforeTest() {
		// setup board etcs
		System.out.println("testBeforeVariants");
	}
	
	@Test(groups = { "variants", "Classic" })
	public void testClassic() {
		System.out.println("Testing Classic");
	}
	
	@AfterTest
	public void testAfterTest() {
		// idk, maybe clean up?
		System.out.println("testAfterVariants");
	}
}
