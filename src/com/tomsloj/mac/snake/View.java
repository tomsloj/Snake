package com.tomsloj.mac.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private final int SQUARE_SIZE = 20;
    private final int MARGIN = 25;

    private Controller controller;
    private JLabel label;
    private GamePanel gamePanel;
    private JPanel menuPanel;

    private int x;
    private int y;

    private int score = 0;

    View(int x, int y, Controller controller, KeyListener keyListener)
    {
        super("newKeyListener");

        setX(x);
        setY(y);

        setController(controller);

        menuPanel = new MenuPanel(this);
        getContentPane().add(menuPanel);
        //gamePanel = new MyPanel(SQUARE_SIZE, MARGIN, x, y);

        //gamePanel.setBackground(Color.BLUE);
        //gamePanel.add(label);
        //gamePanel.revalidate();

       //add(gamePanel, BorderLayout.CENTER);

        //setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
        setSize(200, 300);
        //pack();
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(Information info)
    {
        gamePanel.setInfo(info);
        gamePanel.repaint();
        //System.out.println("update");
    }

    public void setScore(int score)
    {
        this.score = score;
        label.setText("Wynik: " + score);
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void startGame()
    {
        gamePanel = new GamePanel(SQUARE_SIZE, MARGIN, x, y);
        gamePanel.setBackground(Color.BLUE);

        updateSize();

        label = new JLabel("Wynik: 0");
        label.setForeground(Color.GREEN);

        gamePanel.add(label);
        //gamePanel.addKeyListener(new MyKeyListener());
        //gamePanel.revalidate();

        getContentPane().remove(menuPanel);
        getContentPane().add(gamePanel);
        invalidate();
        validate();

        setVisible(false);
        setVisible(true);

        /*
        getContentPane().removeAll();

        getContentPane().add(gamePanel, BorderLayout.CENTER);
        //validate();
        setVisible(true);

         */



        controller.startGame();
    }

    public void gameOver()
    {

        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ImageIcon icon = new ImageIcon(image);
        String[] buttons = { "Wyjdź", "Menu","Zagraj jeszcze raz" };

        try
        {
            int choice = JOptionPane.showOptionDialog(null, "Twój wynik to: " + score, "Koniec gry",
                    JOptionPane.WARNING_MESSAGE, 0, icon, buttons, buttons[2]);

            if( choice == 0 )
            {
                System.exit(0);
            }
            else
            if( choice == 1 )
            {

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

    public void updateSize()
    {
        setSize(SQUARE_SIZE * x + 2 * MARGIN, SQUARE_SIZE * y + 3 * MARGIN );
    }
}
