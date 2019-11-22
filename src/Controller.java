import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {
    Board board;
    View view;

    int speed = 1000;
    int x = 10, y = 10;
    Timer timer;
    boolean gameOver = false;
    char lastKey = 'n';
    MyKeyListener keyListener = new MyKeyListener();

    Controller()
    {
        initGame();

        while (!board.isGameOver())
            ;
    }

    void initGame()
    {
        board = new Board(x,y);
        view = new View(x, y);
        view.addKeyListener(keyListener);



        timer = new Timer(speed, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        System.out.println("tick!");

        lastKey = keyListener.getAndResetLastClicked();
        System.out.println(lastKey);
        board.move(lastKey);

    }
}
