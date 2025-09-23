import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StudentReg 
{
public static void main(String args[]) 
{
JFrame frame = new JFrame("Student Registration");
frame.setSize(300, 300);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new GridLayout(5, 2, 10, 10));

JLabel nameLabel = new JLabel("Name");
JTextField nameField = new JTextField();

JLabel sapidLabel = new JLabel("SAP ID");
JTextField sapidField = new JTextField();

JLabel hobbiesLabel = new JLabel("Hobbies");
JTextField hobbiesField = new JTextField();

JLabel yearLabel = new JLabel("Year");
JTextField yearField = new JTextField();

JButton submitButton = new JButton("Submit");

frame.add(nameLabel);
frame.add(nameField);
frame.add(sapidLabel);
frame.add(sapidField);
frame.add(hobbiesLabel);
frame.add(hobbiesField);
frame.add(yearLabel);
frame.add(yearField);
frame.add(new JLabel());
frame.add(submitButton);

submitButton.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
String name = nameField.getText();
String sapid = sapidField.getText();
String hobbies = hobbiesField.getText();
String year = yearField.getText();
if (name.isEmpty() || sapid.isEmpty() || hobbies.isEmpty() || year.isEmpty()) 
{
JOptionPane.showMessageDialog(frame, "Please fill all your details.");
} 
else 
{
JOptionPane.showMessageDialog(frame, "Submitted Successfully!");
}

}
});
frame.setVisible(true);
}
}
