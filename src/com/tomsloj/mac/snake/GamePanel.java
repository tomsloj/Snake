package com.tomsloj.mac.snake;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class GamePanel extends JPanel {

    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;

    private final Color DARK_GREEN = new Color(50,205,50);

    private int square;
    private int margin;

    private int x;
    private int y;

    private Information info  = new Information();

    GamePanel(int square, int margin, int x, int y)
    {
        this.square = square;
        this.margin = margin;
        this.x = x;
        this.y = y;
    }


    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        paintFrame(g);

        Deque<Pair<Integer, Integer>> snake = info.getSnake();

        if(info.isAppleChanged())
        {
            paintApple(g, info.getAppleX(), info.getAppleY());
        }

        ArrayList<Pair<Integer, Integer>> walls = info.getWalls();

        for(Pair<Integer, Integer> i: walls)
        {
            paintWall(g, i.getKey(), i.getValue());
        }

        Pair<Integer, Integer> head = snake.getFirst();
        snake.removeFirst();
        Pair<Integer, Integer> second = snake.getFirst();
        snake.addFirst(head);

        paintHead(g, head, second);

        Iterator it = snake.iterator();
        Pair<Integer, Integer> prev;

        Pair<Integer, Integer> current = (Pair<Integer, Integer>) it.next();
        Pair<Integer, Integer> next  = (Pair<Integer, Integer>) it.next();;
        for ( ; it.hasNext();  )
        {
            prev = current;
            current = next;
            next = (Pair<Integer, Integer>) it.next();

            paintSnake(g, prev, current, next);

        }

        Pair<Integer, Integer> tail = snake.getLast();
        snake.removeLast();
        second = snake.getLast();
        snake.addLast(tail);

        paintTail(g, tail, second);
    }

    private void paintHead(Graphics g, Pair<Integer, Integer> head, Pair<Integer, Integer> second)
    {
        paintSnake(g, head.getKey(), head.getValue());

        if(  head.getValue() == second.getValue() )
        {
            paintLine(g, head.getKey(), head.getValue(), HORIZONTAL);
            paintLine(g, head.getKey(), head.getValue() + 1, HORIZONTAL);

            if( head.getKey() == (second.getKey()+1)%x )
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

            if( head.getValue() == (second.getValue()+1)%y )
            {
                paintLine(g, head.getKey(), head.getValue() + 1, HORIZONTAL);
            }
            else
            {
                paintLine(g, head.getKey(), head.getValue(), HORIZONTAL);
            }
        }
    }

    private void paintSnake(Graphics g, Pair<Integer, Integer> prev, Pair<Integer, Integer> current, Pair<Integer, Integer> next)
    {
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
        else
        if(  ((prev.getKey()+1)%x == current.getKey() && current.getValue() == (next.getValue() + 1)%y) ||
                ((next.getKey()+1)%x == current.getKey() && current.getValue() == (prev.getValue() + 1)%y))
        {
            paintLine(g, current.getKey() + 1, current.getValue(), VERTICAL);
            paintLine(g, current.getKey(), current.getValue() + 1, HORIZONTAL);
        }
        else
        if(  (prev.getKey() == (current.getKey()+1)%x && current.getValue() == (next.getValue() + 1)%y) ||
                (next.getKey() == (current.getKey()+1)%x && current.getValue() == (prev.getValue() + 1)%y))
        {
            paintLine(g, current.getKey(), current.getValue(), VERTICAL);
            paintLine(g, current.getKey(), current.getValue() + 1, HORIZONTAL);
        }
        else
        if(  (prev.getKey() == (current.getKey()+1)%x && (current.getValue()+1)%y == next.getValue()) ||
                (next.getKey() == (current.getKey()+1)%x && (current.getValue()+1)%y == prev.getValue()))
        {
            paintLine(g, current.getKey(), current.getValue(), VERTICAL);
            paintLine(g, current.getKey(), current.getValue(), HORIZONTAL);
        }
        else
        {
            paintLine(g, current.getKey()+1, current.getValue(), VERTICAL);
            paintLine(g, current.getKey(), current.getValue(), HORIZONTAL);
        }
    }

    private void paintTail(Graphics g, Pair<Integer, Integer> tail, Pair<Integer, Integer> second)
    {
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

    private void paintApple(Graphics g, int i, int j)
    {
        g.setColor(Color.RED);
        g.fillRoundRect(margin + i * square, margin + j * square ,square,square , square/4*3, square/4*3);
        Color brown = new Color(122, 55, 0);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(brown);
        g2d.drawLine(margin + i * square + square/2, margin + j * square, margin + i * square + square/2, margin + j * square + square/6);
    }

    private void paintWall(Graphics g, int i, int j)
    {
        g.setColor(Color.WHITE);
        g.fillRect(margin + i * square, margin + j * square ,square,square);
    }

    private void paintLine(Graphics g, int i, int j, int orientation)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(DARK_GREEN);
        if( orientation == HORIZONTAL)
            g2d.drawLine( margin + i * square, margin + j * square, margin + (i + 1)*square, margin + j * square );
        else
            g2d.drawLine( margin + i * square, margin + j * square, margin + i * square, margin + (j + 1) * square );
    }

    public void setInfo(Information info)
    {
        this.info = info;
    }
}
