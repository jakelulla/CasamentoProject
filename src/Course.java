import java.util.Arrays;

public class Course
{
    private String seimester;
    private String department;
    private String classID;    
    private String description;    
    private String[] requirement;    
    private Double credits;
    private Double years;

    public Course() {
        this.seimester = "";
        this.department = "";
        this.classID = "";
        this.description = "";
        this.requirement = new String[0];
        this.credits = 0.0;
        this.years = 0.0;
    }

    public Course(String seimester, String department, String classID, String description, String[] requirement, Double credits, Double years) {
        this.seimester = seimester;
        this.department = department;
        this.classID = classID;
        this.description = description;
        this.requirement = requirement;
        this.credits = credits;
        this.years = years;
    }

    public Course(String[] tokens)
    {
        this.seimester = tokens[0];
        this.department = tokens[1];
        this.classID = tokens[2];
        this.description = tokens[3];
        this.requirement = flip(new String[]{tokens[4],tokens[5],tokens[6],tokens[7]});
        this.credits = Double.parseDouble(tokens[8]);
        this.years = Double.parseDouble(tokens[9]);
    }

    

    @Override
    public String toString() {
        return "Course [seimester=" + seimester + ", department=" + department + ", classID=" + classID
                + ", description=" + description + ", requirement=" + Arrays.toString(requirement) + ", credits="
                + credits + ", years=" + years + "]";
    }

    
    public static String[] flip(String[] array) {
        String[] flipped = new String[array.length];
        for(int i = 0; i < array.length; i++) {
            flipped[i] = array[array.length - i - 1];
        }
        return flipped;
    }

    public String getClassType()
    {
        return getDepartment();
    }

    public String getSeimester() {
        return seimester;
    }

    public void setSeimester(String seimester) {
        this.seimester = seimester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getRequirement() {
        return requirement;
    }

    public void setRequirement(String[] requirement) {
        this.requirement = requirement;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Double getYears() {
        return years;
    }

    public void setYears(Double years) {
        this.years = years;
    }

    public String getAverage() {
        return null;
    }

}
