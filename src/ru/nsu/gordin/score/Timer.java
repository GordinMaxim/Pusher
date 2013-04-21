package ru.nsu.gordin.score;

import ru.nsu.gordin.viewer.*;
/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/21/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class Timer extends Thread {
    private ViewInterface view;
    private Score score;

    private Timer(){}

    public Timer(ViewInterface view, Score score)
    {
        this.view = view;
        this.score = score;
    }

    public void run()
    {
        try {
            score.start();
            view.updateInfoPanel(score);

            while(score.getTime() < 5*1000*60)
            {
                Timer.sleep(1000);
                view.updateInfoPanel(score);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            view.printError("something wrong with timer");
        }
    }
}
