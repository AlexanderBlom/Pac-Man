import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/*
Alexander Blom
2018-04-??
Pac-Man
*/
public class Game extends Canvas implements Runnable,KeyListener {

    private boolean isRunning = false;

    //Spel variabler
    public static final int width = 1000, height = 1000;
    public static final String title = "Pac-Man";

    private Thread t;

    //Objekt
    public static Player player;
    public static Bounds wallLeft, wallRight, roof, floor;


    public Game(){
        Dimension dimension = new Dimension(Game.width, Game.height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        addKeyListener(this);

        //Ger värde till spelobjekten.
        player = new Player(300, 300);
        wallLeft = new Bounds(0, 0 , 5, 1000);
        wallRight = new Bounds(995,0, 5, 1000);
        roof = new Bounds(0, 0, 1000,5);
        floor = new Bounds(0, 995, 1000, 5);
    }

    public synchronized void start(){
        if(isRunning) return;
        Assets.init();
        isRunning = true;
        t = new Thread(this);
        t.start();
    }


    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTick = System.nanoTime();
        int fps = 60;
        long dt = 1000000000 / fps;

        while(isRunning) {
            if(System.nanoTime() - lastTick > dt) {
                tick();
                render();
            }
        }
        stop();
    }

    private void tick() {
        player.tick();
        Bounds.checkBounds(player, wallRight);
        Bounds.checkBounds(player, wallLeft);
        Bounds.checkBounds(player, floor);
        Bounds.checkBounds(player, roof);
    }


    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();


        g.setColor((Color.BLACK));
        g.fillRect(0, 0, Game.width, Game.height);

        player.render(g);
        wallLeft.render(g, 5, 1000);
        wallRight.render(g, 5, 1000);
        roof.render(g, 1000, 5);
        floor.render(g, 1000, 5);

        Tile.tiles[0].render(g, 200, 200);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle(Game.title);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setFocusableWindowState(true);
        frame.setBackground(Color.BLACK);

        frame.setVisible(true);

        game.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();

        if(keys == KeyEvent.VK_D || keys == KeyEvent.VK_RIGHT){
            player.right = true;
            player.left = false;
            player.up = false;
            player.down = false;
        }

        if(keys == KeyEvent.VK_A || keys == KeyEvent.VK_LEFT){
            player.right = false;
            player.left = true;
            player.up = false;
            player.down = false;
        }

        if(keys == KeyEvent.VK_W || keys == KeyEvent.VK_UP){
            player.right = false;
            player.left = false;
            player.up = true;
            player.down = false;
        }

        if(keys == KeyEvent.VK_S || keys == KeyEvent.VK_DOWN) {
            player.right = false;
            player.left = false;
            player.up = false;
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
