import javax.swing.*;
import java.awt.*;

public class MyPanel  extends JPanel {

    int square;
    int marign;

    int x;
    int y;

    int [][] board;

    static int counter = 0;


    MyPanel(int square, int margin, int x, int y)
    {
        this.square = square;
        this.marign = margin;
        this.x = x;
        this.y = y;
        board = new int[x][y];

    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < x; ++i)
            for(int j = 0; j < y; ++j )
            {
                if(board[i][j] == 0 )
                    paintBackground(g, i, j);
                else
                if( board[i][j] == 1)
                    paintSnake(g, i, j);
                else
                if( board[i][j] == 2)
                    paintPoint(g, i, j);
            }
    }

    private void paintBackground(Graphics g, int i, int j)
    {
        g.setColor(Color.BLUE);
        g.fillRect(marign + i * square,marign + j * square ,square,square);
    }

    private void paintSnake(Graphics g, int i, int j)
    {
        g.setColor(Color.GREEN);
        g.fillRect(marign + i * square,marign + j * square ,square,square);
    }

    private void paintPoint(Graphics g, int i, int j)
    {
        g.setColor(Color.RED);
        g.fillRect(marign + i * square,marign + j * square ,square,square);
    }


    public void setBoard(int[][] board)
    {
        this.board = board;
    }
}
