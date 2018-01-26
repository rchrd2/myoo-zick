package sequencer;
import pitaru.sonia_v2_9.*;
import sequencer.*;

public class ChainVolume extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = true, supportOut = true;
	private ChainDevice inDevice, outDevice;
	
	public float lastRandomOffset = 0;
	
	public double velocityValue = 0.5;

	public ChainVolume ()
	{

	}

	
	public String toString ()
	{
		return "Chain Randomize Note Device";
	}
	
	public double getVelocity()
	{
		return velocityValue;
	}
	
	public void setVelocity(double vel)
	{
		System.out.println("setVelocity: "+vel);
		this.velocityValue = vel;
	}
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n){	}
	
	
	
	public void receiveNote(SequencerNote n) {

		
		if (outDevice != null)
		{
			SequencerNote newNote = new SequencerNote(n.getPitch(), n.getDuration(), n.getScale());
			newNote.setVelocity(velocityValue);

			outDevice.receiveNote(newNote);
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
