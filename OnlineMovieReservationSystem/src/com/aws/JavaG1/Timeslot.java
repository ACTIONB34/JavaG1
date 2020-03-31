package com.aws.JavaG1;

public class Timeslot {
	private int timeSlotID;
	private String timeStart;
	private String timeEnd;
	
	public Timeslot(int num, String ts, String te){
		this.timeSlotID = num;
		this.setTimeStart(ts);
		this.setTimeEnd(te);
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
