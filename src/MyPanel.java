import javax.swing.*;
import java.awt.*;

public class MyPanel  extends JPanel {

    int square;
    int marign;


    MyPanel(int square, int margin)
    {
        this.square = square;
        this.marign = margin;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.BLUE);
        for (int i = 0; i < 10; ++i)
            for(int j = 0; j < 10; ++j )
            g.fillRect(marign + i * square,marign + j * square ,square,square);
        //g.setColor(Color.BLACK);
        //g.drawRect(20,20,20,20);
    }
}
