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
			
			population[i] = new Individual(chromossomeSize);
		}
		
		for(int i = 0; i < matingPool.length; i++) {
			
			matingPool[i] = new Individual(chromossomeSize);
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
			Individual son1 = new Individual(chromossomeSize);
			Individual son2 = new Individual(chromossomeSize);
		
			GroundCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			BlockCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			EnemiesCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			CoinsCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			
			matingPool[i] = son1;
			matingPool[i + 1] = son2;
		}
	}
	
	private void GroundCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
		
		Random generator = new Random();
		
		int groundChromossomeSize = chromossomeSize/Individual.MIN_GROUND;
		int crossPoint = generator.nextInt(groundChromossomeSize - 1);
		
		if(generator.nextDouble() < crossOverProbability)
		{
			System.arraycopy(parent1.getGround(), 0, son1.getGround(), 0, crossPoint);
			System.arraycopy(parent2.getGround(), crossPoint + 1, son1.getGround(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
		
			System.arraycopy(parent2.getGround(), 0, son2.getGround(), 0, crossPoint);
			System.arraycopy(parent1.getGround(), crossPoint + 1, son2.getGround(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
		}
	}
	
	private void BlockCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
	
		
	}
	
	private void EnemiesCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
	
		
	}
	
	private void CoinsCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
	
	}
	
	@Override
	public void Mutation() {
		
	}

}
