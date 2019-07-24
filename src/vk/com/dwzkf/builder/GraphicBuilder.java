package vk.com.dwzkf.builder;

import vk.com.dwzkf.model.CoefficientAndPower;
import vk.com.dwzkf.model.GraphicPoints;
import java.lang.Math;

import java.util.ArrayList;

public class GraphicBuilder {
    private ArrayList<GraphicPoints> points = new ArrayList<GraphicPoints>();
    private double xFrom = -1;
    private double xTo = 1;
    private String function = "";
    private ArrayList<CoefficientAndPower> coefficientAndPowers = new ArrayList<>();


    public GraphicBuilder() {

    }

    public void setFunction(String function) {
        points.clear();
        if (isFunction(function)) {
            this.function = function;
            buildPoints();
        }
        else {
            this.function = "";
        }
    }

    private boolean isFunction(String function) {
        if (function==null) return false;
        boolean result = (function.matches("^\\d*(\\.\\d*)?[x]?(\\^\\d*)?" +
                "(([+,-]\\d*(\\.\\d*)?[x]?(\\^\\d*)?)?)*" +
                "([+,-]\\d*(\\.\\d*)?)?"));
        result = result && function.length()>0;

        return result;
    }

    public void setFromTo(int xFrom, int xTo) {
        if (xFrom>xTo) {
            this.xFrom = xTo;
            this.xTo = xFrom;
        }
        else {
            this.xFrom = xFrom;
            this.xTo = xTo;
        }
    }

    public Double getY(double x) {
        if (coefficientAndPowers.size()>0) {
            double result = 0.0;
            for (int i = 0; i < coefficientAndPowers.size(); i++) {
                result += coefficientAndPowers.get(i).getCoefficient() * Math.pow(x, coefficientAndPowers.get(i).getPower());
            }
            return result;
        }
        return null;
    }

    public ArrayList<GraphicPoints> getPoints() {
        return points;
    }

    public void buildPoints() {
        if (buildFunction()) {
            points.clear();
            for (double x = xFrom; x <= xTo; x += 0.1) {
                points.add(new GraphicPoints(x, getY(x)));
            }
        }
    }

    private boolean buildFunction() {
        coefficientAndPowers.clear();
        if (!isFunction(function)) return false;
        ArrayList<String> functions = new ArrayList<>();
        ArrayList<Integer> koofs = new ArrayList<>();

        String[] split = function.split("[+,-]");
        for (String string : split) {
            if (!"".equals(string)) {
                functions.add(string);
            }
        }
        split = function.split("[^[+,-]]");
        for (String string : split) {
            if (!string.isEmpty()) {
                koofs.add(Integer.parseInt(string + "1"));
            }
        }
        if (functions.size() > koofs.size()) koofs.add(0, 1);
        for (int i = 0; i < functions.size(); i++) {
            double[] coofAndPower = new double[2];
            split = functions.get(i).split("x\\^?");
            if (functions.get(i).contains("x")) coofAndPower[1] = 1;
            if (functions.get(i).contains("x^")) coofAndPower[1] = Integer.parseInt(split[1]);
            try {
                coofAndPower[0] = koofs.get(i) * Double.parseDouble(split[0]);
            } catch (Exception exception) {
                coofAndPower[0] = koofs.get(i);

            }
            coefficientAndPowers.add(new CoefficientAndPower(coofAndPower[0], (int) coofAndPower[1]));
        }
        return true;
    }

    public boolean isBuild(){
        return isFunction(function);
    }
}
