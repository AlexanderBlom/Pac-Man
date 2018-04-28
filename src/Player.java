import java.awt.*;

public class Player extends Rectangle {


    public boolean right,left,up,down;
    private int speed = 1;

    public Player(int x,int y){
        setBounds(x, y, 50, 50);
    }

    public void tick(){
        if(right) {
            x+=speed;
        }
        if(left){
            x-=speed;
        }
        if(up){
            y-=speed;
        }
        if(down){
            y+=speed;
        }

    }

    public void render(Graphics g){
        g.setColor((Color.YELLOW));
        g.fillOval(x, y,50,50);
    }

}
