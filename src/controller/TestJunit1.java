package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class TestJunit1 extends Controller {
	private Controller c1 = new Controller();
	
	@Test
	public void testAddActivity() {
		c1.addActivity(1, null, "gamer", 2);
		assertFalse("Activity was not added to the list!", c1.listActivities().isEmpty());
	}

}
