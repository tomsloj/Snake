import java.util.List;
import javafx.util.Pair;

import java.util.*;


public class Board {

    //game modes
    final int EMPTY = 0;
    final int FRAME = 1;
    final int STRIPS = 2;

    private int mode = 0;

    private boolean isGameOver = false;

    final int EMPTY_FIELD = 0;
    final int SNAKE_FIELD = 1;
    final int APPLE_FIELD = 2;
    final int WALL_FIELD = 3;

    private int[][] table;


    ArrayList<Pair<Integer, Integer> >emptyPoints = new ArrayList<>();

    ArrayList<Pair<Integer, Integer> >walls = new ArrayList<>();
    Deque<Pair<Integer, Integer>> snake = new LinkedList<>();

    private List<View> observers = new ArrayList<>();

    private char currentDirection = 'u';

    private int headX;
    private int headY;
    private int appleX;
    private int appleY;

    private int x, y;

    private int score = 0;

    private Random rand;

    private Information info = new Information();

    Board (int x, int y)
    {
        changeSize(x, y);

    }

    public void changeSize(int x, int y)
    {
        for(Pair<Integer, Integer> i : snake)
        {
            emptyPoints.add(i);
            table[i.getKey()][i.getValue()] = EMPTY_FIELD;
        }
        snake.clear();

        this.x = x;
        this.y = y;
        rand = new Random();

        table = new int[x][y];
        for( int i = 0; i < x; ++i )
            for( int j = 0; j < y; ++j )
            {
                table[i][j] = 0;
                emptyPoints.add(new Pair<>(i,j));
            }

        setHead(x/2, y/2 - 1);
        setHead(x/2, y/2);
        table[headX][headY] = SNAKE_FIELD;

        emptyPoints.remove(new Pair<>(x/2, y/2 - 1));
        emptyPoints.remove(new Pair<>(x/2, y/2));


        info.setWalls(walls);

        randApple();
        notifyAllObservers();
    }

    void setMode(int mode)
    {
        this.mode = mode;

        clearWalls();

        if( mode == FRAME )
        {
            for( int i = 0; i < x; ++i )
            {
                walls.add(new Pair<>(i, 0));
                table[i][0] = WALL_FIELD;
                emptyPoints.remove(new Pair<>(i, 0));
            }

            for( int i = 0; i < x; ++i )
            {
                walls.add(new Pair<>(i, y-1));
                table[i][y-1] = WALL_FIELD;
                emptyPoints.remove(new Pair<>(i, y-1));
            }

            for( int i = 1; i < y-1; ++i )
            {
                walls.add(new Pair<>(0, i));
                table[0][i] = WALL_FIELD;
                emptyPoints.remove(new Pair<>(0, i));
            }

            for( int i = 1; i < y-1; ++i )
            {
                walls.add(new Pair<>(x-1, i));
                table[x-1][i] = WALL_FIELD;
                emptyPoints.remove(new Pair<>(x-1, i));
            }
        }
        else
        if( mode == STRIPS )
        {
            for( int i = 1; i < 3; ++i )
            {
                for( int j = 4; j < y - 4; ++j )
                {
                    walls.add(new Pair<>(i * (x/3), j));
                    table[i * (x/3)][j] = WALL_FIELD;
                    emptyPoints.remove(new Pair<>(i * (x/3), j));
                }
            }
        }
    }

    void setHead(int x, int y)
    {
        headX = x;
        headY = y;
        //table[x][y] = 1;
        snake.addFirst(new Pair<>(x,y));
    }

    boolean isGameOver()
    {
        return isGameOver;
    }

    void move(char lastKey)
    {
        if( lastKey == 'n' )
        {
            lastKey = currentDirection;
        }
        //up
        if( lastKey == 'u' )
        {
            if( currentDirection == 'd' )
            {
                setHead(headX, ((headY + 1)%y+y)%y);
            }
            else
            {
                setHead(headX, ((headY - 1)%y+y)%y);
                currentDirection = 'u';
            }
        }
        //down
        else
        if( lastKey == 'd' )
        {
            if( currentDirection == 'u' )
            {
                setHead(headX, ((headY - 1)%y+y)%y);
            }
            else
            {
                setHead(headX, ((headY + 1)%y+y)%y);
                currentDirection = 'd';
            }
        }
        //left
        else
        if( lastKey == 'l' )
        {
            if( currentDirection == 'r' )
            {
                setHead(((headX + 1)%x+x)%x, headY);
            }
            else
            {
                setHead(((headX - 1)%x+x)%x, headY);
                currentDirection = 'l';
            }
        }
        //right
        else
        if( lastKey == 'r')
        {
            if( currentDirection == 'l' )
            {
                setHead(((headX - 1)%x+x)%x, headY);
            }
            else
            {
                setHead(((headX + 1)%x+x)%x, headY);
                currentDirection = 'r';
            }
        }

        Pair<Integer, Integer> lastTail = snake.getLast();

        if( headX == appleX && headY == appleY)
        {
            score ++;
            randApple();
        }
        else
        {
            emptyPoints.add(snake.getLast());
            table[snake.getLast().getKey()][snake.getLast().getValue()] = 0;
            snake.removeLast();
        }
        emptyPoints.remove(new Pair(headX, headY));

        if( table[headX][headY] == SNAKE_FIELD || table[headX][headY] == WALL_FIELD )
        {
            isGameOver = true;
            table[lastTail.getKey()][lastTail.getValue()] = SNAKE_FIELD;
            System.out.println("GAME OVER");
        }
        else
            table[headX][headY] = SNAKE_FIELD;

        notifyAllObservers();
    }

    void randApple()
    {
        if(emptyPoints.size() == 0)
        {
            isGameOver = true;
            return;
        }
        int number = rand.nextInt(emptyPoints.size());
        Pair<Integer, Integer> pair = emptyPoints.get(number);
        table[pair.getKey()][pair.getValue()] = APPLE_FIELD;
        emptyPoints.remove(pair);
        appleX = pair.getKey();
        appleY = pair.getValue();

        info.setApple(appleX, appleY);
    }

    public void attach(View observer){
        info.setSnake(snake);
        observers.add(observer);
        observer.update(info);
    }

    public void notifyAllObservers(){
        info.setSnake(snake);

        for (View observer : observers) {
            observer.update(info);
        }
    }
    private void writeSnake()
    {
        for (Pair<Integer, Integer> integerIntegerPair : snake)
            System.out.println("\t" + integerIntegerPair);
    }

    private void clearWalls()
    {
        for(Pair<Integer, Integer> i : walls)
        {
            emptyPoints.add(i);
            table[i.getKey()][i.getValue()] = EMPTY_FIELD;
        }
        walls.clear();
    }

    public int getScore()
    {
        return score;
    }

}
