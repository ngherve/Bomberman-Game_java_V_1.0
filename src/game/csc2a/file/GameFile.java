/**
*	@author H Ngomseu Fotsing 217092052
*/
package game.csc2a.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import game.csc2a.model.*;

public class GameFile
{
	/**
	 * Method to read and write Instructions
	 */
	public static void readWriteBinary()
	{
		try
		{
			try (DataOutputStream output =  new DataOutputStream
					(new FileOutputStream("data/Binary.dat")))        
			{
				output.writeUTF("Use arrow keys to move the "
						+ "Sniper,Use SPACE button to fire bullets");
			}
			try (DataInputStream input = new DataInputStream
					(new FileInputStream("data/Binary.dat"))) 
			{
				while (true)
					System.out.println(input.readUTF());
			}
		}catch (EOFException ex){
			 System.out.println("All data were read");
		}catch (IOException ex){
			 ex.printStackTrace();
		}
	}
			
	/**
	 * Method to read Instructions
	 * @param fileName filename of file 
	 */
	public void readInstructions(String fileName)
	{
		File txtFile = new File("data/Instructions.txt");
		Scanner txtIn = null;
	    try
	    {
		    txtIn = new Scanner(txtFile);
		    while(txtIn.hasNext())
		    {
		  	   String Line = txtIn.nextLine();
		 	   System.out.println(Line);	    		  	    		  	    			   
		    }
	    }catch(FileNotFoundException ex)
	    {
	    	ex.printStackTrace();
	  	}finally
	    {
		   if(txtIn != null)
			   txtIn.close();
	    }
	}
	/**
	 * @param filename filename of the file
	 * @param enemies number of enemies
	 * @param time the time 
	 */
	public static void writeToFile(String filename, ArrayList<Bomb> enemies, String time)
	{
		//File oBject
		File PlayerFile = new File(filename);
		//PrintWriter Object
		PrintWriter txtout = null;
		try
		{
			//Instantiate the PrintWriter Object
			txtout = new PrintWriter(PlayerFile);
			txtout.println(enemies);
			txtout.println(time);
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(txtout != null) //If file has something remember to close
				txtout.close();
		}
	}//end method
}
