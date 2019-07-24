package vk.com.dwzkf.model;

public class GraphicPoints {
    private Double x;
    private Double y;

    public GraphicPoints(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        if (x==null) return 0.0;
        return x;
    }

    public Double getY() {
        return y;
    }

    public String toString(){
        return "x = " + x +" y = " + y;
    }
}
