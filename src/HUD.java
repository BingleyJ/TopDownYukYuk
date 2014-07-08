import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HUD {

	private String plrHead = "playerhead.png";
	private Image playerHeadImage;
	private String healthBar = "healthbar.png";
	private Image healthBarImage;
	private String healthBar1percent = "healthbar1percent.png";
	private Image healthBar1PercentImage;
	
	private int playerLives = 3;
	private int playerScore = 0;
	private int playerLivesX = 5;
	private int playerLivesY = 5;
	private int playerHealth = 100;
	private int healthBarX = 5;
	private int healthBarY = 20;
	private int scoreX = 5;
	private int scoreY = 50;
	private int imageHeight;
	private int imageWidth;
	private double x;
	private double y;
	private boolean dead;
	

	public int getPlayerLives() {		return playerLives;	}
	public void setPlayerLives(int playerLives) {this.playerLives = playerLives;}
	public int getPlayerScore() {return playerScore;	}
	public void setPlayerScore(int playerScore) {this.playerScore = playerScore;	}

	public HUD() {
		ImageIcon i = new ImageIcon(this.getClass().getResource(plrHead));
		playerHeadImage = i.getImage();
		ImageIcon i1 = new ImageIcon(this.getClass().getResource(healthBar));
		healthBarImage = i1.getImage();
		ImageIcon i11 = new ImageIcon(this.getClass().getResource(healthBar1percent));
		healthBar1PercentImage = i11.getImage();
	}


	public void update() {
		playerLives = GamePanel.player.getLives();
		playerScore = GamePanel.player.getScore();
		playerHealth = GamePanel.player.getHealth();
	}

	public void draw(Graphics2D g) {
		int offsetX = 0;
		for (int i = 0; i < playerLives; i++){
			g.drawImage(playerHeadImage, playerLivesX + offsetX, playerLivesY, null);
			offsetX += 15;
		}
		g.drawImage(healthBarImage,healthBarX,healthBarY,null );
		offsetX = 0;
		for (int i = 0; i < playerHealth; i++){
			g.drawImage(healthBar1PercentImage,healthBarX + offsetX,healthBarY,null );
			offsetX += 1;
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.BOLD, 18));
		String st = Integer.toString(playerScore);
		g.drawString(st, scoreX, scoreY);
		
	}

}
