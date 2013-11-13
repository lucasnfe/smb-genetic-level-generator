package dk.itu.mario.level;

import java.util.Arrays;
import java.util.Collections;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.Individual;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
	private static final int LEVEL_INIT_WIDTH = 5;
	
    private GamePlay playerM;
    private Individual bestIndividual;
    
    public BestGAIndividualLevel(int width, int height, long seed, int difficulty, int type, GamePlay playerMetrics, Individual bestIndividual) {
        
    	super(width, height);
    	
        this.playerM = playerMetrics;
        this.bestIndividual = bestIndividual;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Attach an initial ground to the terrain avoiding hills
    	// in the beginning of the level
    	int initialGround[] = new int[LEVEL_INIT_WIDTH];
    	
    	Arrays.fill(initialGround, FindFirstNonEmptyBlock());
    	
    	int ground[] = new int[width + LEVEL_INIT_WIDTH];
    	
    	System.arraycopy(initialGround, 0, ground, 0, initialGround.length);
    	System.arraycopy(bestIndividual.getGround(), 0, ground, initialGround.length, width);
    	
    	for(int i = 0; i < ground.length; i++)
    	{
    		// Setting the ground blocks 
    		setBlock(i, height - ground[i], HILL_TOP);
    	}
    	
    	fixTerrain(ground);
    	
        // Create the exit
        xExit = width - 4;
        yExit = height;
    }
    
    private int FindFirstNonEmptyBlock()
    {
    	int ground[] = bestIndividual.getGround();
    	
    	for(int i = 0; i < ground.length; i++)
    	{
    		if(ground[i] > 0)
    		{
    			return ground[i];
    		}
    	}
    	
    	return 1;
    }
    
    private void fixTerrain(int ground[])
    {
    	for(int i = 0; i < ground.length; i++)
    	{
            for(int j = height - ground[i] + 1; j <= height; j++)
            {
            	setBlock(i, j, HILL_FILL);
            }
    	}
    }
	
}
