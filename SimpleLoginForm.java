import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, result;
    private String operator;

    public CustomCalculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        String[] buttons = {"1", "2", "3", "+", "-", "*", "/", "=", "C"};

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(220, 230, 240));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        if (input.matches("[1-3]")) {
            textField.setText(textField.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            operator = input;
            num1 = Double.parseDouble(textField.getText());
            textField.setText("");
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num2 != 0 ? num1 / num2 : 0; break;
            }
            textField.setText(String.valueOf(result));
        } else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new CustomCalculator();
    }
}