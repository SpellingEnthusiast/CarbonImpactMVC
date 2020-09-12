package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class TestJunit5 extends Controller {
	private Controller c1 = new Controller();

	@Test
	public void testListActivitiesByName() {
		c1.addActivity(1, null, "c", 2);
		c1.addActivity(1, null, "b", 3);
		c1.addActivity(1, null, "a", 6);
		String[] c1s = c1.listActivitiesByName().split(" ");
		
		if (c1s[5].equals("a")) {
			
		}
		else {
			fail("The activites were not arranged in alphabetical order!");
		}
	}

}

