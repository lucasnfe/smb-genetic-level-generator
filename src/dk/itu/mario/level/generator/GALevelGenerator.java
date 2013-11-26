package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.UniformGASuperMario;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	private static int LEVEL_WIDTH = 500;
	private static int LEVEL_HEIGHT = 15;
	private static int MIN_GROUND_SEQUENCE = 3;
	private static int INDIVIDUAL_SIZE = LEVEL_WIDTH;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		UniformGASuperMario gaMap = new UniformGASuperMario(LEVEL_WIDTH, INDIVIDUAL_SIZE, 0.01f, 0.8f, (int)(0.25 * INDIVIDUAL_SIZE), 2);
		gaMap.StartEvolution(LEVEL_WIDTH);
		
		LevelInterface level = new BestGAIndividualLevel(LEVEL_HEIGHT, new Random().nextLong(), 1, 1, playerMetrics, gaMap.getBestIndividual(), MIN_GROUND_SEQUENCE);
		
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
