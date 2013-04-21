package ru.nsu.gordin.viewer;

import ru.nsu.gordin.score.Score;

import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/20/13
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */

public interface ViewInterface {
    public static int widthInfoPanel = 100;
    public void createMenuBar();
    public void setStartPanel();
    public void updateInfoPanel(Score score);
    public void setPlayPanel();
    public String getSelectedLevel();
    public void setGameFieldSize(int width, int height);
    public void printError(String msg);
    public void addKeyListener(KeyListener listener);
}
