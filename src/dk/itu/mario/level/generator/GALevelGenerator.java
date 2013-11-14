package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.geneticAlgorithm.UniformGASuperMario;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	private static int levelWidth = 100;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		UniformGASuperMario gaMap = new UniformGASuperMario(15 * levelWidth, levelWidth, 0.01f, 1.0f, (int)(0.22 * levelWidth), 4);
		gaMap.StartEvolution(18 * levelWidth);
		
		LevelInterface level = new BestGAIndividualLevel(levelWidth, 15, new Random().nextLong(), 1, 1, playerMetrics, gaMap.getBestIndividual());
		
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
