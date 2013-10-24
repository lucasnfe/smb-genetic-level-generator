package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

import dk.itu.mario.level.SlicesManager;

public class Individual {

	private float fitness;
	
	private int chromossomeSize;
	private int chromossome[];
	
	public Individual(int chromossomeSize)
	{
		this.fitness = 0.0f;
		this.chromossomeSize = chromossomeSize;
		
		chromossome = new int[chromossomeSize];
		
		Random gerador = new Random();
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			chromossome[i] = gerador.nextInt(SlicesManager.getSliceAmount());
		}
	}
	
	public void calcFitness()
	{
		
	}
	
	public int getGen(int genIndex)
	{
		return chromossome[genIndex];
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
