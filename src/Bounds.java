import java.awt.*;

public class Bounds extends Rectangle {
    public Bounds(int x, int y, int w, int h){
        setBounds(x, y, w, h);
    }

    public void render(Graphics g, int w, int h){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, w, h);
    }

    //Kollar ifall Player intersectar bounds.
    public static void checkBounds(Player player, Bounds bounds) {
        if(player.intersects(bounds)){
            if(bounds == Game.wallLeft){
                player.left = false;
                player.x++;
            }
            else if(bounds == Game.wallRight){
                player.right = false;
                player.x--;
            }
            else if(bounds == Game.roof){
                player.up = false;
                player.y++;
            }
            else{
                player.down = false;
                player.y--;
            }
        }
    }
}
