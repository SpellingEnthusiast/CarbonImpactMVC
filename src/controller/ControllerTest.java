package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class ControllerTest extends Controller {
	private Controller c1 = new Controller();

	/**
	 * 
	 */
	private static final long serialVersionUID = 6468283716699947018L;
	
	@Test
	public void testAddActivity() {
		c1.addActivity(1, null, "gamer", 2);
		assertFalse("Activity was not added to the list!", c1.listActivities().isEmpty());
	}

	@Test
	public void testRemoveActivity() {
		c1.addActivity(1, null, "gamer", 2);
		c1.removeActivity("gamer");
		assertTrue("Activity was not removed from the list!", c1.listActivities().isEmpty());
	}

	@Test
	public void testListActivities() {
		c1.addActivity(1, null, "gamer", 2);
		assertTrue("Activities listed incorrectly!", c1.listActivities().startsWith("Week"));
	}

	@Test
	public void testListActivitiesByName() {
		c1.addActivity(1, null, "c", 2);
		c1.addActivity(1, null, "b", 3);
		c1.addActivity(1, null, "a", 6);
		System.out.println(c1.listActivitiesByName());
		String[] c1s = c1.listActivitiesByName().split(" ");
		System.out.println(c1s[5]);
		
		if (c1s[5].equals("a")) {
			
		}
		else {
			fail("The activites were not arranged in alphabetical order!");
		}
	}

	@Test
	public void testCalcPoints() {
		c1.addActivity(1, null, "a", 3);
		c1.addActivity(1, null, "b", 6);
		assertEquals(9, c1.calcPoints());
	}

}
