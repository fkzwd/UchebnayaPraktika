package vk.com.dwzkf.gui;

import vk.com.dwzkf.helper.Helper;
import vk.com.dwzkf.logic.Controller;
import vk.com.dwzkf.model.GraphicPoints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotMenu extends JComponent {
    private Controller controller;

    private JLabel labelIntervalFrom = new JLabel("Interval from ");
    private JTextField textFrom = new JTextField("-5",3);
    private JLabel labelIntervalTo = new JLabel(" to ");
    private JTextField textTo = new JTextField("5",3);
    private JButton buttonFind = new JButton("Find decide");
    private JLabel labelDecide = new JLabel("y = 0 when x = NULL");
    private JLabel labelIterations = new JLabel("Iterations = 0");
    private JLabel labelEpsilon = new JLabel("Epsilon = ");
    private JTextField textEpsilon = new JTextField("0.0001",5);

    public BotMenu(Controller controller) {
        super();
        this.controller = controller;
        setLayout(new FlowLayout());
        addComponents();
    }

    public BotMenu() {
        super();
        setLayout(new FlowLayout());
        addComponents();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    private void addComponents(){
        add(labelIntervalFrom);
        add(textFrom);
        add(labelIntervalTo);
        add(textTo);
        add(labelEpsilon);
        add(textEpsilon);
        add(buttonFind);
        add(labelDecide);
        add(labelIterations);

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int from = Helper.getInt(textFrom.getText());
                int to = Helper.getInt(textTo.getText());
                double epsilon = Helper.getDouble(textEpsilon.getText());
                if (epsilon==0.0) epsilon = 0.0001;
                if (controller!=null) {
                    controller.findDecide(from, to, epsilon);
                    controller.setInterval(from, to);
                }
            }
        });
    }

    public void setDecide(Double decide){
        String string;
        string = decide!=null ? String.valueOf(decide) : "null";
        labelDecide.setText("y = 0 when x = "+string);
    }

    public void setIterations(int iterations){
        labelIterations.setText("Iterations = "+iterations);
    }
}
