package vk.com.dwzkf.gui;

import vk.com.dwzkf.helper.ScreenHelper;
import vk.com.dwzkf.logic.Controller;

import javax.swing.*;
import java.awt.*;

public class ApplicationGUI extends JFrame {

    public ApplicationGUI(){
        configure();
    }

    private void configure(){
        int width = (int) (ScreenHelper.width/1.3);
        int height = (int) (ScreenHelper.height/1.3);
        setBounds(ScreenHelper.width/2-width/2, ScreenHelper.height/2-height/2, width,height);
        setLayout(new BorderLayout());
        Controller controller = new Controller();

        add(controller.getTopMenu(), BorderLayout.NORTH);
        add(controller.getGraphicForm());
        add(controller.getBotMenu(), BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
