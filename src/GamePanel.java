import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static int WIDTH = 400;
	public static int HEIGHT = 400;

	private Thread thread;
	private boolean running;

	private boolean gameStart;

	private BufferedImage image; // double buffer
	private Graphics2D g; // paintbrush

	private int FPS = 30;
	private double averageFPS;

	public static Player player;
	public static HUD hud;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<BloodSplatter> bloodsplatter;
	public static ArrayList<PowerUp> powerup;

	private long waveStartTimer;
	private long waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay = 2000;

	private long playerBleedingTimer;
	private long playerBleedingDelay;

	private MakeSound sounds;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}

	@Override
	public void run() {

		running = true;
		gameStart = false;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		hud = new HUD();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		bloodsplatter = new ArrayList<BloodSplatter>();
		powerup = new ArrayList<PowerUp>();

		waveStartTimer = 0;
		waveStartTimerDiff = 0;
		waveStart = true;
		waveNumber = 0;

		playerBleedingTimer = System.nanoTime();
		playerBleedingDelay = 700; // can't get hit durring this time

		try {
			sounds = new MakeSound();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 30;

		long targetTime = 1000 / FPS;

		while (running) {

			startTime = System.nanoTime();

			gameUpdate();
			gameRender();
			gameDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;// CREATE OK !! create

			try { //getting along. 
				Thread.sleep(waitTime);
			} catch (Exception c) {

			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	private void gameUpdate() {

		// new wave
		if (waveStartTimer == 0 && enemies.size() == 0) {
			waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime();
		} else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay) {
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}

		// CREATE ENEMIES
		if (waveStart && enemies.size() == 0) {
			createNewEnemies();
		}

		// player
		player.update();
		
		//HUD
		hud.update();

		// BULLET UPDATE
		for (int i = 0; i < bullets.size(); i++) {
			boolean remove = bullets.get(i).update();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}
		
		// ENEMIES UPDATE
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}

		// CHECK BULLET COLLISION WITH ENEMIES
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			double bx = b.getx();
			double by = b.gety();
			for (int j = 0; j < enemies.size(); j++) {
				Enemy e = enemies.get(j);
				double ex = e.getx();
				double ey = e.gety();
				int x2 = e.getWidth();
				int y2 = e.getHeight();
				int et = e.getType();
				if ((bx > ex + 5 && bx < ex + x2 - 5)
						&& (by > ey + 5 && by < ey + y2 - 5)) { // 5 pxl into
					e.hit();
					Random randbloodtype = new Random();
					int randzombieblood = randbloodtype.nextInt((2 - 1) + 1) + 1; 
					bloodsplatter.add(new BloodSplatter((int) ex, (int) ey,	randzombieblood));
					bullets.remove(i);
					i--;
					sounds.deadZombie1();
					break;
				}
			}
		}

		// check for dead enemies
		for (int j = 0; j < enemies.size(); j++) {
			if (enemies.get(j).isDead()) {
				Enemy e = enemies.get(j);

				// POWERCHECKUPS
				// 1 is extra life, 2 is shotgun, 3 is machine gun 4 is laser 5
				// is nuke
				double rand = Math.random();
				// System.out.println(rand);
				if (rand < 0.001) { // abomb
					powerup.add(new PowerUp(5, (int) e.getx() + 15, (int) e
							.gety() + 7));
				} else if (rand < 0.01 && player.getGunType() != 4) {
					powerup.add(new PowerUp(4, (int) e.getx() + 15, (int) e
							.gety() + 7));
				} else if (rand < 0.02) {
					powerup.add(new PowerUp(1, (int) e.getx() + 15, (int) e
							.gety() + 7));
				} else if (rand < 0.03 && player.getGunType() != 2) {
					powerup.add(new PowerUp(2, (int) e.getx() + 15, (int) e
							.gety() + 7));
				} else if (rand < 0.04 && player.getGunType() != 3) {
					powerup.add(new PowerUp(3, (int) e.getx() + 15, (int) e
							.gety() + 7));
				}

				player.addScore(e.getRank() + e.getType());
				enemies.remove(j);
				j--;
			}
		}

		// check to remove old blood
		for (int i = 0; i < bloodsplatter.size(); i++) {
			if (bloodsplatter.get(i).isDead()) {
				bloodsplatter.remove(i);
				i--;
			}
		}

		// check to remove old Powerups
		for (int i = 0; i < powerup.size(); i++) {
			if (powerup.get(i).isDead()) {
				powerup.remove(i);
				i--;
			}
		}

		// player powerup collision
		int plx = player.getx() - 15;
		int ply = player.gety() - 15;
		int plh = player.getHeight() - 15;
		int plw = player.getWidth() - 15;
		Rectangle pr = new Rectangle(plx, ply, plh, plw);
		for (int i = 0; i < powerup.size(); i++) {
			PowerUp p = powerup.get(i);
			int pux = p.getx() - 15;
			int puy = p.gety() - 15;
			int puh = p.getHeight() - 15;
			int puw = p.getWidth() - 15;
			Rectangle pu = new Rectangle(pux, puy, puh, puw);
			if (pr.intersects(pu)) {
				int type = p.getType();
				// 1 is extra life, 2 is shotgun, 3 is machine gun 4 is laser 5
				// is nuke
				if (type == 1) {
					player.addLife();
					sounds.extraLife();
				}
				if (type == 2) {
					player.setGunType(2);
					sounds.shotgunReload();
					player.setFiringDelay(200);
				}
				if (type == 3) {
					player.setGunType(3);
					sounds.machinegunReload();
					player.setFiringDelay(100);
				}
				if (type == 4) {
					player.setGunType(4);
					player.setFiringDelay(500);
					sounds.lasergunReload();
				}
				if (type == 5) {
					sounds.aBomb();
					killEverything();
				}

				powerup.remove(i);
				i--;
			}
		}

		// player enemy collision
		if (!player.isRecovering()) {
			int px = player.getx() - 15;
			int py = player.gety() - 15;
			int ph = player.getHeight() - 15;
			int pw = player.getWidth() - 15;
			Rectangle r1 = new Rectangle(px, py, ph, pw);
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				int ex = (int) e.getx() - 15;
				int ey = (int) e.gety() - 15;
				int ew = e.getWidth() - 15;
				int eh = e.getHeight() - 15;

				Random randblood = new Random(); // this needs to be in
				int randomblood = randblood.nextInt((2 - 1) + 1) + 1; // 2 max
				Rectangle r2 = new Rectangle(ex, ey, eh, ew);
				if (r1.intersects(r2)) {
					if ((System.nanoTime() - playerBleedingTimer) / 1000000 > playerBleedingDelay) {
						player.setHealth(-25);;
						bloodsplatter.add(new BloodSplatter(px,
								py + 15, randomblood));
						playerBleedingTimer = System.nanoTime();
						sounds.playerHit();
					}
				}
			}
		}
	}

	private void gameRender() {
		// draw background
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.setColor(Color.BLACK);
		// g.drawString("FPS : " + averageFPS, 10, 10);
		// g.drawString("Bullets :" + bullets.size(), 10, 20);

		// draw bloodsplatter
		for (int i = 0; i < bloodsplatter.size(); i++) {
			bloodsplatter.get(i).draw(g);
		}

		// DRAW POWERUPS
		for (int i = 0; i < powerup.size(); i++) {
			powerup.get(i).draw(g);
		}

		// DRAW BULLETS
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}

		// DRAW ENEMIES
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// DRAW PLAYER
		player.draw(g);
		
		//DRAW HUD
		hud.draw(g);

		// draw wave number
		if (waveStartTimer != 0) {
			g.setFont(new Font("Century Gothic", Font.BOLD, 18));
			String s = "- W A V E   " + waveNumber + "   -";
			int length = (int) g.getFontMetrics().getStringBounds(s, g)
					.getWidth();
			int alpha = (int) (255 * Math.sin(3.14 * waveStartTimerDiff
					/ waveDelay));
			if (alpha > 255) {
				alpha = 255;
			}
			g.setColor(new Color(0, 0, 0, alpha));
			g.drawString(s, WIDTH / 2 - length / 2, HEIGHT / 2);
		}
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	private void killEverything() {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e;
			e = enemies.get(i);
			double ex = e.getx();
			double ey = e.gety();
			//e.kill();
			Random randbloodtype = new Random();
			int randzombieblood = randbloodtype.nextInt((2 - 1) + 1) + 1;
			bloodsplatter.add(new BloodSplatter((int) ex, (int) ey, randzombieblood));
			enemies.remove(i);
			i--;
		}
		
	}

	private void createNewEnemies() {

		enemies.clear();
		Enemy e;

		for (int i = 0; i < waveNumber * 4; i++) {
			Random rand = new Random();
			int randzombie = rand.nextInt((2 - 1) + 1) + 1;
			if (waveNumber > 0) {
				int Lvl1 = rand.nextInt((2 - 1) + 1) + 1;
				enemies.add(new Enemy(1, Lvl1));
			}
			if (waveNumber > 1 && waveNumber > 4) {
				int lvl2 = rand.nextInt((2 - 1) + 1) + 1;
				int zombietype = rand.nextInt((2 - 1) + 1) + 1;
				enemies.add(new Enemy(zombietype, lvl2));
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
	}

	@Override
	public void keyPressed(KeyEvent key) {
		//int keyCode = key.getKeyCode();
		
		switch (key.getKeyCode()){
		
		case KeyEvent.VK_UP:
			player.setUp(true);
			if (player.getRight()) {
				player.setUpRight(true);
			}
			if (player.getLeft()) {
				player.setUpLeft(true);
			}
			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(true);
			if (player.getUp()) {
				player.setUpRight(true);
			}
			if (player.getDown()){
				player.setDownRight(true);
			}
			break;
		case KeyEvent.VK_LEFT:
			player.setLeft(true);
			if (player.getUp()) {
				player.setUpLeft(true);
			}
			if (player.getDown()){
				player.setDownLeft(true);
			}
			break;
		case KeyEvent.VK_DOWN:
			player.setDown(true);
			if (player.getRight()){
				player.setDownRight(true);
			}
			if (player.getLeft()){
				player.setDownLeft(true);
			}
			break;
		case KeyEvent.VK_F:
			player.setFiring(true);
			System.out.println("FIRING");
			if (player.getGunType() == 1) {
				sounds.pistol();
			} else if (player.getGunType() == 4) {
				sounds.laser();
			} else if (player.getGunType() == 3) {
				sounds.machinegun();
			}
			break;
		//CHEATS FOR DEBUGGING
		case KeyEvent.VK_1:
			player.setGunType(1);
			player.setFiringDelay(300);
			break;
		case KeyEvent.VK_2:
			player.setGunType(2);
			sounds.shotgunReload();
			player.setFiringDelay(200);
			break;
		case KeyEvent.VK_3:
			player.setGunType(3);
			sounds.machinegunReload();
			player.setFiringDelay(100);
			break;
		case KeyEvent.VK_4:
			player.setGunType(4);
			player.setFiringDelay(500);
			sounds.lasergunReload();
			break;
		case KeyEvent.VK_5:
			sounds.aBomb();
			killEverything();
			break;
		
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			player.setLeft(false);
			player.setDownLeft(false);
			player.setUpLeft(false);
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			player.setRight(false);
			player.setUpRight(false);
			player.setDownRight(false);
		}
		if (keyCode == KeyEvent.VK_UP) {
			player.setUp(false);
			player.setUpRight(false);
			player.setUpLeft(false);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			player.setDown(false);
			player.setDownLeft(false);
			player.setDownRight(false);
		}
		if (keyCode == KeyEvent.VK_F) {
			player.setFiring(false);
		}
	}

}
