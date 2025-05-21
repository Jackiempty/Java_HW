import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, KeyListener {
    private final int WIDTH = 800, HEIGHT = 600;
    private Thread thread;
    private boolean running = false;

    private Player player;
    private Map map;
    private Renderer renderer;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        map = new Map();
        player = new Player(2.5, 2.5, 0);
        renderer = new Renderer(WIDTH, HEIGHT, map, player);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (running) {
            repaint();
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g);
    }

    // 處理鍵盤
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.turn(-0.05);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.turn(0.05);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}