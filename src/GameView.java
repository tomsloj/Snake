import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    final int SQUARE_SIZE = 20;
    final int MARGIN = 25;
    JLabel label;
    MyPanel p;

    int score = 0;

    GameView(int x, int y)
    {
        super("newKeyListener");
        p = new MyPanel(SQUARE_SIZE, MARGIN, x, y);

        label = new JLabel("Wynik: 0");
        label.setForeground(Color.GREEN);

        p.setBackground(Color.BLUE);
        p.add(label);
        p.revalidate();

        add(p, BorderLayout.CENTER);
        addKeyListener(new MyKeyListener());

        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
        //pack();
        setVisible(true);
    }

    public void update(Information info)
    {
        p.setInfo(info);
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

    public void setScore(int score)
    {
        this.score = score;
        label.setText("Wynik: " + score);
    }
}
