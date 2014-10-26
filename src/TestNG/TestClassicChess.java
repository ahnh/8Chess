package TestNG;

import java.awt.Point;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Model.Game;
import Model.Rule;


public class TestClassicChess extends TestVariants{
	@BeforeTest(groups = { "variants", "classic" })
	public void testClassic() {
		game = new Game(1);
	}
	@AfterTest(groups = { "variants", "classic" })
	public void testClassicDone() {
		System.out.println("TestClassicAfter");
	}

	@Test(groups = { "variants", "classic" })
	public void testClassicSample1() {
		Point start = new Point(1, 6);
		Point end = new Point(1, 5);
		Assert.assertEquals(game.move(start, end), Rule.VALID_MOVE, "ClassicChessMoveInvalid " + game.getError());
	}

	@Test(groups = { "variants", "classic" })
	public void testClassicSample2() {
		Point start = new Point(1, 2);
		Point end = new Point(1, 2);
		Assert.assertEquals(game.move(start, end), Rule.VALID_MOVE, "ClassicChess Invalid Move " + game.getError());
	}
}
