/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
package game.csc2a.model;

import game.csc2a.Interfaces.Image;

public class RealImage implements Image
{
	//ATTRIBUTES
	private String fileName;
	/**
	 * 
	 * @param fileName the name of the File
	 */
	public RealImage(String fileName)
	{
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	@Override
	public void display()
	{
		System.out.println(fileName);
	}
	
	/**
	 * 
	 * @param fileName the name of the file to be retrieved
	 */
	private void loadFromDisk(String fileName)
	{
		System.out.println(fileName);
	}
}
