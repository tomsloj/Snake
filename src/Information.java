import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class Information {
    boolean isAppleChanged;
    int appleX;
    int appleY;

    Deque<Pair<Integer, Integer>> snake;

    Information()
    {
        snake = new LinkedList<>();
        isAppleChanged = false;
    }

    void setApple(int appleX, int appleY)
    {
        this.appleX = appleX;
        this.appleY = appleY;
        isAppleChanged = true;
    }

}
