package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class TestJunit2 extends Controller {
	private Controller c1 = new Controller();

	@Test
	public void testRemoveActivity() {
		c1.addActivity(1, null, "gamer", 2);
		c1.removeActivity("gamer");
		assertTrue("Activity was not removed from the list!", c1.listActivities().isEmpty());
	}
}
