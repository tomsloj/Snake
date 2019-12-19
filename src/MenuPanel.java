import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    JButton playButton;

    View view;

    MenuPanel(View view)
    {
        this.view = view;
        playButton = new JButton("GRAJ");

        add(playButton);

        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.out.println("GRAJ");
                view.startGame();
            }
        });
    }

}
