package com.tomsloj.mac.snake;

import java.util.List;
import javafx.util.Pair;

import java.util.*;

/**
 * klasa będąca modelem programu wzorca MVC
 */
public class Board {

    //game modes
    private final int EMPTY = 0;
    private final int FRAME = 1;
    private final int STRIPS = 2;

    private int mode = 0;

    private boolean isGameOver = false;

    private final int EMPTY_FIELD = 0;
    private final int SNAKE_FIELD = 1;
    private final int APPLE_FIELD = 2;
    private final int WALL_FIELD = 3;

    /**
     * maksymalna szerokość planszy
     */
    private final int MAX_X = 50;
    /**
     * maksymalna wysokość planszy
     */
    private final int MAX_Y = 40;

    /**
     * tablica przechowująca pola planszy
     */
    private int[][] table = new int[MAX_X][MAX_Y];

    /**
     * lista przechowująca puste pola na planszy
     */
    private ArrayList<Pair<Integer, Integer> >emptyPoints = new ArrayList<>();
    /**
     * przechowuje miejsca gdzie znajduje się ściana
     */
    private ArrayList<Pair<Integer, Integer> >walls = new ArrayList<>();
    /**
     * przechowuje miejsca gdzie znajduje się wąż
     */
    private Deque<Pair<Integer, Integer>> snake = new LinkedList<>();

    private List<View> observers = new ArrayList<>();

    /**
     * aktualny kierunek w którym przesówa się wąż
     * u - góra, d - dół, l - lewo, r - prawo
     */
    private char currentDirection = 'u';

    private int headX;
    private int headY;
    private int appleX;
    private int appleY;

    /**
     * rozmiar planszy
     */
    private int x, y;

    /**
     * liczba zdobytych punktów
     */
    private int score = 0;

    private Random rand;

    /**
     * zawiera informacje potrzebne do zaktualizowania widoku planszy
     */
    private Information info = new Information();

    Board ()
    {
        x = 16;
        y = 12;
    }

    /**
     * metoda inicjująca stan planszy na wartości rozpoczynające grę
     */
    public void startGame()
    {
        rand = new Random();
        isGameOver = false;
        currentDirection = 'u';
        score = 0;
        changeSize();
        changeMod();
        randApple();
        notifyAllObservers();
    }

    /**
     * ustawia rozmiar planszy
     * @param x szerokość planszy
     * @param y wysokość planszy
     */
    public void setSize(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * dostosowuje wielkość planszy i ustawia węża na środku
     */
    public void changeSize()
    {
        emptyPoints.clear();
        snake.clear();

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
    }

    /**
     * ustawia rodzaj planszy
     * @param mode rodzaj planszy: 0 - pusta, 1 - z ramką, 2 - z liniami
     */
    void setMode(int mode)
    {
        this.mode = mode;
    }

    /**
     * ustawia ściany zgodne z ustawionym rodzajem planszy
     */
    void changeMod()
    {
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
                for( int j = 3; j < y - 3; ++j )
                {
                    walls.add(new Pair<>(i * (x/3), j));
                    table[i * (x/3)][j] = WALL_FIELD;
                    emptyPoints.remove(new Pair<>(i * (x/3), j));
                }
            }
        }
        info.setWalls(walls);
    }

    /**
     * ustawia głowę węża
     * @param x współrzędna x wstawianej głowy węża
     * @param y współrzędna y wstawianej głowy węża
     */
    void setHead(int x, int y)
    {
        headX = x;
        headY = y;
        //table[x][y] = 1;
        snake.addFirst(new Pair<>(x,y));
    }

    /**
     * sprawdza czy gracz przegrał
     * @return true jeśli gracz przegrał, inaczej zwraca false
     */
    boolean isGameOver()
    {
        return isGameOver;
    }

    /**
     * przesuwa węża i sprawdza czy nie uderzył w ścianę lub czy siebie nie ugryzł
     * @param lastKey ostatnio naciśnięty przycisk na klawiaturze: u - up, d - down, l - left, r - right, n - nie naciśnięto klawisza
     */
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
        emptyPoints.remove(new Pair<>(headX, headY));

        if( table[headX][headY] == SNAKE_FIELD || table[headX][headY] == WALL_FIELD )
        {
            isGameOver = true;
            table[lastTail.getKey()][lastTail.getValue()] = SNAKE_FIELD;
            snake.addLast(lastTail);
            snake.removeFirst();


            //System.out.println("GAME OVER");
        }
        else
            table[headX][headY] = SNAKE_FIELD;

        notifyAllObservers();
    }

    /**
     * losuje miejsce i wstawia jabłko
     */
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

    /**
     * dodaje obserwatora
     * @observer observator do dodania
     */
    public void attach(View observer){
        info.setSnake(snake);
        observers.add(observer);
        observer.update(info);
    }

    /**
     * wysyła informację do wszystkich obserwatorów
     */
    public void notifyAllObservers(){
        info.setSnake(snake);

        for (View observer : observers) {
            observer.update(info);
        }
    }

    /*
    private void writeSnake()
    {
        for (Pair<Integer, Integer> integerIntegerPair : snake)
            System.out.println("\t" + integerIntegerPair);
    }

    private void writeBoard()
    {
        for( int i = 0; i < y; i++ )
        {
            for( int j = 0; j < x; ++j)
            {
                System.out.print(table[j][i]);
            }
            System.out.println();
        }
    }
    */

    /**
     * usuwa ściany z planszy
     */
    private void clearWalls()
    {
        for(Pair<Integer, Integer> i : walls)
        {
            emptyPoints.add(i);
            table[i.getKey()][i.getValue()] = EMPTY_FIELD;
        }
        walls.clear();
    }

    /**
     * zwraca liczbę punktów
     * @return liczba zdobytych punktów
     */
    public int getScore()
    {
        return score;
    }

}
