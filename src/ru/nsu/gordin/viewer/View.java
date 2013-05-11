package ru.nsu.gordin.viewer;

import ru.nsu.gordin.PusherException;
import ru.nsu.gordin.controller.ControllerInterface;
import ru.nsu.gordin.model.AbstractModel;
import ru.nsu.gordin.model.Model;
import ru.nsu.gordin.model.ModelComponent;
import ru.nsu.gordin.score.Score;
import ru.nsu.gordin.viewer.levelmvc.GameListModel;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/20/13
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */

// что лучше, когда View реализует ActionListener(тогда все элементы интерфейса должны быть полями)
// или когда на каждый элемент свой анонимный или внутренний класс?
public class View implements ViewInterface, Observer {
    private Model model;
    private ControllerInterface controller;
    private JFrame frame;
    private JPanel panel;
    private JPanel infoPanel;
    JPanel scorePanel;
    private JPanel gamePanel;
    ModelComponent modelComponent;
    private JMenuBar menuBar;
    private JComboBox<String> comboBox;


    private View(){
        frame = new JFrame();
        panel = new JPanel();
        infoPanel = new JPanel();
        gamePanel = new JPanel();
        menuBar = new JMenuBar();
        frame.setLayout(new BorderLayout());
        frame.add("North", menuBar);
        frame.add("Center", panel);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public View(ControllerInterface controller,  AbstractModel model) throws PusherException
    {
        this();
        this.model = (Model)model;
        if(null == this.model)
            throw new PusherException("AbstractModel model must reference to Model");

        this.controller = controller;
        this.model.addObserver(this);
    }

    public void setGameFieldSize(int width, int height)
    {
        frame.setSize(width + ViewInterface.widthInfoPanel, height);
        panel.setSize(width + ViewInterface.widthInfoPanel, height);
        gamePanel.setSize(width, height);
    }

    public void createMenuBar()
    {
        JMenu game, help;
        JMenuItem newGame;
        JMenuItem tutorial, about;

        game = new JMenu("Game");
        game.setMnemonic(MenuKeyEvent.VK_G);
        menuBar.add(game);

        help = new JMenu("Help");
        help.setMnemonic(MenuKeyEvent.VK_H);
        menuBar.add(help);


        newGame = new JMenuItem("New game");

        /*нужен ли посредник-контроллер?*/
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStartPanel();
            }
        });
        newGame.setAccelerator(KeyStroke.getKeyStroke(MenuKeyEvent.VK_F2, 0));
        game.add(newGame);

        tutorial = new JMenuItem("Tutorial");
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog tutorial = new JDialog();
                tutorial.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                JLabel text = new JLabel("tutorial");
                tutorial.getContentPane().add(text);
                tutorial.pack();
                tutorial.setVisible(true);
            }
        });
        help.add(tutorial);

        about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog about = new JDialog();
                about.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                JLabel text = new JLabel("about");
                about.getContentPane().add(text);
                about.pack();
                about.setVisible(true);
            }
        });
        help.add(about);
    }

    public void setStartPanel()
    {
        panel.removeAll();
        String levelPath = "levels/levelList.lst";
        frame.setSize(400, 400);

        comboBox = new JComboBox<String>();
        JButton button = new JButton("play");
        comboBox.setModel(new GameListModel(levelPath));

        panel.setLayout(new BorderLayout(5, 5));
        JPanel midPanel = new JPanel();
        panel.add(midPanel);
        midPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        midPanel.add(comboBox);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });
        midPanel.add(/*BorderLayout.EAST,*/ button);

        panel.add(BorderLayout.SOUTH, new JTable(controller.getTableModel()));

        JTable table = new JTable();

        return;
    }

    public String getSelectedLevel()
    {
        return (String)comboBox.getSelectedItem();
    }

    public void updateInfoPanel(Score score)
    {
        scorePanel.removeAll();
        scorePanel.setLayout(new GridLayout(2, 1));
        scorePanel.add(new JLabel("Time: " + score.getTime()));
        scorePanel.add(new JLabel("Steps: " + score.getStepNumber()));
        scorePanel.revalidate();
        scorePanel.repaint();
    }

    void setInfoPanel()
    {
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add("North", new JLabel("Game info: "));
        scorePanel = new JPanel();
        infoPanel.add("Center", scorePanel);
    }

    public void setPlayPanel()
    {
        this.panel.removeAll();
        gamePanel.removeAll();
        modelComponent = new ModelComponent(model);
        gamePanel.setLayout(new GridLayout(1, 1));
        gamePanel.add(modelComponent);
        setInfoPanel();

        this.panel.setLayout(new BorderLayout());
        this.panel.add("Center", gamePanel);
        this.panel.add("East", infoPanel);

        this.panel.revalidate();
        this.panel.repaint();
    }

    public void addKeyListener(KeyListener listener)
    {
        frame.addKeyListener(listener);
        System.out.print("view.addKeyListener\n");
    }

    public void printError(String msg)
    {
        JDialog dialog = new JDialog();
        JLabel label = new JLabel(msg);
        dialog.add(label);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialog.setSize(300, 100);
        dialog.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        gamePanel.revalidate();
        gamePanel.repaint();

        if(model.check())
        {
            System.out.println("WIN!");
            String username = JOptionPane.showInputDialog(frame, "You win. Enter your name:");
            System.out.println(username);
            controller.endGame(username);
        }
    }
}
