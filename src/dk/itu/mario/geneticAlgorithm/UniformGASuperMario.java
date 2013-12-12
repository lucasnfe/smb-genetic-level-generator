package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

public class UniformGASuperMario extends GeneticAlgorithm {

	private int tournamentSize;
	private int chromossomeSize;
	
	public UniformGASuperMario(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
	}

	@Override
	public void InitPopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i] = new GroundIndividual(chromossomeSize);
		}
		
		for(int i = 0; i < matingPool.length; i++) {
			
			matingPool[i] = new GroundIndividual(chromossomeSize);
		}
	}

	@Override
	public void SelectPopulation() {
		
		// A tournament crossover
		Random generator = new Random();
		
		for(int i = 0; i < matingPool.length; i++)
		{
			Individual tournamentPopulation[] = new Individual[tournamentSize];
			
			for(int j = 0; j < tournamentSize; j++)
			{
				int randomGen = generator.nextInt(population.length);
				tournamentPopulation[j] = population[randomGen];
			}
				
			Arrays.sort(tournamentPopulation, GeneticAlgorithm.individualComparator);
			matingPool[i] = tournamentPopulation[0];
		}
	}

	@Override
	public void CrossOver() {
	
		//A 1-point crossover
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			GroundIndividual son1 = new GroundIndividual(chromossomeSize);
			GroundIndividual son2 = new GroundIndividual(chromossomeSize);
		
			GroundCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			
			matingPool[i] = son1;
			matingPool[i + 1] = son2;
		}
	}
	
	private void GroundCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
		
		Random generator = new Random();
		
		int groundChromossomeSize = chromossomeSize/GroundIndividual.MIN_GROUND;
		int crossPoint = generator.nextInt(groundChromossomeSize - 1);
		
		if(generator.nextDouble() < crossOverProbability)
		{
			System.arraycopy(parent1.getChrmossome(), 0, son1.getChrmossome(), 0, crossPoint);
			System.arraycopy(parent2.getChrmossome(), crossPoint + 1, son1.getChrmossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
		
			System.arraycopy(parent2.getChrmossome(), 0, son2.getChrmossome(), 0, crossPoint);
			System.arraycopy(parent1.getChrmossome(), crossPoint + 1, son2.getChrmossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
		}
	}
	
	@Override
	public void Mutation() {
		
	}

}
