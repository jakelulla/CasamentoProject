import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputListener;

public class mainThingie extends JPanel implements KeyListener, MouseInputListener
{ 
    //set screen width and automatically adjust screen height
    private static final int PREF_W = 2500;
    private static final int PREF_H = 1600;

    //array lists for the file contents to go in, these will be processed for data
    private static ArrayList<String> rawStudentData = new ArrayList<String>();
    private static ArrayList<String> rawCoursesOfferedData = new ArrayList<String>();
    private static ArrayList<String> rawRequirementsData = new ArrayList<String>();
    
    //creates each of the file chooser components
    private static FileChooserComponent studentsFCC = new FileChooserComponent("csv", "Students",rawStudentData,1);
    private static FileChooserComponent coursesOfferedFCC = new FileChooserComponent("csv", "Courses Offered",rawCoursesOfferedData,2);
    private static FileChooserComponent yearsRequirementsFCC = new FileChooserComponent("csv", "Year Requirements",rawRequirementsData,3);
    private static SearchBoxPanel searchOP = new SearchBoxPanel();
    private static ScrollableListPanel listPanel = new ScrollableListPanel(new ArrayList<Student>());
    
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static ArrayList<Course> courses = new ArrayList<Course>();
    public static ArrayList<Double> requirements = new ArrayList<Double>();

    private static boolean parsedCourses = false;
    private static boolean parsedStudents = false;
    private static CasamentoGUI CGUI = new CasamentoGUI();

    
    public mainThingie()
    {
        //you should know what these are
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());
        
        JPanel util = new JPanel();
        util.setLayout(new BoxLayout(util, BoxLayout.Y_AXIS));

        listPanel = new ScrollableListPanel(students);
        util.add(listPanel);

        //creates a seperate area for the file pickers, for organization
        util.add(coursesOfferedFCC);
        util.add(studentsFCC);
        util.add(yearsRequirementsFCC);
        studentsFCC.setVisible(false);

        util.setBackground(CasamentoGUI.background);
        util.setForeground(CasamentoGUI.background);

        util.add(searchOP,0);
        
        //puts said seperate area to the right (west) of whatever else the program has
        this.add(util,BorderLayout.WEST);
        //adds the CassementoGUI class in the center of the panel
        this.add(CGUI,BorderLayout.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    public void paintComponent(Graphics g)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
       
    }

    @Override
    public void keyTyped(KeyEvent e)
    {        
        
    }

    private static void createAndShowGUI() {
        mainThingie gamePanel = new mainThingie();
        JFrame frame = new JFrame("ATCaSamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
            
        });     
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
 
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Course> getCourses()
    {
        return courses;
    }

    public static boolean isParsedCourses() {
        return parsedCourses;
    }

    public static void setParsedCourses(boolean parsedCourses) {
        mainThingie.parsedCourses = parsedCourses;
    }

    public static boolean isParsedStudents() {
        return parsedStudents;
    }

    public static void setParsedStudents(boolean parsedStudents) {
        mainThingie.parsedStudents = parsedStudents;
    }

    public static CasamentoGUI getCGUI()
    {
        return CGUI;
    }

    public static void changeStudentVisibility(boolean b)
    {
        studentsFCC.setVisible(b);
    }

    public static void changeCoursesVisibility(boolean b)
    {
        coursesOfferedFCC.setVisible(b);
    }

    public static SearchBoxPanel getSearchOP()
    {
        return searchOP;
    }

    public static int getPrefW() {
        return PREF_W;
    }

    public static int getPrefH() {
        return PREF_H;
    }

    public static ArrayList<String> getRawStudentData() {
        return rawStudentData;
    }

    public static void setRawStudentData(ArrayList<String> rawStudentData) {
        mainThingie.rawStudentData = rawStudentData;
    }

    public static ArrayList<String> getRawCoursesOfferedData() {
        return rawCoursesOfferedData;
    }

    public static void setRawCoursesOfferedData(ArrayList<String> rawCoursesOfferedData) {
        mainThingie.rawCoursesOfferedData = rawCoursesOfferedData;
    }

    public static FileChooserComponent getStudentsFCC() {
        return studentsFCC;
    }

    public static void setStudentsFCC(FileChooserComponent studentsFCC) {
        mainThingie.studentsFCC = studentsFCC;
    }

    public static FileChooserComponent getCoursesOfferedFCC() {
        return coursesOfferedFCC;
    }

    public static void setCoursesOfferedFCC(FileChooserComponent coursesOfferedFCC) {
        mainThingie.coursesOfferedFCC = coursesOfferedFCC;
    }

    public static FileChooserComponent getYearsRequirementsFCC() {
        return yearsRequirementsFCC;
    }

    public static void setYearsRequirementsFCC(FileChooserComponent yearsRequirementsFCC) {
        mainThingie.yearsRequirementsFCC = yearsRequirementsFCC;
    }

    public static void setSearchOP(SearchBoxPanel searchOP) {
        mainThingie.searchOP = searchOP;
    }

    public static void setStudents(ArrayList<Student> students) {
        mainThingie.students = students;
    }

    public static void setCourses(ArrayList<Course> courses) {
        mainThingie.courses = courses;
    }

    public static ScrollableListPanel getListPanel() {
        return listPanel;
    }

    public static void setListPanel(ScrollableListPanel listPanel) {
        mainThingie.listPanel = listPanel;
    }

    public static void setCGUI(CasamentoGUI cGUI) {
        CGUI = cGUI;
    }

    public static ArrayList<String> getRawRequirementsData() {
        return rawRequirementsData;
    }

    public static ArrayList<Double> getRequirements() {
        return requirements;
    }

    public static void setRawRequirementsData(ArrayList<String> rawRequirementsData) {
        mainThingie.rawRequirementsData = rawRequirementsData;
    }

    public static void setRequirements(ArrayList<Double> requirements) {
        mainThingie.requirements = requirements;
    }

    
}