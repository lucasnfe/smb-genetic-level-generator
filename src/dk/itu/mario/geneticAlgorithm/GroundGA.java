package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class GroundGA extends GeneticAlgorithm {
			
	private int tournamentSize;
	private int chromossomeSize;
	
	private float entropy;
	
	public GroundGA(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize, float entropy, int stopCriteria) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize, stopCriteria);
		// TODO Auto-generated constructor stub
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
		this.entropy = entropy;
	}
	
	@Override
	protected void initPopulation() {
		
		for(int i = 0; i < population.length; i++)
		{
			population[i] = new GroundIndividual(chromossomeSize, entropy);
		}
		
		for(int i = 0; i < matingPool.length; i++)
		{
			matingPool[i] = new GroundIndividual(chromossomeSize, entropy);
		}
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
	protected void crossOver() {
		
		Random rand = new Random();
		
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			if(rand.nextFloat() < crossOverProbability)
			{
				GroundIndividual son1 = new GroundIndividual(chromossomeSize, entropy);
				GroundIndividual son2 = new GroundIndividual(chromossomeSize, entropy);
		
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
				population[i].getChromossome()[rand.nextInt() % chromossomeSize] = rand.nextInt() % GroundIndividual.MAX_GROUND_HEIGHT ;
			}
		}
	}
	
	@Override
	protected void groupPopulation()
	{
		
	}
}
