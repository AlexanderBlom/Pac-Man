
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable,KeyListener {

    private boolean isRunning = false;

    public static final int width = 1000, height = 1000;
    public static final String title = "Pac-Man";

    private Thread t;

    public static Player player;

    public static Bounds wall, wall2, roof, floor;

    private BufferedImage testImage;

    public Game(){
        Dimension dimension = new Dimension(Game.width, Game.height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        addKeyListener(this);
        player = new Player(300, 300);
        wall = new Bounds(10, 10 , 5, 600);
        wall2 = new Bounds(600,10, 5, 600);
        roof = new Bounds(10, 10, 600 ,5);
        floor = new Bounds(10, 600, 600, 5);
    }

    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        t = new Thread(this);
        t.start();
        testImage = ImageLoader.loadImage("/test4.png");
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
        long lastUpdate = System.nanoTime();
        long lastTick = System.nanoTime();
        int fps = 60;
        int ups = 60;
        long dt = 1000000000 / fps;
        long dt2 = 1000000000 / ups;

        while(isRunning) {
            if(System.nanoTime() - lastTick > dt2) {
                tick();
                render();
            }
        }
        stop();
    }

    private void tick() {
        player.tick();
       /* checkWalls(player, wall2);
        checkWalls(player, wall);
        checkBounds(player, floor);
        checkBounds(player, roof);
    */}

  /*  private void checkWalls(Player player, Bounds bounds) {
        if(player.intersects(bounds)) {
            player.right = false;
            player.left = false;
        }
    }

    private void checkBounds(Player player, Bounds bounds) {
        if(player.intersects(bounds)){
            player.up = false;
            player.down = false;
        }
    }
*/
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor((Color.BLACK));
        g.fillRect(0, 0, Game.width, Game.height);

        g.setColor(Color.GRAY);
        for(int y = 1; y < 12; y++){
            for(int x = 1; x < 12; x++){
                g.fillRect(x * 50, y * 50, 50, 1);
                g.fillRect(x * 50, y * 50, 1, 50);
                g.fillRect((x + 1) * 50, y* 50, 1, 50);
            }
        }

        //g.drawImage(testImage, 0, 0, null);

        player.render(g);
        wall.render(g, 5, 600);
        wall2.render(g, 5, 600);
        roof.render(g, 600, 5);
        floor.render(g, 600, 5);

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

        frame.setVisible(true);

        game.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();

        if(keys == KeyEvent.VK_D){
            player.right = true;
            player.left = false;
            player.up = false;
            player.down = false;
        }

        if(keys == KeyEvent.VK_A){
            player.right = false;
            player.left = true;
            player.up = false;
            player.down = false;
        }

        if(keys == KeyEvent.VK_W){
            player.right = false;
            player.left = false;
            player.up = true;
            player.down = false;
        }

        if(keys == KeyEvent.VK_S) {
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
