import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileChooserComponent extends JPanel {
    //declares necessary variables
    private JButton selectButton;
    private JFileChooser fileChooser = new JFileChooser();
    private File choosenFile;

    //creates a FCC with several file filters
    public FileChooserComponent(String defaultType, String ButtonMessage, ArrayList<String> listToFill, int id) {
        //creates the button with the message
        selectButton = new JButton(ButtonMessage);
        fileChooser = new JFileChooser();

        //creates a new filter and filters for the "defaultType" of file
        final FileFilter defaultTypeFilter = new FileFilter() {
            @Override
            public boolean accept(final File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith("."+defaultType);
            }
            //changes the description to be capitalized (Only for style)
            @Override
            public String getDescription() {
                if(defaultType.length()>3)
                    return defaultType.substring(0,1).toUpperCase()+ defaultType.substring(1).toLowerCase()+" files (*."+defaultType.toLowerCase()+")";
                return defaultType.toLowerCase()+" files (*."+defaultType.toLowerCase()+")";
            }
        };
        //adds and sets the filter 
        fileChooser.addChoosableFileFilter(defaultTypeFilter);
        fileChooser.setFileFilter(defaultTypeFilter);

        //the following code is run when a file is selected
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    choosenFile = selectedFile;

                    //once a file has been choosen completely, the code beyond this point is run
                    //this code reads the file with a scanner and adds each line to the listToFill
                    System.out.println("you picked a file");
                    try {
                        Scanner sc = new Scanner(choosenFile);
                        while(sc.hasNextLine())
                            listToFill.add(sc.nextLine());
                        sc.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    switch(id)
                    {
                        case 1:
                            if(mainThingie.isParsedCourses())
                            {
                                String header = listToFill.get(0);
                                listToFill.remove(0);
                                mainThingie.setParsedStudents(FileUtility.newStudentFiller(mainThingie.getStudents(),listToFill,header, mainThingie.getCourses()));
                            } else {
                                System.out.println("Need to insert Courses file first");
                                mainThingie.getStudents().clear();
                            }
                            listToFill.clear();
                            break;

                        case 2:
                            if(FileUtility.courseFiller(mainThingie.getCourses(), listToFill))
                            {
                                mainThingie.getCourses().clear();
                                mainThingie.setParsedCourses(FileUtility.courseFiller(mainThingie.getCourses(), listToFill));
                                mainThingie.changeStudentVisibility(true);
                                mainThingie.changeCoursesVisibility(false);
                            } else {
                                mainThingie.getCourses().clear();
                            }
                            listToFill.clear();
                            break;

                        case 3:
                            if(!FileUtility.requirementsFiller(mainThingie.getRequirements(), listToFill))
                            {
                                mainThingie.getRawRequirementsData().clear();
                                mainThingie.getRequirements().clear();
                            }
                            listToFill.clear();
                            break;
                            
                        default:
                            System.out.println("That isnt supposed to happen, check the id of your FCCs");
                            listToFill.clear();
                            break;
                    }
                    mainThingie.getStudentsFCC().getFileChooser().setCurrentDirectory(selectedFile);
                    if(mainThingie.isParsedCourses() && mainThingie.isParsedStudents())
                    {
                        System.out.println("Both files have been parsed");
                        mainThingie.getListPanel().updateEverything(mainThingie.getStudents());
                    } else {
                        System.out.println("One or more files have not been parsed");
                    }
                    
                }
            }
        });
        //adds the button
        add(selectButton);
        //keeps the user in the same folder when reopening
        fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
    }
    //returns the file that was chosen
    public File getChoosenFile() {
        return choosenFile;
    }
    
    public boolean hasChoosenFile()
    {
        return choosenFile != null;
    }
    public JButton getSelectButton() {
        return selectButton;
    }
    public void setSelectButton(JButton selectButton) {
        this.selectButton = selectButton;
    }
    public JFileChooser getFileChooser() {
        return fileChooser;
    }
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    public void setChoosenFile(File choosenFile) {
        this.choosenFile = choosenFile;
    }
    
}