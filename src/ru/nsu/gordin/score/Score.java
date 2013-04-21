package ru.nsu.gordin.score;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/21/13
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */

public class Score {
    private long startTime;
    private int stepNumber;

    public void start()
    {
        startTime = System.currentTimeMillis();
        stepNumber = 0;
    }

    public long getTime()
    {
        return (System.currentTimeMillis() - startTime)/1000;
    }

    public int getStepNumber()
    {
        return stepNumber;
    }

    public void incrementStep()
    {
        stepNumber++;
    }
}
