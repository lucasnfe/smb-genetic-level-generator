package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.SuperMarioGA;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	private static int LEVEL_WIDTH = 400;
	private static int LEVEL_HEIGHT = 15;
	private static int INDIVIDUAL_SIZE = LEVEL_WIDTH;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		SuperMarioGA gaMap = new SuperMarioGA(LEVEL_WIDTH * 10, INDIVIDUAL_SIZE, 0.3f, 0.9f, 1, 2, 50);
		gaMap.startEvolution(LEVEL_WIDTH * 2);
		
		LevelInterface level = new BestGAIndividualLevel(LEVEL_HEIGHT, new Random().nextLong(), 1, 1, playerMetrics, 
				gaMap.GetBestGround(), gaMap.GetBestBlocks(), gaMap.GetBestEnemies(), gaMap.GetBestCoins());
		
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
