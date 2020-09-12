package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import model.Model;


public class Controller extends Model implements Serializable {
	/**
	 The Controller class has methods which act on the Model class, these methods are used by the GUI to provide various functionality to the application.
	 @author Vytautas Vosylius
	 @version 2.0
	 @since 02-03-2020 
	 */
	private static final long serialVersionUID = 467879533096476446L;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	public List<String> activitiesDropDown = new ArrayList<String>();
	
	/**
	 The addActivity method creates a new Activity object and adds it to an arraylist of Activity objects.
	 * 
	 * @param week This is the first parameter to the addActivity method, it represents the week number which the activity was carried out in.
	 * @param date This is the second parameter to the addActivity method, it represents the date the activity took place in.
	 * @param activityname This is the third parameter to the addActivity method, it represents the name of the activity which took place.
	 * @param points This is the fourth parameter to the addActivity method, it represents the point value of the activity (could be negative or positive).
	 */
	public void addActivity(int week, LocalDate date, String activityname, int points) {
		activities.add(new Activity(week, date, activityname.toLowerCase(), points));
	}
	
	public void destroyMemory() {
		while (true) {
			System.out.println("Added activity");
			activities.add(new Activity(1, null, "aaaaa", 3));
		}
	}
	
	/**
	 The removeActivity method removes an activity object from the arraylist of activity objects, if the parameter passed is a valid activity name.
	 * @param activityname The name of the activity which is going to be removed from the arraylist.
	 */
	public void removeActivity(String activityname) {
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getActivity().equals(activityname)) {
				activities.remove(i);
			}
		}
	}
	
	/**
	 The listActivities method loops through every item in the activities arraylist and appends information of each activity to a string, followed by a new line.
	 The resulting string is then used in the GUI to display activities to the user.
	 * @return String this returns a string containing all the details of every activity. Each activity is seperated by a new line.
	 */
	public String listActivities() {
		String tmp = "";
		for (int i = 0; i < activities.size(); i++) {
			tmp += "Week: " + activities.get(i).getWeek() + " " + "Date: " + activities.get(i).getDate() + " " + "Activity: " + activities.get(i).getActivity() + " " + "Points: " + activities.get(i).getPoints() + "\n";
		}
		return tmp;
	}
	
	/**
	 The listActivitiesByDate method sorts the activities arraylist by their date (using comparator), in ascending order. Once the activites are sorted, it appends them to a String,
	 seperating each activity by a new line.
	 * @return String this returns a string containing all the details of every activity, organised by date. Each activity is seperated by a new line.
	 */
	public String listActivitiesByDate() {
		Comparator<Activity> compareByDate = (Activity a1, Activity a2) -> 
	    a1.getDate().compareTo(a2.getDate());
		Collections.sort(activities, compareByDate);
		String tmp = "";
		for (int i = 0; i < activities.size(); i++) {
			tmp += "Week: " + activities.get(i).getWeek() + " " + "Date: " + activities.get(i).getDate() + " " + "Activity: " + activities.get(i).getActivity() + " " + "Points: " + activities.get(i).getPoints() + "\n";
		}
		return tmp;
	}
	
	/**
	 The listActivitiesByName method sorts the activities arraylist by their name (using comparator), in alphabetical order. Once the activites are sorted, it appends them to a String,
	 seperating each activity by a new line.
	 * @return String this returns a string containing all the details of every activity, organised by name. Each activity is seperated by a new line.
	 */
	public String listActivitiesByName() {
		Comparator<Activity> compareByName = (Activity a1, Activity a2) -> 
	    a1.getActivity().compareTo(a2.getActivity());
		Collections.sort(activities, compareByName);
		String tmp = "";
		for (int i = 0; i < activities.size(); i++) {
			tmp += "Week: " + activities.get(i).getWeek() + " " + "Date: " + activities.get(i).getDate() + " " + "Activity: " + activities.get(i).getActivity() + " " + "Points: " + activities.get(i).getPoints() + "\n";
		}
		return tmp;
	}
	
	/**
	 The calcPoints method returns the sum of every activities points, it achieves this by looping through every activity in the arraylist and getting the points of each activity,
	 adding them to a counter.
	 * @return int Total points accumulated by activities.
	 */
	public int calcPoints() {
		int total = 0;
		for (int i = 0; i < activities.size(); i++) {
			total += activities.get(i).getPoints();
		}
		return total;
	}
	
	/**
	 The saveToFile method saves the activities list to a file so it can be loaded at any time. This is achieved by implementing the Serializable interface and creating
	 an ObjectOutputStream.
	 */
	public void saveToFile(){
	  try
	   {
	    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("activities.txt"));
	            {
	            	os.writeObject(activities);
	            }
	    os.close();
	    } catch (Exception ex) {
	     System.out.println("could not save");
	     ex.printStackTrace();
	   }
	 }
	
	/**
	 The loadFromFile function loads the activities list from a file, this is achieved by implementing the Serializable interface and creating an ObjectInputStream.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadFromFile(){
		activities.removeAll(activities);
		  try
		   {
		     ObjectInputStream is = new ObjectInputStream(new FileInputStream("activities.txt"));
		     activities = (ArrayList) is.readObject();
		     is.close();       
		   } 
		   catch (Exception ex) {
		     System.out.println("could not load");
		     ex.printStackTrace();
		   }
		}
	
	/**
	 The loadFromFileToList method populates the dropdown list contained in the program with a few predetermined activities, it does this by reading the textfile and 
	 concatenating each line to an empty String, it then creates a list which contains the aforementioned empty String (however it is no longer empty because
	 it has been appended to.). This list is then used for adding to the dropdown.
	 * @throws FileNotFoundException The following file was not found: C:\carbondata\summary.txt
	 */
	public void loadFromFileToList() throws FileNotFoundException {
		File file = new File("C:\\carbondata\\summary.txt");
		Scanner sc = new Scanner(file);
		String fileContent = "";
		
		while (sc.hasNextLine()) {
			fileContent = fileContent.concat(sc.nextLine() + "\n");
		}
		
		String[] data = fileContent.split("\\*");
		
		for (int i = 0; i < data.length; i++) {
			activitiesDropDown.add(data[i]);
			i++;
		}
		
		sc.close();
	}
	
}