package com.tomsloj.mac.snake;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * klasa obsługująca słuchanie klawiatury
 */
public class MyKeyListener implements KeyListener
{
    /**
     * ostatnio naciśnięty przycisk;
     * n - nie naciśnięto przycisku
     * d - strzałka w dół
     * u - strzałka w górę
     * p - strzałka w prawo
     * l - strzałka w lewo
     */
    private char lastClicked = 'n';

    /**
     * konstroler wywołujący słuchacza
     */
    Controller controller;

    /**
     * konstruktor ustawiający kontroler aplikacji
     * @param controller kontroler
     */
    public MyKeyListener( Controller controller )
    {
        this.controller = controller;
    }

    /**
     * obsługa naciśnnięcia przycisku
     * @param e naciśnięcie przycisku
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * obsługa naciśnięcia przycisku
     * ustawienie ostatnio naciśniętego klawisza określającego kierunek węża
     * @param e naciśnięcie przycisku
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            //System.out.println("Right key pressed");
            lastClicked = 'r';
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            //System.out.println("Left key pressed");
            lastClicked = 'l';
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
        {
            //System.out.println("Up key pressed");
            lastClicked = 'u';
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            //System.out.println("Down key pressed");
            lastClicked = 'd';
        }

    }

    /**
     * obsługa puszczeia przycisku
     * jeśli naciśnięte zostało 'p' informuje o tym kontroler
     * @param e naciśnięcie przycisku
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        char c = e.getKeyChar();
        if ( c == 'p' || c == 'P' )
        {
            controller.pressedP();
        }
    }

    /**
     * zwraca ostatnio naciśnięty klawisz i resetuje go
     * @return ostatnio naciśnięty klawisz
     */
    public char getAndResetLastClicked()
    {
        char tmp = lastClicked;
        lastClicked = 'n';
        return tmp;
    }
}