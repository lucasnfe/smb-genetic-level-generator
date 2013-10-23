package dk.itu.mario.level;

public class SlicesManager extends Level {

	private static byte[] sliceA = 
	{
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE,
		HILL_TOP 
	}; 
	
	private static byte[] sliceB = 
	{
		EMPTY_SPACE,
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE,
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		EMPTY_SPACE, 
		COIN, 
		EMPTY_SPACE,
		EMPTY_SPACE, 
		EMPTY_SPACE,
		HILL_TOP 
	};
	
	private static byte[][] slices = {sliceA, sliceB};
	
	public static byte[] getSlice(int sliceNumber)
	{
		return slices[sliceNumber];
	}
	
	public static int getSliceAmount()
	{
		return slices.length;
	}
}
