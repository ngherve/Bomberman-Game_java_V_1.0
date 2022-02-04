/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import game.csc2a.Interfaces.IDrawVisitor;
import game.csc2a.model.*;

@SuppressWarnings("serial")
public class DrawGraphicsVisitor extends JPanel implements IDrawVisitor
{
	//ATTRIBUTES
    private Graphics g;
    private ArrayList<Enemy> enemies;
	//Draw Enemy
	public void visit(Enemy e)
	{
		enemies = new ArrayList<>();
		if(e != null)
		{
			for (Enemy E : enemies)
	        {
	            if (E.isVisible()) 
	            {
	            	g.drawImage(e.getImage(), e.getX(), e.getY(), null);
	                g.setColor(Color.RED);
	            }
	        }
		}
	}
	public void setGraphics(Graphics g)
	{
		this.g = g;
	}
}

