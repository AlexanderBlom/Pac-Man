import java.awt.*;

public class Bounds extends Rectangle {
    public Bounds(int x, int y, int w, int h){
        setBounds(x, y, w, h);
    }

    public void render(Graphics g, int w, int h){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, w, h);
    }
}
