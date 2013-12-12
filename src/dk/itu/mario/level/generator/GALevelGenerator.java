package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.GAEnemies;
import dk.itu.mario.geneticAlgorithm.GAGround;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	private static int LEVEL_WIDTH = 100;
	private static int LEVEL_HEIGHT = 15;
	private static int MIN_GROUND_SEQUENCE = 3;
	private static int INDIVIDUAL_SIZE = LEVEL_WIDTH;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		GAGround gaMap = new GAGround(LEVEL_WIDTH * 2, INDIVIDUAL_SIZE, 0.01f, 0.8f, (int)(0.25 * INDIVIDUAL_SIZE), 2);
		gaMap.StartEvolution(LEVEL_WIDTH * 2);
		
		System.out.println("---------------------------");
                
        GAEnemies gaEnemies = new GAEnemies(LEVEL_WIDTH * 2, INDIVIDUAL_SIZE, 0.01f, 0.8f, (int)(0.25 * INDIVIDUAL_SIZE), 2, gaMap.getBestIndividual(), 0f);
		gaEnemies.StartEvolution(LEVEL_WIDTH * 2);
                
		LevelInterface level = new BestGAIndividualLevel(LEVEL_HEIGHT, new Random().nextLong(), 1, 1, playerMetrics, gaMap.getBestIndividual(), gaEnemies.getBestIndividual(), MIN_GROUND_SEQUENCE);
		
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
