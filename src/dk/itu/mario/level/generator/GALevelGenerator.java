package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.UniformGASuperMario;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	private static int LEVEL_WIDTH = 50;
	private static int LEVEL_HEIGHT = 15;
	private static int INDIVIDUAL_SIZE = LEVEL_WIDTH;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		UniformGASuperMario gaMap = new UniformGASuperMario(LEVEL_WIDTH * 4, INDIVIDUAL_SIZE, 0.01f, 0.8f, 5, 2);
		gaMap.startEvolution(LEVEL_WIDTH * 2);
		
		LevelInterface level = new BestGAIndividualLevel(LEVEL_HEIGHT, new Random().nextLong(), 1, 1, playerMetrics, gaMap.GetBestGround());
		
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
