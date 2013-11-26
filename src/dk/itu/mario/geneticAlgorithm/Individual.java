package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

public class Individual {

	public static final int MIN_GROUND = 3;
	public static final int MIN_GROUND_SEQUENCE = 3;
	public static final int MAX_GROUND_HEIGHT = 10;
	public static final int MAX_BLOCK_HEIGHT = 6;
	public static final int ENEMIES_TYPES = 3;
	
	private float fitness;
	private float groundFitness;
	private float blocksFitness;
	private float coinsFitness;
	private float enemiesFitness;
	
	private int chromossomeSize;
	
	private int ground[];
	private int blocks[];
	private int coins[];
	private int enemies[];
	
	public Individual(int chromossomeSize)
	{
		this.fitness = 0.0f;
		this.chromossomeSize = chromossomeSize;
		
		ground = new int[chromossomeSize/MIN_GROUND];
		blocks = new int[chromossomeSize];
		enemies = new int[chromossomeSize];
		coins = new int[chromossomeSize];
		
		Random gerador = new Random();
		
		for(int i = 0; i < chromossomeSize/MIN_GROUND; i++)
		{
			ground[i] = gerador.nextInt(MAX_GROUND_HEIGHT);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			blocks[i] = gerador.nextInt(MAX_BLOCK_HEIGHT);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			enemies[i] = gerador.nextInt(ENEMIES_TYPES);
		}
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			coins[i] = gerador.nextInt(1);
		}
	}
	
	public void calcFitness()
	{
		fitness = 0.0f;
		
		// Calculating ground fitness
		groundFitness = 0.0f;
		
		int groundChromossomeSize = chromossomeSize/MIN_GROUND;
		for(int i = 0; i < groundChromossomeSize/MIN_GROUND_SEQUENCE; i++)
		{
			int tempArray[] = new int[MIN_GROUND_SEQUENCE];
			
			System.arraycopy(ground, i * MIN_GROUND_SEQUENCE, tempArray, 0, MIN_GROUND_SEQUENCE);
			groundFitness += Entropy(tempArray);
		}
		
		// Calculating blocks fitness
		blocksFitness = 0.0f;
		
		
		
		fitness += groundFitness;
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
	
	public void setGroundBlock(int position, int block)
	{
		ground[position] = block;
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
	
	public String toString() 
	{
	   return  Arrays.toString(ground) + " " + fitness;
	}
}
