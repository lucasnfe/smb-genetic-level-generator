package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class EnemiesIndividual extends Individual {
	
	public static final int ENEMIES_TYPES = 3;
    public static final int ENEMIES_BLOCKS_SIZE = 6;
	
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
        float localSparseness = 0.0f;
        
        int groundChromossomeSize = chromossomeSize/ENEMIES_BLOCKS_SIZE;
        for(int i = 0; i < groundChromossomeSize; i++)
        {
			int tempArray[] = new int[ENEMIES_BLOCKS_SIZE];
			System.arraycopy(chromossome, i * ENEMIES_BLOCKS_SIZE, tempArray, 0, ENEMIES_BLOCKS_SIZE);
            
            localSparseness += Sparseness(tempArray);
        }
        
        float normalDist = (localSparseness/maxFitness) - desiredSparseness;
        fitness = (float) Math.sqrt(normalDist * normalDist);
	}
        
    public float Sparseness(int tempArray[])
    {
        int nEnemies = countEnemies(tempArray);

        float sparseness = 0.0f;
        
        if(nEnemies > 1)
        {
	        for(int i = 0; i < tempArray.length; i++)
	        {
	            for(int j = 0; j < tempArray.length; j++)
	            {
	                if(tempArray[i] != 0 && tempArray[j] != 0)
	                {   
	                    int xDist = (i - j) * (i - j);
	                    sparseness += Math.sqrt(xDist);
	                }
	            }
	        }
	        
	        sparseness = (2 * sparseness)/(nEnemies * nEnemies - 1);
        }

        
        return sparseness;
    }
}
