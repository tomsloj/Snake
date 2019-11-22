import javax.swing.*;

public class View extends JFrame {

    final int SQUARE_SIZE = 20;
    final int MARGIN = 20;
    JLabel label;

    int x, y;

    View(int x, int y)
    {
        super("newKeyListener");
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(new MyKeyListener());
        add(new MyPanel(SQUARE_SIZE, MARGIN));

        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
        //pack();
        setVisible(true);
    }

}
