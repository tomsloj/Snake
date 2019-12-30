package com.tomsloj.mac.snake;


import java.util.ArrayList;
import javafx.util.Pair;
import java.util.Deque;
import java.util.LinkedList;

/**
 * klasa zawierająca informację potrzebne do zmodyfikowania widoku
 */
public class Information {
    /**
     * zawiera informację czy jabłko zmieniło swoje miejsce
     */
    private boolean isAppleChanged;

    /**
     * zawiera miejsca na planszy na których znajduje się wąż
     */
    private Deque<Pair<Integer, Integer>> snake;

    /**
     * zawiera miejsca na planszy na których znajdują się ściany
     */
    private ArrayList<Pair<Integer, Integer>> walls;

    private int appleX;
    private int appleY;

    Information()
    {
        snake = new LinkedList<>();
        walls = new ArrayList<>();
        isAppleChanged = false;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public Deque<Pair<Integer, Integer>> getSnake() {
        return snake;
    }

    public ArrayList<Pair<Integer, Integer>> getWalls() {
        return walls;
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
