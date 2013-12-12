/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.mario.geneticAlgorithm;

import static dk.itu.mario.geneticAlgorithm.GroundIndividual.ENEMIES_TYPES;
import static dk.itu.mario.geneticAlgorithm.GroundIndividual.MIN_GROUND;
import static dk.itu.mario.geneticAlgorithm.GroundIndividual.MIN_GROUND_SEQUENCE;

import java.util.Random;

public class EnemiesIndividual extends Individual{
	public static final int ENEMIES_TYPES = 3;
        
        public static final int MIN_GROUND = 3;
        public static final int MIN_GROUND_SEQUENCE = 3;
        
        private Individual bestGroundIndividual;
        
        private float desiredSparseness;
        private float maxFitness;
        
	public EnemiesIndividual(int chromossomeSize, Individual bestGroundIndividual, float desiredSparseness, float maxFitness)
	{
                super(chromossomeSize);
		
                this.maxFitness = maxFitness;
                this.desiredSparseness = desiredSparseness;
                this.bestGroundIndividual = bestGroundIndividual;
		
		Random gerador = new Random();
		
                chromossome = new int[chromossomeSize];
		
		for(int i = 0; i < chromossomeSize; i++)
		{
                    chromossome[i] = gerador.nextInt(ENEMIES_TYPES);
		}

	}
        
        public EnemiesIndividual(int chromossomeSize, Individual bestGroundIndividual, int[] chromossome)
	{
                super(chromossomeSize);
		
                this.bestGroundIndividual = bestGroundIndividual;
                this.chromossome = chromossome;
	}
        
        private int countEnemies(int tempArray[])
        {
            //Contar numero inimigos
            int nEnemies = 0;
                
            for(int i = 0; i < tempArray.length; i++ )
            {
                if( tempArray[i] != 0 )
                {
                    nEnemies++;
                }
            }
            
            return nEnemies;
        }
	
	public void calcFitness()
	{
            //int enemyChromossomeSize = chromossomeSize/MIN_GROUND;
            float localSparseness;
            float normalDist;
            
            fitness = 0;
            
            for(int i = 0; i < chromossomeSize/(MIN_GROUND_SEQUENCE*MIN_GROUND); i++)
            {
                int tempArray[] = new int[MIN_GROUND_SEQUENCE*MIN_GROUND];	
                System.arraycopy(chromossome, i * MIN_GROUND_SEQUENCE*MIN_GROUND, tempArray, 0, MIN_GROUND_SEQUENCE*MIN_GROUND);
                localSparseness = Sparseness(tempArray);
                normalDist = (localSparseness/maxFitness) - desiredSparseness;
                fitness += (float) Math.sqrt(normalDist * normalDist);
            }
	}
        
        public float Sparseness(int tempArray[])
        {
            int nEnemies = countEnemies(tempArray);
            
            //System.out.println("nememies "+nEnemies);
            
            float sparseness = 0.0f;
            for( int i = 0; i < tempArray.length; i++ )
            {
                for( int j = 0; j < tempArray.length; j++ )
                {
                    if( tempArray[i] != 0 && tempArray[j] != 0 )
                    {   
                        int xDist = (i - j) * (i - j);
                        
                        int posYFirstEnemy = bestGroundIndividual.getChrmossome()[i/MIN_GROUND];
                        int posYSecondEnemy = bestGroundIndividual.getChrmossome()[j/MIN_GROUND];
                        
                        int yDist = (posYFirstEnemy - posYSecondEnemy) * (posYFirstEnemy - posYSecondEnemy);
                        
                        sparseness += Math.sqrt(xDist + yDist);
                    }
                }
            }
            if(nEnemies != 1)
            {
                sparseness = (2 * sparseness)/(nEnemies * nEnemies - 1);
            }
            else
            {
                sparseness = 2*sparseness;
            }
            
            return sparseness;
        }
}
