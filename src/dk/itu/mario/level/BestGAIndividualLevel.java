package dk.itu.mario.level;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
    private GamePlay playerM;

    public BestGAIndividualLevel(int width, int height, long seed, int difficulty, int type, GamePlay playerMetrics) {
        
    	super(width, height);
        this.playerM = playerMetrics;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Create the map
    	int floor = height - 1;
    	Random gerador = new Random();
    	
    	for(int i = 0; i < width; i++)
    	{
            int randomSlice = gerador.nextInt(SlicesManager.getSliceAmount());
    		
            setBlock(i, SlicesManager.getSlice(randomSlice));
    	}
    	
        // Create the exit
        xExit = width - 4;
        yExit = floor;
    }
	
}
