package com.tomsloj.mac.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * panel zawierający menu gry
 */
public class MenuPanel extends JPanel {

    /**
     * przycisk rozpoczynający grę
     */
    JButton playButton;
    /**
     * przycisk umożliwiający ustawienie szybkości węża
     */
    JButton speedButton;
    /**
     * przycisk umożliwiający wybór rodzaju planszy
     */
    JButton boardButton;
    /**
     * przycisk umożliwiający ustawienie rozmiaru planszy
     */
    JButton sizeButton;
    /**
     * przycisk wyjścia z gry
     */
    JButton closeButton;

    /**
     * widok otwierający panel
     */
    View view;

    /**
     * ustawia widok menu  i obsługę przycisków
     * @param view widok otwierający panel
     */
    MenuPanel(View view)
    {
        this.view = view;

        setBackground(Color.BLUE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel label = new JLabel("Snake");
        label.setFont(new Font("Copperplate", Font.BOLD, 75));
        label.setForeground(Color.GREEN);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        playButton = new JButton("GRAJ");
        customizeButton(playButton);
        add(playButton);

        speedButton = new JButton("SZYBKOŚĆ");
        customizeButton(speedButton);
        add(speedButton);

        sizeButton = new JButton("ROZMIAR PLANSZY");
        customizeButton(sizeButton);
        add(sizeButton);

        boardButton = new JButton("RODZAJ PLANSZY");
        customizeButton(boardButton);
        add(boardButton);

        closeButton = new JButton("WYJDŹ");
        customizeButton(closeButton);
        add(closeButton);

        playButton.addActionListener(actionEvent ->
        {
            System.out.println("GRAJ");
            view.startGame();
        });

        speedButton.addActionListener(actionEvent ->
        {
            Integer[] levels = {1, 2, 3, 4, 5, 6};
            int currentLevel = view.getSpeedLevel();
            try
            {
                Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                ImageIcon icon = new ImageIcon(image);
                int speedLevel = (int) JOptionPane.showInputDialog(view, "Wybierz poziom szybkści", "Szybkość", JOptionPane.QUESTION_MESSAGE, icon, levels, levels[currentLevel - 1]);
                view.setSpeed(speedLevel);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        });

        sizeButton.addActionListener(actionEvent ->
        {
            int currentSize = view.getBoardSize();
            String[] boards = {"MAŁA", "ŚREDNIA", "DUŻA"};
            try
            {
                Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                ImageIcon icon = new ImageIcon(image);
                String  speedLevel = (String) JOptionPane.showInputDialog(view, "Wybierz wielkość planszy", "Rozmiar", JOptionPane.QUESTION_MESSAGE, icon, boards, boards[currentSize]);

                switch (speedLevel)
                {
                    case "MAŁA":
                        view.setSize(0);
                        break;
                    case "ŚREDNIA":
                        view.setSize(1);
                        break;
                    case "DUŻA":
                        view.setSize(2);
                        break;
                }

            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        });

        boardButton.addActionListener(actionEvent ->
        {
            String[] boards = {"PUSTA", "RAMKA", "PASY"};
            int currentMode = view.getMode();
            try
            {
                Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                ImageIcon icon = new ImageIcon(image);
                String mode = (String) JOptionPane.showInputDialog(view, "Wybierz rodzaj planszy", "Rodzaj planszy", JOptionPane.QUESTION_MESSAGE, icon, boards, boards[currentMode]);
                switch (mode)
                {
                    case "PUSTA":
                        view.setMode(0);
                        break;
                    case "RAMKA":
                        view.setMode(1);
                        break;
                    case "PASY":
                        view.setMode(2);
                        break;
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }

        });

        closeButton.addActionListener(actionEvent -> System.exit(0));
    }

    /**
     * ustawia styl przycisku
     * @param button przycisk którego styl ustawiamy
     */
    private void customizeButton(JButton button)
    {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        button.setBorderPainted(false);
        button.setFont(new Font("Cochin", Font.PLAIN, 22));
    }

}
