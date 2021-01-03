package game;

public class Scene {
	protected String mDescription;
	protected String[] mOptions;
	protected String[] mOptionReferences;
	protected String mSceneName;
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		//append scene description
		buffer.append("Scene Description: ");
		buffer.append(mDescription + "\n");
		
		buffer.append("\n");
		
		//append options
		for(String each_str : mOptions)
		{
			buffer.append("Option: " + each_str + "\n");
		}
		
		buffer.append("\n");
		
		//append references
		for(String each_str: mOptionReferences)
		{
			buffer.append("Option Reference: " + each_str + "\n");
		}
		
		buffer.append("Scene Name: ");
		buffer.append(mSceneName + "\n");
		
		return buffer.toString();
	}
}
