package sequencer;
import sequencer.*;


public class ChainPulse extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = true, supportOut = false;
	private ChainDevice outDevice;
	private Sequencer global_seq; 
	private Sequencer seq;
	
	public boolean noteFlag = false;
	
	public ChainPulse (Sequencer global_seq)
	{
		this.global_seq = global_seq;
		this.seq = new Sequencer(1, global_seq);
		this.seq.modifyNote(0, .5, false);
		this.seq.addListener(this);
	}
	
	public ChainPulse ()
	{
		
	}
	

	
	public String toString ()
	{
		return "ChainPulse Device";
	}
	
	
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n)
	{
		//System.out.println ("handleSequencerBroadcastNote: " + n);
		this.noteFlag = !this.noteFlag;

		if (this.outDevice != null)
		{
			this.outDevice.receiveNote(n);
			
			
			
		}
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
