package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class BlocksIndividual extends Individual {

	public static final int BLOCKS_TYPES = 6;
    public static final int BLOCKS_AREA = 20;
    
    private float desiredSparseness;
    private float maxFitness;
        
	public BlocksIndividual(int chromossomeSize, float desiredSparseness, float maxFitness)
	{
        super(chromossomeSize);

        this.maxFitness = maxFitness;
        this.desiredSparseness = desiredSparseness;
		
		Random gerador = new Random();
		
		chromossome = new int[chromossomeSize];
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			chromossome[i] = gerador.nextInt(BLOCKS_TYPES);
		}
	}
        
    public BlocksIndividual(int chromossomeSize, int[] chromossome)
	{
        super(chromossomeSize);

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
	
	public void calculateFitness()
	{
        float localSparseness = 0.0f;
        
        int groundChromossomeSize = chromossomeSize/BLOCKS_AREA;
        for(int i = 0; i < groundChromossomeSize; i++)
        {
			int tempArray[] = new int[BLOCKS_AREA];
			System.arraycopy(chromossome, i * BLOCKS_AREA, tempArray, 0, BLOCKS_AREA);
            
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
