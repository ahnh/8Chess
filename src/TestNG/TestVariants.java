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
	
	@BeforeTest(groups = "variants")
	public void testBeforeTest() {
		// setup board etcs
		System.out.println("testBeforeVariants");
	}
	
	@AfterTest(groups = { "variants", "Classic" })
	public void testAfterTest() {
		// idk, maybe clean up?
		System.out.println("testAfterVariants - inc classic");
	}
	
	@Test(groups = { "variants", "Classic" })
	public void testClassic() {
		System.out.println("Testing Classic variants");
	}
	
	@Test(groups = { "variants", "brokenTests" })
	public void testSomething() {
		System.out.println("Testing Classic");
	}
	
}
