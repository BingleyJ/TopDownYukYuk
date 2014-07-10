import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class PowerUp {
	
	private String extraLife = "/assets/powerup/1up.png";
	private Image extraLifeImage;
	private String machineGun = "/assets/powerup/machinegun.png";
	private Image machineGunImage;
	private String shotGun = "/assets/powerup/shotgun.png";
	private Image shotGunImage;
	private String laserGun = "/assets/powerup/lazergun.png";
	private Image laserGunImage;
	private String aBomb = "/assets/powerup/abomb.png";
	private Image aBombImage;
	
	private int currentframe;
	private long spriteTimer;
	private long spriteDelay;
	private long spriteElapsed;
	
	private boolean dead;
	
	private int x;
	private int y;
	private int type;
	private int imageHeight;
	private int imageWidth;
	
	
	//1 -- extra life
	//2 -- shotgun
	//3 -- lasergun
	
	public PowerUp(int type, int x, int y){
		
		this.type = type;
		this.x = x;
		this.y = y;
		
		ImageIcon i = new ImageIcon(this.getClass().getResource(extraLife));
		extraLifeImage = i.getImage();
		ImageIcon i2 = new ImageIcon(this.getClass().getResource(machineGun));
		machineGunImage = i2.getImage();
		ImageIcon i3 = new ImageIcon(this.getClass().getResource(shotGun));
		shotGunImage = i3.getImage();
		ImageIcon i4 = new ImageIcon(this.getClass().getResource(laserGun));
		laserGunImage = i4.getImage();
		ImageIcon i5 = new ImageIcon(this.getClass().getResource(aBomb));
		aBombImage = i5.getImage();
		
		if ( type == 1){
			imageHeight = extraLifeImage.getHeight(null);
			imageWidth = extraLifeImage.getWidth(null);
		}
		if ( type == 2){
			imageHeight = shotGunImage.getHeight(null);
			imageWidth = shotGunImage.getWidth(null);
		}
		if ( type == 3){
			imageHeight = machineGunImage.getHeight(null);
			imageWidth = machineGunImage.getWidth(null);
		}
		if ( type == 4){
			imageHeight = laserGunImage.getHeight(null);
			imageWidth = laserGunImage.getWidth(null);
		}
		if ( type == 5){
			imageHeight = aBombImage.getHeight(null);
			imageWidth = aBombImage.getWidth(null);
		}
		
		currentframe = 0;
		spriteTimer = System.nanoTime();
		spriteDelay = 30000;
		
		dead = false;
		
	}
	
	public int getx() {return x;}
	public int gety() {return y;}
	public int getHeight() {return imageHeight;}
	public int getWidth() {return imageWidth;}
	public boolean isDead() {return dead;}
	public void setDead() {dead = true;}
	
	public int getType() {return type;}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		spriteElapsed = (System.nanoTime() - spriteTimer) / 1000000;
		
		if (type == 1 ){
			g.drawImage(extraLifeImage,x,y,null);
		}
		if (type == 2){
			g.drawImage(shotGunImage,x,y,null);
		}
		if (type == 3){
			g.drawImage(machineGunImage,x,y,null);
		}
		if (type == 4){
			g.drawImage(laserGunImage,x,y,null);
		}
		if (type == 5){
			g.drawImage(aBombImage,x,y,null);
		}
		if (spriteElapsed > spriteDelay){
			setDead();
		}
	}

}
