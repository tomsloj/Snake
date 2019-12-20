import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                Integer[] levels = {1, 2, 3, 4, 5};
                int currentLevel = view.getSpeedLevel();
                int speedLevel = (int) JOptionPane.showInputDialog(view, "Wybierz poziom szybkści", "Szybkość", JOptionPane.QUESTION_MESSAGE, null, levels, levels[currentLevel - 1]);
                view.setSpeed(speedLevel);
            }
        });

        sizeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String[] boards = {"MAŁA", "ŚREDNIA", "DUŻA"};
                String  speedLevel = (String) JOptionPane.showInputDialog(view, "Wybierz wielkość planszy", "Rozmiar", JOptionPane.QUESTION_MESSAGE, null, boards, boards[0]);


            }
        });

        boardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String[] boards = {"PUSTA", "RAMKA", "PASY"};
                String  speedLevel = (String) JOptionPane.showInputDialog(view, "Wybierz rodzaj planszy", "Rodzaj planszy", JOptionPane.QUESTION_MESSAGE, null, boards, boards[0]);



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
