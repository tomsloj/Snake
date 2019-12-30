package com.tomsloj.mac.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * klasa reprezentująca widok aplikacji
 */
public class View extends JFrame {
    /**
     * rozmiar jednego pola planszy
     */
    private final int SQUARE_SIZE = 20;
    /**
     * szerokość marginesu
     */
    private final int MARGIN = 25;

    /**
     * kontroler obsługujący widok
     */
    private Controller controller;

    /**
     * etykieta zawierająca informację o aktualnym wyniku
     */
    private JLabel label;

    /**
     * panel na którym rysowana jest gra
     */
    private GamePanel gamePanel;
    /**
     * panel na którym rysowane jest menu
     */
    private MenuPanel menuPanel;

    /**
     * szerokość planszy
     */
    private int x;
    /**
     * wysokość planszy
     */
    private int y;

    /**
     * ilość zdobytych punktów
     */
    private int score = 0;

    /**
     * konstruktor ustawiający widok gry
     * wyświetla panel menu
     * @param x szerokość planszy
     * @param y wysokość planszy
     * @param controller kontroler obsługujący widok
     */
    View(int x, int y, Controller controller)
    {
        super("Snake");

        setX(x);
        setY(y);

        setController(controller);

        menuPanel = new MenuPanel(this);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        getContentPane().add(menuPanel);

        setSize(400, 350);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * aktualizuje widok gry
     * @param info zawiera informacje o zaktualizowanej planszy
     */
    public void update(Information info)
    {
        gamePanel.setInfo(info);
        gamePanel.repaint();
    }

    /**
     * ustawia ilość zdobytych punktów i aktualizuje napis informujący o wyniku
     * @param score aktualna liczba punktów
     */
    public void setScore(int score)
    {
        this.score = score;
        label.setText("Wynik: " + score);
    }

    /**
     * ustawia kontroler obsługujący widok
     * @param controller kontroler obsługujący widok
     */
    private void setController(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * metoda przechodząca z menu do gry
     */
    public void startGame()
    {
        gamePanel = new GamePanel(SQUARE_SIZE, MARGIN, x, y, controller.getMode());
        gamePanel.setBackground(Color.BLUE);

        updateSize();

        label = new JLabel("Wynik: 0");
        label.setForeground(Color.WHITE);

        gamePanel.add(label);

        getContentPane().remove(menuPanel);
        getContentPane().add(gamePanel);
        invalidate();
        validate();

        setVisible(false);
        setVisible(true);

        controller.startGame();
    }

    /**
     * metoda wyświetlająca okienko po zakończeniu gry
     */
    public void gameOver()
    {

        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ImageIcon icon = new ImageIcon(image);
        String[] buttons = { "Wyjdź", "Menu","Zagraj jeszcze raz" };

        try
        {
            int choice = JOptionPane.showOptionDialog(null, "Twój wynik to: " + score, "Koniec gry",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, icon, buttons, buttons[2]);

            if( choice == 0 )
            {
                System.exit(0);
            }
            else
            if( choice == 1 )
            {
                setSize(400, 350);

                getContentPane().remove(gamePanel);
                getContentPane().add(menuPanel);

                invalidate();
                validate();
            }
            else
            if( choice == 2 )
            {
                controller.startGame();
            }
            else
            {
                System.exit(0);
            }
        }
        catch (Exception e)
        {
            System.exit(0);
        }
    }

    /**
     * ustawia szerokość planszy
     * @param x szerokość planszy
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * ustawia wysokość planszy
     * @param y wysokość planszy
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * ustawia poziom szybkości węża
     * @param speed poziom szybkości
     */
    public void setSpeed(int speed)
    {
        controller.setSpeed(speed);
    }

    /**
     * ustawia rodzaj planszy
     * @param mode rodzaj planszy
     */
    public void setMode( int mode )
    {
        controller.setMode( mode );
    }

    /**
     * ustawia poziom wielkości planszy
     * @param size poziom wielkości planszy
     */
    public void setSize(int size)
    {
        controller.setSize(size);
    }

    /**
     * zwraca poziom szybkości węża
     * @return poziom szybkości węża
     */
    public int getSpeedLevel()
    {
        return controller.getSpeedLevel();
    }

    /**
     * zwraca rodzaj planszy
     * @return rodzaj planszy
     */
    public int getMode()
    {
        return controller.getMode();
    }

    /**
     * zwraca poziom wielkości planszy
     * @return poziom wielkości planszy
     */
    public int getBoardSize()
    {
        if( x == 16 )
            return 0;
        if( x == 22)
            return 1;
        if( x == 32 )
            return 2;

        return 0;
    }

    /**
     * ustawia rozmiar okna dostosowany do rozmiaru planszy do gry
     */
    public void updateSize()
    {
        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
    }
}
