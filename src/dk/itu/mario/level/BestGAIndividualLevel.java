package dk.itu.mario.level;

import java.util.Arrays;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.GroundIndividual;
import dk.itu.mario.geneticAlgorithm.Individual;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
	private static final int LEVEL_INIT_WIDTH = 1;
	private static final int EXIT_DISTANCE_FROM_END = 4;
	
	private int minGroundSequence;
    private GamePlay playerM;
    
    private Individual bestGround;
    private Individual bestBlocks;
    
    public BestGAIndividualLevel(int height, long seed, int difficulty, int type, GamePlay playerMetrics, Individual bestGround, Individual bestBlocks) {
        
    	super((LEVEL_INIT_WIDTH + bestGround.getChromossome().length + EXIT_DISTANCE_FROM_END) * GroundIndividual.MIN_GROUND, height);
    	
        this.playerM = playerMetrics;
        this.bestGround = bestGround;
        this.bestBlocks = bestBlocks;
        this.minGroundSequence = GroundIndividual.MIN_GROUND;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Attach an initial ground to the terrain avoiding hills
    	// in the beginning of the level
    	int initialGround[] = new int[LEVEL_INIT_WIDTH];
    	int endGround[] = new int[EXIT_DISTANCE_FROM_END];
    	
    	int bestIndividualGround[] = bestGround.getChromossome();    	
    	
    	Arrays.fill(initialGround, FindFirstNonEmptyBlock(bestIndividualGround));
    	Arrays.fill(endGround, FindFirstNonEmptyBlock(reverseGround(bestIndividualGround)));
    	
    	int ground[] = new int[initialGround.length + bestIndividualGround.length + endGround.length];
    	
    	System.out.println(width);
    	
    	System.arraycopy(initialGround, 0, ground, 0, initialGround.length);
    	System.arraycopy(bestGround.getChromossome(), 0, ground, initialGround.length, bestIndividualGround.length);
    	System.arraycopy(endGround, 0, ground, initialGround.length + bestIndividualGround.length, endGround.length);
    	    	    	
    	int k = 0;
    	
    	for(int i = 0; i < ground.length; i++)
    	{
        	for(int j = 0; j < minGroundSequence; j++)
        	{
        		// Setting the ground blocks 
        		setBlock(k + j, height - ground[i], HILL_TOP);
        		
              	// Adding path to the beginning of level to avoid falling 
            	// and dying when spawning the player 
                for(int currentHeight = height - ground[i]; currentHeight <= height; currentHeight++)
                {
                	if(i > 0 && j == 0)
                	{
                		if(ground[i] > ground[i - 1])
                		{
                			if(currentHeight == height - ground[i - 1])
                			{
                				setBlock(k + j, currentHeight, RIGHT_POCKET_GRASS);
                			}
                			else if(currentHeight == height - ground[i])
                			{
                				setBlock(k + j, currentHeight, LEFT_UP_GRASS_EDGE);
                			}
                			else if(currentHeight < height - ground[i - 1])
                			{
                				setBlock(k + j, currentHeight, LEFT_GRASS_EDGE);
                			}
                			else
                			{
                				setBlock(k + j, currentHeight, HILL_FILL);
                			}
                		}
            			else
            			{
            				setBlock(k + j, currentHeight + 1, HILL_FILL);
            			}
                	}
                	else if(i < ground.length - 1 && j == minGroundSequence - 1)
                	{
                		if(ground[i] > ground[i + 1])
                		{
                			if(currentHeight == height - ground[i + 1])
                			{
                				setBlock(k + j, currentHeight, LEFT_POCKET_GRASS);
                			}
                			else if(currentHeight == height - ground[i])
                			{
                				setBlock(k + j, currentHeight, RIGHT_UP_GRASS_EDGE);
                			}
                			else if(currentHeight < height - ground[i + 1])
                			{
                				setBlock(k + j, currentHeight, RIGHT_GRASS_EDGE);
                			}
                			else
                			{
                				setBlock(k + j, currentHeight, HILL_FILL);
                			}
                		}
            			else
            			{
            				setBlock(k + j, currentHeight + 1, HILL_FILL);
            			}
                	}
                	else
                	{
                		setBlock(k + j, currentHeight + 1, HILL_FILL);
                	}
                }
        	}
        	
        	k += minGroundSequence;
    	}
    	
        // Create the exit
        xExit = width - (int)((EXIT_DISTANCE_FROM_END * minGroundSequence)/ 2);
        yExit = height - endGround[0];
    	
    	fixTerrain(ground);
    }
    
    private int []reverseGround(int ground[])
    {
    	int reversedGround[] = new int[ground.length];
    	
    	System.arraycopy(ground, 0, reversedGround, 0, ground.length);
    	
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

    }
	
}
