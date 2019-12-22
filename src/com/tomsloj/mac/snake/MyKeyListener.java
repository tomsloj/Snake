package com.tomsloj.mac.snake;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * klasa obsługująca słuchanie klawiatury
 */
public class MyKeyListener implements KeyListener
{
    /**
     * ostatnio naciśnięty przycisk
     * n - nie naciśnięto przycisku
     * d - strzałka w dół
     * u - strzałka w górę
     * p - strzałka w prawo
     * l - strzałka w lewo
     */
    private char lastClicked = 'n';
    /*
    u - up
    d - down
    r - right
    l - left
    n - nothing
     */

    public MyKeyListener()
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * obsługa naciśnięcia przycisku
     * ustawienie ostatnio naciśniętego klawisza
     * @param e naciśnięcie przycisku
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        //System.out.println("Clicked!!!");
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            //System.out.println("Right key pressed");
            lastClicked = 'r';
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            //System.out.println("Left key pressed");
            lastClicked = 'l';
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            //System.out.println("Up key pressed");
            lastClicked = 'u';
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            //System.out.println("Down key pressed");
            lastClicked = 'd';
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

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