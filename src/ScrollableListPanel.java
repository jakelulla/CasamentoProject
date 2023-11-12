import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScrollableListPanel extends JPanel {

    private DefaultListModel<String> listModel;
    private JList<String> list;
    private static List<Student> sl;

    public ScrollableListPanel(List<Student> listOfStudents) {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        sl = listOfStudents;

        list.setFont(new Font("Helvetica", Font.PLAIN, 16));

        for (Student s : listOfStudents) {
            listModel.addElement(s.getLastName()+", "+s.getFirstName());
        }

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        scrollPane.setBackground(CasamentoGUI.middleground);
        scrollPane.getVerticalScrollBar().setBackground(CasamentoGUI.middleground);
        scrollPane.getVerticalScrollBar().setForeground(CasamentoGUI.background);
        list.setBackground(CasamentoGUI.background);
        list.setForeground(CasamentoGUI.foreground);
        list.setSelectionBackground(CasamentoGUI.background);
        list.setSelectionForeground(CasamentoGUI.selection);
        add(scrollPane, BorderLayout.CENTER);
    
        // Add a ListSelectionListener
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()&&list.getSelectedValue()!=null) {
                    onItemSelected(list.getSelectedValue());
                }
            }
        });
    }
    // Define a method to handle item selection
    private void onItemSelected(String selectedItem)
    {
        CasamentoGUI.updateCasamentoGUI(mainThingie.getCGUI(), getStudentGivenNameString(selectedItem));
    }

    public Dimension getPreferredSize() {
        return new Dimension(222, 560); // Set the desired width and height here
    }

    public static Student getStudentGivenNameString(String name)
    {
        if(name==null) return new Student();
        for (Student s : sl) {
            if(name.equals(s.getLastName()+", "+s.getFirstName()))
                return s;
        }
        return new Student();
    }

    public static void addStudent(Student s)
    {
        sl.add(s);
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void updateEverything(ArrayList<Student> s)
    {
        // DefaultListModel<String> listModel = (DefaultListModel<String>) this.getList().getModel();
        listModel.clear();
        for(int i = 0; i<s.size(); i++)
        {
            String lname = s.get(i).getLastName();
            String fname = s.get(i).getFirstName();
            String textIn = mainThingie.getSearchOP().getText();
            if(lname.contains(textIn)||fname.contains(textIn))
            listModel.addElement(lname+", "+fname); // Add a new student to the list
        }
        sortAlphabeticaly(listModel);
        this.setListModel(listModel);
    }

    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }

    public JList<String> getList() {
        return list;
    }

    public void setList(JList<String> list) {
        this.list = list;
    }

    public static List<Student> getSl() {
        return sl;
    }

    public static void setSl(List<Student> sl) {
        ScrollableListPanel.sl = sl;
    }

    public static void sortAlphabeticaly(DefaultListModel<String> model) {
        // Convert the model's elements to a List for sorting
        java.util.List<String> list = Collections.list(model.elements());

        // Sort the list alphabetically
        Collections.sort(list);

        // Clear the model
        model.clear();

        // Add the sorted elements back to the model
        for (String element : list) {
            model.addElement(element);
        }
    }
}
