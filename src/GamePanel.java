import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class GamePanel extends JPanel {

    final int HORIZONTAL = 0;
    final int VERTICAL = 1;

    Color darkGreen = new Color(50,205,50);
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

        /*
        for(Pair<Integer, Integer> i: snake)
        {
            paintSnake(g, i.getKey(), i.getValue());
        }
         */

        Pair<Integer, Integer> head = snake.getFirst();
        snake.removeFirst();
        Pair<Integer, Integer> second = snake.getFirst();
        snake.addFirst(head);

        paintSnake(g, head.getKey(), head.getValue());

        if(  head.getValue() == second.getValue() )
        {
            paintLine(g, head.getKey(), head.getValue(), HORIZONTAL);
            paintLine(g, head.getKey(), head.getValue() + 1, HORIZONTAL);

            if( head.getKey() > second.getKey() )
            {
                paintLine(g, head.getKey() + 1, head.getValue(), VERTICAL);
            }
            else
            {
                paintLine(g, head.getKey(), head.getValue() , VERTICAL);
            }
        }
        else
        if( head.getKey() == second.getKey() )
        {
            paintLine(g, head.getKey(), head.getValue(), VERTICAL);
            paintLine(g, head.getKey() + 1, head.getValue(), VERTICAL);

            if( head.getValue() > second.getValue() )
            {
                paintLine(g, head.getKey(), head.getValue() + 1, HORIZONTAL);
            }
            else
            {
                paintLine(g, head.getKey(), head.getValue(), HORIZONTAL);
            }
        }

        Iterator it = snake.iterator();
        Pair<Integer, Integer> prev;

        Pair<Integer, Integer> current = (Pair<Integer, Integer>) it.next();
        Pair<Integer, Integer> next  = (Pair<Integer, Integer>) it.next();;
        for ( ; it.hasNext();  )
        {
            prev = current;
            current = next;
            next = (Pair<Integer, Integer>) it.next();

            paintSnake( g, current.getKey(), current.getValue() );

            if(  prev.getValue() == current.getValue() && current.getValue() == next.getValue() )
            {
                paintLine(g, current.getKey(), current.getValue(), HORIZONTAL);
                paintLine(g, current.getKey(), current.getValue() + 1, HORIZONTAL);
            }
            else
            if( prev.getKey() == current.getKey() &&  current.getKey() == next.getKey() )
            {
                paintLine(g, current.getKey(), current.getValue(), VERTICAL);
                paintLine(g, current.getKey() + 1, current.getValue(), VERTICAL);
            }
            /*
            else
            if(  prev.getValue() == current.getValue() && current.getValue() > next.getValue() )
            {
                paintLine(g, current.getKey(), current.getValue(), HORIZONTAL);
                paintLine(g, current.getKey(), current.getValue() + 1, HORIZONTAL);
            }

             */
        }

        Pair<Integer, Integer> tail = snake.getLast();
        snake.removeLast();
        second = snake.getLast();
        snake.addLast(tail);

        paintSnake(g, tail.getKey(), tail.getValue());

        if(  tail.getValue() == second.getValue() )
        {
            paintLine(g, tail.getKey(), tail.getValue(), HORIZONTAL);
            paintLine(g, tail.getKey(), tail.getValue() + 1, HORIZONTAL);

            if( tail.getKey() > second.getKey() )
            {
                paintLine(g, tail.getKey() + 1, tail.getValue(), VERTICAL);
            }
            else
            {
                paintLine(g, tail.getKey(), tail.getValue() , VERTICAL);
            }
        }
        else
        if( tail.getKey() == second.getKey() )
        {
            paintLine(g, tail.getKey(), tail.getValue(), VERTICAL);
            paintLine(g, tail.getKey() + 1, tail.getValue(), VERTICAL);

            if( tail.getValue() > second.getValue() )
            {
                paintLine(g, tail.getKey(), tail.getValue() + 1, HORIZONTAL);
            }
            else
            {
                paintLine(g, tail.getKey(), tail.getValue(), HORIZONTAL);
            }
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

    private void paintLine(Graphics g, int i, int j, int orientation)
    {
        g.setColor(darkGreen);
        if( orientation == HORIZONTAL)
            g.drawLine( margin + i * square, margin + j * square, margin + (i + 1)*square, margin + j * square );
        else
            g.drawLine( margin + i * square, margin + j * square, margin + i * square, margin + (j + 1) * square );
    }


    public void setInfo(Information info)
    {
        this.info = info;
    }
}
