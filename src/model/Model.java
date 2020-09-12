package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Model {
	
	public class Activity implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6301470030768181064L;

		public Activity(int week, LocalDate date, String activityname, int points) {
			super();
			this.week = week;
			this.date = date;
			this.activityname = activityname;
			this.points = points;
		}
		
		private int week;
		private LocalDate date;
		private String activityname;
		private int points;
		
		public int getWeek() {
			return this.week;
		}
		public void setWeek(int week) {
			this.week = week;
		}
		public LocalDate getDate() {
			return this.date;
		}
		public void setDate(LocalDate localDate) {
			this.date = localDate;
		}
		public String getActivity() {
			return this.activityname;
		}
		public void setActivity(String activity) {
			this.activityname = activity;
		}
		public void setPoints(int points) {
			this.points = points;
		}
		public int getPoints() {
			return this.points;
		}
		
	}
	

}



