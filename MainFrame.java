import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextArea textArea;
    private JButton btn;
    public MainFrame(){
        super("Hello World");
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        btn = new JButton("Click me!");
        // add them to main frame use layout manager.
        // actually it is adding it to a content pane(it is a panel Jframe contains)
        add(textArea, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
