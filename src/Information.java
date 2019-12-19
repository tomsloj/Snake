import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Information {
    private boolean isAppleChanged;

    private Deque<Pair<Integer, Integer>> snake;

    private ArrayList<Pair<Integer, Integer>> walls;

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    private int appleX;
    private int appleY;

    public Deque<Pair<Integer, Integer>> getSnake() {
        return snake;
    }

    public ArrayList<Pair<Integer, Integer>> getWalls() {
        return walls;
    }

    Information()
    {
        snake = new LinkedList<>();
        walls = new ArrayList<>();
        isAppleChanged = false;
    }

    void setApple(int appleX, int appleY)
    {
        this.appleX = appleX;
        this.appleY = appleY;
        isAppleChanged = true;
    }

    void setSnake(Deque<Pair<Integer, Integer>>snake)
    {
        this.snake = snake;
    }

    void setWalls(ArrayList<Pair<Integer,Integer>>walls)
    {
        this.walls = walls;
    }

    boolean isAppleChanged()
    {
        return isAppleChanged;
    }

}
