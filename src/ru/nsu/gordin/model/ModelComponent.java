package ru.nsu.gordin.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/4/13
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */

public class ModelComponent extends JPanel {
    private Model model = null;
    private static BufferedImage floor = null;
    private static BufferedImage BOX = null;
    private static BufferedImage person = null;
    private static BufferedImage place = null;
    private static BufferedImage wall = null;

    static {

        try{
            floor = ImageIO.read(new File("images/floor.jpg"));
            BOX = ImageIO.read(new File("images/box.jpg"));
            person = ImageIO.read(new File("images/person.jpg"));
            wall = ImageIO.read(new File("images/wall.jpg"));
            place = ImageIO.read(new File("images/place.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private ModelComponent(){};

    public ModelComponent(AbstractModel m)
    {
        this.model = (Model)m;
    }

    public void paintComponent(final Graphics g)
    {
        System.out.println("modelComponent.paintComponent()");
        /*заполнение слева-направо по строкам*/
        model.apply(new Model.ApplyToCell() {
            @Override
            public void apply(char cell, char box, int i, int j) {
                Dimension dimension = getSize();

                int cellWidth = dimension.width / model.getWidth();
                int cellHeight = dimension.height / model.getHeight();
                int multiplier = 5;
                int shift = 50;
                int x = model.getX();
                int y = model.getY();

                if(i == x && j == y)
                {
                    g.drawImage(person, j*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight,
                            0, 0, person.getWidth(), person.getHeight(), null);
                }
                else if('x' == cell)
                {
                    g.drawImage(wall, j*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight,
                            shift, shift, shift+cellWidth*multiplier, shift+cellHeight*multiplier, null);
                }
                else if('*' == box)
                {
                    g.drawImage(BOX, j*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight,
                            0, 0, BOX.getWidth(), BOX.getHeight(), null);
                }
                else if('&' == cell)
                {
                    g.drawImage(place, j*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight,
                            0, 0, cellWidth*multiplier, cellHeight*multiplier, null);
                }
                else
                {
                    g.drawImage(floor, j*cellWidth, i*cellHeight, (j+1)*cellWidth, (i+1)*cellHeight,
                            0, 0, cellWidth*multiplier, cellHeight*multiplier, null);
                }
            }
        });

    }
}
