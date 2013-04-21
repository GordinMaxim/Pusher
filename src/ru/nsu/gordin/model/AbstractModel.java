package ru.nsu.gordin.model;

import ru.nsu.gordin.PusherException;

import java.io.IOException;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/17/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */

abstract public class AbstractModel extends Observable {
    abstract public void init(String fileName) throws IOException, PusherException;
    abstract public void move(Directions d) throws PusherException;
    abstract public boolean check();
    abstract public int getX();
    abstract public int getY();
    abstract public int getWidth();
    abstract public int getHeight();
}
