package ru.nsu.gordin.score;

import javafx.util.Pair;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 5/10/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Table {
    private static final int rowCount = 5;
    private static final int columnCount = 4;
    private Object[][] table = new Object[rowCount][columnCount];

    public void addRecord(String level, String user, long time, int steps)
    {
        for (int i = rowCount-1; i > 0; i--)
        {
            table[i] = table[i-1];
        }
        Object[] entry = {level, user, time, steps};
        table[0] = entry;
    }

    public Object[][] getTable()
    {
        return table.clone();
    }
}

