package game;

public class Save {
	protected String mName;
	protected String mJsonName;
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Save Name: " + mName + "\n");
		buffer.append("Save URL: " + mJsonName + "\n");
		
		return buffer.toString();
	}
}
