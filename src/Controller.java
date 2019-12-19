import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {
    Board board;
    View view;

    int speed = 500;
    int x = 10, y = 10;
    Timer timer;
    boolean gameOver = false;
    char lastKey = 'n';
    MyKeyListener keyListener = new MyKeyListener();

    Controller()
    {
        initGame();
    }

    void initGame()
    {

        view = new View(x, y, this, keyListener);
        view.addKeyListener(keyListener);
    }

    void startGame()
    {
        System.out.println("START GAME");
        board = new Board(x,y);
        board.attach(view);
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
        view.setScore(board.score);
        if(board.isGameOver())
            gameOver();
    }

    public void gameOver()
    {
        System.out.println(board.score);
        timer.stop();
        gameOver = true;
    }
}
