package dk.itu.mario.level;

import dk.itu.mario.engine.sprites.Enemy;

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
		BLOCK_COIN, 
		EMPTY_SPACE,
		EMPTY_SPACE, 
		EMPTY_SPACE,
		HILL_TOP 
	};
	
	private static byte[] sliceC = 
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
		EMPTY_SPACE 
	};
	
	private static byte[][] slices = {sliceA, sliceB, sliceC};
	
	public static byte[] getSlice(int sliceNumber)
	{
		return slices[sliceNumber];
	}
	
	public static int getSliceAmount()
	{
		return slices.length;
	}
}
