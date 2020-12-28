package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public static final int MAIN_TEXT_X = 255;
	public static final int MAIN_TEXT_Y = 100;
	public static final int MAIN_TEXT_WIDTH = 512;
	public static final int MAIN_TEXT_HEIGHT = 400;
	
	//define fonts
	public static final Font TITLE_FONT = new Font("SansSerif", Font.PLAIN, 88);
	public static final Font MENU_FONT = new Font("SansSerif", Font.PLAIN, 36);
	public static final Font MAIN_TEXT_FONT = new Font("SansSerif", Font.PLAIN, 28);
	
	//define colors
	public static final Color WINDOW_BKG = Color.darkGray;
	public static final Color BUTTON_BKG = Color.gray;
	public static final Color TEXT_COLOR = Color.white;
	
	private Scene mCurrentScene;
	private JFrame mGameWindow;
	private Container mWindowContainer;
	
	//load a scene from a specific Gson file
	public void LoadSceneFromJson(String filename) throws FileNotFoundException
	{
		//create reader object and read
		Reader fReader = new FileReader(filename);
		mCurrentScene = gson.fromJson(fReader, Scene.class);
	}
	
	//initialize game window
	public void InitializeGameWindow()
	{
		//game window & window container
		mGameWindow = new JFrame();
		mGameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mGameWindow.setResizable(false);
		mGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mGameWindow.getContentPane().setBackground(WINDOW_BKG);
		mGameWindow.setLayout(null);
		mWindowContainer = mGameWindow.getContentPane();
	}
	
	//initialize the menu at start of game
	public void InitializeMenu()
	{
		//clear anything in container
		mWindowContainer.removeAll();
		mWindowContainer.repaint();
		
		//title panel
		JPanel titleNamePanel = new JPanel();
		titleNamePanel.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		titleNamePanel.setBackground(WINDOW_BKG);
		
		//title label
		JLabel titleName = new JLabel("Marionette");
		titleName.setForeground(TEXT_COLOR);
		titleName.setFont(TITLE_FONT);
		
		//add title to title panel
		titleNamePanel.add(titleName);
		
		//menu option panel
		JPanel menuOptionPanel = new JPanel();
		menuOptionPanel.setBounds(OPTION_X, OPTION_Y, OPTION_WIDTH, OPTION_HEIGHT);
		menuOptionPanel.setBackground(WINDOW_BKG);
		
		//start button
		JButton startButton = new JButton("Start A New Story");
		startButton.setBackground(BUTTON_BKG);
		startButton.setForeground(TEXT_COLOR);
		startButton.setFont(MENU_FONT);
		startButton.setFocusPainted(false);
		startButton.addActionListener(new startButtonHandler());
		menuOptionPanel.add(startButton);
		
		//load button
		JButton loadButton = new JButton("Continue A Story");
		loadButton.setBackground(BUTTON_BKG);
		loadButton.setForeground(TEXT_COLOR);
		loadButton.setFont(MENU_FONT);
		loadButton.setFocusPainted(false);
		menuOptionPanel.add(loadButton);
		
		//menu option layout
		menuOptionPanel.setLayout(new GridLayout(2, 0));
		
		//add JPanels to window container
		mWindowContainer.add(titleNamePanel);
		mWindowContainer.add(menuOptionPanel);
		
		//update container
		mWindowContainer.validate();
		
		//set window visible
		mGameWindow.setVisible(true);
	}
	
	//create the name input scene
	public void CreateNameInputScene()
	{
		//clear the container
		mWindowContainer.removeAll();
		mWindowContainer.repaint();
		
		//create caption
		JPanel nameInputPanel = new JPanel();
		nameInputPanel.setLayout(new GridLayout(4, 0));
		nameInputPanel.setBounds(MAIN_TEXT_X, MAIN_TEXT_Y, MAIN_TEXT_WIDTH, MAIN_TEXT_HEIGHT);
		nameInputPanel.setBackground(WINDOW_BKG);
		JLabel caption = new JLabel("Please enter your name and continue:");
		caption.setForeground(TEXT_COLOR);
		caption.setFont(MAIN_TEXT_FONT);
		nameInputPanel.add(caption);
		
		//create input box
		JTextField nameBox = new JTextField();
		nameBox.setFont(MAIN_TEXT_FONT);
		nameInputPanel.add(nameBox);
		
		//create submit button
		JButton nameSubmitButton = new JButton("Continue");
		nameSubmitButton.setFont(MENU_FONT);
		nameSubmitButton.setBackground(BUTTON_BKG);
		nameSubmitButton.setForeground(TEXT_COLOR);
		nameSubmitButton.setFocusPainted(false);
		nameInputPanel.add(nameSubmitButton);
		
		mWindowContainer.add(nameInputPanel);
		
		//update container
		mWindowContainer.validate();
	}
	
	//test JSON file I/O
	public void TestIO(String filename) throws FileNotFoundException
	{
		LoadSceneFromJson(filename);
		System.out.println(mCurrentScene.toString());
	}
	
	//main menu start button handler
	public class startButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			CreateNameInputScene();
		}
	}

	public static void main(String[] args) {
		//instantiate a game
		Game game = new Game();
		game.InitializeGameWindow();
		game.InitializeMenu();
		
		//ONLY for testing I/O
		try {
			game.TestIO("testJson/test1.json");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
	}

}
