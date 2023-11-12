import java.util.ArrayList;

public class Student
{
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private String studentID;
    private String firstName;
    private String lastName;
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    public Student() {
    	this.schedules = new ArrayList<Schedule>();
    	this.studentID = "";
    	this.firstName = lastName = "";
    }
    public Student(ArrayList<Schedule> schedules, String studentID, String firstName, String lastName) {
    	this.schedules = schedules;
    	this.studentID = studentID;
    	this.lastName = lastName;
    	this.firstName = firstName;
    }
    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }
    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID; 
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getName()
    {
        return lastName+", "+firstName;
    }
    public void setName(String string) {
        lastName = string;
    }
    @Override
    public String toString() {
        return "Student [schedules=" + schedules.toString() + ", studentID=" + studentID + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }

    
}