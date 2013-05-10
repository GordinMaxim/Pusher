package ru.nsu.gordin.model;

import ru.nsu.gordin.PusherException;
import ru.nsu.gordin.score.Score;

import java.io.IOException;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/17/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */

public interface AbstractModel {
    public void init(String fileName) throws IOException, PusherException;
    public void move(Directions d, Score score) throws PusherException;
    public boolean check();
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public String getLevel();
}
