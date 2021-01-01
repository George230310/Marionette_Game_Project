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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

public class Game {
	//DEFINE CONSTANTS
	public static final Gson gson = new Gson();
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	
	//game title panel
	public static final int TITLE_X = 255;
	public static final int TITLE_Y = 100;
	public static final int TITLE_WIDTH = 512;
	public static final int TITLE_HEIGHT = 150;
	
	//game menu panel
	public static final int OPTION_X = 255;
	public static final int OPTION_Y = 300;
	public static final int OPTION_WIDTH = 512;
	public static final int OPTION_HEIGHT = 200;
	
	//main text panel
	public static final int MAIN_TEXT_X = 255;
	public static final int MAIN_TEXT_Y = 100;
	public static final int MAIN_TEXT_WIDTH = 512;
	public static final int MAIN_TEXT_HEIGHT = 400;
	
	//in-game main text panel
	public static final int INGAME_MAIN_TEXT_X = 102;
	public static final int INGAME_MAIN_TEXT_Y = 75;
	public static final int INGAME_MAIN_TEXT_WIDTH = 819;
	public static final int INGAME_MAIN_TEXT_HEIGHT = 400;
	
	//in-game options panel
	public static final int INGAME_OPTIONS_X = 307;
	public static final int INGAME_OPTIONS_Y = 500;
	public static final int INGAME_OPTIONS_WIDTH = 410;
	public static final int INGAME_OPTIONS_HEIGHT = 200;
	
	//define fonts
	public static final Font TITLE_FONT = new Font("SansSerif", Font.PLAIN, 88);
	public static final Font MENU_FONT = new Font("SansSerif", Font.PLAIN, 36);
	public static final Font MAIN_TEXT_FONT = new Font("SansSerif", Font.PLAIN, 28);
	public static final Font INGAME_MAIN_TEXT_FONT = new Font("SansSerif", Font.PLAIN, 22);
	public static final Font INGAME_OPTIONS_FONT = new Font("SansSerif", Font.PLAIN, 24);
	
	//define colors
	public static final Color WINDOW_BKG = Color.darkGray;
	public static final Color BUTTON_BKG = Color.gray;
	public static final Color TEXT_COLOR = Color.white;
	public static final Color INGAME_MAINTEXT_BKG = Color.darkGray;
	public static final Color INGAME_BUTTON_BKG = Color.darkGray;
	
	private Scene mCurrentScene;
	private String mCurrentSceneFileName;
	private JFrame mGameWindow;
	private Container mWindowContainer;
	private String mPlayerName;
	
	//name input box
	private static JTextField mNameBox;
	
	//load a scene from a specific Gson file
	public void LoadSceneFromJson(String filename) throws FileNotFoundException
	{
		//create reader object and read
		Reader fReader = new FileReader(filename);
		mCurrentScene = gson.fromJson(fReader, Scene.class);
		mCurrentSceneFileName = new String(filename);
		
		System.out.println(mCurrentSceneFileName + "\n"); //for debug ONLY
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
		menuOptionPanel.setLayout(new GridLayout(2, 1));
		
		//TODO: create credit???
		
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
		nameInputPanel.setLayout(new GridLayout(4, 1));
		nameInputPanel.setBounds(MAIN_TEXT_X, MAIN_TEXT_Y, MAIN_TEXT_WIDTH, MAIN_TEXT_HEIGHT);
		nameInputPanel.setBackground(WINDOW_BKG);
		JLabel caption = new JLabel("Please enter your name and continue:");
		caption.setForeground(TEXT_COLOR);
		caption.setFont(MAIN_TEXT_FONT);
		nameInputPanel.add(caption);
		
		//create input box
		mNameBox = new JTextField();
		mNameBox.setFont(MAIN_TEXT_FONT);
		mNameBox.setHorizontalAlignment(JTextField.CENTER);
		nameInputPanel.add(mNameBox);
		
		//create submit button
		JButton nameSubmitButton = new JButton("Continue");
		nameSubmitButton.setFont(MAIN_TEXT_FONT);
		nameSubmitButton.setBackground(BUTTON_BKG);
		nameSubmitButton.setForeground(TEXT_COLOR);
		nameSubmitButton.setFocusPainted(false);
		nameSubmitButton.addActionListener(new nameSubmitButtonHandler());
		nameInputPanel.add(nameSubmitButton);
		
		mWindowContainer.add(nameInputPanel);
		
		//update container
		mWindowContainer.validate();
	}
	
