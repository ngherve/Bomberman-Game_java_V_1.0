/**
*	@author H Ngomseu Fotsing 217092052
*	@version Practical X
*/
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import game.csc2a.file.GameFile;
import game.csc2a.gui.GamePanel;
import game.csc2a.model.*;

@SuppressWarnings("serial")
public class Main extends JFrame
{
	Player player = new Player(10,10);
	private ArrayList<Bomb> b  = player.getBullets();
	Graphics g = null;
	//Constructor for initialisations
    public Main() 
    {
    	JMenuBar mainBar = new JMenuBar();
		//File menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new MyOpenListener());
		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new MySaveListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new MyExitListener());
		//adding the components to the panel
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
        mainBar.add(fileMenu);
        setJMenuBar(mainBar);   
        add(new GamePanel());
        setResizable(false);
        pack();
        setTitle("BomberMan V1.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //open listenner for open menu
    public class MyOpenListener implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent a)
		{
			JFileChooser jfc = new JFileChooser("data");
			int result = jfc.showOpenDialog(Main.this);
			
			switch(result)
			{
			case JFileChooser.APPROVE_OPTION:
				GameFile.readWriteBinary();
			break;
			default:
				System.out.println(result);
			break;
			}
		}
	}
    //save listenner for save menu
	public class MySaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent a)
		{
			JFileChooser jfc = new JFileChooser("data");
			int result = jfc.showSaveDialog(Main.this);
			switch(result)
			{
			case JFileChooser.APPROVE_OPTION:
				GameFile.writeToFile(jfc.getSelectedFile().getAbsolutePath(), b,GamePanel.formatTime());
			break;
			default:
				System.out.println(result);
			break;
			}
		}
	}
	
   	public class MyExitListener implements ActionListener
   	{
   		@Override
   		public void actionPerformed(ActionEvent a)
   		{
   			System.exit(0);
   		}
   	}
	/**
	 * @param args object made of an array of Strings
	 */
	public static void main(String[] args)  throws InterruptedException 
    {
		//reading the files
		GameFile.readWriteBinary();
      
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run()
            {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}

