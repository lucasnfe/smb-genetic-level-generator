package dk.itu.mario.geneticAlgorithm;

public class SuperMarioGA extends GeneticAlgorithm {

	private static final int SUB_POPULATION_AMOUNT = 4;
		
	GroundGA groundGA;
	BlocksGA blocksGA;
	EnemiesGA enemiesGA;
	CoinsGA coinsGA;
				
	public SuperMarioGA(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize, int stopCriteria) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize, stopCriteria);
		
		groundGA  = new GroundGA(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize, 0f, stopCriteria);
		blocksGA  = new BlocksGA(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize, 1f, stopCriteria);
        enemiesGA = new EnemiesGA(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize, 0.5f, stopCriteria);
		coinsGA   = new CoinsGA(populationSize/SUB_POPULATION_AMOUNT, chromossomeSize, mutationProbability, crossOverProbability, eliteSize, tournamentSize, 0.5f, stopCriteria);
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
		
		System.out.println(currentGeneration + " " + groundGA.getBestIndividual().fitness + " " + blocksGA.getBestIndividual().fitness + " " + enemiesGA.getBestIndividual().fitness + " " + coinsGA.getBestIndividual().fitness);
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
	
	public Individual GetBestEnemies()
	{
		return enemiesGA.getBestIndividual();
	}
	
	public Individual GetBestCoins()
	{
		return coinsGA.getBestIndividual();
	}
}

