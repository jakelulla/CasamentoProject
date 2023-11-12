import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class SearchBoxPanel extends JPanel {

    private static JTextField searchJTF = new JTextField(8);
    private static JLabel searchJL = new JLabel("Search Name:" );
    private static JToggleButton metReqJTB = new JToggleButton("Enable Filter");

    public SearchBoxPanel() {
        add(searchJL);
        add(searchJTF);
        add(metReqJTB);
        this.setBackground(Color.BLACK);
        // this.setBackground(CasamentoGUI.middleground);
        metReqJTB.setForeground(CasamentoGUI.foreground); //green
        metReqJTB.setBackground(CasamentoGUI.middleground); //dark blue
        searchJL.setForeground(CasamentoGUI.foreground);
        searchJTF.setBackground(CasamentoGUI.background);
        searchJTF.setForeground(CasamentoGUI.foreground);
        // searchJTF.setSelectionColor(CasamentoGUI.middleground);
        searchJTF.setSelectedTextColor(CasamentoGUI.selection);
        metReqJTB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    metReqJTB.setText("Disable Filter");
                    metReqJTB.setForeground(CasamentoGUI.selection);//orange
                    System.out.println("Selectd");
                } else {
                    metReqJTB.setText("Enable Filter");
                    metReqJTB.setForeground(CasamentoGUI.foreground);//green
                    System.out.println("unselected");
                }
            }
        });

        searchJTF.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainThingie.getListPanel().updateEverything(mainThingie.getStudents());
        }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(222, 70); // Set the desired width and height here
    }

    public String getText()
    {
        if(searchJTF.getText()==null) return "";
        return searchJTF.getText();
    }
}
