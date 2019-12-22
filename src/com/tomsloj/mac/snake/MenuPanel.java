package com.tomsloj.mac.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class MenuPanel extends JPanel {

    JButton playButton;
    JButton speedButton;
    JButton boardButton;
    JButton sizeButton;
    JButton closeButton;

    View view;

    MenuPanel(View view)
    {
        this.view = view;

        playButton = new JButton("GRAJ");
        add(playButton);

        speedButton = new JButton("SZYBKOŚĆ");
        add(speedButton);

        sizeButton = new JButton("ROZMIAR PLANSZY");
        add(sizeButton);

        boardButton = new JButton("RODZAJ PLANSZY");
        add(boardButton);

        closeButton = new JButton("WYJDŹ");
        add(closeButton);

        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.out.println("GRAJ");
                view.startGame();
            }
        });

        speedButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Integer[] levels = {1, 2, 3, 4, 5, 6};
                int currentLevel = view.getSpeedLevel();
                try {
                    Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                    ImageIcon icon = new ImageIcon(image);
                    int speedLevel = (int) JOptionPane.showInputDialog(view, "Wybierz poziom szybkści", "Szybkość", JOptionPane.QUESTION_MESSAGE, icon, levels, levels[currentLevel - 1]);
                    view.setSpeed(speedLevel);
                }
                catch (NullPointerException e)
                {

                }
            }
        });

        sizeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int currentSize = view.getBoardSize();
                String[] boards = {"MAŁA", "ŚREDNIA", "DUŻA"};
                try{
                    Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                    ImageIcon icon = new ImageIcon(image);
                    String  speedLevel = (String) JOptionPane.showInputDialog(view, "Wybierz wielkość planszy", "Rozmiar", JOptionPane.QUESTION_MESSAGE, icon, boards, boards[currentSize]);
                    if (speedLevel.equals("MAŁA")) {
                        view.setSize(0);
                    } else if (speedLevel.equals("ŚREDNIA")) {
                        view.setSize(1);
                    } else if (speedLevel.equals("DUŻA")) {
                        view.setSize(2);
                    }
                }
                catch (NullPointerException e)
                {

                }
            }
        });

        boardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String[] boards = {"PUSTA", "RAMKA", "PASY"};
                int currentMode = view.getMode();
                try
                {
                    Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                    ImageIcon icon = new ImageIcon(image);
                    String mode = (String) JOptionPane.showInputDialog(view, "Wybierz rodzaj planszy", "Rodzaj planszy", JOptionPane.QUESTION_MESSAGE, icon, boards, boards[currentMode]);
                    if( mode.equals("PUSTA") )
                    {
                        view.setMode( 0 );
                    }
                    else
                    if( mode.equals("RAMKA") )
                    {
                        view.setMode( 1 );
                    }
                    else
                    if( mode.equals("PASY") )
                    {
                        view.setMode( 2 );
                    }
                }
                catch (NullPointerException e)
                {

                }

            }
        });

        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
    }

}