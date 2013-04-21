package ru.nsu.gordin.model;

import org.apache.log4j.Logger;
import ru.nsu.gordin.PusherException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Model  extends AbstractModel {
    public static final int HEIGHT_INIT = 100;
    final Logger log = Logger.getLogger(Model.class);

    private int goalCount;
    private int goalNumber;
    private int width;
    private int height = HEIGHT_INIT;
    private int posX = -1;
    private int posY = -1;
    private final char[][] map = new char[HEIGHT_INIT][];                          //не красиво
    private final char[][] boxes = new char[HEIGHT_INIT][];

    public Model(){}

    public void init(String fileName) throws IOException, PusherException
    {
        goalNumber = 0;
        goalCount = 0;
        int i = 0;
        int pos = -1;
        int boxNumber = 0;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
            String str = reader.readLine();
            if(!"map".equals(str))
            {
                throw new PusherException("wrong file format");
            }

            for(str = reader.readLine(); str != null; str = reader.readLine(), i++)
            {
                pos = str.indexOf('t');
                if(pos >= 0)
                {
                    posY = pos;
                    posX = i;
                }

                goalNumber += str.replaceAll("[^&]", "").length();
                boxNumber += str.replaceAll("[^*]", "").length();

                map[i] = str.replaceAll("[^x&]", ".").toCharArray();
                boxes[i] = str.replaceAll("[^*]", ".").toCharArray();
            }
            height = i;
            width = map[0].length;

            if(boxNumber != goalNumber)
            {
                throw new PusherException("should be the same number of boxes and goals");
            }
        }
    }

    private boolean changeCoordinates(Dimension dim, Directions d)
    {
        if(dim.height == 0 || dim.height == this.height -1 || dim.width == 0 || dim.width == this.width -1)
        {
            return false;
        }
        switch (d)
        {
            case UP:
            {
                dim.setSize(dim.width, dim.height -1);
                break;
            }
            case DOWN:
            {
                dim.setSize(dim.width, dim.height +1);
                break;
            }
            case LEFT:
            {
                dim.setSize(dim.width -1, dim.height);
                break;
            }
            case RIGHT:
            {
                dim.setSize(dim.width +1, dim.height);
                break;
            }
        }
        return true;
    }

    public void move(Directions d) throws PusherException
    {
        Dimension next = new Dimension(posY, posX);

        if(!changeCoordinates(next, d))
            return;

        Elements anyWallOrGoal = Elements.decoder(map[next.height][next.width]);
        Elements anyBox = Elements.decoder(boxes[next.height][next.width]);

        if(Elements.WALL == anyWallOrGoal)
        {
            return;
        }
        else if(Elements.BOX == anyBox)
        {
            Dimension afterNext = new Dimension(next);
            if(!changeCoordinates(afterNext, d))
                return;

            Elements nextAnyWallOrGoal = Elements.decoder(map[afterNext.height][afterNext.width]);
            Elements nextAnyBox = Elements.decoder(boxes[afterNext.height][afterNext.width]);

            if(Elements.BOX == nextAnyBox || Elements.WALL == nextAnyWallOrGoal)
            {
                return;
            }
            else if(Elements.GOAL == nextAnyWallOrGoal && Elements.GOAL != anyWallOrGoal)
            {
                goalCount++;
            }
            else if(Elements.GOAL != nextAnyWallOrGoal && Elements.GOAL == anyWallOrGoal)
            {
                goalCount--;
            }
            boxes[next.height][next.width] = '.';
            boxes[afterNext.height][afterNext.width] = '*';
        }

        posY = next.width;
        posX = next.height;
        setChanged();
        notifyObservers();
        return;
    }

    public boolean check()
    {
        return (goalCount == goalNumber);
    }

    public void apply(ApplyToCell c){
             /*заполнение слева-направо по строкам*/
        for(int i = 0; i < map.length ; i++)
        {
            for(int j = 0; map[i]!=null && j < map[i].length; j++)
            {
                 c.apply(map[i][j], boxes[i][j], i, j);
            }
        }
    }

    public int getX()
    {
        return posX;
    }

    public int getY()
    {
        return posY;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public static interface  ApplyToCell{
        void apply(char cell, char box, int x, int y);
    }

}
