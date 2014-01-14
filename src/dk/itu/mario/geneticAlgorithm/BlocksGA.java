package dk.itu.mario.geneticAlgorithm;

public class BlocksGA extends GeneticAlgorithm {
	
	private int tournamentSize;
	private int chromossomeSize;

	public BlocksGA(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
	}

	@Override
	protected void groupPopulation() {

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
	protected void initPopulation() 
	{
		for(int i = 0; i < population.length; i++)
		{
			population[i] = new BlocksIndividual(chromossomeSize);
		}
		
		for(int i = 0; i < matingPool.length; i++)
		{
			matingPool[i] = new BlocksIndividual(chromossomeSize);
		}
	}

	@Override
	protected void crossOver() 
	{
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			BlocksIndividual son1 = new BlocksIndividual(chromossomeSize);
			BlocksIndividual son2 = new BlocksIndividual(chromossomeSize);
		
			onePointCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			
			matingPool[i] = son1;
			matingPool[i + 1] = son2;
		}

	}

	@Override
	protected void mutation() 
	{
		
	}
}
