package com.aws.JavaG1;

public class Timeslot {
	private int timeSlotID;
	private String timeStart;

	public Timeslot() {
	}

	public Timeslot(int timeSlotID, String timeStart) {
		this.timeSlotID = timeSlotID;
		this.timeStart = timeStart;
	}

	public int getTimeSlotID() {
		return timeSlotID;
	}

	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
}
