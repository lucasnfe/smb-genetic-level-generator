
package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

public class CoinsGA extends GeneticAlgorithm {
    
	private int tournamentSize;
	private int chromossomeSize;
    private float desiredSparseness;
        
    private float maxFitness;
        
	public CoinsGA(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize, float desiredSparseness, int stopCriteria) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize, stopCriteria);
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
		this.desiredSparseness = desiredSparseness;
                
		calcMaxFitness();
	}

	protected void calcMaxFitness()
    {
        int[] maxSparsenessChromossome = new int[this.chromossomeSize/CoinsIndividual.COINS_AREA];
        
    	maxSparsenessChromossome[0] = 1;
    	maxSparsenessChromossome[this.chromossomeSize/CoinsIndividual.COINS_AREA - 1] = 1;    
        
    	CoinsIndividual ind = new CoinsIndividual(chromossomeSize/CoinsIndividual.COINS_AREA, maxSparsenessChromossome);
        maxFitness = ind.Sparseness(maxSparsenessChromossome) * maxSparsenessChromossome.length;
                     
        System.out.println("maxFitness: " + maxFitness);
    }

	@Override
	protected void selectPopulation() {
		
		Individual selectedPopulation[] = Tournament(population, tournamentSize);
		
		for(int i = 0; i < selectedPopulation.length; i++)
		{
			matingPool[i] =  selectedPopulation[i];
		}
	}

	@Override
	protected void initPopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i] = new CoinsIndividual(chromossomeSize, desiredSparseness, maxFitness);
		}
		
		for(int i = 0; i < matingPool.length; i++) {
			
			matingPool[i] = new CoinsIndividual(chromossomeSize, desiredSparseness, maxFitness);
		}
		
	}

	@Override
	protected void groupPopulation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void crossOver() {
		
		Random rand = new Random();
		
		//A 1-point crossover
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			if(rand.nextFloat() < crossOverProbability)
			{
				CoinsIndividual son1 = new CoinsIndividual(chromossomeSize, desiredSparseness, maxFitness);
				CoinsIndividual son2 = new CoinsIndividual(chromossomeSize, desiredSparseness, maxFitness);
                        
				onePointCrossover(matingPool[i], matingPool[i + 1], son1, son2);
				
				matingPool[i] = son1;
				matingPool[i + 1] = son2;
			}
		}
	}

	@Override
	protected void mutation() {
		
		Random rand = new Random(); 
		
		for(int i = 0; i < population.length; i++) {
			
			if(rand.nextFloat() < mutationProbability)
			{
				population[i].getChromossome()[rand.nextInt() % chromossomeSize] = rand.nextInt() % CoinsIndividual.MAX_COINS_HEIGHT ;
			}
		}
	}
    
}
