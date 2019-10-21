package model;

import java.util.HashMap;

public class Group {
	private String description;
	private String from;
	private String to;
	private int groupNo;
	private HashMap<Employee, Integer> hoursOfWork;
	
	public Group(String description) {
		setDescription(description);
		hoursOfWork = new HashMap<Employee, Integer>();
	}
	
	public Group(String description, int groupNo) {
		setDescription(description);
		setGroupNo(groupNo);
		hoursOfWork = new HashMap<Employee, Integer>();
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getGroupNo() {
		return groupNo;
	}

	private void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public HashMap<Employee, Integer> getHoursOfWork() {
		return hoursOfWork;
	}

	@SuppressWarnings("unused")
	private void setHoursOfWork(HashMap<Employee, Integer> hoursOfWork) {
		this.hoursOfWork = hoursOfWork;
	}
	
	public void addEmployee(Employee employee, int hours) 
	{
		this.hoursOfWork.put(employee, hours);
	}
	
	public String toString() 
	{
		String string = "Description: " + description + ", Group No: " + groupNo + "\n\tEmployees:";
		for (Employee employee : hoursOfWork.keySet()) {
			string += "\n\t\tEmployee: " + employee.toString() + "\n\t\tHours: " + hoursOfWork.get(employee) + "\n";
		}
		return string;
	}
}
