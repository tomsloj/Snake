import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame {

    final int SQUARE_SIZE = 20;
    final int MARGIN = 20;
    JLabel label;
    MyPanel p;

    int x, y;

    View(int x, int y)
    {
        super("newKeyListener");
        p = new MyPanel(SQUARE_SIZE, MARGIN, x, y);
        //label = new JLabel("Key Listener!");
        //p.add(label);
        p.revalidate();
        add(p);
        addKeyListener(new MyKeyListener());

        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
        //pack();
        setVisible(true);
    }

    public void update(int[][] table)
    {
        p.setBoard(table);
        p.repaint();
        System.out.println("update");
        /*
        if(MyPanel.c== Color.RED)
        System.out.println("RED");
        if(MyPanel.c== Color.BLUE)
            System.out.println("BLUE");
        if(MyPanel.c== Color.GREEN)
            System.out.println("GREEN");
         */
    }
}
