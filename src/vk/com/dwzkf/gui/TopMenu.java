package vk.com.dwzkf.gui;

import vk.com.dwzkf.helper.Helper;
import vk.com.dwzkf.logic.Controller;
import vk.com.dwzkf.model.GraphicPoints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TopMenu extends JComponent {
    private Controller controller;

    public TopMenu(Controller controller){
        super();
        this.controller = controller;
        setLayout(new FlowLayout());
        addComponents();
    }

    public TopMenu(){
        super();
        setLayout(new FlowLayout());
        addComponents();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void addComponents(){
        JLabel labelFunction = new JLabel("Function: ");
        JTextField textFunction = new JTextField("3x^3+5x",15);
        JLabel labelPriceX = new JLabel("Price X: ");
        JTextField textPriceX = new JTextField("1",2);
        JLabel labelPriceY = new JLabel("Price Y: ");
        JTextField textPriceY = new JTextField("1",2);
        JButton buttonDraw = new JButton("Draw");

        buttonDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String function = textFunction.getText();
                int priceX = Helper.getInt(textPriceX.getText());
                int priceY = Helper.getInt(textPriceY.getText());
                if (controller!=null) {
                    controller.setPriceX(priceX);
                    controller.setPriceY(priceY);
                    controller.setFunction(function);
                    controller.repaint();
                }
            }
        });

        add(labelPriceX);
        add(textPriceX);
        add(labelPriceY);
        add(textPriceY);
        add(labelFunction);
        add(textFunction);
        add(buttonDraw);
    }
}
