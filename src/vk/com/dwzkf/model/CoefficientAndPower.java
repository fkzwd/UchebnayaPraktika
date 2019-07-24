package vk.com.dwzkf.model;

public class CoefficientAndPower {
    private double coefficient;
    private int power;

    public CoefficientAndPower(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }
}
