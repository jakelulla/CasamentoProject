import java.util.ArrayList;

public class Requirements {
//	//CHECKS IF THE CLASS IS A REQUIRED CLASS.
	private Student student;
	private boolean needsVPA = false;
	private boolean needsPA = false;
	private int gradeNum = 9;
		//Constructors
		public Requirements(Student student) {
			this.student = student;
		}
		//ENG
		public ArrayList<Boolean> engReqClasses()
		{
			ArrayList<Boolean> engReqClasses = new ArrayList<Boolean>();
			int engCount = 0;
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
				{
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("English") && pass(i, j))
						engCount++;
				}
				engReqClasses.add(engCount >=1);
				engReqClasses.add(engCount >=2);
				engReqClasses.add(engCount >=3);
				engReqClasses.add(engCount >=4);
				return engReqClasses;		
		}
	//HIST
		public ArrayList<Boolean> histReqClasses()
		{
			ArrayList<Boolean> histReqClasses = new ArrayList<Boolean>();
			boolean worldHistory = false;
			boolean USH1 = false;
			boolean USH2 = false;
			
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("Social Studies") && pass(i, j))
					{
						if(worldHistory == false)
							worldHistory = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SS1"));
						if(USH1 == false)
							USH1 = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SS2"));
						if(USH2 == false)
							USH2 = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SS3"));
					}
			histReqClasses.add(worldHistory);
			histReqClasses.add(USH1);
			histReqClasses.add(USH2);
			return histReqClasses;
		}
	//MATH
		public ArrayList<Boolean> mathReqClasses() //Skip if student took alg1 in middle school
		{
			ArrayList<Boolean> mathReqClasses = new ArrayList<Boolean>();
			boolean alg1 = false;
			boolean geom = false;
			boolean alg2 = false;
			boolean precalc = false;
			boolean other = false;
			boolean roundCheck = false;
			int numskip = -1;
			/* What is roundCheck? If the Math Course falls under one of these known categories, roundCheck is set to true. If roundCheck stays false,
			 that means the current Math Course falls under "Other". */
			
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("Math") && pass(i, j))
					{
							if(student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA2") && pass(i, j))
							{
								alg1 = true;
								roundCheck = true;
								if(Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel()) == gradeNum)
								{
									numskip = 0;
								}
							}
							if(student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA3") && pass(i, j))
							{
								geom = true;
								roundCheck = true;
								if(Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel()) == gradeNum)
								{
									numskip = 1;
								}
							}
							if(student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA4") && pass(i, j))
							{
								alg2 = true;
								roundCheck = true;
								if(Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel()) == gradeNum)
								{
									numskip = 2;
								}
							}
							if((student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA64") && pass(i, j))
							|| (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA66") && pass(i, j)))
							{
								precalc = true;
								roundCheck = true;
								if(Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel()) == gradeNum)
								{
									numskip = 3;
								}
							}
						if(other == false && roundCheck == false)
						{
							other = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("MA6")); //POSSIBLE MISTAKE HERE
							numskip = 4;
						}
					}
			if(numskip == 0) { //Passed Algebra 1 as first math class
				mathReqClasses.add(alg1);
				mathReqClasses.add(geom);
				mathReqClasses.add(alg2);
			}
			if(numskip == 1) { //Passed Geometry Fresh as first math class
				mathReqClasses.add(geom);
				mathReqClasses.add(alg2);
				mathReqClasses.add(precalc);
			}
			if(numskip >= 2) { //Passed Algebra 2 as first math class
				mathReqClasses.add(alg2);
				mathReqClasses.add(precalc);
				mathReqClasses.add(other);
			}
			//WATCH OUT IF YOU HAVE MULTIPLE OTHERS
			if(numskip == -1 && gradeNum < 12) { //Couldn't find a passed class this first year, looking at next year
				gradeNum++;
				mathReqClasses();
			}
			if(numskip == -1 && gradeNum >= 12) //Couldn't find a passed class at any time during high school.
			{
				mathReqClasses.add(false);
				// System.out.println("Dude you like totally failed");
				gradeNum = 9;
			}
			return mathReqClasses;
		}
	//SCI
		public ArrayList<Boolean> sciReqClasses()
		{
			ArrayList<Boolean> sciReqClasses = new ArrayList<Boolean>();
			boolean bio = false;
			boolean chem = false;
			boolean phys = false;
			
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("Science") && pass(i, j))
					{
						if(bio == false)
							bio = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SC1"));
						if(chem == false)
							chem = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SC2"));
						if(phys == false)
							phys = (student.getSchedules().get(j).getCourses().get(i).getClassID().contains("SC3"));
					}
			sciReqClasses.add(bio);
			sciReqClasses.add(chem);
			sciReqClasses.add(phys);
			return sciReqClasses;
		}
	//WORLD LANG
		public ArrayList<Boolean> worldLangReqClasses()
		{
			ArrayList<Boolean> worldLangReqClasses = new ArrayList<Boolean>();
			int langCount = 0;
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("World Language") && pass(i, j))
						langCount++;
				worldLangReqClasses.add(langCount >=1);
				worldLangReqClasses.add(langCount >=2);
				return worldLangReqClasses;		
		}
	//PE
		public ArrayList<Boolean> peReqClasses()
		{
			ArrayList<Boolean> peReqClasses = new ArrayList<Boolean>();
			int peCount = 0;
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("Phys. Ed. / Health") && pass(i, j))
						peCount++;
				peReqClasses.add(peCount >=1);
				peReqClasses.add(peCount >=2);
				peReqClasses.add(peCount >=3);
				peReqClasses.add(peCount >=4);
				return peReqClasses;
		}
	//PA
		public ArrayList<Boolean> paReqClasses()
		{
            ArrayList<Boolean> paReqClasses = new ArrayList<Boolean>();
            for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
                if(student.getSchedules().get(j).getCourses().get(i).getCourse().getRequirement()[Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel())-9].equals("PA") && pass(i, j))
                {
                    paReqClasses.add(true);
                    needsPA = false;
                }
                else
                {
                    paReqClasses.add(false);
                    needsPA = true;
                }
            return paReqClasses;
		}
	//VPA
		public ArrayList<Boolean> vpaReqClasses()
		{
            ArrayList<Boolean> vpaReqClasses = new ArrayList<Boolean>();
            for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
                if(student.getSchedules().get(j).getCourses().get(i).getCourse().getRequirement()[Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel())-9].equals("VPA") && pass(i, j))
                {
                    vpaReqClasses.add(true);
                    needsVPA = false;
                }
                else
                {
                    vpaReqClasses.add(false);
                    needsVPA = true;
                }
            return vpaReqClasses;
		}
	//VPA OR PA (GOES TO PA BY DEFAULT IF BOTH ARE NEEDED) STILL PROBLEMS
		public ArrayList<Boolean> vpaAndpa()
		{
            ArrayList<Boolean> paAndvpa = new ArrayList<Boolean>();
            for(int j = 0; j < student.getSchedules().size(); j++)
                for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
                if(student.getSchedules().get(j).getCourses().get(i).getCourse().getRequirement()[Integer.parseInt(student.getSchedules().get(j).getCourses().get(i).getGradelevel())-9].equals("VPA PA") && pass(i, j))
                {
                    if(needsPA == true && needsVPA == true)
                    {
                        paAndvpa.add(true);
                        paAndvpa.add(false);
                        needsPA = false;
                        needsVPA = true;
                    }
                    else if(needsPA == true && needsVPA == false)
                    {
                        paAndvpa.add(true);
                        paAndvpa.add(false);
                        needsPA = false;
                        needsVPA = false;
                    }
                    else if(needsPA == false && needsVPA == true)
                    {
                        paAndvpa.add(false);
                        paAndvpa.add(true);
                        needsPA = false;
                        needsVPA = false;
                    }
                    else
                    {
                        paAndvpa.add(false);
                        paAndvpa.add(false);
                        needsPA = false;
                        needsVPA = false;
                    }
                }
                else
                {
                    paAndvpa.add(false);
                    paAndvpa.add(false);
                }
                return paAndvpa;
		}
	//PFL
		public ArrayList<Boolean> pflReqClasses()
		{
			ArrayList<Boolean> pflReqClasses = new ArrayList<Boolean>();
			boolean pflReqClass = false;
			for(int j = 0; j < student.getSchedules().size(); j++)
				for(int i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
					if(student.getSchedules().get(j).getCourses().get(i).getCourse().getDepartment().equals("Business") && pass(i, j))
						pflReqClass = true;
			pflReqClasses.add(pflReqClass);
			return pflReqClasses;
			}
		
			public boolean pass(int i, int j)
		{
			for(j = 0; j < student.getSchedules().size(); j++)
				for(i = 0; i < student.getSchedules().get(j).getCourses().size(); i++)
				{
					if(student.getSchedules().get(j).getCourses().get(i).getAverage().charAt(0) < 'F')
						return true;
				}
			return false;
		}
		public Student getStudent() {
			return student;
		}
		public void setStudent(Student student) {
			this.student = student;
		}
}
