import javafx.beans.InvalidationListener;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import javafx.util.Pair;

import java.util.*;

public class Board {
    private int[][] table;
    /*
    0 - empty field
    1 - snake
    2 - point
    3 - wall
     */
    ArrayList<Pair<Integer, Integer> >emptyPoints = new ArrayList<>();

    private List<View> observers = new ArrayList<>();

    char currentDirection = 'u';

    private int headX;
    private int headY;
    private int tailX;
    private int tailY;
    private int pointX;
    private int pointY;

    Random rand;

    Board (int x, int y)
    {
        rand = new Random();

        table = new int[x][y];
        for( int i = 0; i < x; ++i )
            for( int j = 0; j < y; ++j )
            {
                table[i][j] = 0;
                emptyPoints.add(new Pair(i,j));
            }

        setHead(x/2, y/2);
        setTail(x/2, y/2);

        emptyPoints.remove(new Pair(x/2, y/2));

        randPoint();
        notifyAllObservers();

    }

    void setHead(int x, int y)
    {
        headX = x;
        headY = y;
        table[x][y] = 1;
    }

    void setTail(int x, int y)
    {
        tailX = x;
        tailY = y;
    }

    int getField(int x, int y)
    {
        return table[x][y];
    }
    int[][] getTable()
    {
        return table;
    }

    boolean isGameOver()
    {

        return false;
    }

    void move(char lastKey)
    {
        System.out.println("MOVE");
        if( lastKey == 'n' )
        {
            lastKey = currentDirection;
        }
        //up
        if( lastKey == 'u' )
        {
            if( currentDirection == 'd' )
            {
                setHead(headX, ((headY + 1)%10+10)%10);
            }
            else
            {
                setHead(headX, ((headY - 1)%10+10)%10);
                currentDirection = 'u';
            }
            notifyAllObservers();
        }
        //down
        else
        if( lastKey == 'd' )
        {
            if( currentDirection == 'u' )
            {
                setHead(headX, ((headY - 1)%10+10)%10);
            }
            else
            {
                setHead(headX, ((headY + 1)%10+10)%10);
                currentDirection = 'd';
            }
            notifyAllObservers();
        }
        //left
        else
        if( lastKey == 'l' )
        {
            if( currentDirection == 'r' )
            {
                setHead(((headX + 1)%10+10)%10, headY);
            }
            else
            {
                setHead(((headX - 1)%10+10)%10, headY);
                currentDirection = 'l';
            }
            notifyAllObservers();
        }
        //right
        else
        if( lastKey == 'r')
        {
            if( currentDirection == 'l' )
            {
                setHead(((headX - 1)%10+10)%10, headY);
            }
            else
            {
                setHead(((headX + 1)%10+10)%10, headY);
                currentDirection = 'r';
            }
            notifyAllObservers();
        }
    }

    void randPoint()
    {
        int number = rand.nextInt(emptyPoints.size());
        Pair<Integer, Integer> pair = emptyPoints.get(number);
        table[pair.getKey()][pair.getValue()] = 2;
    }

    public void attach(View observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        System.out.println("NOTIFY");
        for (View observer : observers) {
            observer.update(table);
        }
    }

}
