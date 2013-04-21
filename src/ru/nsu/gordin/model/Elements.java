package ru.nsu.gordin.model;

import ru.nsu.gordin.PusherException;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 3/31/13
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */

public enum Elements
{
    WALL('x'), BOX('*'), GOAL('&'), EMPTY('.');
    private char c;

    Elements(char s)
    {
        this.c = s;
    }

    public static Elements decoder(char s) throws PusherException {
        switch (s)
        {
            case 'x' : return WALL;
            case '*' : return BOX;
            case '&' : return GOAL;
            case '.' : return EMPTY;
        }
        throw new PusherException("no such element " + s);
    }

}
