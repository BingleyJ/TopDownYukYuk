import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	private String plr = "player.png";
	private String plrup = "playerup.png";
	private String plrup2 = "playerup2.png";
	private String plrdown = "playerdown.png";
	private String plrdown2 = "playerdown2.png";
	private String plrleft = "playerleft.png";
	private String plrleft2 = "playerleft2.png";
	private String plrright = "playerright.png";
	private String plrright2 = "playerright2.png";
	private String plrupright = "playerupright.png";
	private String plrupright2 = "playerupright2.png";
	private String plrupleft = "playerupleft.png";
	private String plrupleft2 = "playerupleft2.png";
	private String plrdownleft = "playerdownleft.png";
	private String plrdownleft2 = "playerdownleft2.png";
	private String plrdownright = "playerdownright.png";
	private String plrdownright2 = "playerdownright2.png";

	private Image playerUpImage;
	private Image playerDownImage;
	private Image playerUpImage2;
	private Image playerDownImage2;
	private Image playerLeftImage;
	private Image playerLeftImage2;
	private Image playerRightImage;
	private Image playerRightImage2;
	private Image playerImage;
	private Image playerUpRightImage;
	private Image playerUpRightImage2;
	private Image playerUpLeftImage;
	private Image playerUpLeftImage2;
	private Image playerDownRightImage;
	private Image playerDownRightImage2;
	private Image playerDownLeftImage;
	private Image playerDownLeftImage2;
	
	private Image playerLastImage;
	
	private int playerGunType;

	private int currentframe;
	private long spriteTimer;
	private long spriteDelay;
	long spriteElapsed;
	
	private int imageWidth;
	private int imageHeight;
	
	private int x;
	private int y;
	private int r;

	private int dx;
	private int dy;
	private int speed;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean upright;
	private boolean upleft;
	private boolean downright;
	private boolean downleft;

	private boolean oldleft;
	private boolean oldright;
	private boolean oldup;
	private boolean olddown;
	private boolean oldupright;
	private boolean oldupleft;
	private boolean olddownright;
	private boolean olddownleft;
	
	private boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	private boolean recovering;
	private long recoveringTimer;
	
	private int lives;
	private int health;
	private int nukes;
	
	private int score;
	private Color color1;
	private Color color2;

	public Player() {

		ImageIcon i1  = new ImageIcon(this.getClass().getResource(plrup));
		playerUpImage = i1.getImage();
		ImageIcon i11  = new ImageIcon(this.getClass().getResource(plrup2));
		playerUpImage2 = i11.getImage();
		ImageIcon i2  = new ImageIcon(this.getClass().getResource(plrdown));
		playerDownImage = i2.getImage();
		ImageIcon i22  = new ImageIcon(this.getClass().getResource(plrdown2));
		playerDownImage2 = i22.getImage();
		ImageIcon i3  = new ImageIcon(this.getClass().getResource(plrleft));
		playerLeftImage = i3.getImage();
		ImageIcon i33  = new ImageIcon(this.getClass().getResource(plrleft2));
		playerLeftImage2 = i33.getImage();
		ImageIcon i4  = new ImageIcon(this.getClass().getResource(plrright));
		playerRightImage = i4.getImage();
		ImageIcon i44  = new ImageIcon(this.getClass().getResource(plrright2));
		playerRightImage2 = i44.getImage();
		ImageIcon i5  = new ImageIcon(this.getClass().getResource(plrupright));
		playerUpRightImage = i5.getImage();
		ImageIcon i55  = new ImageIcon(this.getClass().getResource(plrupright2));
		playerUpRightImage2 = i55.getImage();	
		ImageIcon i6  = new ImageIcon(this.getClass().getResource(plrupleft));
		playerUpLeftImage = i6.getImage();
		ImageIcon i66  = new ImageIcon(this.getClass().getResource(plrupleft2));
		playerUpLeftImage2 = i66.getImage();
		ImageIcon i7  = new ImageIcon(this.getClass().getResource(plrdownleft));
		playerDownLeftImage = i7.getImage();
		ImageIcon i77  = new ImageIcon(this.getClass().getResource(plrdownleft2));
		playerDownLeftImage2 = i77.getImage();
		ImageIcon i8  = new ImageIcon(this.getClass().getResource(plrdownright));
		playerDownRightImage = i8.getImage();
		ImageIcon i88  = new ImageIcon(this.getClass().getResource(plrdownright2));
		playerDownRightImage2 = i88.getImage();
		
		playerLastImage = playerImage;
		
		currentframe = 0;
		spriteTimer = System.nanoTime();
		spriteDelay = 100;
		
		imageHeight = playerUpImage.getHeight(null);
		imageWidth = playerUpImage.getWidth(null);
		
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 4;
		r = 20;

		dx = 0;
		dy = 0;
		speed = 5;
		lives = 3;
		health = 100;
		score = 0;
		color1 = Color.WHITE;
		color2 = Color.RED;
		
		playerGunType = 1;
		
		firing  = false;
		firingTimer = System.nanoTime();
		firingDelay = 300;
		
		recovering = false;
		recoveringTimer = 0;
		
	}

	public long getFiringDelay() {return firingDelay;}
	public void setFiringDelay(long inFiringDelay) {firingDelay = inFiringDelay;}
	
	public int getGunType() {return playerGunType;}
	public void setGunType(int inType) {playerGunType = inType;}
	
	public int getWidth() {return imageWidth;}
	public int getHeight() {return imageHeight;}
	
	public int getx(){return x;}
	public int gety(){return y;}
	public int getr(){return r;}
	
	public boolean isRecovering(){return recovering;}
	public long getRecoveringTimer(){return recoveringTimer;}

	public int getScore(){return score;}
	public void addScore(int inScore){score += inScore;}
	public int getLives(){ return lives;}
	
	public int getHealth() {		return health;	}
	public void setHealth(int inHealth) {		this.health += inHealth;	}

	
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	
	public void setUpRight(boolean b) {upright = b;}
	public void setUpLeft(boolean b) {upleft = b;}
	public void setDownLeft(boolean b) {downleft = b;}
	public void setDownRight(boolean b) {downright = b;}
	
	public boolean getLeft(){return left;}
	public boolean getRight(){return right;}
	public boolean getUp(){return up;}
	public boolean getDown(){return down;}
	public boolean getUpLeft(){return upleft;}
	public boolean getUpRight(){return upright;}
	public boolean getDownLeft(){return downleft;}
	public boolean getDownRight(){return downright;}

	public void setFiring(boolean b) {firing = b;}
	
	public void loseLife(){
		lives--;
		//recovering = true;
		//recoveringTimer = System.nanoTime();
	}
	
	public void addLife(){
		lives++;
		//recovering = true;
		//recoveringTimer = System.nanoTime();
	}
	
	public void resetOldKeypredded(){
		oldleft = false;
		oldright = false;
		oldup = false;
		olddown = false;
		oldupright = false;
		oldupleft = false;
		olddownright = false;
		olddownleft = false;

	}
	
	public void update() {
		
		
		if (health <= 0){
			lives -= 1;
			if (lives > 0){
				health = 100;
			}
		}
		//add current direction to move
		if (left) {
			dx = -speed;
		}
		if (right) {
			dx = speed;
		}
		if (up) {
			dy = -speed;
		}
		if (down) {
			dy = speed;
		}
		if (upright){
			dx = speed;
			dy = -speed;
		}
		if (upleft){
			dx = -speed;
			dy = -speed;
		}
		if (downright){
			dx = speed;
			dy = speed;
		}
		if (downleft){
			dx = -speed;
			dy = speed;
		}

		x += dx;
		y += dy;

		if (x < r)
			x = r;
		if (y < r)
			y = r;
		if(x > GamePanel.WIDTH - r) x = GamePanel.WIDTH - r;
		if(y > GamePanel.HEIGHT - r) y = GamePanel.HEIGHT - r;
		
		dx = 0;
		dy = 0;

		//SHOOT STUFF
		if(firing){
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if(elapsed > firingDelay) { 
				boolean shooting = true;
				if ((up && shooting && !upright && !upleft) || (oldup && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(270, x + (playerUpImage.getWidth(null) / 2), y)); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(270, x + (playerUpImage.getWidth(null) / 2), y)); 
						GamePanel.bullets.add(new Bullet(285, x + (playerUpImage.getWidth(null) / 2), y)); 
						GamePanel.bullets.add(new Bullet(255, x + (playerUpImage.getWidth(null) / 2), y)); 
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(270, x + (playerUpImage.getWidth(null) / 2), y)); 
					}
					if (playerGunType == 4){
						for (int i = 0; i < y; i++){
							GamePanel.bullets.add(new Bullet(270, x + (playerUpImage.getWidth(null) / 2), i)); 
						}
					}
					shooting = false;
				}
				
				if ((upright && shooting) || (oldupright && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(315, x + 14 + (playerUpImage.getWidth(null) / 2), y + 5)); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(315, x + 14  + (playerUpImage.getWidth(null) / 2), y + 5)); 
						GamePanel.bullets.add(new Bullet(300, x + 14  + (playerUpImage.getWidth(null) / 2), y + 5)); 
						GamePanel.bullets.add(new Bullet(330, x + 14  + (playerUpImage.getWidth(null) / 2), y + 5)); 
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(315, x + 14 + (playerUpImage.getWidth(null) / 2), y + 5)); 
					}
					if (playerGunType == 4){
						int j = x;
						for (int i = y; i > 0; i--){
							GamePanel.bullets.add(new Bullet(315, j + 6 +(playerUpImage.getWidth(null) / 2), i + 14)); 
							j++;
						}
					}
					shooting = false;
				}
				
				
				if ((downright && shooting) || (olddownright && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(45, x + 14 + (playerUpImage.getWidth(null) / 2), y  + imageHeight - 5)); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(60, x + 14  + (playerUpImage.getWidth(null) / 2), y  + imageHeight - 5)); 
						GamePanel.bullets.add(new Bullet(45, x + 14  + (playerUpImage.getWidth(null) / 2), y  + imageHeight - 5)); 
						GamePanel.bullets.add(new Bullet(30, x + 14  + (playerUpImage.getWidth(null) / 2), y  + imageHeight - 5)); 
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(45, x + 14 + (playerUpImage.getWidth(null) / 2), y + imageHeight - 5)); 
					}
					if (playerGunType == 4){
						int j = x;
						for (int i = y; i < GamePanel.HEIGHT; i++){
							GamePanel.bullets.add(new Bullet(45, j + (playerUpLeftImage.getWidth(null) / 2), i + 16)); 
							j++;
						}
					}
					shooting = false;
				}
				
				if ((upleft && shooting) || (oldupleft && shooting)){
					int offsetx = 5;
					int offsety = 10;
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(225, x + offsetx , y + offsety)); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(225, x + offsetx, y + offsety)); 
						GamePanel.bullets.add(new Bullet(210, x + offsetx, y + offsety)); 
						GamePanel.bullets.add(new Bullet(240, x + offsetx, y + offsety)); 
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(225, x + offsetx , y + offsety)); 
					}
					if (playerGunType == 4){
						int j = x;
						for (int i = y; i > 0; i--){
							GamePanel.bullets.add(new Bullet(225, j + offsetx , i + offsety )); 
							j--;
						}
					}
					shooting = false;
				}
				
				if ((downleft && shooting) || (olddownleft && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(135, x , y + imageHeight - 5)); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(120, x , y + imageHeight - 5));
						GamePanel.bullets.add(new Bullet(135, x , y + imageHeight - 5)); 
						GamePanel.bullets.add(new Bullet(150, x , y + imageHeight - 5));
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(135, x , y + imageHeight - 5));
					}
					if (playerGunType == 4){
						int j = x;
						for (int i = y; i < GamePanel.HEIGHT; i++){
							GamePanel.bullets.add(new Bullet(135, j + 14 + (playerUpLeftImage.getWidth(null) / 2), i + 5)); 
							j--;
						}
					}
					shooting = false;
				}
				
				
				
				
				
				if ((down && shooting) || (olddown && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(90,  x + (playerUpImage.getWidth(null) / 2), y + (playerDownImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(90,  x + (playerUpImage.getWidth(null) / 2), y + (playerDownImage.getHeight(null) / 2))); 
						GamePanel.bullets.add(new Bullet(105, x + (playerUpImage.getWidth(null) / 2), y)); 
						GamePanel.bullets.add(new Bullet(75, x + (playerUpImage.getWidth(null) / 2), y)); 
					}
					if (playerGunType == 3){
						GamePanel.bullets.add(new Bullet(90,  x + (playerUpImage.getWidth(null) / 2), y + (playerDownImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 4){
						for (int i = y; i < GamePanel.HEIGHT; i++){
							GamePanel.bullets.add(new Bullet(90, x + (playerUpImage.getWidth(null) / 2), i)); 
						}
					}
					shooting = false;
				}
				if ((right && shooting) || (oldright && shooting)){
					if (playerGunType == 1){	
						GamePanel.bullets.add(new Bullet(360, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(360, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
						GamePanel.bullets.add(new Bullet(15, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
						GamePanel.bullets.add(new Bullet(345, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 3){	
						GamePanel.bullets.add(new Bullet(360, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 4){
						for (int i = x + playerUpImage.getWidth(null); i < GamePanel.WIDTH - imageWidth; i++){
							GamePanel.bullets.add(new Bullet(360, i + (playerUpImage.getWidth(null) / 2), y +(playerRightImage.getHeight(null) / 2))); 
						}
					}
					shooting = false;
				}
				if ((left && shooting && !upleft && !downleft) || (oldleft && shooting)){
					if (playerGunType == 1){
						GamePanel.bullets.add(new Bullet(180, x + (playerUpImage.getWidth(null) / 2), y + (playerLeftImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 2){
						GamePanel.bullets.add(new Bullet(180, x + (playerUpImage.getWidth(null) / 2), y + (playerLeftImage.getHeight(null) / 2))); 
						GamePanel.bullets.add(new Bullet(165, x + (playerUpImage.getWidth(null) / 2), y + (playerLeftImage.getHeight(null) / 2))); 
						GamePanel.bullets.add(new Bullet(195, x + (playerUpImage.getWidth(null) / 2), y + (playerLeftImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 3){	
						GamePanel.bullets.add(new Bullet(180, x + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
					}
					if (playerGunType == 4){	
						for (int i = 0; i < x; i++)
						GamePanel.bullets.add(new Bullet(180, i + (playerUpImage.getWidth(null) / 2), y + (playerRightImage.getHeight(null) / 2))); 
					}
					shooting = false;
				}
				firingTimer = System.nanoTime();
			}
		}
		//allows GodMode for 2 seconds
		long elapsed = (System.nanoTime() - recoveringTimer) / 1000000;
		if(elapsed > 2000){
			recovering = false;
			recoveringTimer = 0;
		}
	}

	public void draw(Graphics2D g) {
		
		if (recovering){
			g.setColor(color2);
			g.fillOval(x-r, y-r, 2 * r, 2 * r);
			
			g.setStroke(new BasicStroke(3));
			
			g.setColor(color2.darker());
			g.drawOval(x -r, y - r, 2 * r, 2 * r);
			
			g.setStroke(new BasicStroke(1));

		}
		else{
			long elapsed = (System.nanoTime() - spriteTimer) / 1000000;
			if (down && !downright && !downleft){
				if (currentframe == 1){
					g.drawImage(playerDownImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownImage2;
						resetOldKeypredded();
						olddown = true;
						System.out.println("down");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerDownImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownImage;
						resetOldKeypredded();
						olddown = true;
						System.out.println("down");
					}
				}				
			}
			
			if (downleft){
				if (currentframe == 1){
					g.drawImage(playerDownLeftImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownLeftImage2;
						resetOldKeypredded();
						olddownleft = true;
						System.out.println("down left");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerDownLeftImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownLeftImage;
						resetOldKeypredded();
						olddownleft = true;
						System.out.println("down left");
					}
				}				
			}
			
			if (downright){
				if (currentframe == 1){
					g.drawImage(playerDownRightImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownRightImage2;
						resetOldKeypredded();
						olddownright = true;
						System.out.println("down right");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerDownRightImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerDownRightImage;
						resetOldKeypredded();
						olddownright = true;
						System.out.println("down right");
					}
				}				
			}
			
			
			if (up && !upright && !upleft){
				if (currentframe == 1){
					g.drawImage(playerUpImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpImage2;
						resetOldKeypredded();
						oldup = true;
						System.out.println("up");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerUpImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpImage;
						resetOldKeypredded();
						oldup = true;
						System.out.println("up");
					}
				}				
			}
			
			if (upright){
				if (currentframe == 1){
					g.drawImage(playerUpRightImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpRightImage2;
						resetOldKeypredded();
						oldupright = true;
						System.out.println("upright");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerUpRightImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpRightImage;
						resetOldKeypredded();
						oldupright = true;
						System.out.println("upright");
					}
				}
			}
			
			
			if (upleft){
				if (currentframe == 1){
					g.drawImage(playerUpLeftImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpLeftImage2;
						resetOldKeypredded();
						oldupleft = true;
						System.out.println("upleft");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerUpLeftImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerUpLeftImage;
						resetOldKeypredded();
						oldupleft = true;
						System.out.println("upleft");
					}
				}				
			}
			
			
			if (left && !upleft && !downleft){
				if (currentframe == 1){
					g.drawImage(playerLeftImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerLeftImage2;
						resetOldKeypredded();
						oldleft = true;
						System.out.println("left");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerLeftImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerLeftImage;
						resetOldKeypredded();
						oldleft = true;
						System.out.println("left");
					}
				}				
			}
			if (right && !upright && !downright){
				if (currentframe == 1){
					g.drawImage(playerRightImage2, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerRightImage2;
						resetOldKeypredded();
						oldright = true;
						System.out.println("right");
					}
				}
				if (currentframe == 0){
					g.drawImage(playerRightImage, this.getx(), this.gety(), null);
					if (elapsed > spriteDelay){
						currentframe++;
						spriteTimer = System.nanoTime();
						playerLastImage = playerRightImage;
						resetOldKeypredded();
						oldright = true;
						System.out.println("right");
					}
				}
				
			}
			if (!up && !down && !left && !right)
				g.drawImage(playerLastImage, this.getx(), this.gety(), null);
			
			if(currentframe >=2 ){
				currentframe = 0;
			}
		/*	g.setColor(color1);
			g.fillOval(x-r, y-r, 2 * r, 2 * r);
		
			g.setStroke(new BasicStroke(3));
		
			g.setColor(color1.darker());
			g.drawOval(x -r, y - r, 2 * r, 2 * r);
		
			g.setStroke(new BasicStroke(1));*/
		}
	}

}	
