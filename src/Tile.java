import java.awt.*;
import java.awt.image.BufferedImage;

//Skapar tiles och ger de id

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static TestTile testTile = new TestTile(0);

    public static final int tileWidth = 32, tileHeight = 32;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
        System.out.println("Tile Created");
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, tileWidth, tileHeight, null);
    }
}