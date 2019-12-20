import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    final int NUMBER_OF_LEVELS = 5;
    final int SPEED_UNIT = 150;

    //game modes
    final int EMPTY = 0;
    final int FRAME = 1;
    final int STRIPS = 2;

    Board board;
    View view;

    int speed = 450;
    int x = 16, y = 12;
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

    public void setSpeed(int speed)
    {
        this.speed = (NUMBER_OF_LEVELS - speed + 1) * SPEED_UNIT;
    }

    public int getSpeedLevel()
    {
        System.out.println(NUMBER_OF_LEVELS + 1 - ( speed / SPEED_UNIT ));
        return  NUMBER_OF_LEVELS + 1 - ( speed / SPEED_UNIT );
    }

    public void setMode( int mode )
    {
        board.setMode( mode );
    }

    void setSize(int sizeLevel)
    {
        if( sizeLevel == 1 )
        {
            this.x = 16;
            this.y = 12;
        }
        else
        if( sizeLevel == 2 )
        {
            this.x = 22;
            this.y = 16;
        }
        else
        if( sizeLevel == 3 )
        {
            this.x = 32;
            this.y = 24;
        }

        view.setX(x);
        view.setY(y);

        view.repaint();

    }

}
