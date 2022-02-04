/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.model;

import game.csc2a.Interfaces.Image;

public class ProxyImage implements Image
{
	//ATTRIBUTES
	private RealImage realImage;
	private String fileName;
	
	/**
	 * @param fileName The filename
	 */
	public ProxyImage(String fileName)
	{
		this.fileName = fileName;
	}
	/**
	 * Displays the image
	 */
	public void display()
	{
		if(realImage == null)
		{
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}
