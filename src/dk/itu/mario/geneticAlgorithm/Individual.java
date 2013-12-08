package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;

public abstract class Individual {

	protected float fitness;	
	protected int chromossomeSize;
	
	protected int chromossome[];
	
	public Individual(int chromossomeSize)
	{
		this.fitness = 0.0f;
		this.chromossomeSize = chromossomeSize;
	}
	
	public abstract void calculateFitness();
	
	public int[] getChromossome()
	{
		return chromossome;
	}
	
	public float getFitness()
	{
		return fitness;
	}
		
	public String toString() 
	{
	   return  Arrays.toString(chromossome) + " " + fitness;
	}
}
