package sequencer;

public class ChainKeyboard extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = true, supportOut = false;
	private ChainDevice outDevice;
	private Sequencer global_seq; 
	private Sequencer seq;
	
	
	public ChainKeyboard (Sequencer global_seq)
	{
		this.global_seq = global_seq;
	}
	
	public String toString ()
	{
		return "ChainKeyboard Device";
	}
	
	
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n)
	{
		//System.out.println ("handleSequencerBroadcastNote: " + n);
		
		if (this.outDevice != null)
			this.outDevice.receiveNote(n);
	}
	
	public void playNote(double pitch)
	{
		SequencerNote n = new SequencerNote(pitch, .5, global_seq.getScaleRef());
		if (this.outDevice != null)
			this.outDevice.receiveNote(n);
	}
	
	
	public void die()
	{
		clearOutChain();
		clearInChain();
	}
	
	
	public void receiveNote(SequencerNote n) {}
	
	
	
	//////////////////////////////
	// Chain Device methods
	
	// In Methods (not used)
	public void setInChain(ChainDevice cd) {}
	public ChainDevice getInChain() { return null; }
	public void connectIn(ChainDevice cd) {}
	public ChainDevice clearInChain() {return null;}
	
	
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
