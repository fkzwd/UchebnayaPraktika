package vk.com.dwzkf.logic;

import vk.com.dwzkf.builder.GraphicBuilder;
import vk.com.dwzkf.gui.BotMenu;
import vk.com.dwzkf.gui.GraphicForm;
import vk.com.dwzkf.gui.TopMenu;
import vk.com.dwzkf.model.GraphicPoints;
import vk.com.dwzkf.model.MyDecide;

import java.util.ArrayList;

public class Controller {
    private GraphicBuilder graphicBuilder = new GraphicBuilder();
    private TopMenu topMenu = new TopMenu(this);
    private GraphicForm graphicForm = new GraphicForm();
    private BotMenu botMenu = new BotMenu(this);
    private int from = -5;
    private int to = 5;

    public Controller(){

    }

    public GraphicBuilder getGraphicBuilder() {
        return graphicBuilder;
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public GraphicForm getGraphicForm() {
        return graphicForm;
    }

    public BotMenu getBotMenu() {
        return botMenu;
    }

    public void setPriceX(int priceX){
        graphicForm.setPriceX(priceX);
        graphicBuilder.setFromTo(-20*priceX,20*priceX);
    }

    public void setPriceY(int priceY){
        graphicForm.setPriceY(priceY);
    }

    public void setFunction(String function){
        graphicBuilder.setFunction(function);
        graphicForm.setGraphicPoints(graphicBuilder.getPoints());
        setInterval(from,to);
    }

    public void setInterval(int from, int to){
        ArrayList<GraphicPoints> verticalLines = new ArrayList<>();
        this.from = from;
        this.to = to;
        Double yFrom = graphicBuilder.getY(from);
        Double yTo = graphicBuilder.getY(to);
        if (yFrom!=null & yTo!=null) {
            verticalLines.add(new GraphicPoints((double) from, yFrom));
            verticalLines.add(new GraphicPoints((double) to, yTo));
        }
        graphicForm.setVerticalLines(verticalLines);
        repaint();
    }

    private void setDecide(Double decide){
        botMenu.setDecide(decide);
    }

    private void setIterations(int iterations){
        botMenu.setIterations(iterations);
    }

    public void findDecide(int from, int to, double epsilon){
        DecideFinder finder = new DecideFinder(from, to, graphicBuilder);
        finder.setEpsilon(epsilon);
        finder.setResultListener(new OnResultListener<MyDecide>() {
            @Override
            public void onResult(MyDecide result) {
                if (result!=null) {
                    setDecide(result.getDecide());
                    setIterations(result.getIterations());
                }
                else {
                    setDecide(null);
                    setIterations(0);
                }
            }
        });
        finder.start();
    }

    public Double getY(double x){
        if (graphicBuilder!=null)
        return graphicBuilder.getY(x);
        else return 0.0;
    }

    public void repaint(){
        graphicForm.repaint();
    }
}
