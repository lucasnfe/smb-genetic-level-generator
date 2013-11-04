package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public abstract class GeneticAlgorithm {

	private int eliteSize;
	
	protected int currentGeneration;
	protected int fitnessEvaluationAmount;
	
	protected float mutationProbability;
	protected float crossOverProbability;
	
	protected Individual population[];
	protected Individual matingPool[];
	
	protected static Comparator<Individual> individualComparator;

    // We initialize static variables inside a static block.
    static {
    	
    	individualComparator = new Comparator<Individual>() {
        
    	@Override
        public int compare(Individual i1, Individual i2) {
        	
    	        if (i1.getFitness() < i2.getFitness()) 
    	        {
    	        	return 1;
    	        }
    	        else if (i1.getFitness() > i2.getFitness())
    	        {
    	        	return -1;
    	        }
    	        	
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
	
	private void EvaluatePopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i].calcFitness();
			fitnessEvaluationAmount++;
		}
	}
	
	protected abstract void SelectPopulation();
	
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
			
			population = matingPool.clone();
				
			EvaluatePopulation();
			currentGeneration++;
			
			System.out.format("best fitness: %f\n", getBestIndividual().getFitness());
		} 
	}
	
	protected abstract void InitPopulation();
	protected abstract void CrossOver();
	protected abstract void Mutation();
	
	private void Elitism()
	{
		Arrays.sort(population, individualComparator);
		
		for(int i = 0; i < eliteSize; i++)
		{
			matingPool[i] = population[i];
		}
	}
	
	public Individual getBestIndividual()
	{
		Arrays.sort(population, individualComparator);
		
		return population[0];
	}
}
