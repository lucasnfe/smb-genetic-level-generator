package dk.itu.mario.level;

import java.util.Arrays;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.Enemy;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.geneticAlgorithm.Individual;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
	private static final int LEVEL_INIT_WIDTH = 1;
	private static final int EXIT_DISTANCE_FROM_END = 4;
	
	private int minGroundSequence;
    private GamePlay playerM;
    private Individual bestGroundIndividual;
    private Individual bestEnemyIndividual;
    
    public BestGAIndividualLevel(int height, long seed, int difficulty, int type, GamePlay playerMetrics, Individual bestGroundIndividual, Individual bestEnemyIndividual, int minGroundSequence) {
        
    	super((LEVEL_INIT_WIDTH + bestGroundIndividual.getChrmossome().length + EXIT_DISTANCE_FROM_END) * minGroundSequence, height);
    	
        this.playerM = playerMetrics;
        this.bestGroundIndividual = bestGroundIndividual;
        this.bestEnemyIndividual = bestEnemyIndividual;
        this.minGroundSequence = minGroundSequence;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Attach an initial ground to the terrain avoiding hills
    	// in the beginning of the level
    	int initialGround[] = new int[LEVEL_INIT_WIDTH];
    	int endGround[] = new int[EXIT_DISTANCE_FROM_END];
    	
    	int bestIndividualGround[] = bestGroundIndividual.getChrmossome();    	
    	
    	Arrays.fill(initialGround, FindFirstNonEmptyBlock(bestIndividualGround));
    	Arrays.fill(endGround, FindFirstNonEmptyBlock(reverseGround(bestIndividualGround)));
    	
    	int ground[] = new int[initialGround.length + bestIndividualGround.length + endGround.length];

    	
    	System.out.println(width);
    	
    	System.arraycopy(initialGround, 0, ground, 0, initialGround.length);
    	System.arraycopy(bestGroundIndividual.getChrmossome(), 0, ground, initialGround.length, bestIndividualGround.length);
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
        	
                //Fazer as contas da altura
                for(int j = 0; j < minGroundSequence; j++)
        	{
                    int enemyPos = i * (minGroundSequence - 1) + j;
                    if(i > LEVEL_INIT_WIDTH && bestEnemyIndividual.getChrmossome()[enemyPos] != 0)
                    {
                        SpriteTemplate enemies = new SpriteTemplate(bestEnemyIndividual.getChrmossome()[enemyPos], false);
                        setSpriteTemplate(enemyPos, height - ground[i] - 4, enemies);
                    }
                }
        	k += minGroundSequence;
    	}
    	for( int i = 0; i < bestEnemyIndividual.getSize(); i++ )
        {
            System.out.print(bestEnemyIndividual.getChrmossome()[i]+" ");
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
