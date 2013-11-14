package dk.itu.mario.level;

import java.util.Arrays;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.Individual;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
	private static final int LEVEL_INIT_WIDTH = 5;
	private static final int EXIT_DISTANCE_FROM_END = 10;
	
    private GamePlay playerM;
    private Individual bestIndividual;
    
    public BestGAIndividualLevel(int width, int height, long seed, int difficulty, int type, GamePlay playerMetrics, Individual bestIndividual) {
        
    	super(LEVEL_INIT_WIDTH + width + EXIT_DISTANCE_FROM_END, height);
    	
        this.playerM = playerMetrics;
        this.bestIndividual = bestIndividual;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Attach an initial ground to the terrain avoiding hills
    	// in the beginning of the level
    	int initialGround[] = new int[LEVEL_INIT_WIDTH];
    	int endGround[] = new int[EXIT_DISTANCE_FROM_END];
    	
    	int bestIndividualGround[] = bestIndividual.getGround();    	
    	
    	Arrays.fill(initialGround, FindFirstNonEmptyBlock(bestIndividualGround));
    	Arrays.fill(endGround, FindFirstNonEmptyBlock(reverseGround(bestIndividualGround)));
    	
    	int ground[] = new int[width];
    	
    	System.out.println(width);
    	
    	System.arraycopy(initialGround, 0, ground, 0, initialGround.length);
    	System.arraycopy(bestIndividual.getGround(), 0, ground, initialGround.length, bestIndividualGround.length);
    	System.arraycopy(endGround, 0, ground, initialGround.length + bestIndividualGround.length, endGround.length);
    	    	    	
    	for(int i = 0; i < ground.length; i++)
    	{
    		// Setting the ground blocks 
    		setBlock(i, height - ground[i], HILL_TOP);
    	}
    	
        // Create the exit
        xExit = ground.length - (int)(EXIT_DISTANCE_FROM_END / 2);
        yExit = height - endGround[0];
    	
    	fixTerrain(ground);
    }
    
    private int []reverseGround(int ground[])
    {
    	int reversedGround[] = ground;
    	
    	for (int i = 0; i < ground.length / 2; i++) 
    	{
			int temp = reversedGround[i];
			reversedGround[i] = reversedGround[reversedGround.length - 1 - i];
			reversedGround[ground.length - 1 - i] = temp;
    	}
    	
    	return reversedGround;
    }
    
    private int FindFirstNonEmptyBlock(int ground[])
    {
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
      	// Adding path to the beginning of level to avoid falling 
    	// and dying when spawning the player 
    	for(int i = 0; i < ground.length; i++)
    	{
            for(int j = height - ground[i] + 1; j <= height; j++)
            {
            	setBlock(i, j, HILL_FILL);
            }
    	}
    }
	
}
