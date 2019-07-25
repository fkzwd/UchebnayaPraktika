package vk.com.dwzkf.gui;

import vk.com.dwzkf.helper.ScreenHelper;
import vk.com.dwzkf.model.GraphicPoints;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicForm extends JComponent {

    private int priceX = 1;
    private int priceY = 1;
    private final int cols = 20;
    private final int rows = 20;
    private int centerX;
    private int centerY;
    private double offset = ScreenHelper.offset;
    private ArrayList<GraphicPoints> points = new ArrayList<>();
    private ArrayList<GraphicPoints> verticalLines = new ArrayList<>();

    public GraphicForm() {
        super();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resetParams();
        drawOXY(g);
        drawSegmentation(g);
        drawGraphic(g);
        drawVerticalLines(g);
    }

    private void drawOXY(Graphics g) {
        int offset = ScreenHelper.height*ScreenHelper.width/(400*400);
        //Draw lines X & Y
        g.drawLine(0,centerY, centerX*2, centerY);
        g.drawLine(centerX, 0, centerX, centerY*2);

        //Draw point Y
        g.drawLine(centerX-offset, offset, centerX, 0);
        g.drawLine(centerX+offset, offset, centerX, 0);
        drawNewString(g, Color.RED, "Y", -priceX, priceY*(rows-0.5));

        //Draw point X
        g.drawLine(centerX*2 - offset, centerY-offset, centerX*2, centerY);
        g.drawLine(centerX*2 - offset, centerY+offset, centerX*2, centerY);
        drawNewString(g, Color.RED, "X", priceX*(cols-0.5), -priceY);
    }

    private void drawSegmentation(Graphics g) {
        for (int i = -cols+1; i<cols; i++) {
            g.drawLine(centerX+i*centerX/cols, (int) (centerY-offset), centerX+i*centerX/cols, (int) (centerY+offset));
        }
        for (int i = -rows+1; i<rows; i++) {
            g.drawLine((int) (centerX+offset), centerY-i*centerY/rows, (int) (centerX-offset), centerY-i*centerY/rows);
        }
        drawNewString(g, Integer.toString(priceY), priceX/2.0, priceY);
        drawNewString(g, Integer.toString(priceX), priceX, -priceY);
    }

    private void drawGraphic(Graphics g) {
        if (points.size()>=2) {
            for (int i = 0; i < points.size() - 1; i++) {
                drawNewLine(g, points.get(i).getX(),
                        points.get(i).getY(),
                        points.get(i + 1).getX(),
                        points.get(i + 1).getY());
            }
        }
    }

    private void drawVerticalLines(Graphics g) {
        if (verticalLines.size()>0) {
            for (int i = 0; i<verticalLines.size(); i++) {
                drawNewLine(g, Color.GRAY, verticalLines.get(i).getX(), 0,
                                verticalLines.get(i).getX(), verticalLines.get(i).getY());
                drawNewString(g, Color.RED, Double.toString(verticalLines.get(i).getX()),
                                verticalLines.get(i).getX(), priceY * (verticalLines.get(i).getY()>=0.0? -1 : 1));
            }
        }
    }

    private void drawNewLine(Graphics g, double x1, double y1, double x2, double y2) {
        g.drawLine(getX(x1),getY(y1),getX(x2),getY(y2));
    }

    private void drawNewLine(Graphics g, Color color, double x1, double y1, double x2, double y2) {
        Color color1 = g.getColor();
        g.setColor(color);
        drawNewLine(g,x1,y1,x2,y2);
        g.setColor(color1);
    }

    private void drawNewString(Graphics g, String string, double x, double y) {
        FontMetrics fm = g.getFontMetrics();
        int offsetX = fm.stringWidth(string)/2;
        int offsetY = (fm.getHeight()-fm.getDescent())/2;
        g.drawString(string, getX(x) - offsetX, getY(y) + offsetY);
    }

    private void drawNewString(Graphics g, Color color, String string, double x, double y) {
        Color color1 = g.getColor();
        g.setColor(color);
        drawNewString(g,string,x,y);
        g.setColor(color1);
    }

    private void resetParams() {
        centerX = getWidth()/2;
        centerY = getHeight()/2;
    }

    public void setPriceX(int priceX) {
        if (priceX>=1) {
            this.priceX = priceX;
            repaint();
        }
    }

    public void setPriceY(int priceY) {
        if (priceY>=1) {
            this.priceY = priceY;
            repaint();
        }
    }

    public void setGraphicPoints(ArrayList<GraphicPoints> points) {
        this.points.clear();
        if (points!=null)
        this.points.addAll(points);
        repaint();
    }

    public void setVerticalLines(ArrayList<GraphicPoints> verticalLines) {
        this.verticalLines.clear();
        if (verticalLines!=null) {
            this.verticalLines.addAll(verticalLines);
        }
    }

    private int getX(double x){
        return centerX + (int) ((double) centerX/cols*x/priceX);
    }

    private int getY(double y){
        return centerY - (int) ((double) centerY/rows*y/priceY);

    }
}
