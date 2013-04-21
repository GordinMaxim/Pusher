package ru.nsu.gordin;

import org.apache.log4j.PropertyConfigurator;
import ru.nsu.gordin.controller.Controller;
import ru.nsu.gordin.controller.ControllerInterface;
import ru.nsu.gordin.model.AbstractModel;
import ru.nsu.gordin.model.Model;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 3/31/13
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
class GUITthread implements Runnable {
    public void run()
    {
        AbstractModel model = new Model();
        ControllerInterface controller = new Controller(model);

        controller.run();
    }
}

public class Main {

    public static void main(String args[]) {
        PropertyConfigurator.configure("properties/log4j.properties");
        SwingUtilities.invokeLater(new GUITthread());

    }

}
