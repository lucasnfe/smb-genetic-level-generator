package dk.itu.mario.geneticAlgorithm;

public class UniformGASuperMario extends GeneticAlgorithm {

	private int chromossomeSize;
	
	public UniformGASuperMario(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		
		this.chromossomeSize = chromossomeSize;
	}

	@Override
	public void InitPopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i] = new Individual(chromossomeSize);
		}
	}

	@Override
	public void SelectPopulation() {
		
	}

	@Override
	public void CrossOver() {
		
	}

	@Override
	public void Mutation() {
		
	}

}
