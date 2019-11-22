import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private int[][] table;
    /*
    0 - empty field
    1 - snake
    2 - point
    3 - wall
     */
    ArrayList<Pair<Integer, Integer> >emptyPoints = new ArrayList<>();
    private int headX;
    private int headY;
    private int tailX;
    private int tailY;
    private int pointX;
    private int pointY;

    Board (int x, int y)
    {
        table = new int[x][y];
        for( int i = 0; i < x; ++i )
            for( int j = 0; j < y; ++j )
            {
                table[i][j] = 0;
                emptyPoints.add(new Pair(i,j));
            }

        //setHead(x/2, y/2);


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

    }

}
