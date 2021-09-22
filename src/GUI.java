import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private Domain domain = new Domain();
    private ArrayList<Character> password = new ArrayList<Character>();
    private int length = 0;
    private JLabel passLab = new JLabel("Password length");
    private JTextField passLength = new JTextField(7);

    private JPanel requirements = new JPanel();
    private JCheckBox numbers = new JCheckBox();
    private JCheckBox smallLetters = new JCheckBox();
    private JCheckBox capitalLetters = new JCheckBox();
    private JCheckBox symbols = new JCheckBox();

    private JLabel numbersLab = new JLabel("1, 2, 3, ..., 9");
    private JLabel smallLettersLab = new JLabel("a, b, c, ..., z");
    private JLabel capitalLettersLab = new JLabel("A, B, C,..., Z");
    private JLabel symnolsLab = new JLabel("!, @, #, etc.");

    private JButton generate = new JButton("Generate a password");
    private JButton show = new JButton("Show password");
    private JButton reset = new JButton("Reset password");
    private JTextArea output = new JTextArea(3,40);

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        GUI window = new GUI();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * A constructor
     */
    public GUI () {
        super("Password Generator");
        setLayout(new FlowLayout(FlowLayout.LEFT, 3, 15));
        add(passLab);
        add(passLength);
        passLength.setEditable(true);
        requirements.setPreferredSize(new Dimension(100,110));
        requirements.add(numbers);
        requirements.add(numbersLab);
        requirements.add(smallLetters);
        requirements.add(smallLettersLab);
        requirements.add(capitalLetters);
        requirements.add(capitalLettersLab);
        requirements.add(symbols);
        requirements.add(symnolsLab);
        add(requirements);

        generate.addActionListener(this);
        add(generate);
        show.addActionListener(this);
        add(show);
        reset.addActionListener(this);
        add(reset);
        add(output);
        setSize(429,300);
        setVisible(true);
        setBackground(Color.lightGray);
    }

    /**
     * Determines what type of acton to perform depending on which button is clicked
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        String result = "";
        if(event.getSource() == generate) {
            if (!password.isEmpty()) {
                result = "Password already exists. If you wish to change it, click the reset button";
            } else {
                //tries to convert the passed text by the user  from the text field to an int, if it can't - throws an exception
                try {
                    length = Integer.parseInt(passLength.getText());
                    if (length < 16 || length > 25) {
                        result = "The password must be 16-25 characters long";
                    } else {
                        //Prompts the user to sellect some of the characters to be included in the domain
                        if (!(numbers.isSelected() || smallLetters.isSelected() || capitalLetters.isSelected() || symbols.isSelected())) {
                                result = "Please select which symbols you would like your password to contain";

                        //Adds the characters from the selected check boxes to the domain
                        } else {
                            if (numbers.isSelected()) {
                                domain.addCharacters(domain.getNums());
                            }
                            if (smallLetters.isSelected()) {
                                domain.addCharacters(domain.getSmallLet());
                            }
                            if (capitalLetters.isSelected()) {
                                domain.addCharacters(domain.getCapitalLet());
                            }
                            if (symbols.isSelected()) {
                                domain.addCharacters(domain.getChars());
                            }
                            domain.constructPassword(password, length);
                            domain.clearDomain();

                            result = "Password was just created.";
                        }
                    }
                } catch (Exception e) {
                    result = "Please enter a number for length";
                }
            }
        }

        // if the show button is clicked
        if (event.getSource() == show) {
            if(password.isEmpty()) {
                result = "A password does not exist yet";
            } else {
                String pass = "";
                for (int i = 0; i < password.size(); i ++) {
                    pass += password.get(i);    //Shows the current password
                }
                result = "Your password is: " + pass;
            }
        }

        // If the reset button is clicked
        if(event.getSource() == reset) {
            if(password.isEmpty()) {
                result = "A password haven't been created yet.";
            } else {
                password.clear();   // resets the current password
                result = "Password was deleted. You can create a new one.";
            }
        }
        output.setText(result);

        blank();
    }

    /**
     * Clears the text from some of the components
     */
    public void blank() {
        numbers.setSelected(false);
        smallLetters.setSelected(false);
        capitalLetters.setSelected(false);
        symbols.setSelected(false);
        passLength.setText("");
    }
}
