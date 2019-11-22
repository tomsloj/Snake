import javax.swing.*;
import java.awt.*;

public class MyPanel  extends JPanel {

    int square;
    int marign;

    static Color c = Color.BLUE;


    MyPanel(int square, int margin)
    {
        this.square = square;
        this.marign = margin;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawString("This is my custom Panel!",10,20);
        for (int i = 0; i < 10; ++i)
            for(int j = 0; j < 10; ++j )
                paintBackground(g, i, j);
        //g.setColor(Color.BLACK);
        //g.drawRect(20,20,20,20);
    }

    private void paintBackground(Graphics g, int i, int j)
    {
        g.setColor(c);
        g.fillRect(marign + i * square,marign + j * square ,square,square);
    }

    private void paintSnake(Graphics g, int i, int j)
    {
        g.setColor(c);
        g.fillRect(marign + i * square,marign + j * square ,square,square);
    }



}
