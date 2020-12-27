package game;

import java.io.*;
import com.google.gson.Gson;

public class Game {
	Scene mCurrentScene;
	
	//load a scene from a specific gson file
	public void LoadSceneFromJson(String filename) throws FileNotFoundException
	{
		//create reader object and read
		Gson gson = new Gson();
		Reader fReader = new FileReader(filename);
		mCurrentScene = gson.fromJson(fReader, Scene.class);
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
		
		//ONLY for testing I/O
		try {
			game.TestIO("testJson/test1.json");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
	}

}
