package dk.itu.mario.geneticAlgorithm;

public class UniformGASuperMario extends GeneticAlgorithm {

	private static final int SUB_POPULATION_AMOUNT = 4;
		
	GroundGeneticAlgorithm groundGA;
	BlocksGeneticAlgorithm blocksGA;
	GroundGeneticAlgorithm enemiesGA;
	GroundGeneticAlgorithm coinsGA;
				
	public UniformGASuperMario(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		
		groundGA  = new GroundGeneticAlgorithm(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize);
		blocksGA  = new BlocksGeneticAlgorithm(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize);
		enemiesGA = new GroundGeneticAlgorithm(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize);
		coinsGA   = new GroundGeneticAlgorithm(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize);
	}

	@Override
	protected void initPopulation() {
		
		groundGA.initPopulation();
		blocksGA.initPopulation();
		enemiesGA.initPopulation();
		coinsGA.initPopulation();
		
		groupPopulation();
	}
	
	@Override
	protected void selectPopulation() {
		
		groundGA.selectPopulation();
		blocksGA.selectPopulation();
		enemiesGA.selectPopulation();
		coinsGA.selectPopulation();
		
		System.out.println("Best ground fitness = " + groundGA.getBestIndividual().fitness);
	}

	@Override
	protected void crossOver() {
	
		groundGA.crossOver();
		blocksGA.crossOver();
		enemiesGA.crossOver();
		coinsGA.crossOver();
	}
		
	@Override
	protected void mutation() {
		
	}
	
	@Override
	protected void groupPopulation()
	{
		groundGA.updatePopulation();
		blocksGA.updatePopulation();
		enemiesGA.updatePopulation();
		coinsGA.updatePopulation();
		
		int subPopulationIndex = 0;
		
		for(int i = 0 * population.length/SUB_POPULATION_AMOUNT; i < 1 * matingPool.length/SUB_POPULATION_AMOUNT; i++)
		{
			population[i] = groundGA.population[subPopulationIndex];
			matingPool[i] = groundGA.matingPool[subPopulationIndex];
			
			subPopulationIndex++;
		}
		
		subPopulationIndex = 0;
		
		for(int i = 1 * population.length/SUB_POPULATION_AMOUNT; i < 2 * matingPool.length/SUB_POPULATION_AMOUNT; i++)
		{
			population[i] = blocksGA.population[subPopulationIndex];
			matingPool[i] = blocksGA.matingPool[subPopulationIndex];
			
			subPopulationIndex++;
		}
		
		subPopulationIndex = 0;
		
		for(int i = 2 * population.length/SUB_POPULATION_AMOUNT; i < 3 * matingPool.length/SUB_POPULATION_AMOUNT; i++)
		{
			population[i] = enemiesGA.population[subPopulationIndex];
			matingPool[i] = enemiesGA.matingPool[subPopulationIndex];
			
			subPopulationIndex++;
		}
		
		subPopulationIndex = 0;
		
		for(int i = 3 * population.length/SUB_POPULATION_AMOUNT; i < 4 * matingPool.length/SUB_POPULATION_AMOUNT; i++)
		{
			population[i] = coinsGA.population[subPopulationIndex];
			matingPool[i] = coinsGA.matingPool[subPopulationIndex];
			
			subPopulationIndex++;
		}
	}
	
	protected void elitism()
	{
		groundGA.elitism();
		blocksGA.elitism();
		enemiesGA.elitism();
		coinsGA.elitism();		
	}
	
	public Individual GetBestGround()
	{
		return groundGA.getBestIndividual();
	}
	
	public Individual GetBestBlocks()
	{
		return blocksGA.getBestIndividual();
	}
}

