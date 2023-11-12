import java.util.ArrayList;

public class Schedule
{
    private ArrayList<StudentCourse> courses = new ArrayList<StudentCourse>();
    private int year;
    
    public Schedule() {
    	//changed from arraylist of courses to student courses throughout class
    	this.courses = new ArrayList<StudentCourse>();
    	this.year = 0;
    }
    
    public Schedule(ArrayList<StudentCourse> courses, int year) {
        this.courses = courses;
        this.year = year;
    }

    public ArrayList<StudentCourse> getCourses() {
        return this.courses;
    }
    public void setCourses(ArrayList<StudentCourse> courses) {
        this.courses = courses;
    }
    
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }   
    public void addCourse(StudentCourse c)
    {
    	courses.add(c);
    }

    @Override
    public String toString() {
        return "Schedule [courses=" + courses + ", year=" + year + "]";
    }
    
}