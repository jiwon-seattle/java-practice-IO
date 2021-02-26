package report;

public class Student {
	private String studentName;
	private int marks;
	private double percentage;
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public void calculatePercentage() {
		setPercentage((marks * 100) / 300);
	}
	
	@Override
	public String toString() {
		return "Name" + getStudentName() + " Percentage: " + getPercentage() + "%";
	}
}
