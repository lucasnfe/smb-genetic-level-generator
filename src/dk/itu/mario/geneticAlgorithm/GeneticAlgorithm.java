package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public abstract class GeneticAlgorithm {

	private int eliteSize;
	
	private int currentGeneration;
	private int fitnessEvaluationAmount;
	
	private float mutationProbability;
	private float crossOverProbability;
	
	protected Individual population[];
	protected Individual matingPool[];
	
	static private Comparator<Individual> ascTitle;

    // We initialize static variables inside a static block.
    static {
    	
    	ascTitle = new Comparator<Individual>() {
        
    	@Override
        public int compare(Individual i1, Individual i2) {
        	
    	        if (i1.getFitness() < i2.getFitness()) 
    	        	return 1;

    	        return 0;
    		}
    	};
    };
	
	public GeneticAlgorithm(int populationSize, float mutationProbability, float crossOverProbability, int eliteSize) {
		
		population = new Individual[populationSize];
		matingPool = new Individual[populationSize];
		
		this.mutationProbability = mutationProbability;
		this.crossOverProbability = crossOverProbability;
		
		this.eliteSize = eliteSize;
	}
	
	public abstract void InitPopulation();
	
	public void EvaluatePopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i].calcFitness();
			fitnessEvaluationAmount++;
		}
	}
	
	public abstract void SelectPopulation();
	
	public void StartEvolution(int maxGeneration) {
	
		InitPopulation();
		EvaluatePopulation();
		
		while(currentGeneration < maxGeneration)
		{
			SelectPopulation();
			CrossOver();
			Mutation();
			
			if(eliteSize > 0) {
				
				Elitism();
			}
				
			EvaluatePopulation();
			currentGeneration++;
		} 
	}
	
	public abstract void CrossOver();
	public abstract void Mutation();
	
	public void Elitism()
	{
		Arrays.sort(population);
		
		for(int i = 0; i < eliteSize; i++)
		{
			matingPool[i] = population[i];
		}
	}
	
	public Individual getBestIndividual()
	{
		Arrays.sort(population, ascTitle);
		
		return population[population.length - 1];
	}
	
	public float getMurationProbability() {
	
		return mutationProbability;
	}
	
	public float getCrossOverProbability() {
		
		return crossOverProbability;
	}
	
	public int getEliteSize() {
		
		return eliteSize;
	}
	
	public int getCurrentGeneration() {
		
		return currentGeneration;
	}
	
	public int getFitnessEvaluationAmount()
	{
		return fitnessEvaluationAmount;
	}
}
