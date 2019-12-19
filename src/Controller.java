import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {
    Board board;
    GameView gameView;

    int speed = 500;
    int x = 6, y = 6;
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

        gameView = new GameView(x, y);
        gameView.addKeyListener(keyListener);
        board = new Board(x,y);
        board.attach(gameView);


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
        gameView.setScore(board.score);
        if(board.isGameOver())
            gameOver();
    }

    public void gameOver()
    {
        System.out.println(board.score);
        timer.stop();
    }
}
