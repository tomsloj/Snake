import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyKeyListener implements KeyListener
{
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

    public char getAndResetLastClicked()
    {
        char tmp = lastClicked;
        lastClicked = 'n';
        return tmp;
    }
}