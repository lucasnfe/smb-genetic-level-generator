package dk.itu.mario.level;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.Individual;

public class BestGAIndividualLevel extends Level implements LevelInterface {
	
    private GamePlay playerM;
    private Individual bestIndividual;

    public BestGAIndividualLevel(int width, int height, long seed, int difficulty, int type, GamePlay playerMetrics, Individual bestIndividual) {
        
    	super(width, height);
    	
        this.playerM = playerMetrics;
        this.bestIndividual = bestIndividual;
        
        creat(seed, difficulty, type);
    }
    
    private void creat(long seed, int difficulty, int type) {
    
    	// Create the map
    	int floor = height - 1;
    	
    	for(int i = 0; i < bestIndividual.getSize(); i++)
    	{
            setBlock(i, SlicesManager.getSlice(bestIndividual.getGen(i)));
    	}
    	
        // Create the exit
        xExit = width - 4;
        yExit = floor;
    }
	
}
