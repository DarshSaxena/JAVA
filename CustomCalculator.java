import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, result;
    private String operator;
    private boolean isResultDisplayed;

    public CustomCalculator() {
        setTitle("Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

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

        if (input.matches("[0-9]") || input.equals(".")) {
            if (isResultDisplayed) {
                textField.setText("");
                isResultDisplayed = false;
            }
            if (input.equals(".") && textField.getText().contains(".")) return;
            textField.setText(textField.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                operator = input;
                textField.setText("");
            }
        } else if (input.equals("=")) {
            if (!textField.getText().isEmpty() && operator != null) {
                try {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case "+": result = num1 + num2; break;
                        case "-": result = num1 - num2; break;
                        case "*": result = num1 * num2; break;
                        case "/":
                            if (num2 == 0) {
                                textField.setText("Error: /0");
                                isResultDisplayed = true;
                                return;
                            }
                            result = num1 / num2;
                            break;
                    }
                    textField.setText(String.valueOf(result));
                    isResultDisplayed = true;
                    operator = null;
                } catch (NumberFormatException ex) {
                    textField.setText("Error");
                    isResultDisplayed = true;
                }
            }
        } else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
            isResultDisplayed = false;
        }
    }

    public static void main(String[] args) {
        new CustomCalculator();
    }
}
