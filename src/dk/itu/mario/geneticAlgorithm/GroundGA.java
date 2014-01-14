package dk.itu.mario.geneticAlgorithm;

public class GroundGA extends GeneticAlgorithm {
			
	private int tournamentSize;
	private int chromossomeSize;
	
	public GroundGA(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		// TODO Auto-generated constructor stub
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
	}
	
	@Override
	protected void initPopulation() {
		
		for(int i = 0; i < population.length; i++)
		{
			population[i] = new GroundIndividual(chromossomeSize);
		}
		
		for(int i = 0; i < matingPool.length; i++)
		{
			matingPool[i] = new GroundIndividual(chromossomeSize);
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
		
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			GroundIndividual son1 = new GroundIndividual(chromossomeSize);
			GroundIndividual son2 = new GroundIndividual(chromossomeSize);
		
			onePointCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			
			matingPool[i] = son1;
			matingPool[i + 1] = son2;
		}

	}
		
	@Override
	protected void mutation() {

	}
	
	@Override
	protected void groupPopulation()
	{
		
	}
}
