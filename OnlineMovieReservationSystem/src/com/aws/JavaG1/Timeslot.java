package com.aws.JavaG1;

public class Timeslot {
	private int timeSlotID;
	private String timeStart;
	private String timeEnd;

	public Timeslot() {
	}

	public Timeslot(int timeSlotID, String timeStart, String timeEnd) {
		this.timeSlotID = timeSlotID;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
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

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
}
