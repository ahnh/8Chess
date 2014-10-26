package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import java.awt.Point;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Model.Game;
import Model.Rule;
import Views.ViewBase;

public class TestStationaryChess extends TestVariants {
	@BeforeTest(groups = { "variants", "stationary" })
	public void testStationary() {
		game = new Game(8);
	}
	
	@AfterTest(groups = { "variants", "stationary" })
	public void testStationaryDone() {
		System.out.println("TestStionaryAfter");
	}

	@Test(groups = { "variants", "stationary" })
	public void testStationarySample1() {
		Point start = new Point(7, 6);
		Point end = new Point(6, 6);
		assertEquals("StionaryMoveInvalid" + game.getError(),
				game.move(start, end), Rule.VALID_MOVE);
	}

	@Test(groups = { "variants", "stationary" })
	public void testStationaryicSample2() {
		Point start = new Point(1, 6);
		Point end = new Point(1, 6);
		assertEquals("StionaryMoveInvalid:" + game.getError(),
				game.move(start, end), Rule.VALID_MOVE);
	}
}