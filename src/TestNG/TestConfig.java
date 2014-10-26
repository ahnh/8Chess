package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
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


public class TestConfig {
	 
	@BeforeSuite
	public void testBeforeSuite() {
		System.out.println("testBeforeSuite()");
	}
 
	@AfterSuite
	public void testAfterSuite() {
		System.out.println("testAfterSuite()");
	}
 
	@BeforeTest
	public void testBeforeTest() {
		// we may can create board here and access it.
		System.out.println("testBeforeTest()");
	}
 
	@AfterTest
	public void testAfterTest() {
		System.out.println("testAfterTest()");
	}
 
}