package utils;

import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class GetData {
    public static Image getImage(String source) {
        return new Image(ClassLoader.getSystemResource(source).toString());
    }
    public static Font getFont(String source, double size) {
        return Font.loadFont(ClassLoader.getSystemClassLoader().getResourceAsStream(source),size);
    }
}
