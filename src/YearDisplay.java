import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//Ryan Schwartz
//Program Description: for displaying the transcripts within the GUI using JTable and JScrollPane
//Oct 19, 2023

public class YearDisplay extends JTable
{
   final private String[] header = {"Class", "Grade", "Requirements"};
   private JScrollPane scroll;
   private JTable table;
   private Font font;

   public YearDisplay()
   {
      String[][] schedule = new String[][]{
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
         { "No Data",   "N/A",   "N/A"},
      };
      table = new JTable(schedule, header);
      scroll = new JScrollPane(table);
   }
   
   public YearDisplay(Student student, int year)
   {
      ArrayList<StudentCourse> courseList = student.getSchedules().get(year).getCourses();
      String[][] schedule = new String[courseList.size()][header.length];
      for(int i = 0; i < schedule.length; i++)
      {
         String name = courseList.get(i).getCourse().getDescription();
         String grade = courseList.get(i).getAverage();
         String requirements = Arrays.toString(courseList.get(i).getCourse().getRequirement())/*.getClassType()*/;  
         schedule[i] = new String[]{name, grade, requirements};
      }
      table = new JTable(schedule, header);
      scroll = new JScrollPane(table);
   }
   
   public void prepForDisplay(Font font)
   {
      //remove unwanted inherent aspects
      table.setDefaultEditor(Object.class, null);
      scroll.setBorder(BorderFactory.createEmptyBorder());
      
      //colors
      scroll.setBackground(CasamentoGUI.background);
      scroll.setForeground(CasamentoGUI.background);
      table.getTableHeader().setForeground(CasamentoGUI.background);
      table.getTableHeader().setBackground(CasamentoGUI.background);
      table.setBackground(CasamentoGUI.background);
      table.setForeground(CasamentoGUI.foreground);
      table.setOpaque(true);
      table.setGridColor(CasamentoGUI.background);
      table.setFillsViewportHeight(true);
      table.setSelectionBackground(CasamentoGUI.background);
      table.setSelectionForeground(CasamentoGUI.selection);
               
      //sizing
      int rowHeight = 25;
      table.getTableHeader().setPreferredSize(new Dimension(scroll.getWidth(), rowHeight));
      table.setRowHeight(rowHeight);
      table.getColumnModel().getColumn(1).setMinWidth(70);
      table.getColumnModel().getColumn(1).setMaxWidth(70);
      table.getColumnModel().getColumn(2).setMinWidth(110);
      table.getColumnModel().getColumn(2).setMaxWidth(110);
      
      //centering
      DefaultTableCellRenderer centerer = new DefaultTableCellRenderer();
      centerer.setHorizontalAlignment(SwingConstants.CENTER);
      table.getTableHeader().setDefaultRenderer(centerer);
      table.getColumnModel().getColumn(1).setCellRenderer(centerer);
      table.getColumnModel().getColumn(2).setCellRenderer(centerer);
      
      //font
      table.setFont(font);
      
      //remove unnecessary decimals in credits
      // for(int i = 0; i < table.getRowCount(); i++)
      // if(Double.parseDouble((String)table.getModel().getValueAt(i, 2)) == Math.round(Double.parseDouble((String)table.getModel().getValueAt(i, 2))))
      // table.getModel().setValueAt(Integer.toString((int)Math.round(Double.parseDouble((String)table.getModel().getValueAt(i, 2)))), i, 2);

      //funny colors that might make me kms
      // table.setDefaultRenderer(getClass(), centerer);
      
      // table.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer(Color.RED));

      //bruhb
      this.font = font;
   }
            
   public void setBorder(Rectangle border)
   {
      scroll.setBounds(border);
      table.setSize((int)border.getWidth(), (int)border.getHeight());
   }
   
   public JScrollPane getScroll()
   {
      return scroll;
   }

   public JTable getTable()
   {
      return table;
   }

   public void update(Student student, int year) {
      ArrayList<StudentCourse> courseList = student.getSchedules().get(year).getCourses();
      String[][] schedule = new String[courseList.size()][header.length];
      Rectangle border = scroll.getBounds();
      
      for(int i = 0; i < schedule.length; i++) {
         String name = courseList.get(i).getCourse().getDescription();
         String grade = courseList.get(i).getAverage();
         String requirements = courseList.get(i).getCourse().getRequirement()[0]/*.getClassType()*/;
         schedule[i] = new String[]{name, grade, requirements};
      }

      if(schedule.length == 0 || schedule[0].length == 0 || schedule[0][0] == null)
         new YearDisplay();
      else
         table.setModel(new javax.swing.table.DefaultTableModel(schedule, header));
      setBorder(border);
      prepForDisplay(font);
   }
}
/* 1. Table Row Colors- how tf?!
 * 2. Requirements get Finished/Connected to GUI at Bottom
 * 3. GRADES ?!
 * 4. Methods for credit requirements
 */

 class CustomCellRenderer extends DefaultTableCellRenderer {
   private Color backgroundColor;

   public CustomCellRenderer(Color backgroundColor) {
       this.backgroundColor = backgroundColor;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

       // Check if this is the specific cell to change the background color
       if (row == 1 && column == 1) {
           component.setForeground(backgroundColor);
       }

       return component;
   }
}