/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.csc2a.model.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener 
{
	//ATTRIBUTES
	private DrawGraphicsVisitor visitor = new DrawGraphicsVisitor();
    private Timer timer;
    private Player player;
 
    private ArrayList<Enemy> enemies;
    private boolean ingame;
    private final int P_X = 40;
    private final int P_Y = 60;
    private final int WIDTH = 1020;
    private final int HEIGHT = 630;
    private final int DELAY = 15;
    //private Menu menu;

    private final int[][] pos = 
    	{
        {2380, 100}, {2500, 59}, {1380, 350},
        {780, 109}, {580, 139}, {680, 239},
        {790, 259}, {760, 50}, {790, 500},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 400},
        {940, 300}, {990, 450}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
    };
/**
 * CONSTRUCTOR
 */
    public GamePanel() 
    {
    	addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.CYAN);
    	ingame = true;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        player = new Player(P_X, P_Y);
       
        initEnemy();     
        timer = new Timer(DELAY, this);
        timer.start();
    }
/**
 * Function to play sound 
 */
    public void playSound() 
    {
      try {
    	  //reference for sound
    	  //<http://soundbible.com/1454-Pain.html>
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/PAIN.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
/**
 * INITIALIZE THE ENEMY
 */
    public void initEnemy() 
    {
        enemies = new ArrayList<>();

        for (int[] p : pos)
        {
            enemies.add(new Enemy(p[0], p[1]));
        }
    }
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
		for(int r=1; r<40; r+=2)
		{
			for(int c=0; c<40; c+=2)
			{
				g.fillRect(r*70, c*70, 60, 60);
			}
		}	
        if (ingame)
        {
            drawObjects(g);
            if(enemies.size() == 20)
            {
            	drawGameWon(g);
            	timer.stop();
            	player.setVisible(false);
            }
        }else
        {
            drawGameOver(g);
        }
        for(Enemy e : enemies)
		{
			e.accept(visitor); //Make each enemy accepts the visitor so it can be drawn
		}
        Toolkit.getDefaultToolkit().sync();
    }
/**
 * 
 * @param g object of the Graphics type
 */
    private void drawObjects(Graphics g)
    {          
        if (player.isVisible())
        {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        ArrayList<Bomb> ms = player.getBullets();
        for (Bomb b : ms) 
        {
            if (b.isVisible()) 
            {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
        for (Enemy e : enemies)
        {
            if (e.isVisible()) 
            {
                g.drawImage(e.getImage(), e.getX(), e.getY(), this);
            }
        }
        // Draw statistics
        g.setFont(getFont());
        g.setColor(Color.RED);
        g.drawString("TIME :" + formatTime(), 5, 60);
        ArrayList<Bomb> b = player.getBullets();
        g.setColor(Color.WHITE);
        g.drawString("Enemiems left: " + (enemies.size() - 20), 5, 15);
        g.drawString("Bombs shot continously : " + (b.size())  ,5 , 45);
        g.setColor(Color.GREEN);
        g.drawString("INSTRUCTIONS : Use arrow keys to move the Player, Use SPACE button to fire a Bomb." , 300, 15);

    }
    /**
     * Format given time into 00:00 format.
     *@return Time in 00:00 format.
     */
    public static  String formatTime()
    {
    	long totalMilliseconds = System.currentTimeMillis();
    	long totalSeconds = totalMilliseconds / 1000;
        // Given time in seconds.
        int sec = (int)totalSeconds % 60;
        // Given time in minutes and seconds.
        int min = sec / 60;
        sec = sec - (min * 60);
        String minString, secString;
        if(min <= 9)
            minString = "0" + Integer.toString(min);
        else
            minString = "" + Integer.toString(min);
        if(sec <= 9)
            secString = "0" + Integer.toString(sec);
        else
            secString = "" + Integer.toString(sec);
        return minString + ":" + secString;
    }    
/**
 * 
 * @param g object of the graphics type 
 */
    private void drawGameOver(Graphics g) 
    {
        String msg = "GAME OVER : YOU HAVE LOST";
        Font small = new Font("Arial", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - fm.stringWidth(msg)) / 2,  HEIGHT / 2);
    }
/**
 * 
 * @param g object of the graphics type 
 */
    private void drawGameWon(Graphics g) 
    {
        String msg = "CONGRATS YOU HAVE WON";
        Font small = new Font("Arial", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - fm.stringWidth(msg)) / 2,  HEIGHT / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        inGame();
        updatePlayer();
        updateBombs();
        updateEnemy();
        checkCollisions();
        repaint();
    }
/**
 * Test to see whether game is running
 */
    private void inGame()
    {
        if (!ingame)
        	timer.stop();
    }
/**
 * Update the Player
 */
    private void updatePlayer() 
    {
        if (player.isVisible()) 
        	player.move();
        if(formatTime().equals("00:60"))
        	ingame = false;
    }
/**
 * update Bomb
 */
    private void updateBombs() 
    {
        ArrayList<Bomb> bs = player.getBullets();
        for (int i = 0; i < bs.size(); i++)
        {
        	Bomb b = bs.get(i);
            if (b.isVisible())
                b.move();
            else 
                bs.remove(i);
        }
    }
	/**
	 * Update enemy details
	 */
    private void updateEnemy() 
    {
        if (enemies.isEmpty()) 
        {
            ingame = false;
            return;
        }
        for (int i = 0; i < enemies.size() ; i++) 
        {
            Enemy a = enemies.get(i);
            if (a.isVisible())
                a.move();
            else 
                enemies.remove(i);
        }
    }
	/**
	 * Check for collisions
	 */
    public void checkCollisions() 
    {
    

    	//IF THE ENEMY CATCHES THE SNIPER THE SNIPER DISAAPEARS
        Rectangle r3 = player.getBounds();

        for (Enemy e : enemies) 
        {
            Rectangle r2 = e.getBounds();

            if (r3.intersects(r2)) 
            {
            	player.setVisible(false);
                e.setVisible(false);
                ingame = false;
            }
        }
        //IF THE Bomb TOUCHES THE ENEMY THE ENEMY DIES AND DISAPPEARS
        ArrayList<Bomb> ms = player.getBullets();
        for (Bomb m : ms) 
        {
            Rectangle r1 = m.getBounds();
            for (Enemy e : enemies) 
            {
                Rectangle r2 = e.getBounds();
                if (r1.intersects(r2))
                {
                    m.setVisible(false);
                    e.setVisible(false);
                    playSound();
                }
            }
        }        
    }
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
        	player.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e)
        {
        	player.keyPressed(e);
        }
    }
}

