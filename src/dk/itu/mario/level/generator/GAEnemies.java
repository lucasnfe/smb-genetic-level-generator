
package dk.itu.mario.level.generator;

import dk.itu.mario.geneticAlgorithm.EnemiesIndividual;
import static dk.itu.mario.geneticAlgorithm.EnemiesIndividual.MIN_GROUND_SEQUENCE;
import static dk.itu.mario.geneticAlgorithm.EnemiesIndividual.MIN_GROUND;
import dk.itu.mario.geneticAlgorithm.GeneticAlgorithm;
import dk.itu.mario.geneticAlgorithm.Individual;
import java.util.Arrays;
import java.util.Random;

public class GAEnemies extends GeneticAlgorithm{
        private int tournamentSize;
	private int chromossomeSize;
        private float desiredSparseness;
	private Individual bestGroundIndividual;
        
        private float maxFitness;
        
	public GAEnemies(int populationSize, int chromossomeSize, float mutationProbability, float crossOverProbability, int eliteSize, int tournamentSize, Individual bestGroundIndividual, float desiredSparseness) {
		
		super(populationSize, mutationProbability, crossOverProbability, eliteSize);
		
                this.bestGroundIndividual = bestGroundIndividual;
		this.tournamentSize = tournamentSize;
		this.chromossomeSize = chromossomeSize;
                this.desiredSparseness = desiredSparseness;
                
                calcMaxFitness();
             
	}

        public void calcMaxFitness()
        {
            int[] maxSparsenessChromossome = new int[this.chromossomeSize];
            
            for(int i = 0; i < chromossomeSize/(MIN_GROUND_SEQUENCE*MIN_GROUND); i++)
            {
            maxSparsenessChromossome[i*MIN_GROUND_SEQUENCE*MIN_GROUND] = 1;
            maxSparsenessChromossome[i*MIN_GROUND_SEQUENCE*MIN_GROUND+MIN_GROUND_SEQUENCE*MIN_GROUND-1] = 1;    
            }
            EnemiesIndividual ind = new EnemiesIndividual(chromossomeSize, bestGroundIndividual, maxSparsenessChromossome);
             
            maxFitness = 0;

            for(int i = 0; i < chromossomeSize/(MIN_GROUND_SEQUENCE*MIN_GROUND); i++)
            {
                int tempArray[] = new int[MIN_GROUND_SEQUENCE*MIN_GROUND];
                System.arraycopy(ind.getChrmossome(), i * MIN_GROUND_SEQUENCE*MIN_GROUND, tempArray, 0, MIN_GROUND_SEQUENCE*MIN_GROUND);
                maxFitness += ind.Sparseness(tempArray);
            }
            System.out.println("maxFitness: "+maxFitness);
        }
        
	@Override
	public void InitPopulation() {
		
		for(int i = 0; i < population.length; i++) {
			
			population[i] = new EnemiesIndividual(chromossomeSize, bestGroundIndividual, desiredSparseness, maxFitness);
		}
		
		for(int i = 0; i < matingPool.length; i++) {
			
			matingPool[i] = new EnemiesIndividual(chromossomeSize, bestGroundIndividual, desiredSparseness, maxFitness);
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
			EnemiesIndividual son1 = new EnemiesIndividual(chromossomeSize, bestGroundIndividual, desiredSparseness, maxFitness);
			EnemiesIndividual son2 = new EnemiesIndividual(chromossomeSize, bestGroundIndividual, desiredSparseness, maxFitness);
                        
			EnemiesCrossover(matingPool[i], matingPool[i + 1], son1, son2);
			
			
			matingPool[i] = son1;
			matingPool[i + 1] = son2;
		}
	}
	
	private void EnemiesCrossover(Individual parent1, Individual parent2, Individual son1, Individual son2) {
		
		Random generator = new Random();
		
		int groundChromossomeSize = chromossomeSize;
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
