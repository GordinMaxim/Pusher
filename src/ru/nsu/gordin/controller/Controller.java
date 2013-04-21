package ru.nsu.gordin.controller;

import ru.nsu.gordin.PusherException;
import ru.nsu.gordin.model.*;
import ru.nsu.gordin.score.Score;
import ru.nsu.gordin.score.Timer;
import ru.nsu.gordin.viewer.View;
import ru.nsu.gordin.viewer.ViewInterface;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/20/13
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */

public class Controller extends KeyAdapter implements ControllerInterface{
    private AbstractModel model;
    private ViewInterface view;
    private Timer timer;
    private Score score;

    private Controller(){}

    public Controller(AbstractModel model)
    {
        this.model = model;
    }

    public void run()
    {
        view = new View(this, model);
        view.createMenuBar();
        view.setStartPanel();
        view.addKeyListener(this);
    }


    public void startGame()
    {
        String gameFile = view.getSelectedLevel();
        boolean result = this.initModel(gameFile);
        if(!result)
        {
            view.setStartPanel();
            view.printError("level not found");
            return;
        }

        int width = model.getWidth();
        int height = model.getHeight();
        view.setGameFieldSize(width * 50, height * 50);
        score = new Score();
        score.start();
        timer = new Timer(view, score);

//        не работает, не разобрался как правильно
//        SwingUtilities.invokeLater(timer);
    }

    public boolean initModel(String gameFile) {
        try {
            model.init(gameFile);
            view.setPlayPanel();
        }
        catch (PusherException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return true;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed "+e.getKeyCode());
        try {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP :
                {
                    System.out.println("UP");
                    model.move(Directions.UP);
                    break;
                }
                case KeyEvent.VK_DOWN :
                {
                    System.out.println("DOWN");
                    model.move(Directions.DOWN);
                    break;
                }
                case KeyEvent.VK_LEFT :
                {
                    System.out.println("LEFT");
                    model.move(Directions.LEFT);
                    break;
                }
                case KeyEvent.VK_RIGHT :
                {
                    System.out.println("RIGHT");
                    model.move(Directions.RIGHT);
                    break;
                }
            }
        }
        catch (PusherException ex)
        {
            ex.printStackTrace();
        }
    }
}
