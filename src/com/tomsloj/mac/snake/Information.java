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

    /**
     * współrzędna X jabłka
     */
    private int appleX;
    /**
     * współrzędna Y jabłka
     */
    private int appleY;

    /**
     * konstruktor tworzący nową informację
     */
    Information()
    {
        snake = new LinkedList<>();
        walls = new ArrayList<>();
        isAppleChanged = false;
    }

    /**
     * zwraca współrzędną X jabłka
     * @return współrzędna X jabłka
     */
    public int getAppleX() {
        return appleX;
    }

    /**
     * zwraca współrzędną Y jabłka
     * @return współrzędna Y jabłka
     */
    public int getAppleY() {
        return appleY;
    }

    /**
     * zwraca kolejkę zawierającą węża
     * @return kolejka zawierająca węża
     */
    public Deque<Pair<Integer, Integer>> getSnake() {
        return snake;
    }

    /**
     * zwraca zbiór pól zawierających ściany
     * @return zbiór pól zawierających ściany
     */
    public ArrayList<Pair<Integer, Integer>> getWalls() {
        return walls;
    }

    /**
     * ustawia współrzędne jabłka
     * @param appleX współrzędna X jabłka
     * @param appleY współrzędna Y jabłka
     */
    void setApple(int appleX, int appleY)
    {
        this.appleX = appleX;
        this.appleY = appleY;
        isAppleChanged = true;
    }

    /**
     * ustawia kolejkę zawierającą węża
     * @param snake kolejka zawierająca węża
     */
    void setSnake(Deque<Pair<Integer, Integer>>snake)
    {
        this.snake = snake;
    }

    /**
     * ustawia zbiór pól zawierających ściany
     * @param walls zbiór pól zawierających ściany
     */
    void setWalls(ArrayList<Pair<Integer,Integer>>walls)
    {
        this.walls = walls;
    }

    /**
     * zwraca informację o tym czy jabłko zmieniło swoje miejsce
     * @return true jeśli jabłko zmieniło swoje miejsce, w przeciwnym wypadku zwraca false
     */
    boolean isAppleChanged()
    {
        return isAppleChanged;
    }

}
