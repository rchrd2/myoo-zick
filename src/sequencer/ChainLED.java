package sequencer;
import pitaru.sonia_v2_9.*;
import sequencer.*;

public class ChainLED extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = false, supportOut = false;
	private Sequencer global_seq; 
	
	
	public boolean metStatus;
	
	public ChainLED (Sequencer global_seq)
	{
		this.global_seq = global_seq;
		System.out.println("global_seq: "+global_seq);
		global_seq.addListener(this);
	}
	
	
	public String toString ()
	{
		return "ChainLED Device";
	}
	
	
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n)
	{
		//System.out.println ("handleSequencerBroadcastNote: " + n);
		
		metStatus = !metStatus;
	}
	
	
	public boolean getMetStatus()
	{
		if (global_seq.isPlaying())
		{
			return metStatus;
		}
		else 
		{
			return false;
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
	public ChainDevice clearOutChain() { return null;}
	
	public ChainDevice getOutChain() { return null;	}
	
	public void setOutChain(ChainDevice cd) {  };
	
	// Access methods
	public boolean supportIn() { return supportIn; }
	public boolean supportOut() { return supportOut; }

}
