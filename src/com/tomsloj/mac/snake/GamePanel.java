package com.tomsloj.mac.snake;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/**
 * panel zawierający grę
 */
public class GamePanel extends JPanel {

    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;

    /**
     * kolor obwódki węża
     */
    private final Color DARK_GREEN = new Color(50,205,50);
    /**
     * kolor ścian
     */
    private final Color WALL_COLOR = new Color(0,0, 190);
    /**
     * rozmiar jednego pola planszy
     */
    private int square;
    /**
     * wielkość marginesów
     */
    private int margin;

    /**
     * rodzaj planszy
     */
    int mode;

    /**
     * szerokość planszy
     */
    private int x;
    /**
     * wysokość planszy
     */
    private int y;

    /**
     * informacja o modyfikacjach planszy
     */
    private Information info  = new Information();

    GamePanel(int square, int margin, int x, int y, int mode)
    {
        this.square = square;
        this.margin = margin;
        this.x = x;
        this.y = y;
        this.mode = mode;
    }

    /**
     * odmalowanie planszy
     */
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if( mode != 1)
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

    /**
     * metoda rysująca głowę węża
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param head umiejscowienie głowy węża
     * @param second umiejscowienie drugiego elementu węża
     */
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

    /**
     * metoda rysująca element węża
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param prev poprzedni element węża
     * @param current element węża który rysujemy
     * @param next następny element węża
     */
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

    /**
     * metoda rysująca ogon węża
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param tail umiejscowienie ogona (ostatniego elementu) węża
     * @param second umiejscowienie przedostatniego elementu węża
     */
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

    /**
     * rysuje ramkę dookoła planszy po której może poruszać się wąż
     * @param g obiekt umożliwiający rysowanie po panelu
     */
    private void paintFrame(Graphics g)
    {
        g.setColor(new Color(135,206,250));
        g.drawRect(margin, margin, x * square, y * square);
    }

    /**
     * rysuje kwadrat będący elementem węża
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param i numer kolumny w której ma być namalowany element
     * @param j numer wiersza w którym ma być namalowany element
     */
    private void paintSnake(Graphics g, int i, int j)
    {
        g.setColor(Color.GREEN);
        g.fillRect(margin + i * square, margin + j * square ,square,square);
    }

    /**
     * rysuje jabłko
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param i numer kolumny w której ma być namalowane jabłko
     * @param j numer wiersza w którym ma być namalowane jabłko
     */
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

    /**
     * rysuje element ściany
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param i numer kolumny w której ma być namalowana ściana
     * @param j numer wiersza w którym ma być namalowana ściana
     */
    private void paintWall(Graphics g, int i, int j)
    {
        g.setColor(WALL_COLOR);
        g.fillRect(margin + i * square, margin + j * square,square,square);
    }

    /**
     * rysuje linię która jest obwódką węża
     * @param g obiekt umożliwiający rysowanie po panelu
     * @param i numer kolumny w której ma być namalowana linia
     * @param j numer wiersza w którym ma być namalowana linia
     * @param orientation orientacja linii 0 - pozioma, 1 - pionowa
     */
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
