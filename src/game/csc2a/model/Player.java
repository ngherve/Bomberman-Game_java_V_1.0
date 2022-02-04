/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.model;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player extends Sprite 
{
	//ATTRIBUTES
    private int dx;
    private int dy;
    private ArrayList<Bomb> bombs;
 
    /**
     * @param x X coordinate of player
     * @param y Y coordinate of player
     */
    public Player(int x, int y)
    {
    	//Call super class constructor
        super(x, y);
        //Instantiate the ArrayList
        bombs = new ArrayList<>();
        //reference for image used
        //http://www.systemed.me/en/post_tag/bomberman/
        loadImage("data/bomberman.jpg");
        getImageDimensions();
    }
	/**
	 * Method to control player movement
	 */
    public void move() 
    {
        x += dx;
        y += dy;
        if (x < 1) x = 1;
        if (y < 1) y = 1;
        //Stop player from moving out of bounds
        if(x < 0) x = 0;
        if(x > 800) x = 800;
        if(y < 0) y = 0;
        if(y > 600) y = 600; 
    }
	/**
	 * 
	 * @return the object of the Bullet Type
	 */
    public ArrayList<Bomb> getBullets()
    {
        return bombs;
    }
    /**
     * Method to play sound
     */
    public void playSound() 
    {
		try 
		{
    	  //REFERENCE FOR SOUND USED
    	  //<http://http://soundbible.com/1234-Bomb.html
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/Bomb-SoundBible.com-891110113.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    /**
	 * @param e object to control movement
	 */
    public void keyPressed(KeyEvent e) 
    {
        //Get the code to move
    	switch (e.getKeyCode()) 
        {
        case KeyEvent.VK_DOWN: y += 10; 
        break;
        case KeyEvent.VK_UP: y -= 10; 
        break;
        case KeyEvent.VK_LEFT: x -= 10;
        break;
        case KeyEvent.VK_RIGHT: x += 10;
        break;
        case KeyEvent.VK_SPACE : 
        	fire();
        	playSound(); 
        default: 
        	System.err.println("");
        }
    }
	/**
	 * Method to control firing of bullets
	 */
    public void fire() 
    {
        bombs.add(new Bomb(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) 
            dx = 0;
        if (key == KeyEvent.VK_RIGHT)
            dx = 0;
        if (key == KeyEvent.VK_UP)
            dy = 0;
        if (key == KeyEvent.VK_DOWN) 
            dy = 0;
    }
}

