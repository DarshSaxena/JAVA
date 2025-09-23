import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentRegi {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
     
        frame.setLayout(new GridLayout(6, 2, 4, 4));
        
        JTextField nameField = new JTextField();
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        
        JTextField sapIdField = new JTextField();
        frame.add(new JLabel("SAP ID:"));
        frame.add(sapIdField);
        
        String[] year = {"1", "2", "3", "4"};
        JComboBox<String> yearCombo = new JComboBox<>(year);
        frame.add(new JLabel("Year:"));
        frame.add(yearCombo);
        
        frame.add(new JLabel("Hobbies:"));
        JPanel hobbiesPanel = new JPanel(new GridLayout(3, 2));
        JCheckBox sports = new JCheckBox("Sports");
        JCheckBox music = new JCheckBox("Music");
        JCheckBox reading = new JCheckBox("Reading");
        JCheckBox coding = new JCheckBox("Coding");
        JCheckBox art = new JCheckBox("Craft");
        
        hobbiesPanel.add(sports);
        hobbiesPanel.add(music);
        hobbiesPanel.add(reading);
        hobbiesPanel.add(coding);
        hobbiesPanel.add(art);
        frame.add(hobbiesPanel);
        
        frame.add(new JLabel(""));
        
        JButton submitButton = new JButton("Submit");  
        frame.add(submitButton);
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String sapId = sapIdField.getText();
                String year = (String) yearCombo.getSelectedItem();
                
                StringBuilder hobbies = new StringBuilder();
                if (sports.isSelected()) hobbies.append("Sports, ");
                if (music.isSelected()) hobbies.append("Music, ");
                if (reading.isSelected()) hobbies.append("Reading, ");
                if (coding.isSelected()) hobbies.append("Coding, ");
                if (art.isSelected()) hobbies.append("Art, ");
                
                
                String hobbiesStr = hobbies.length() > 0 ? 
                    hobbies.substring(0, hobbies.length() - 2) : "None";

                String message = "Registration Details:\n\n" +
                                 "Name: " + name + "\n" +
                                 "SAP ID: " + sapId + "\n" +
                                 "Year: " + year + "\n" +
                                 "Hobbies: " + hobbiesStr;
                                 
                JOptionPane.showMessageDialog(frame, message, "Registration Successful",JOptionPane.INFORMATION_MESSAGE);
    }
        });
        
        frame.setVisible(true);
    }
}