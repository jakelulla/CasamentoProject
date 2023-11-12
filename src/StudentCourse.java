//Giulia DiSalvo 
//Oct 12, 2023
//Program Description: 
public class StudentCourse
{
	private Course course;
	private String average; 
	private String gradelevel; 
	
	public StudentCourse(Course course, String average, String gradelevel)
	{
		this.course = course; 
		this.average = average; 
		this.gradelevel = gradelevel; 
	}
	
	public Course getCourse()
	{
		return course;
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	public String getAverage()
	{
		return average;
	}

	public void setAverage(String average)
	{
		this.average = average;
	}

	public String getGradelevel()
	{
		return gradelevel;
	}

	public void setGradelevel(String gradelevel)
	{
		this.gradelevel = gradelevel;
	}

	@Override
	public String toString()
	{
		return "StudentCourse [course=" + course + ", average=" + average + ", gradelevel=" + gradelevel + "]";
	}

   	public String getClassType()
   	{
      return this.course.getClassType();
   	}

   	public String getClassID()
   	{
	   return this.course.getClassID();
   	}

}
