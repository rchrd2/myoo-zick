package sequencer;

public class SequencerScale {
	
	public static final int[] MISC = { 20, 22, 24, 25, 27, 28, 29, 30, 32, 33, 34, 36, 38, 40, 50 };

	public static final int[] PERFECT_FOURTHS = { 40, 45, 50, 55, 60, 65, 70, 75 };
	public static final int[] MINOR_BLUES = { 40, 43, 45, 46, 47, 50, 52, 55 , 57, 58, 59, 62, 64};

	public static final int[] PENTATONIC_MAJOR = { 40, 42, 44, 47, 49, 52, 54, 56, 59, 61, 64 };

	public static final int[] C_MAJOR = { 40, 42, 44, 45, 47, 49, 51, 52, 54, 56, 57, 59, 61, 63, 64 };
	
	public static final int[] C_MINOR = { 40, 42, 43, 45, 47, 48, 50, 52, 44, 55, 57, 59, 60, 62, 64 };
	
	public static final int[] WHOLE_TONE = { 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66 };

	
	// INSEN SCALE C, C#, F, G, A#, C
	//public static final int[] INSEN = { 40, 41, 45, 47, 50, 52, 52, 53, 57, 59, 62, 64, 64, 65, 69,
	//		71, 74, 76, 76, 77, 81, 83, 86, 88 };
	public static final int[] INSEN = { 40, 41, 45, 47, 50, 52, 52, 53, 57, 59, 62, 64, 64, 65, 69 };
	
	// arabian
	
	public static final int[] EASTERN = { 40, 41, 44, 45, 47, 48, 50, 52 };
	
	public static final int[][] SCALES = { 
		PENTATONIC_MAJOR, C_MAJOR, C_MINOR, INSEN, EASTERN, PERFECT_FOURTHS, MINOR_BLUES,  WHOLE_TONE };
	public static final String[] SCALE_NAMES = { 
		"~  AIRY", "~  CLASSICAL",  "~  SAD", "~  JAPANESE", "~  EASTERN", "~  FREEDOM", "~  MINOR BLUES", "~  MYSTERY"};
	
	/////////////////////
	
	int[] degree;
	
	public String toString()
	{
		int index = this.getDegreeIndex();
		return SCALE_NAMES[index];
	}
	
	public int getDegreeIndex()
	{
		for (int i = 0; i < SCALES.length; i++)
		{
			if (SCALES[i] == degree)
			{
				return i;
			}
		}
		return 0;
	}
	
	public SequencerScale(int[] degree)
	{
		this.degree = degree;
	}
	

	public int getNoteMidi(double ratio)
	{
		int index = (int)Math.round(ratio * (degree.length - 1));
		if (index >= degree.length)
		{
			//return (int) (degree[index % degree.length] + (float)degree.length * 12 / (float)index);
			return degree[degree.length - 1];
		}
		else {
			return degree[index];
		}
			
	}
	public double getNoteFreq (double ratio)
	{
		int midiNote = getNoteMidi(ratio);
		return noteToFreq (midiNote);
	}

	////////////////////////////////////////////////////
	// Static helper methods
	
	public static double noteToFreq (int n)
	{
	  float freq = (float)(440.0 *  Math.pow (2.0,  (n-69) / 12.0 ));
	  //println ("noteToFreq: "+n +", "+freq);  
	  return freq;
	}

	public static int freqToNote (double freq)
	{
	  int n = (int)Math.floor(69 + 12* Math.log(freq/440) / Math.log(2));
	  return n;
	}

}
