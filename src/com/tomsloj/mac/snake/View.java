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

    private Controller controller;
    private JLabel label;

    /**
     * panel na którym rysowana jest gra
     */
    private GamePanel gamePanel;
    /**
     * panel na którym rysowane jest menu
     */
    private MenuPanel menuPanel;

    private int x;
    private int y;

    /**
     * ilość zdobytych punktów
     */
    private int score = 0;

    View(int x, int y, Controller controller)
    {
        super("Snake");

        setX(x);
        setY(y);

        setController(controller);

        menuPanel = new MenuPanel(this);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        //menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        //menuPanel.setHorizontalAlignment(JLabel.CENTER);
        //menuPanel.setVerticalAlignment(JLabel.CENTER);
        //menuPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        getContentPane().add(menuPanel);

        //setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
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

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * metoda przechodząca z menu do gry
     */
    public void startGame()
    {
        gamePanel = new GamePanel(SQUARE_SIZE, MARGIN, x, y);
        gamePanel.setBackground(Color.BLUE);

        updateSize();

        label = new JLabel("Wynik: 0");
        label.setForeground(Color.GREEN);

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed)
    {
        controller.setSpeed(speed);
    }

    public void setMode( int mode )
    {
        controller.setMode( mode );
    }

    public void setSize(int size)
    {
        controller.setSize(size);
    }

    public int getSpeedLevel()
    {
        return controller.getSpeedLevel();
    }

    public int getMode()
    {
        return controller.getMode();
    }

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
