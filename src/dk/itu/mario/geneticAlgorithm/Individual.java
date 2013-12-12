package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

public abstract class Individual {

	protected float fitness;
	
        protected int chromossome[];
        
	protected int chromossomeSize;	
        
	public Individual(int chromossomeSize)
	{
		this.fitness = 0.0f;
		this.chromossomeSize = chromossomeSize;
	}
	
	public abstract void calcFitness();
	
	public float getFitness()
	{
		return fitness;
	}
	
	public float getSize()
	{
            return chromossomeSize;
	}
        
        public int[] getChrmossome()
        {
            return chromossome;
        }
	
	public String toString() 
	{
	   return  Arrays.toString(chromossome) + " " + fitness;
	}
}
