import java.util.ArrayList;

public class FileUtility
{
	public static boolean courseFiller(ArrayList<Course> courses, ArrayList<String> courseData)
	{	//checks if the file has the correct header
		if(courseData.get(0).equals("SM,DEPOT,ID,DESC,REQS-4,REQS-3,REQS-2,REQS-1,CREDS,YRS"))
		{	//creates a new course for each line of the file
			String[] tokens = null;
			for(int i = 1; i < courseData.size(); i++) 
			{	//split the line into tokens
				tokens = courseData.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				//creates a new course with the tokens
				//I made a new constructor for course that takes in a string array
				courses.add(new Course(tokens)); 
			}
			//returns true if the file was read correctly and all of the data is put into courses
			return true;
		} else {
			//returns false if the file has the wrong header (the file is the wrong file)
			System.out.println("ERROR: Wrong File inputed");
			return false;
		}
	}

	public static boolean newStudentFiller(ArrayList<Student> studentList, ArrayList<String> fileContents, String fileHeader, ArrayList<Course> courses)
	{
		if(fileHeader.equals("Teacher,Gradebook,Department,Student ID,Student Name,Grade Level,Team,Counselor,Full Year Average,Full Year Letter Grade,FG Average,FG Letter Grade")||fileHeader.equals("Teacher,Gradebook,Department,Student ID,Student Name,Grade Level,Team,Counselor,MP2 Average,MP2 Letter Grade,FG Average,FG Letter Grade"))
		{
			for(int i = 0; i<fileContents.size();i++)
			{
				String[] tokens = fileContents.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

				boolean studentIsInList = false;
				for(int sLIndex = 0; sLIndex<studentList.size(); sLIndex++)
				{
					if(studentList.get(sLIndex).getStudentID().equals(tokens[3]))
					{
						for(int j = 0; j<4; j++)
							studentList.get(sLIndex).getSchedules().add(new Schedule());
						studentIsInList = true;
						studentList.get(sLIndex).getSchedules().get(Integer.parseInt(tokens[5])-9).addCourse(
							new StudentCourse(
								getCourseFromCode(tokens[1].substring(1,7), courses),
								tokens[10], 
								tokens[5]));
					}
				}
				if(studentIsInList==false)
				{	
					studentList.add(new Student(
									new ArrayList<Schedule>(),
									tokens[3],
									tokens[4].substring(tokens[4].indexOf(", ")+1,tokens[4].length()-1),
									tokens[4].substring(1,tokens[4].indexOf(", "))));
				}
			}
			return true;
		} else {
			System.out.println("ERROR: Wrong File inputed");
			return false;
		}
	}
	
	public static boolean requirementsFiller(ArrayList<Double> requirements, ArrayList<String> reqData)
	{
		int temp = 0;
		for(int i = 0; i<reqData.get(0).length()-1;i++)
			if(reqData.get(0).substring(i, i+1).equals(","))
				temp++;
		//checks if the file has the correct header
		if(temp == 1)
		{	
			System.out.println("We in");
			String[] tokens = null;
			for(int i = 0; i < reqData.size(); i++) 
			{	//split the line into tokens
				tokens = reqData.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

				requirements.add(Double.parseDouble(tokens[1]));
			}
			//returns true if the file was read correctly and all of the data is put into requirements
			System.out.println("Succesfully Read");
			return true;
		} else {
			//returns false if the file has the wrong header (the file is the wrong file)
			System.out.println("ERROR: Wrong File inputed");
			return false;
		}
	}

	public static Course getCourseFromCode(String requestedCourseCode, ArrayList<Course> courses)
	{
		for(int i = 0; i < courses.size(); i++)
			if(requestedCourseCode.equals(courses.get(i).getClassID()))
				return courses.get(i);
		return null; 
	}


}