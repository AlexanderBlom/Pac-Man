import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage test;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sheet.png"));

        test = sheet.crop(0, 0, width, height);
    }
}
