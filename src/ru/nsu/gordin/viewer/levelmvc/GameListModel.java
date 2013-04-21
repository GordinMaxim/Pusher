package ru.nsu.gordin.viewer.levelmvc;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/3/13
 */

public class GameListModel extends AbstractListModel<String> implements ComboBoxModel<String> {
    private List<String> data = new ArrayList<>();
    private int selected = 0;

    public GameListModel(String configFile)
    {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(configFile)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
            String level = null;

            while(null != (level = reader.readLine()))
            {
                data.add(level);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setSelectedItem(Object o)
    {
        selected = data.indexOf(o);
    }

    public String getSelectedItem()
    {
        return data.get(selected);
    }

    public int getSize()
    {
        return data.size();
    }

    public String getElementAt(int i)
    {
        return data.get(i);
    }

}
