package game;

public class Save {
	protected String mName;
	protected String mJsonName;
	protected String mSceneName;
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Save Name: " + mName + "\n");
		buffer.append("Save URL: " + mJsonName + "\n");
		buffer.append("Saved Scene: " + mSceneName + "\n");
		
		return buffer.toString();
	}
}