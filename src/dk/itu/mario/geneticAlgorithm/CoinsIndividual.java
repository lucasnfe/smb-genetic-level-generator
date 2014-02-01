package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class CoinsIndividual extends Individual {

	public static final int MAX_COINS_HEIGHT = 4;
    public static final int COINS_AREA = 10;
    
    private float desiredSparseness;
    private float maxFitness;
        
	public CoinsIndividual(int chromossomeSize, float desiredSparseness, float maxFitness)
	{
        super(chromossomeSize);

        this.maxFitness = maxFitness;
        this.desiredSparseness = desiredSparseness;
		
		Random gerador = new Random();
		
		chromossome = new int[chromossomeSize];
		
		for(int i = 0; i < chromossomeSize; i++)
		{
			chromossome[i] = gerador.nextInt(MAX_COINS_HEIGHT);
		}
	}
        
    public CoinsIndividual(int chromossomeSize, int[] chromossome)
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
        
        int groundChromossomeSize = chromossomeSize/COINS_AREA;
        for(int i = 0; i < groundChromossomeSize; i++)
        {
			int tempArray[] = new int[COINS_AREA];
			System.arraycopy(chromossome, i * COINS_AREA, tempArray, 0, COINS_AREA);
            
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
