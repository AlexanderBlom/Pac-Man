import java.awt.*;

//Renderar Player och styr rörelse.

public class Player extends Rectangle {
    public boolean right,left,up,down;


    Player(int x, int y){
        setBounds(x, y, 32, 32);
    }

    //Kollar vilket håll Player ska röra sig
    public void tick(){
        int speed = 1;
        if(right) {
            x+= speed;
        }
        if(left){
            x-= speed;
        }
        if(up){
            y-= speed;
        }
        if(down){
            y+= speed;
        }

    }

    public void render(Graphics g){
        g.setColor((Color.YELLOW));
        g.fillOval(x, y,32,32);
    }
}
