package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;

public class Game {
	//define constants
	public static final Gson gson = new Gson();
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	public static final int TITLE_X = 255;
	public static final int TITLE_Y = 100;
	public static final int TITLE_WIDTH = 512;
	public static final int TITLE_HEIGHT = 150;
	public static final int OPTION_X = 255;
	public static final int OPTION_Y = 300;
	public static final int OPTION_WIDTH = 512;
	public static final int OPTION_HEIGHT = 200;
	
	//define fonts
	public static final Font TITLE_FONT = new Font("SansSerif", Font.PLAIN, 88);
	public static final Font MENU_FONT = new Font("SansSerif", Font.PLAIN, 36);
	
	private Scene mCurrentScene;
	private JFrame mGameWindow;
	private Container mWindowContainer;
	
	//load a scene from a specific gson file
	public void LoadSceneFromJson(String filename) throws FileNotFoundException
	{
		//create reader object and read
		Reader fReader = new FileReader(filename);
		mCurrentScene = gson.fromJson(fReader, Scene.class);
	}
	
	//initialize the menu at start of game
	public void InitializeMenu()
	{
		//game window & window container
		mGameWindow = new JFrame();
		mGameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mGameWindow.setResizable(false);
		mGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mGameWindow.getContentPane().setBackground(Color.blue);
		mGameWindow.setLayout(null);
		mWindowContainer = mGameWindow.getContentPane();
		
		//title panel
		JPanel titleNamePanel = new JPanel();
		titleNamePanel.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		titleNamePanel.setBackground(Color.blue);
		
		//title label
		JLabel titleName = new JLabel("Marionette");
		titleName.setForeground(Color.white);
		titleName.setFont(TITLE_FONT);
		
		//add title to title panel
		titleNamePanel.add(titleName);
		
		//menu option panel
		JPanel menuOptionPanel = new JPanel();
		menuOptionPanel.setBounds(OPTION_X, OPTION_Y, OPTION_WIDTH, OPTION_HEIGHT);
		menuOptionPanel.setBackground(Color.blue);
		
		//start button
		JButton startButton = new JButton("Start A New Story");
		startButton.setBackground(Color.gray);
		startButton.setForeground(Color.white);
		startButton.setFont(MENU_FONT);
		startButton.setFocusPainted(false);
		menuOptionPanel.add(startButton);
		
		//load button
		JButton loadButton = new JButton("Continue A Story");
		loadButton.setBackground(Color.gray);
		loadButton.setForeground(Color.white);
		loadButton.setFont(MENU_FONT);
		loadButton.setFocusPainted(false);
		menuOptionPanel.add(loadButton);
		
		//menu option layout
		menuOptionPanel.setLayout(new GridLayout(2, 0));
		
		//add JPanels to window container
		mWindowContainer.add(titleNamePanel);
		mWindowContainer.add(menuOptionPanel);
		
		//set window visible
		mGameWindow.setVisible(true);
	}
	
	//test json file I/O
	public void TestIO(String filename) throws FileNotFoundException
	{
		LoadSceneFromJson(filename);
		System.out.println(mCurrentScene.toString());
	}

	public static void main(String[] args) {
		//instantiate a game
		Game game = new Game();
		game.InitializeMenu();
		
		//ONLY for testing I/O
		try {
			game.TestIO("testJson/test1.json");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
	}

}