	//prints current scene to game window
	//CAUTION: There cannot be more than 4 options for a scene
	//CAUTION: The # of references must match the # of options
	public void CreateCurrentScene()
	{
		//check for existence of current scene
		if(mCurrentScene == null)
		{
			return;
		}
		
		mWindowContainer.removeAll();
		mWindowContainer.repaint();
		
		//make main text panel
		JPanel mainTextPanel = new JPanel();
		mainTextPanel.setBounds(INGAME_MAIN_TEXT_X, INGAME_MAIN_TEXT_Y, INGAME_MAIN_TEXT_WIDTH, INGAME_MAIN_TEXT_HEIGHT);
		mainTextPanel.setBackground(INGAME_MAINTEXT_BKG);
		
		//make main text area
		String temp = "";
		if(mCurrentScene != null)
		{
			temp = new String(mCurrentScene.mDescription);
		}
		
		//replace player name
		if(mPlayerName != null)
		{
			temp = temp.replaceAll("@", mPlayerName);
		}
		
		//make main text area
		JTextArea mainTextArea = new JTextArea(temp);
		mainTextArea.setBounds(INGAME_MAIN_TEXT_X, INGAME_MAIN_TEXT_Y, INGAME_MAIN_TEXT_WIDTH, INGAME_MAIN_TEXT_HEIGHT);
		mainTextArea.setBackground(INGAME_MAINTEXT_BKG);
		mainTextArea.setForeground(TEXT_COLOR);
		mainTextArea.setFont(INGAME_MAIN_TEXT_FONT);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		mainTextPanel.add(mainTextArea);
		
		//add main text panel to container
		mWindowContainer.add(mainTextPanel);
		
		//create option buttons panel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(INGAME_OPTIONS_X, INGAME_OPTIONS_Y, INGAME_OPTIONS_WIDTH, INGAME_OPTIONS_HEIGHT);
		buttonsPanel.setBackground(INGAME_BUTTON_BKG);
		buttonsPanel.setLayout(new GridLayout(4, 1));
		
		//create option buttons
		for(int i = 0; i < mCurrentScene.mOptions.length; i++)
		{
			optionButton newButton = new optionButton(mCurrentScene.mOptions[i], mCurrentScene.mOptionReferences[i]);
			newButton.setFocusPainted(false);
			buttonsPanel.add(newButton);
		}
		
		//TODO: make save button and implement save function
		
		
		//add option buttons panel to container
		mWindowContainer.add(buttonsPanel);
		
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
	
	//name submit button handler
	public class nameSubmitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String temp = mNameBox.getText();
			
			//validate input
			if(temp.isEmpty())
			{
				System.out.println("No name input..."); //for debug
			}
			else
			{
				mPlayerName = new String(temp);
				mNameBox.setText("");
				System.out.println(mPlayerName);
				
				//load the first scene
				try {
					LoadSceneFromJson("testJson/test1.json");
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found");
				}
				
				//print the current scene
				CreateCurrentScene();
			}
		}
	}
	
	//customed button for in-game options
	//TODO: implement last scene return to menu
	public class optionButton extends JButton
	{
		private static final long serialVersionUID = -7225007505306667685L;
		private String URL;
		
		public optionButton(String text, String url)
		{
			super(text);
			setFont(INGAME_OPTIONS_FONT);
			setBackground(BUTTON_BKG);
			setForeground(TEXT_COLOR);
			URL = url;
			addActionListener(new optionButtonHandler());
		}
		
		public String getURL()
		{
			return URL;
		}
		
		public class optionButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//TODO: reinitialize menu
				try {
					LoadSceneFromJson(URL);
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found");
				}
				
				CreateCurrentScene();
			}
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
