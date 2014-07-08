import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;





public class MakeSound {
	File playerHit;
	AudioClip playerHitSound;
	File pistol;
	AudioClip pistolSound;
	File extralife;
	AudioClip extraLifeSound;
	File deadzombie1;
	AudioClip deadZombie1Sound;
	File shotgunreload;
	AudioClip shotgunReloadSound;
	File machinegun;
	AudioClip machineGunSound;
	File machinereload;
	AudioClip machineReloadSound;
	File laser;
	AudioClip laserSound;
	File laserreload;
	AudioClip laserreloadSound;
	File aBomb;
	AudioClip aBombSound;
	
	public void deadZombie1(){
		deadZombie1Sound.play();
	}
	
	public void extraLife(){
		extraLifeSound.play();
	}
	
	public void playerHit(){
		playerHitSound.play();
	}
	
	public void pistol(){
		pistolSound.play();
	}
	
	public void shotgunReload(){
		shotgunReloadSound.play();
	}
	public void machinegunReload(){
		machineReloadSound.play();
	}
	public void aBomb(){
		aBombSound.play();
	}
	public void lasergunReload(){
		laserreloadSound.play();
	}
	public void laser(){
		laserSound.play();
	}
	public void machinegun(){
		machineGunSound.play();
	}

	
	public MakeSound() throws MalformedURLException{
		playerHit = new File("playerhit.wav");
		playerHitSound = Applet.newAudioClip(playerHit.toURL());
		pistol = new File("pistol.wav");
		pistolSound = Applet.newAudioClip(pistol.toURL());
		extralife = new File("1up.wav");
		extraLifeSound = Applet.newAudioClip(extralife.toURL());
		deadzombie1 = new File("deadzombie1.wav");
		deadZombie1Sound = Applet.newAudioClip(deadzombie1.toURL());
		shotgunreload = new File("shotgunreload.wav");
		shotgunReloadSound = Applet.newAudioClip(shotgunreload.toURL());
		laser = new File("laser.wav");
		laserSound = Applet.newAudioClip(laser.toURL());
		laserreload = new File("laserreload.wav");
		laserreloadSound = Applet.newAudioClip(laserreload.toURL());
		machinereload = new File("machinegunreload.wav");
		machineReloadSound = Applet.newAudioClip(machinereload.toURL());
		machinegun = new File("machinegun.wav");
		machineGunSound = Applet.newAudioClip(machinegun.toURL());
		aBomb = new File("abomb.wav");
		aBombSound = Applet.newAudioClip(aBomb.toURL());
		
	}
	
	
	
}