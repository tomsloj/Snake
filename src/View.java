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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(Information info)
    {
        gamePanel.setInfo(info);
        gamePanel.repaint();
        System.out.println("update");
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
        invalidate();
        validate();

        setVisible(false);
        setVisible(true);

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

    public void setSpeed(int speed)
    {
        controller.setSpeed(speed);
    }

    public void setMode( int mode )
    {
        controller.setMode( mode );
    }

    public void setSize(int size)
    {
        controller.setSize(size);
    }

    public int getSpeedLevel()
    {
        return controller.getSpeedLevel();
    }

    public int getMode()
    {
        return controller.getMode();
    }

    public int getBoardSize()
    {
        System.out.println(x);
        if( x == 16 )
            return 0;
        if( x == 22)
            return 1;
        if( x == 32 )
            return 2;

        return 0;
    }

    public void updateSize()
    {
        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
    }
}
