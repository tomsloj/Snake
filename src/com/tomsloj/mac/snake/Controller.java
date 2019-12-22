package com.tomsloj.mac.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * klasa będąca kontrolerem zarządzającym działaniem aplikacji
 */
public class Controller implements ActionListener {
    /**
     * liczba poziomów szybkości
     */
    final int NUMBER_OF_LEVELS = 6;
     /**
     * kwant czasu o jaki zmienia się szybkość węża na każdym poziomie szybkości
     */
    final int SPEED_UNIT = 120;

    //game modes
    final int EMPTY = 0;
    final int FRAME = 1;
    final int STRIPS = 2;

    /**
     * rodzaj planszy
     * 0 - pusta, 1 - z ramką, 2 - z paskami
     */
    private int mode = 0;

    /**
     * model aplikacji
     */
    Board board;
    /**
     * widok aplikacji
     */
    View view;

    /**
     * odstęp pomiędzy ruchami węża w ms
     */
    private int speed = 480;
    /**
     * rozmiar planszy
     */
    private int x = 16, y = 12;
    /**
     * wątek taktujący
     */
    private Timer timer;
    /**
     * słuchacz klawiatury
     */
    MyKeyListener keyListener = new MyKeyListener();

    Controller()
    {
        view = new View(x, y, this);
        board = new Board();

        view.addKeyListener(keyListener);
    }

    /**
     * metoda uruchamiająca mechanizm gry
     */
    void startGame()
    {
        view.addKeyListener(keyListener);
        board.attach(view);

        board.startGame();

        timer = new Timer(speed, this);
        timer.start();
    }

    /**
     * metoda będąca słuchaczem timera
     * uruchamia się w momencie tyknięcia timera i powoduje uruchomienie mechanizmu poruszającego wężem
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        //System.out.println("tick!");

        char lastKey = keyListener.getAndResetLastClicked();
        board.move(lastKey);
        view.setScore(board.getScore());
        if(board.isGameOver())
            gameOver();
    }

    /**
     * metoda uruchamiana gdy gracz przegrał
     */
    public void gameOver()
    {
        timer.stop();
        view.gameOver();
    }

    /**
     * ustawia prędkość węża
     * @param speed poziom szybkości wybrany przez użytkownika
     */
    public void setSpeed(int speed)
    {
        this.speed = (NUMBER_OF_LEVELS - speed + 1) * SPEED_UNIT;
    }

    /**
     * @return aktualnie ustawiony poziom szybkości
     */
    public int getSpeedLevel()
    {
        //System.out.println(NUMBER_OF_LEVELS + 1 - ( speed / SPEED_UNIT ));
        return  NUMBER_OF_LEVELS + 1 - ( speed / SPEED_UNIT );
    }

    /**
     * ustawia rodzaj planszy
     * @param mode numer oznaczający rodzaj planszy: 0 - pusta, 1 - z ramką, 2 - z paskami
     */
    public void setMode( int mode )
    {
        this.mode = mode;
        board.setMode( mode );
    }

    /**
     * @return aktualnie ustawiony rodzaj planszy
     */
    public int getMode()
    {
        return mode;
    }

    /**
     * ustawia wielkość planszy
     * @param sizeLevel wielkość planszy: 0 - mała, 1 - średnia, 2 - duża
     */
    void setSize(int sizeLevel)
    {
        if( sizeLevel == 0 )
        {
            this.x = 16;
            this.y = 12;
        }
        else
        if( sizeLevel == 1 )
        {
            this.x = 22;
            this.y = 16;
        }
        else
        if( sizeLevel == 2 )
        {
            this.x = 32;
            this.y = 24;
        }

        view.setX(x);
        view.setY(y);
        //view.updateSize();

        board.setSize(x, y);

    }

}
