package sequencer;
import pitaru.sonia_v2_9.*;
import processing.core.*;


public class ChainSonia extends ChainDevice {

	private static boolean sonia_is_started = false;
	
	String sampleString = "md_2_003_oct_3.wav";
	public int polyphony = 8;
	Sample[] sampleInstances;
	private int polyphonyIndex = 0;
	private PApplet p;
	
	// Chain Device variables
	boolean supportIn = true, supportOut = false;
	ChainDevice inDevice;
	
	public ChainSonia (String sampleString, PApplet p)
	{
		this.p = p;
		this.sampleString = sampleString;
		initSynth();
	}
	
	public String toString ()
	{
		return "ChainSonia Device";
	}
	
	public void receiveNote(SequencerNote n) {
		//System.out.println ("Chain Sonia Recieve Note!!: " + n);
		float f = (float) n.getFrequency();
		playSampleFreq (f, n.getVelocity());

	}

	public void playSampleFreq (float frequency, double velocity)
	{
	  // loop the sample
	  //System.out.println ("playSampleFreq: "+ frequency);
	  //float rate = (frequency / 700) * 88200 ;
	  float rate = (frequency / 300) * 88200 ;

	  
	  Sample mySample = sampleInstances[polyphonyIndex];
	  
	  mySample.stop();
	  
	  mySample.setRate(rate);
	  mySample.setVolume((float) velocity);
	  mySample.play();
	  //mySample.stop(100);

	  polyphonyIndex = (polyphonyIndex + 1) % polyphony;
	}
	
	public void initSynth()
	{
		
	  if (sonia_is_started == false)
	  {
		  Sonia.start(p); // Start Sonia engine.
		  sonia_is_started = true;
	  }

	  sampleInstances = new Sample[polyphony];
	  for (int i = 0; i < polyphony; i++)
	  {
		  sampleInstances[i] = new Sample (sampleString);
	  }
	}
	
	// Safely close the sound engine upon Browser shutdown.
	public void stopSynth()
	{
	  Sonia.stop();
	}
	
	public void die()
	{
		//this.outDevice.clearInChain();
		clearOutChain();
		clearInChain();
	}
	
	
	//////////////////////////////
	// Chain Device methods
	
	public void setInChain(ChainDevice cd) {
		System.out.println ("Sonia setInChain: "+cd);
		this.inDevice = cd;
	}
	
	public ChainDevice getInChain() {
		return this.inDevice;
	}
	
	public ChainDevice clearInChain() {
		ChainDevice old = this.inDevice;
		this.inDevice = null;	
		return old;
	}
	

	public ChainDevice clearOutChain() { return null; }


	
	public ChainDevice getOutChain() { return null;	}
	
	

	
	public void setOutChain(ChainDevice cd) { }
	
	public boolean supportIn() { return supportIn; }
	public boolean supportOut() { return supportOut; }
	
	public void handleSequencerBroadcastNote(SequencerNote n){}

}
