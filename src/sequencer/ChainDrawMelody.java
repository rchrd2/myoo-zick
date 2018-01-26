package sequencer;
import pitaru.sonia_v2_9.*;
import sequencer.*;

public class ChainDrawMelody extends ChainDevice {

	
	// Chain Device variables
	private boolean supportIn = false, supportOut = true;
	private ChainDevice outDevice;
	private Sequencer global_seq; 
	private Sequencer seq;
	
	private int patternLength = 16;
	
	
	public ChainDrawMelody (Sequencer global_seq, int plength)
	{
		this.patternLength = plength;
		this.global_seq = global_seq;
		this.seq = new Sequencer(patternLength, global_seq);
		this.seq.addListener(this);
		
		initNotes();
		
	}
	
	public String toString () 
	{
		return "Chain Craw Melody Device";
	}
	
	public int getBeatNum()
	{
		return seq.getBeatNum() % patternLength;
	}
	
	public int getPatternLength()
	{
		return patternLength;
	}
	
	public double[] getNotePitchArray()
	{
		return seq.getNotePitchArray();
	}
	
	public void updateNote(int index, double pitch, boolean mute)
	{
		this.seq.modifyNote(index, pitch, mute);
	}
	
	private void initNotes()
	{
		for (int i = 0; i <  patternLength; i++)
		{
			updateNote (i, 0, true);
		}
	}
	
	// This method needs to exist for sequencer to work;
	public void handleSequencerBroadcastNote (SequencerNote n)
	{
		//System.out.println ("handleSequencerBroadcastNote: " + n);
		
		if (this.outDevice != null && n.isMuted() == false)
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
