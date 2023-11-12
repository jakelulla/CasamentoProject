 //Ryan Schwartz
//Program Description: Big Ol' Gooey GUI
//Sep 26, 2023

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CasamentoGUI extends JPanel
{
    private static final int PREF_W = 1218;
    private static final int PREF_H = 847;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private Student displayStudent = new Student();
    private Requirements studRequirements = new Requirements(displayStudent);
    private YearDisplay[] transcripts = {new YearDisplay(), new YearDisplay(), new YearDisplay(), new YearDisplay()};
    public static Color background = new Color(196 , 213, 239);
    public static Color foreground = new Color(90, 134, 51);
    public static Color middleground = new Color(114, 148, 199);
    public static Color selection = new Color(228, 130, 58);

    public CasamentoGUI()
    {
        setFocusable(true);
        requestFocus();
        
        for(int i = 0; i < displayStudent.getSchedules().size(); i++)
            transcripts[i] = new YearDisplay(displayStudent, i);
        for(int j = 0; j<transcripts.length;j++)
        {
            transcripts[j].prepForDisplay(new Font("Helvetica", Font.PLAIN, 15));
            transcripts[j].getScroll().setVisible(true);
            add(transcripts[j].getScroll());
        }
   }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHints(hints);
        
        Color border = Color.BLACK;
        BasicStroke thick = new BasicStroke(10);
        BasicStroke thin = new BasicStroke(3);
        Font lVarsity = new Font("Varsity", Font.PLAIN, 40);
        Font sVarsity = new Font("Varsity", Font.PLAIN, 18);
        
        g2.setStroke(thick);
        g2.setFont(lVarsity);
        FontMetrics fmLargeText = g2.getFontMetrics();
        
        //Student Name Area
        g2.setColor(background);
        g2.fillRect(5, 5, PREF_W-10, 90);
        g2.setColor(border);
        g2.drawRect(5, 5, PREF_W-10, 90);
        g2.setStroke(thin);
        g2.setColor(middleground);
        Image logo = new ImageIcon(this.getClass().getResource("GradCapLogo.png")).getImage();
        g2.drawRect(91, 11, PREF_W-103, 78);
        g2.setColor(border);
        g2.fillRect(10, 10, 80, 81);
        g2.drawImage(logo, 8, 11, 78, 78, this);
        
        g2.setColor(foreground);
        int textW = fmLargeText.stringWidth(!displayStudent.getName().equals(", ") ? displayStudent.getName() : "No Data");
        int textX = PREF_W/2 - textW/2;
        g2.drawString(!displayStudent.getName().equals(", ") ? displayStudent.getName() : "No Data", textX, 65);
        
        //Classes Area 
        String[] years = {"Freshman", "Sophomore", "Junior", "Senior"};
        for(int i = 0; i < years.length; i++)
        {
            int x = i%2*(PREF_W/2 - 10) + 5 + i%2*10 - i%2*5; int y =i/2*((PREF_H - 200)/2) + 100/*+ i/2*10*/; int w = PREF_W/2 - 5; int h = (PREF_H - 200)/2;
           
            //box
            g2.setStroke(thick);
            g2.setColor(background);
            g2.fillRect(x, y, w, h);
            g2.setColor(border);
            g2.drawRect(x, y, w, h);
            g2.setStroke(thin);
            g2.setColor(middleground);
            g2.drawRect(x + 5, y + 5, w - 12, h - 12);
            
            //text
            g2.setFont(lVarsity);
            g2.setColor(foreground);
            textW = fmLargeText.stringWidth(years[i]);
            textX = x + PREF_W/4 - textW/2;
            g2.drawString(years[i], textX, y + 45);
            
            //transcript
            transcripts[i].setBorder(new Rectangle(x+13, y+55, w-25, h-65));
        }
        
        //Requirement Area
        //main box
        g2.setStroke(thick);
        g2.setColor(background);
        g2.fillRect(5, PREF_H-95, PREF_W-10, 90);
        g2.setColor(border);
        g2.drawRect(5, PREF_H-95, PREF_W-10, 90);

        //small boxes
        String[] classTypes = new String[]{"ENG", "MATH", "HIST", "SCI", "WL", "PE", "VPA", "PA", "PL", "CRED"};
        for(int i = 0; i < 10; i++)
        {
            //sections
            int x = i + i*(PREF_W-10)/10; int y = PREF_H - 95;  int w = (PREF_W-10)/10; int h = 90;
            g2.setStroke(thin);
            g2.setColor(border);
            g2.drawRect(x, y, w, h);

            g2.setStroke(new BasicStroke(2));
            g2.setColor(middleground);
            Rectangle section = new Rectangle(i==0? 10 : x+2, y + 6, i%9==0? w-12+i/9 : w-4, h - 12);
            if(i % 5 == 1){section.x = (int)section.getX() + 1; section.setSize((section.width-1), section.height);}
            g2.draw(section);

            //text
            g2.setColor(foreground);
            g2.setFont(sVarsity);
            FontMetrics fmSmallText = g2.getFontMetrics();
            textW = fmSmallText.stringWidth(classTypes[i]);
            textX = (int)(section.getX() + section.getWidth()/2) - textW/2;
            g2.drawString(classTypes[i]/*.toLowerCase()*/, textX, (int)(section.getY() + section.getHeight()/3) + 5);

            g2.setFont(new Font("Helvetica", Font.PLAIN, 15));
            fmSmallText = g2.getFontMetrics();
            textW = fmSmallText.stringWidth("met/need");
            textX = (int)(section.getX() + section.getWidth()/2) - textW/2;
            if((int)(Math.random()*2) == 0)//NEED TO CHANGE: if req not met, make white.
                g2.setColor(selection);
            g2.drawString("met/need", textX, (int)(section.getY() + section.getHeight()/3*2) + 5);
        }
    }

    public int getTrue(ArrayList<Boolean> classes)
    {
    	int truecount = 0;
    	for(int i = 0; i < classes.size(); i++)
    		if(classes.get(i) == true)
    			truecount++;
    	return truecount;
    }

    public int getTrueSingle(Boolean course)
    {
        if(course == true)
            return 1;
        else
            return 0;
    }

    public static int getPrefW() {
        return PREF_W;
    }

    public static int getPrefH() {
        return PREF_H;
    }

    public RenderingHints getHints() {
        return hints;
    }

    public void setHints(RenderingHints hints) {
        this.hints = hints;
    }

    public Requirements getStudRequirements() {
        return studRequirements;
    }

    public void setStudRequirements(Requirements studRequirements) {
        this.studRequirements = studRequirements;
    }

    public static void updateCasamentoGUI(CasamentoGUI cgui, Student student)
    {
        cgui.displayStudent = student;
        for(int i = 0; i < cgui.transcripts.length; i++)
        {
            cgui.transcripts[i].update(student, i);
        }
        cgui.repaint();
    }

    public static void updateCasamentoGUI(CasamentoGUI cgui)
    {
        for(int i = 0; i < cgui.transcripts.length; i++)
        {
            cgui.transcripts[i].update(cgui.displayStudent, i);
        }
        cgui.repaint();
    }
}