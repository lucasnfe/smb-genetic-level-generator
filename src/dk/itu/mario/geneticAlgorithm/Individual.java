package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class Individual {

	private static final int MIN_GROUND_SEQUENCE = 5;
	private static final int MAX_GROUND_HEIGHT = 6;
	
	private float fitness;
	
	private int chromossomeSize;
	
	private int ground[];
	private int coins[];
	private int enemies[];
	private int blocks[];
	
	public Individual(int chromossomeSize)
	{
		this.fitness = 0.0f;
		this.chromossomeSize = chromossomeSize;
		
		ground = new int[chromossomeSize];
		coins = new int[chromossomeSize];
		enemies = new int[chromossomeSize];
		blocks = new int[chromossomeSize];
		
		Random gerador = new Random();
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			ground[i] = gerador.nextInt(MAX_GROUND_HEIGHT);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			coins[i] = gerador.nextInt(1);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			enemies[i] = gerador.nextInt(1);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			blocks[i] = gerador.nextInt(1);
		}
	}
	
	public void calcFitness()
	{
		fitness = 0.0f;
		
		for(int i = 0; i < chromossomeSize/MIN_GROUND_SEQUENCE; i++)
		{
			int tempArray[] = new int[MIN_GROUND_SEQUENCE];
			
			System.arraycopy(ground, i * MIN_GROUND_SEQUENCE, tempArray, 0, MIN_GROUND_SEQUENCE);
			fitness += Entropy(tempArray);
		}
	}
	
	private float Entropy(int chromossomeSegment[])
	{
		float entropy = 0.0f;
		
		// calculate ground fitness based on its entropy
		for(int i = 0; i < MAX_GROUND_HEIGHT; i++)
		{
			int heightAmount = 0;
			
			for(int j = 0; j < chromossomeSegment.length; j++)
			{
				if(i == chromossomeSegment[j])
				{
					heightAmount++;
				}
			}			
			
			if(heightAmount > 0)
			{
				double heightProportion = (double) heightAmount / (double)chromossomeSegment.length;
				heightProportion =  heightProportion * (Math.log(heightProportion) / Math.log(2));
						
				entropy -= heightProportion;
			}
		}
	
		return entropy;
	}
	
	public int[] getGround()
	{
		return ground;
	}
	
	public int[] getCoins()
	{
		return coins;
	}
	
	public int[] getEnemies()
	{
		return enemies;
	}
	
	public int[] getBlocks()
	{
		return blocks;
	}
	
	public float getFitness()
	{
		return fitness;
	}
	
	public float getSize()
	{
		return chromossomeSize;
	}
}
