package sequencer;
import pitaru.sonia_v2_9.*;
import sequencer.*;

public class ChainRandomizeNote extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = true, supportOut = true;
	private ChainDevice inDevice, outDevice;
	
	public float lastRandomOffset = 0;

	public ChainRandomizeNote ()
	{

	}

	
	public String toString ()
	{
		return "Chain Randomize Note Device";
	}
	
	public double getLastRandomOffset()
	{
		return lastRandomOffset;
	}
	
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n){	}
	
	
	
	public void receiveNote(SequencerNote n) {
		double pitch = (double)(n.getPitch() + -.5 + Math.random());
		if (pitch > 1) pitch = 1;
		else if (pitch < 0) pitch = 0;
		
		this.lastRandomOffset = (float)pitch;	

		
		if (outDevice != null)
		{
			SequencerNote randomNote = new SequencerNote(pitch, n.getDuration(), n.getScale());
			
			if (Math.random() > .1)
				outDevice.receiveNote(randomNote);
		}

	}
	
	
	public void die()
	{
		clearOutChain();
		clearInChain();
	}
	
	
	//////////////////////////////
	// Chain Device methods
	
	// In Methods (not used)
	public void setInChain(ChainDevice cd) {
		System.out.println ("Randomize setInChain: "+cd);
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
	
	// Out Methods
	public ChainDevice clearOutChain() { 
		System.out.println ("Chain Draw Melody clear out chain");
		ChainDevice cd = this.outDevice;
		this.outDevice = null;
		return cd;
	}
	
	public ChainDevice getOutChain() { 
		return this.outDevice;	
	}
	
	public void setOutChain(ChainDevice cd) { 
		System.out.println ("setOutChain: "+cd);
		this.outDevice = cd;
	}
	
	// Access methods
	public boolean supportIn() { return supportIn; }
	public boolean supportOut() { return supportOut; }

}
