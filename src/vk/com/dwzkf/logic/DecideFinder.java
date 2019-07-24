package vk.com.dwzkf.logic;

import vk.com.dwzkf.builder.GraphicBuilder;
import vk.com.dwzkf.model.MyDecide;

public class DecideFinder extends Finder<MyDecide> {
    private int from;
    private int to;
    private GraphicBuilder builder;
    private double epsilon = 0.0001;

    public DecideFinder(int from, int to, GraphicBuilder builder) {
        this.from = from;
        this.to = to;
        this.builder = builder;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setBuilder(GraphicBuilder builder) {
        this.builder = builder;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public MyDecide operation() {
        long timeStart = System.currentTimeMillis();
        int iterations = 0;
        Double a = (double) from;
        Double c = (double) to;
        Double b = (a+c)/2.0;
        Double first;
        Double mid;
        Double second;
        if (builder.isBuild()) {
            while (true) {
                iterations++;
                if (System.currentTimeMillis() - timeStart > 5000) break;
                first = builder.getY(a);
                mid = builder.getY(b);
                second = builder.getY(c);
                if ((Math.abs(mid) <= epsilon && (b-a<=epsilon) || mid==0.0)) return new MyDecide(b,iterations);
                if (first * mid < 0) {
                    c = b;
                    b = (a + c) / 2.0;
                } else if (mid * second < 0) {
                    a = b;
                    b = (a + c) / 2.0;
                } else break;
            }
        }
        return null;
    }
}
