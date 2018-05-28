import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Klass för att ladda in bilder


public class ImageLoader {

    public static BufferedImage loadImage (String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new BufferedImage((0),0,0);
    }
}
