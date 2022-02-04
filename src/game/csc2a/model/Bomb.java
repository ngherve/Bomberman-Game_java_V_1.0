/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.model;

public class Bomb extends Sprite
{
	//ATTRIBUTES
    private final int BOARD_WIDTH = 900;
    private final int BOMB_SPEED = 7;

    /**
     * Constructor
     * @param x THE X-coordinate of Bomb
     * @param y the y-coordinate of the Bomb
     */
    public Bomb(int x, int y)
    {
        super(x, y);
        //Reference for Bullet Image
        //https://3.bp.blogspot.com/-_ruDpsB2ZzE/Us9hDvzaFmI/AAAAAAAAF5Y/JJn1qee5Rxk/s1600/Bombe.png
        loadImage("data/download.jpg");
        getImageDimensions(); 
    }

/**
 * Method to control movement of Bomb
 */
    public void move() 
    {
        x += BOMB_SPEED;
        if (x > BOARD_WIDTH)
            vis = false;
    }
}
