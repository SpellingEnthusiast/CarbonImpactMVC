package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class TestJunit3 extends Controller {
	private Controller c1 = new Controller();

	@Test
	public void testListActivities() {
		c1.addActivity(1, null, "gamer", 2);
		assertTrue("Activities listed incorrectly!", c1.listActivities().startsWith("Week"));
	}
}
