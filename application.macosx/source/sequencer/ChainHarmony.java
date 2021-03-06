package sequencer;
import pitaru.sonia_v2_9.*;
import sequencer.*;

public class ChainHarmony extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = true, supportOut = true;
	private ChainDevice inDevice, outDevice;
	
	public float lastRandomOffset = 0;

	public ChainHarmony ()
	{

	}

	
	public String toString ()
	{
		return "Chain Harmony Note Device";
	}

	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n){	}
	
	
	
	public void receiveNote(SequencerNote n) {
		
		double pitch;
		if (n.getPitch() > .5)
		{
			pitch = (double)(n.getPitch() - .2);
		}
		else
		{
			pitch = (double)(n.getPitch() + .2);
		}
		if (outDevice != null)
		{
			SequencerNote newnote = new SequencerNote(pitch, n.getDuration(), n.getScale());
			outDevice.receiveNote(n);
			outDevice.receiveNote(newnote);
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
		System.out.println ("Chain Harmony setInChain: "+cd);
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
