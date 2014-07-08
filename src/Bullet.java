import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {

	private double x;
	private double y;
	private int r;

	private double dx;
	private double dy;
	private double rad;
	private double speed;
	
	//1 - handgun
	//2 - shotgun
	private int type; 

	private Color color1;
	private Color color2;

	public Bullet(double angle, int x, int y) {
		this.x = x;
		this.y = y;
		r = 2;
		
		rad = Math.toRadians(angle);
		speed = 10;
		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;
		color1 = Color.BLACK;
		color2 = Color.GREEN;
		color2.brighter();

	}
	
	public double getx() {return x;}
	public double gety() {return y;}
	public double getr() {return r;}


	public boolean update() {
		x += dx;
		y += dy;

		if (x < -r || x > GamePanel.WIDTH + r || y < -r
				|| y > GamePanel.HEIGHT + r) {
			return true;
		}
		return false;
	}

	
	public void draw(Graphics2D g) {

		g.setColor(color1);
		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		if (GamePanel.player.getGunType() == 4) {
			g.setColor(color2);
			g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			g.setColor(color2.darker());
			g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		}
	}

}
