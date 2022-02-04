package game.csc2a.model;

import java.awt.*;
import javax.swing.*;

public abstract class Sprite 
{
	//ATTRIBUTES
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
   
/**
 * CONSTRUCTOR
 * @param x THE x coordinate of the sprite
 * @param y the y coordinate of the sprite
 */
    public Sprite(int x, int y)
    {

        this.x = x;
        this.y = y;
        vis = true;
    }
/**
 * Method to get the Dimensions of the image
 */
    protected void getImageDimensions()
    {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
/**
 * 
 * @param imageName the imagename
 */
    protected void loadImage(String imageName)
    {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
/**
 * 
 * @return the image
 */
    public Image getImage() 
    {
        return image;
    }
/**
 * 
 * @return x
 */
    public int getX()
    {
        return x;
    }
/**
 * 
 * @return y
 */
    public int getY() 
    {
        return y;
    }
/**
 * 
 * @return true or false
 */
    public boolean isVisible() 
    {
        return vis;
    }
/**
 * 
 * @param visible true or false if visible
 */
    public void setVisible(Boolean visible) 
    {
        vis = visible;
    }
/**
 * 
 * @return the image dimensions
 */
    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, width, height);
    }
}

