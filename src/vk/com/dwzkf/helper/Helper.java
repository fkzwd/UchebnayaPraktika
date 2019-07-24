package vk.com.dwzkf.helper;

public class Helper {
    private Helper(){

    }

    public static int getInt(String string){
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            return 1;
        }
    }

    public static Integer getInteger(String string){
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public static double getDouble(String string){
        try {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
