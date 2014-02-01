package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public abstract class GeneticAlgorithm {

	float lastBestFitness;
	
	private int stopCriteria;
	private int generationsWithoutFitnessIncrease;
	
	protected int eliteSize;
	protected int populationSize;
	protected int currentGeneration;
	protected int fitnessEvaluationAmount;
	
	protected float mutationProbability;
	protected float crossOverProbability;
	
	protected Individual population[];
	protected Individual matingPool[];
	
	protected static Comparator<Individual> individualComparator;

    static {
    	
    	individualComparator = new Comparator<Individual>() {
        
    	@Override
        public int compare(Individual i1, Individual i2) {
        	
    	        if (i1.getFitness() > i2.getFitness()) 
    	        {
    	        	return 1;
    	        }
    	        else if (i1.getFitness() < i2.getFitness())
    	        {
    	        	return -1;
    	        }
    	        	
    	        return 0;
    		}
    	};
    };
    
	public GeneticAlgorithm(int populationSize, float mutationProbability, float crossOverProbability, int eliteSize, int stopCriteria) {
		
		population = new Individual[populationSize];
		matingPool = new Individual[populationSize];
		
		this.populationSize = populationSize;
		this.mutationProbability = mutationProbability;
		this.crossOverProbability = crossOverProbability;
		this.eliteSize = eliteSize;
		this.stopCriteria = stopCriteria;
	}
    
	protected Individual []Tournament(Individual population[], int tournamentSize)
	{
		// A tournament crossover
		Random generator = new Random();
		Individual selectedIndividuals[] = new Individual[population.length];
		
		for(int i = 0; i < population.length; i++)
		{
			Individual tournamentPopulation[] = new Individual[tournamentSize];
			
			for(int j = 0; j < tournamentSize; j++)
			{
				int randomGen = generator.nextInt(population.length);
				tournamentPopulation[j] = population[randomGen];
			}
				
			Arrays.sort(tournamentPopulation, individualComparator);
			selectedIndividuals[i] = tournamentPopulation[0];
		}
		
		return selectedIndividuals;
	}
	
	protected void onePointCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
		
		Random generator = new Random();
		
		//A 1-point crossover
		int groundChromossomeSize = parent1.getChromossome().length;
		int crossPoint = generator.nextInt(groundChromossomeSize - 1);
		
		System.arraycopy(parent1.getChromossome(), 0, son1.getChromossome(), 0, crossPoint);
		System.arraycopy(parent2.getChromossome(), crossPoint + 1, son1.getChromossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
	
		System.arraycopy(parent2.getChromossome(), 0, son2.getChromossome(), 0, crossPoint);
		System.arraycopy(parent1.getChromossome(), crossPoint + 1, son2.getChromossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
	}
	
	protected void uniformCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
		
		Random rand = new Random(); 
		
		for(int i = 0; i < son1.getChromossome().length; i++)
		{
			if(rand.nextFloat() < 0.5f)
			{
				son1.getChromossome()[i] = parent1.getChromossome()[i];
			}
			else
			{
				son1.getChromossome()[i] = parent2.getChromossome()[i];
			}  
		}
		
		for(int i = 0; i < son2.getChromossome().length; i++)
		{
			if(rand.nextFloat() < 0.5f)
			{
				son2.getChromossome()[i] = parent1.getChromossome()[i];
			}
			else
			{
				son2.getChromossome()[i] = parent2.getChromossome()[i];
			}  
		}
	}
		
	protected void averageCrossover(Individual parent1, Individual parent2, Individual son1)
	{
		for(int i = 0; i < son1.getChromossome().length; i++)
		{
			son1.getChromossome()[i] = (parent1.getChromossome()[i] + parent2.getChromossome()[i])/2;
		}
	}
	
	protected boolean stopCriteria()
	{
		if(stopCriteria > 0)
		{
			if(getBestIndividual().getFitness() <= lastBestFitness)
			{
				generationsWithoutFitnessIncrease++;
			}
			else
			{
				lastBestFitness = getBestIndividual().getFitness();
				generationsWithoutFitnessIncrease = 0;
			}
		
			if(generationsWithoutFitnessIncrease >= stopCriteria)
			{
				System.out.println("generations = " + generationsWithoutFitnessIncrease);
				return true;
			}
		}
		
		return false;
	}
			
	private void evaluatePopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i].calculateFitness();
			fitnessEvaluationAmount++;
		}
	}
	
	public void startEvolution(int maxGeneration) {
	
		initPopulation();
		evaluatePopulation();
		
		lastBestFitness = getBestIndividual().getFitness();
		
		while(currentGeneration < maxGeneration)
		{
			selectPopulation();
			crossOver();
			mutation();
			
			if(eliteSize > 0) {
				
				elitism();
			}
			
			updatePopulation();
			evaluatePopulation();
			
			currentGeneration++;
		} 
	}
	
	protected void updatePopulation()
	{
		groupPopulation();
		
		population = matingPool.clone();
	}
	
	protected abstract void initPopulation();
	protected abstract void selectPopulation();
	
	protected abstract void groupPopulation();
	protected abstract void crossOver();
	protected abstract void mutation();
	
	protected void elitism() 
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
