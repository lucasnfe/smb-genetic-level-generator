package dk.itu.mario.geneticAlgorithm;

public abstract class GeneticAlgorithm {

	protected int eliteSize;
	protected int populationSize;
	protected int currentGeneration;
	protected int fitnessEvaluationAmount;
	
	protected float mutationProbability;
	protected float crossOverProbability;
	
	protected Individual population[];
	protected Individual matingPool[];
		
	public GeneticAlgorithm(int populationSize, float mutationProbability, float crossOverProbability, int eliteSize) {
		
		population = new Individual[populationSize];
		matingPool = new Individual[populationSize];
		
		this.populationSize = populationSize;
		this.mutationProbability = mutationProbability;
		this.crossOverProbability = crossOverProbability;
		this.eliteSize = eliteSize;
	}
	
	private void evaluatePopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i].calculateFitness();
			fitnessEvaluationAmount++;
		}
	}
	
	protected abstract void groupPopulation();

	protected abstract void selectPopulation();
	
	public void startEvolution(int maxGeneration) {
	
		initPopulation();
		evaluatePopulation();
		
		while(currentGeneration < maxGeneration)
		{
			selectPopulation();
			crossOver();
			mutation();
			
			if(eliteSize > 0) {
				
				elitism();
			}
			
			groupPopulation();
			updatePopulation();
			evaluatePopulation();
			
			currentGeneration++;
		} 
	}
	
	protected void updatePopulation()
	{
		population = matingPool.clone();
	}
	
	protected abstract void initPopulation();
	protected abstract void crossOver();
	protected abstract void mutation();
	protected abstract void elitism();

}
