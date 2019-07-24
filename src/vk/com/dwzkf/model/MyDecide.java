package vk.com.dwzkf.model;

public class MyDecide {
    private Double decide;
    private int iterations;

    public MyDecide(Double decide, int iterations){
        this.decide = decide;
        this.iterations = iterations;
    }

    public Double getDecide() {
        return decide;
    }

    public int getIterations() {
        return iterations;
    }
}
