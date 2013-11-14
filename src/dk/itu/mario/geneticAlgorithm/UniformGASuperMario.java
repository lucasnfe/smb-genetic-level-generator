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
	
		Random generator = new Random();
		
		int crossPoint = generator.nextInt(chromossomeSize - 1);
		 
		Individual son1 = new Individual(chromossomeSize);
		Individual son2 = new Individual(chromossomeSize);
		
		//A 1-point crossover
		for(int i = 0; i < matingPool.length/2; i += 2)
		{
			if(generator.nextDouble() < crossOverProbability)
			{
				System.arraycopy(matingPool[i].getGround(), 0, son1.getGround(), 0, crossPoint);
				System.arraycopy(matingPool[i + 1].getGround(), crossPoint + 1, son1.getGround(), crossPoint + 1, chromossomeSize - crossPoint - 1);
			
				System.arraycopy(matingPool[i + 1].getGround(), 0, son2.getGround(), 0, crossPoint);
				System.arraycopy(matingPool[i].getGround(), crossPoint + 1, son2.getGround(), crossPoint + 1, chromossomeSize - crossPoint - 1);
			
				matingPool[i] = son1;
				matingPool[i + 1] = son2;
			}
		}
	}

	@Override
	public void Mutation() {
		
		Random generator = new Random();
		
		for(int i = 0; i < chromossomeSize/Individual.MIN_GROUND_SEQUENCE; i++)
		{
			if(generator.nextDouble() < mutationProbability)
			{
				for(int j = i; j < i + Individual.MIN_GROUND_SEQUENCE; j++)
				{
					int randomBlockInSegment = generator.nextInt(Individual.MIN_GROUND_SEQUENCE);
					matingPool[i].setGroundBlock(j, matingPool[i].getGround()[i + randomBlockInSegment]);
				}
			}
		}
	}

}
