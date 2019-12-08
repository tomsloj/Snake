import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {
    Board board;
    View view;

    int speed = 350;
    int x = 22, y = 22;
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

        view = new View(x, y);
        view.addKeyListener(keyListener);
        board = new Board(x,y);
        board.attach(view);


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
    }
}
