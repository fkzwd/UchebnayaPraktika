package vk.com.dwzkf.helper;

import java.awt.*;

public class ScreenHelper {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Dimension screenSize = toolkit.getScreenSize();
    public static final int height = screenSize.height;
    public static final int width = screenSize.width;
    public static final int resolution = toolkit.getScreenResolution();
    public static final int offset = height>width ? height/resolution/(5) : width/resolution/(5);

    private ScreenHelper() {

    }
}
