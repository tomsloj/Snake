import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;

public class GamePanel extends JPanel {

    int square;
    int margin;

    int x;
    int y;

    Information info  = new Information();


    boolean paintWalls;


    GamePanel(int square, int margin, int x, int y)
    {
        this.square = square;
        this.margin = margin;
        this.x = x;
        this.y = y;

        paintWalls = true;

        //board = new int[x][y];

    }


    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);


        paintFrame(g);

        Deque<Pair<Integer, Integer>> snake = info.getSnake();

        for(Pair<Integer, Integer> i: snake)
        {
            paintSnake(g, i.getKey(), i.getValue());
        }

        ArrayList<Pair<Integer, Integer>> walls = info.getWalls();

        for(Pair<Integer, Integer> i: walls)
        {
            paintWall(g, i.getKey(), i.getValue());
        }

        if(info.isAppleChanged())
        {
            paintPoint(g, info.getAppleX(), info.getAppleY());
        }
        /*
        if( paintWalls )
        {
            for (int i = 0; i < x; ++i)
                for (int j = 0; j < y; ++j)
                {
                    if (board[i][j] == 0)
                        paintBackground(g, i, j);
                    else if (board[i][j] == 1)
                        paintSnake(g, i, j);
                    else if (board[i][j] == 2)
                        paintPoint(g, i, j);
                }

        }
         */
    }

    private void paintFrame(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(margin, margin, x * square, y * square);
    }

    private void paintSnake(Graphics g, int i, int j)
    {
        g.setColor(Color.GREEN);
        g.fillRect(margin + i * square, margin + j * square ,square,square);
    }

    private void paintPoint(Graphics g, int i, int j)
    {
        g.setColor(Color.RED);
        g.fillRect(margin + i * square, margin + j * square ,square,square);
    }

    private void paintWall(Graphics g, int i, int j)
    {
        g.setColor(Color.WHITE);
        g.fillRect(margin + i * square, margin + j * square ,square,square);
    }


    public void setInfo(Information info)
    {
        this.info = info;
    }
}
