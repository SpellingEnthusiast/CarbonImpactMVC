package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class TestJunit4 extends Controller {
	private Controller c1 = new Controller();
	
	@Test
	public void testCalcPoints() {
		c1.addActivity(1, null, "a", 3);
		c1.addActivity(1, null, "b", 6);
		assertEquals(9, c1.calcPoints());
	}
}
