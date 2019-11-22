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


    }

    void setHead(int x, int y)
    {
        headX = x;
        headY = y;
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

    boolean isGameOver()
    {

        return false;
    }

    void move(char lastKey)
    {
        System.out.println("MOVE");
        if( lastKey == 'n' )
        {

        }
        //up
        if( lastKey == 'u' )
        {
            MyPanel.c = Color.GREEN;
            notifyAllObservers();
        }
        //down
        else
        if( lastKey == 'd' )
        {
            MyPanel.c = Color.RED;
            notifyAllObservers();
        }
        //left
        else
        if( lastKey == 'l' )
        {

        }
        //right
        else
        if( lastKey == 'r')
        {

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
            observer.update();
        }
    }

}
