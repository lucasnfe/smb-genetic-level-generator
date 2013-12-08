package dk.itu.mario.geneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GroundGeneticAlgorithm extends GeneticAlgorithm {
			
	private int tournamentSize;
	private int chromossomeSize;
	
	public GroundGeneticAlgorithm(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		// TODO Auto-generated constructor stub
		
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
	}
	
	protected static Comparator<Individual> individualComparator;

    // We initialize static variables inside a static block.
    static {
    	
    	individualComparator = new Comparator<Individual>() {
        
    	@Override
        public int compare(Individual i1, Individual i2) {
        	
    	        if (i1.getFitness() > i2.getFitness()) 
    	        {
    	        	return 1;
    	        }
    	        else if (i1.getFitness() < i2.getFitness())
    	        {
    	        	return -1;
    	        }
    	        	
    	        return 0;
    		}
    	};
    };
	
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
		
		Individual selectedPopulation[] = Tournament(population);
		
		for(int i = 0; i < selectedPopulation.length; i++)
		{
			matingPool[i] =  selectedPopulation[i];
		}
	}
	
	private Individual []Tournament(Individual population[])
	{
		// A tournament crossover
		Random generator = new Random();
		Individual selectedIndividuals[] = new Individual[population.length];
		
		for(int i = 0; i < population.length; i++)
		{
			Individual tournamentPopulation[] = new Individual[tournamentSize];
			
			for(int j = 0; j < tournamentSize; j++)
			{
				int randomGen = generator.nextInt(population.length);
				tournamentPopulation[j] = population[randomGen];
			}
				
			Arrays.sort(tournamentPopulation, individualComparator);
			selectedIndividuals[i] = tournamentPopulation[0];
		}
		
		return selectedIndividuals;
	}

	@Override
	protected void crossOver() {
		
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
		
		//A 1-point crossover
		int groundChromossomeSize = parent1.getChromossome().length;
		int crossPoint = generator.nextInt(groundChromossomeSize - 1);
		
		System.arraycopy(parent1.getChromossome(), 0, son1.getChromossome(), 0, crossPoint);
		System.arraycopy(parent2.getChromossome(), crossPoint + 1, son1.getChromossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
	
		System.arraycopy(parent2.getChromossome(), 0, son2.getChromossome(), 0, crossPoint);
		System.arraycopy(parent1.getChromossome(), crossPoint + 1, son2.getChromossome(), crossPoint + 1, groundChromossomeSize - crossPoint - 1);
	}
	
	public Individual getBestIndividual()
	{		
		Arrays.sort(population, individualComparator);
		
		return population[0];
	}

	@Override
	protected void mutation() {

	}

	@Override
	protected void elitism() {
		
		Arrays.sort(population, individualComparator);
		
		for(int i = 0; i < eliteSize; i++)
		{
			matingPool[i] = population[i];
		}

	}
	
	@Override
	protected void groupPopulation()
	{
		
	}
}
