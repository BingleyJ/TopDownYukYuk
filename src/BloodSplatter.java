import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BloodSplatter {

	private String zmbBlood = "bloodsplatter.png";
	private Image zombieBloodImage;
	private String zmbBlood2 = "bloodsplatter2.png";
	private Image zombieBloodImage2;
	private String zmbBlood3 = "bloodsplatter3.png";
	private Image zombieBloodImage3;
	private String zmbBlood4 = "bloodsplatter4.png";
	private Image zombieBloodImage4;
	
	private String zmbBloodb = "bloodsplatterb.png";
	private Image zombieBloodbImage;
	private String zmbBloodb2 = "bloodsplatterb2.png";
	private Image zombieBloodbImage2;
	private String zmbBloodb3 = "bloodsplatterb3.png";
	private Image zombieBloodbImage3;
	private String zmbBloodb4 = "bloodsplatterb4.png";
	private Image zombieBloodbImage4;
	
	private int x;
	private int y;
	private int type;

	private int currentframe;
	private long spriteTimer;
	private long spriteDelay;
	private long spriteElapsed;

	private boolean dead;

	public BloodSplatter(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		ImageIcon i = new ImageIcon(this.getClass().getResource(zmbBlood));
		zombieBloodImage = i.getImage();
		ImageIcon i2 = new ImageIcon(this.getClass().getResource(zmbBlood2));
		zombieBloodImage2 = i2.getImage();
		ImageIcon i3 = new ImageIcon(this.getClass().getResource(zmbBlood3));
		zombieBloodImage3 = i3.getImage();
		ImageIcon i4 = new ImageIcon(this.getClass().getResource(zmbBlood4));
		zombieBloodImage4 = i4.getImage();
		
		ImageIcon bs = new ImageIcon(this.getClass().getResource(zmbBloodb));
		zombieBloodbImage = bs.getImage();
		ImageIcon bs2 = new ImageIcon(this.getClass().getResource(zmbBloodb2));
		zombieBloodbImage2 = bs2.getImage();
		ImageIcon bs3 = new ImageIcon(this.getClass().getResource(zmbBloodb3));
		zombieBloodbImage3 = bs3.getImage();
		ImageIcon bs4 = new ImageIcon(this.getClass().getResource(zmbBloodb4));
		zombieBloodbImage4 = bs4.getImage();
		
		currentframe = 0;
		spriteTimer = System.nanoTime();
		spriteDelay = 6000;
		
		dead = false;		
	}
	
	public int getx() {return x;}
	public int gety() {return y;}
	public boolean isDead() {return dead;}
	public void update() {
	
	}
	
	public void draw(Graphics2D g) {
			spriteElapsed = (System.nanoTime() - spriteTimer) / 1000000;
			if (currentframe == 3 && type == 1){
				g.drawImage(zombieBloodImage4,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					dead = true;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 2 && type == 1){
				g.drawImage(zombieBloodImage3,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 1 && type == 1){
				g.drawImage(zombieBloodImage2,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 1){
				g.drawImage(zombieBloodImage,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 3 && type == 2){
				g.drawImage(zombieBloodbImage4,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					dead = true;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 2 && type == 2){
				g.drawImage(zombieBloodbImage3,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 1 && type == 2){
				g.drawImage(zombieBloodbImage2,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			if (currentframe == 0 && type == 2){
				g.drawImage(zombieBloodbImage,x,y,null);
				if (spriteElapsed > spriteDelay){
					currentframe++;
					spriteTimer = System.nanoTime();
				}
			}
			
			if (currentframe >= 4) {
				currentframe = 0;
			}
	}

}
