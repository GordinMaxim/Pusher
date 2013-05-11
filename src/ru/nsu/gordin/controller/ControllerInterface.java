package ru.nsu.gordin.controller;

import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/20/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */

public interface ControllerInterface {
    public void run();
    public boolean initModel(String gameFile);
    public void startGame();
    public void endGame(String user);
    public TableModel getTableModel();
    public Object[] getColumns();
}
