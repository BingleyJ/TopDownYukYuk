import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Enemy {

	private String zmbDown = "zombiedown.png";
	private Image zombieDownImage;
	private String zmbDown2 = "zombiedown2.png";
	private Image zombieDownImage2;
	private String zmbUp = "zombieup.png";
	private Image zombieUpImage;
	private String zmbUp2 = "zombieup2.png";
	private Image zombieUpImage2;
	private String zmbRight = "zombieright.png";
	private Image zombieRightImage;
	private String zmbRight2 = "zombieright2.png";
	private Image zombieRightImage2;
	private String zmbLeft = "zombieleft.png";
	private Image zombieLeftImage;
	private String zmbLeft2 = "zombieleft2.png";
	private Image zombieLeftImage2;
	
	private String zmbDemonUp = "zombiedemonup.png";
	private Image zombieDemonUpImage;
	private String zmbDemonUp2 = "zombiedemonup2.png";
	private Image zombieDemonUpImage2;
	private String zmbDemonDown = "zombiedemondown.png";
	private Image zombieDemonDownImage;
	private String zmbDemonDown2 = "zombiedemondown2.png";
	private Image zombieDemonDownImage2;
	private String zmbDemonLeft = "zombiedemonLeft.png";
	private Image zombieDemonLeftImage;
	private String zmbDemonLeft2 = "zombiedemonLeft2.png";
	private Image zombieDemonLeftImage2;
	private String zmbDemonRight = "zombiedemonRight.png";
	private Image zombieDemonRightImage;
	private String zmbDemonRight2 = "zombiedemonRight2.png";
	private Image zombieDemonRightImage2;
	
	private int currentframe;
	private long spriteTimer;
	private long spriteDelay;
	long spriteElapsed;

	private double movementAngle;
	
	private int imageHeight;
	private int imageWidth;

	private double x;
	private double y;
	private int r;

	private double oldx;
	private double oldy;

	private double dx;
	private double dy;
	private double rad;
	private double speed;

	private int health;
	private int type;
	private int rank;

	private Color color1;

	private boolean ready;
	private boolean dead;

	public Enemy(int type, int rank) {

		ImageIcon i = new ImageIcon(this.getClass().getResource(zmbDown));
		zombieDownImage = i.getImage();
		ImageIcon i2 = new ImageIcon(this.getClass().getResource(zmbDown2));
		zombieDownImage2 = i2.getImage();
		ImageIcon i1 = new ImageIcon(this.getClass().getResource(zmbUp));
		zombieUpImage = i1.getImage();
		ImageIcon i11 = new ImageIcon(this.getClass().getResource(zmbUp2));
		zombieUpImage2 = i11.getImage();

		ImageIcon i3 = new ImageIcon(this.getClass().getResource(zmbRight));
		zombieRightImage = i3.getImage();
		ImageIcon i33 = new ImageIcon(this.getClass().getResource(zmbRight2));
		zombieRightImage2 = i33.getImage();
		ImageIcon i4 = new ImageIcon(this.getClass().getResource(zmbLeft));
		zombieLeftImage = i4.getImage();
		ImageIcon i44 = new ImageIcon(this.getClass().getResource(zmbLeft2));
		zombieLeftImage2 = i44.getImage();
		
		ImageIcon zdu = new ImageIcon(this.getClass().getResource(zmbDemonUp));
		zombieDemonUpImage = zdu.getImage();
		ImageIcon zdu2 = new ImageIcon(this.getClass().getResource(zmbDemonUp2));
		zombieDemonUpImage2 = zdu2.getImage();
		ImageIcon zdd = new ImageIcon(this.getClass().getResource(zmbDemonDown));
		zombieDemonDownImage = zdd.getImage();
		ImageIcon zdd2 = new ImageIcon(this.getClass().getResource(zmbDemonDown2));
		zombieDemonDownImage2 = zdd2.getImage();
		
		ImageIcon zdl2 = new ImageIcon(this.getClass().getResource(zmbDemonLeft2));
		zombieDemonLeftImage2 = zdl2.getImage();
		ImageIcon zdl = new ImageIcon(this.getClass().getResource(zmbDemonLeft));
		zombieDemonLeftImage = zdl.getImage();
		
		ImageIcon zdr2 = new ImageIcon(this.getClass().getResource(zmbDemonRight2));
		zombieDemonRightImage2 = zdr2.getImage();
		ImageIcon zdr = new ImageIcon(this.getClass().getResource(zmbDemonRight));
		zombieDemonRightImage = zdr.getImage();
		
		currentframe = 0;
		spriteTimer = System.nanoTime();
		spriteDelay = 234;
		
		imageHeight = zombieDownImage.getHeight(null);
		imageWidth = zombieDownImage.getWidth(null);


		this.type = type;
		this.rank = rank;

		//basic enemy
		if (type == 1) {
			//color1 = Color.BLUE;
			if (rank == 1) {
				speed = 1;
				r = 25;
				health = 1;
			}
			if (rank == 2) {
				speed = 2;
				r = 25;
				health = 2;
			}
		}
		
		//zombie demon
		if (type == 2){
			if (rank == 1){
				speed = 2;
				health = 3;
			}
			if (rank == 2){
				speed = 3;
				health = 3;
			}
		}
		//basic enemy upgraded
		

		x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;
		y = -r;

		double angle = Math.random() * 140 + 20;
		rad = Math.toRadians(angle);

		movementAngle = rad;

		dx = Math.cos(rad) * speed / 2;
		dy = Math.sin(rad) * speed / 2;

		ready = false;
		dead = false;
	}

	
	public int getWidth() {return imageWidth;}
	public int getHeight() {return imageHeight;}
	
	public int getType() {
		return type;
	}

	public int getRank() {
		return rank;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public double getr() {
		return r;
	}

	public void kill(){
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void hit() {
		health--;
		if (health <= 0) {
			dead = true;
		}
	}

	public void update() {

		oldx = x;
		oldy = y;

		x += dx;
		y += dy;

		if (!ready) {
			if (x > r && x < GamePanel.WIDTH - r && y > r
					&& y < GamePanel.HEIGHT - r) {
				ready = true;
			}
		}
		// bounce off walls
		if (x < 0 && dx < 0)
			dx = -dx;
		if (y < 0 && dy < 0)
			dy = -dy;
		if (x > GamePanel.WIDTH - zombieDownImage.getWidth(null) && dx > 0)
			dx = -dx;
		if (y > GamePanel.HEIGHT - zombieDownImage.getHeight(null) && dy > 0)
			dy = -dy;

		/*
		 * if (x < r && dx < 0) dx = -dx; if (y < r && dy < 0) dy = -dy; if (x >
		 * GamePanel.WIDTH - r && dx > 0) dx = -dx; if (y > GamePanel.HEIGHT - r
		 * && dy > 0) dy = -dy;
		 */

		// movementAngle = (float)(Math.atan2(oldy - y, oldx - x));
		movementAngle = (float) (Math.atan2(oldx - x, oldy - y) * 180 / 3.14);
		movementAngle += 90;
		if (movementAngle <= 0)
			movementAngle += 360;
	}

	public void draw(Graphics2D g) {

		long elapsed = (System.nanoTime() - spriteTimer) / 1000000;
		//animate enemies
		//MOVING UP
		if (movementAngle < 135 && movementAngle > 45) {
			//normal zombie moving up
			if (currentframe == 1 && type == 1) {
				g.drawImage(zombieUpImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 1) {
				g.drawImage(zombieUpImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			else if (currentframe == 1 && type == 2) {
				g.drawImage(zombieDemonUpImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			else if (currentframe == 0 && type == 2) {
				g.drawImage(zombieDemonUpImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
		}
		//MOVING DOWN
		if (movementAngle < 315 && movementAngle > 225) {
			if (currentframe == 1 && type == 1) {
				g.drawImage(zombieDownImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 1) {
				g.drawImage(zombieDownImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 1 && type == 2) {
				g.drawImage(zombieDemonDownImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 2) {
				g.drawImage(zombieDemonDownImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
		}
		//MOVING LEFT
		if (movementAngle < 226 && movementAngle > 136) {
			if (currentframe == 1 && type == 1) {
				g.drawImage(zombieLeftImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 1) {
				g.drawImage(zombieLeftImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 1 && type == 2) {
				g.drawImage(zombieDemonLeftImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 2) {
				g.drawImage(zombieDemonLeftImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
		}
		//MOVING RIGHT
		if ((movementAngle < 46 && movementAngle > 0)
				|| (movementAngle < 360 && movementAngle > 316)) {
			if (currentframe == 1 && type == 1) {
				g.drawImage(zombieRightImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 1) {
				g.drawImage(zombieRightImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 1 && type == 2) {
				g.drawImage(zombieDemonRightImage, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 2) {
				g.drawImage(zombieDemonRightImage2, (int) x, (int) y, null);
				if (elapsed > spriteDelay) {
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
		}

		if (currentframe >= 2) {
			currentframe = 0;
		}
	}

}
