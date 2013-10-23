package dk.itu.mario.level.generator;

import java.util.Random;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.BestGAIndividualLevel;

public class GALevelGenerator implements LevelGenerator {
	
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		
		LevelInterface level = new BestGAIndividualLevel(320, 15, new Random().nextLong(), 1, 1, playerMetrics);
		return level;
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
