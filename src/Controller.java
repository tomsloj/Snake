import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class Controller implements ActionListener {
    Board board;

    int speed = 1000;
    int x = 10, y = 10;
    Timer timer;
    boolean gameOver = false;
    char lastKey = 'n';
    MyKeyListener keyListener;
    Controller()
    {
        initGame();

    while (1==1)
        ;
    }

    void initGame()
    {
        board = new Board(x,y);
        keyListener = new MyKeyListener();

        timer = new Timer(speed, this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("tick!\n");
        lastKey = keyListener.getAndResetLastClicked();
        System.out.println(lastKey);
        board.move(lastKey);

    }
}
