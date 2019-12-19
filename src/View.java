import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class View extends JFrame {

    private final int SQUARE_SIZE = 20;
    private final int MARGIN = 25;

    private Controller controller;
    private JLabel label;
    private GamePanel gamePanel;
    private JPanel menuPanel;

    private int x;
    private int y;

    private int score = 0;

    View(int x, int y, Controller controller, KeyListener keyListener)
    {
        super("newKeyListener");

        setX(x);
        setY(y);

        setController(controller);

        menuPanel = new MenuPanel(this);
        getContentPane().add(menuPanel);
        //gamePanel = new MyPanel(SQUARE_SIZE, MARGIN, x, y);

        //gamePanel.setBackground(Color.BLUE);
        //gamePanel.add(label);
        //gamePanel.revalidate();

       //add(gamePanel, BorderLayout.CENTER);

        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
        //pack();
        setVisible(true);

    }

    public void update(Information info)
    {
        gamePanel.setInfo(info);
        gamePanel.repaint();
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

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void startGame()
    {
        gamePanel = new GamePanel(SQUARE_SIZE, MARGIN, x, y);
        gamePanel.setBackground(Color.BLUE);

        label = new JLabel("Wynik: 0");
        label.setForeground(Color.GREEN);

        gamePanel.add(label);
        //gamePanel.addKeyListener(new MyKeyListener());
        //gamePanel.revalidate();

        getContentPane().remove(menuPanel);
        getContentPane().add(gamePanel);
        validate();
        /*
        getContentPane().removeAll();

        getContentPane().add(gamePanel, BorderLayout.CENTER);
        //validate();
        setVisible(true);

         */



        controller.startGame();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
