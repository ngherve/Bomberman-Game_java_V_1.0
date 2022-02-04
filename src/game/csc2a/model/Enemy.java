/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.model;

import java.util.Random;

import game.csc2a.Interfaces.IDrawVisitor;
import game.csc2a.Interfaces.IDrawable;

public class Enemy extends Sprite implements IDrawable
{
	//ATTRIBUTES
    private final int INITIAL_X = 700;
    Random  random = new Random();
    /**
     * CONSTRUCTOR
     * @param x THE X-coordinate of bomb
     * @param y the y-coordinate of the Bomb
     */
    public Enemy(int x, int y)
    {
        super(x, y);
        loadImage("data/poulpi.png");
        getImageDimensions();
    }

	/**
	 * Method to control Enemy movement
	 */
    public void move() 
    {
        if (x < 0) 
            x = INITIAL_X;
        x -= 1;
    }
	@Override
	public void accept(IDrawVisitor visitor)
	{
		visitor.visit(this);
	}
}

