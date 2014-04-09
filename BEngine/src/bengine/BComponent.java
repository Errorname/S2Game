package bengine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
//import java.io.IOException;
import java.util.*;

import javax.swing.*;

import bengine.gui.*;
import bengine.gui.Button;
import bengine.screen.*;
import bengine.level.*;
import bengine.entity.*;

public class BComponent extends Canvas implements Runnable, MouseMotionListener,MouseListener,ButtonListener,KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = GAME_WIDTH * 3 / 4;
    public static final int SCALE = 1;
    private boolean running = true;
    private Cursor emptyCursor;
    private double framerate = 60;
    private int fps;
    
    private Screen screen = new Screen(GAME_WIDTH, GAME_HEIGHT);
    private Level level;
    
    private Stack<GuiMenu> menuStack = new Stack<GuiMenu>();
    
    private boolean mouseMoved = false;
    private boolean mouseHidden = false;
    private int mouseHideTime = 0;
    
    public MouseButtons mouseButtons = new MouseButtons();
    public Keys keys = new Keys();
    public Keys[] synchedKeys = {
            new Keys(), new Keys()
    };

    public Player player;
    
    /*public static SoundPlayer soundPlayer;*/
    
    public BComponent() {
    	this.setPreferredSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMinimumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMaximumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.addKeyListener(new InputHandler(keys));
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        TitleMenu menu = new TitleMenu(GAME_WIDTH, GAME_HEIGHT);
        addMenu(menu);
        addKeyListener(this);
    }
    
    public void mouseDragged(MouseEvent arg0) {
        mouseMoved = true;
    }

    public void mouseMoved(MouseEvent arg0) {
        mouseMoved = true;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseButtons.releaseAll();
    }

    public void mousePressed(MouseEvent e) {
        mouseButtons.setNextState(e.getButton(), true);
    }

    public void mouseReleased(MouseEvent e) {
        mouseButtons.setNextState(e.getButton(), false);
    }
    
    public void paint(Graphics g) {
    }

    public void update(Graphics g) {
    }
    
    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
    
    public void stop() {
        running = false;
        /*soundPlayer.shutdown();*/
    }
    
    private void init() {
        /*soundPlayer = new SoundPlayer();
        soundPlayer.startBackgroundMusic();*/

        try {
            emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        setFocusTraversalKeysEnabled(false);
        requestFocus();

    }
    
    private synchronized void createLevel() {
        try {
            level = new Level();
            level.fromFile("/levels/1.map");
        } catch (Exception ex) {
            throw new RuntimeException("Unable to load level", ex);
        }

        level.init();

        /*players[0] = new Player(synchedKeys[0], level.width * Tile.WIDTH / 2 - 16, (level.height - 5 - 1) * Tile.HEIGHT - 16, Team.Team1);
        players[0].setFacing(4);
        level.addEntity(players[0]);
        level.addEntity(new Base(34 * Tile.WIDTH, 7 * Tile.WIDTH, Team.Team1));
        if (isMultiplayer) {
            players[1] = new Player(synchedKeys[1], level.width * Tile.WIDTH / 2 - 16, 7 * Tile.HEIGHT - 16, Team.Team2);
//            players[1] = new Player(synchedKeys[1], 10, 10);
            level.addEntity(players[1]);
            level.addEntity(new Base(32 * Tile.WIDTH - 20, 32 * Tile.WIDTH - 20, Team.Team2));
        }
        player = players[localId];
        player.setCanSee(true);*/
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        int frames = 0;
        long lastTimer1 = System.currentTimeMillis();

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int toTick = 0;

        long lastRenderTime = System.nanoTime();
        int min = 999999999;
        int max = 0;

        while (running) {
            if (!this.hasFocus()) {
                keys.release();
            }

            double nsPerTick = 1000000000.0 / framerate;
            boolean shouldRender = false;
            while (unprocessed >= 1) {
                toTick++;
                unprocessed -= 1;
            }

            int tickCount = toTick;
            if (toTick > 0 && toTick < 3) {
                tickCount = 1;
            }
            if (toTick > 20) {
                toTick = 20;
            }

            for (int i = 0; i < tickCount; i++) {
                toTick--;
                tick();
                shouldRender = true;
            }

            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                continue;
            }
            if (shouldRender) {
                frames++;
                Graphics g = bs.getDrawGraphics();

                render(g);

                long renderTime = System.nanoTime();
                int timePassed = (int) (renderTime - lastRenderTime);
                if (timePassed < min) {
                    min = timePassed;
                }
                if (timePassed > max) {
                    max = timePassed;
                }
                lastRenderTime = renderTime;
            }

            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                if (bs != null) {
                    bs.show();
                }
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                fps = frames;
                frames = 0;
            }
        }
    }
    
    private synchronized void render(Graphics g) {
        /*if (level != null) {
            int xScroll = (int) (player.pos.x - screen.w / 2);
            int yScroll = (int) (player.pos.y - (screen.h - 24) / 2);
            soundPlayer.setListenerPosition((float) player.pos.x, (float) player.pos.y);
            level.render(screen, xScroll, yScroll);
        }*/
    	
        if (!menuStack.isEmpty()) {
            menuStack.peek().render(screen);
        }

        /*Font.draw(screen, "FPS: " + fps, 10, 10);
        
        if (player != null && menuStack.size() == 0) {
            Font.draw(screen, player.health + " / 10", 340, screen.h - 19);
            Font.draw(screen, "" + player.score, 340, screen.h - 33);
        }*/

        g.setColor(Color.BLACK);

        g.fillRect(0, 0, getWidth(), getHeight());
        g.translate((getWidth() - GAME_WIDTH * SCALE) / 2, (getHeight() - GAME_HEIGHT * SCALE) / 2);
        g.clipRect(0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE);

        if (!menuStack.isEmpty()/* || level != null*/) {
            g.drawImage(screen.image, 0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE, null);
        }

    }
    
    private void tick() {
        /*if (level != null) {
            if (level.player1Score >= Level.TARGET_SCORE) {
                addMenu(new WinMenu(GAME_WIDTH, GAME_HEIGHT, 1));
                level = null;
                return;
            }
            if (level.player2Score >= Level.TARGET_SCORE) {
                addMenu(new WinMenu(GAME_WIDTH, GAME_HEIGHT, 2));
                level = null;
                return;
            }
        }
        if (level != null) {
            if (synchronizer.preTurn()) {
                synchronizer.postTurn();
                for (int index = 0; index < keys.getAll().size(); index++) {
                    Keys.Key key = keys.getAll().get(index);
                    boolean nextState = key.nextState;
                    if (key.isDown != nextState) {
                        synchronizer.addCommand(new ChangeKeyCommand(index, nextState));
                    }
                }
                keys.tick();
                for (Keys skeys : synchedKeys) {
                    skeys.tick();
                }
                level.tick();
            }
        }*/
        mouseButtons.setPosition(getMousePosition());
        if (!menuStack.isEmpty()) {
            menuStack.peek().tick(mouseButtons);
        }
        if (mouseMoved) {
            mouseMoved = false;
            mouseHideTime = 0;
            if (mouseHidden) {
                mouseHidden = false;
                setCursor(null);
            }
        }
        if (mouseHideTime < 60) {
            mouseHideTime++;
            if (mouseHideTime == 60) {
                setCursor(emptyCursor);
                mouseHidden = true;
            }
        }
        mouseButtons.tick();
    }
    
	public static void main(String[] args) {
		BComponent bc = new BComponent();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(bc);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        bc.start();
	}
	
	public void buttonPressed(Button button) {
        if (button.getId() == TitleMenu.RESTART_GAME_ID) {
            clearMenus();
            TitleMenu menu = new TitleMenu(GAME_WIDTH, GAME_HEIGHT);
            addMenu(menu);


        } else if (button.getId() == TitleMenu.START_GAME_ID) {
            clearMenus();

            createLevel();
        }  else if (button.getId() == TitleMenu.EXIT_GAME_ID) {
            System.exit(0);
        }
    }
	
	private void clearMenus() {
        while (!menuStack.isEmpty()) {
            menuStack.pop();
        }
    }

    private void addMenu(GuiMenu menu) {
        menuStack.add(menu);
        menu.addButtonListener(this);
    }

    @SuppressWarnings("unused")
	private void popMenu() {
        if (!menuStack.isEmpty()) {
            menuStack.pop();
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (!menuStack.isEmpty()) {
            menuStack.peek().keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (!menuStack.isEmpty()) {
            menuStack.peek().keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e) {
        if (!menuStack.isEmpty()) {
            menuStack.peek().keyTyped(e);
        }
    }
}
