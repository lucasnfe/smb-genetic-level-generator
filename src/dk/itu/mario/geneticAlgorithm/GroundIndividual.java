package dk.itu.mario.geneticAlgorithm;

import java.util.Random;

public class GroundIndividual extends Individual {

	public static final int MIN_GROUND = 3;
	public static final int JUMP_SIZE = 4;
	public static final int MIN_GROUND_SEQUENCE = 3;
	public static final int MAX_GROUND_HEIGHT = 10;
	public static final int MAX_BLOCK_HEIGHT = 6;
	
	public GroundIndividual(int chromossomeSize)
	{
		super(chromossomeSize);
		
		chromossome = new int[chromossomeSize/MIN_GROUND];
		
		Random randomGenerator = new Random();
		for(int i = 0; i < chromossomeSize/MIN_GROUND; i++)
		{
			chromossome[i] = randomGenerator.nextInt(MAX_GROUND_HEIGHT);
		}
	}
	
	@Override
	public void calculateFitness() {
		
		// Ground fitness is based on the entropy of the terrain's sequences
		// of size MIN_GROUND_SEQUENCE
		fitness = 0.0f;
		
		int groundChromossomeSize = chromossomeSize/MIN_GROUND;
		for(int i = 0; i < groundChromossomeSize/MIN_GROUND_SEQUENCE; i++)
		{
			int tempArray[] = new int[MIN_GROUND_SEQUENCE];
			
			System.arraycopy(chromossome, i * MIN_GROUND_SEQUENCE, tempArray, 0, MIN_GROUND_SEQUENCE);
			fitness += calculateEntropy(tempArray);
		}
	}
	
	private float calculateEntropy(int chromossomeSegment[])
	{
		float entropy = 0.0f;
		
		// calculate ground fitness based on its entropy
		for(int i = 0; i < MAX_GROUND_HEIGHT; i++)
		{
			int heightAmount = 0;
			
			for(int j = 0; j < chromossomeSegment.length; j++)
			{
				if(i == chromossomeSegment[j])
				{
					heightAmount++;
				}
			}			
			
			if(heightAmount > 0)
			{
				double heightProportion = (double) heightAmount / (double)chromossomeSegment.length;
				heightProportion =  heightProportion * (Math.log(heightProportion) / Math.log(2));
						
				entropy -= heightProportion;
			}
		}
	
		return entropy;
	}
}
