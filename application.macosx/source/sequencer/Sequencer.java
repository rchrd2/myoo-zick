package sequencer;
/**
 * Richard Caceres
 * rcaceres [at] ucla [dot] edu
 * $Id$ 
 *
 * This class will be a general sequencer. That can queue notes. It must have 
 * a clock object that notifies when every tick.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Sequencer {
	
	// This is in clock ticks (ie 16 notes, or 1/16).
	private int phraseLength, beatNum; 
	private SequencerClock clock;
	private SequencerScale scale;
	private SequencerNoteReceiver receiver;
	
	private ArrayList<SequencerNote> notes;
	
	private ArrayList<ChainDevice> cdListeners;
	
	private ArrayList<Sequencer> seqListeners;
	
	
	/**
	 * This Constructor takes another sequencer that will drive this sequencer.
	 * @param phraseLength
	 * @param parentSeq
	 */
	
	public Sequencer (int phraseLength, Sequencer parentSeq)
	{	
		this.phraseLength = phraseLength;
		
		this.clock = parentSeq.getClockRef();		
		this.scale = parentSeq.getScaleRef();	

		parentSeq.addChildSequencer(this);		

		init();
		

	}
	
	/**
	 * This constructor will drive itself. 
	 * @param phraseLength
	 * @param c  This is a sequencer clock object.
	 * @param sl This is a sequencer scale object
	 */
	
	public Sequencer (int phraseLength, SequencerClock c, SequencerScale sl)
	{
		this.phraseLength = phraseLength;
		this.clock = c;
		this.scale = sl;
		init();
	}
	
	private void init()
	{
		clock.addListener(this);
		
		
		cdListeners = new ArrayList<ChainDevice>();
		seqListeners = new ArrayList<Sequencer>();
		

		this.notes = new ArrayList<SequencerNote>();
		for (int i = 0; i < phraseLength; i++)
		{
			SequencerNote n = new SequencerNote(.5, .25, this.scale);
			n.setMute(true);
			notes.add(i, n);
		}
		

	}
	
	public int getBeatNum()
	{
		return beatNum;
	}
	
	public double[] getNotePitchArray()
	{
		double[] pitches = new double[phraseLength];
		for (int i = 0; i < phraseLength; i++)
		{
			pitches[i] = notes.get(i).getPitch();
		}	
		return pitches;
	}
	
	public void handleClockTick(SequencerClockEvent e)
	{
		long timestamp = e.timestamp;
		int phraseIndex = (int) (timestamp % phraseLength);
		
		this.beatNum = phraseIndex;
		
		//System.out.println ("timestamp: "+ timestamp);
		//System.out.println ("phraseIndex: "+ phraseIndex);

		
		SequencerNote n = notes.get(phraseIndex);
		
		
		if (n != null && cdListeners.size() > 0)// && n.isMuted() == false)
		{
			for (ChainDevice cd : cdListeners)
			{	
				cd.handleSequencerBroadcastNote(n);
			}
			//sendNoteToReceiever(n);
			//parentListener.handleSequencerBroadcastNote(n);
			
			/*
			try {
				//callbackMethod.invoke(parentListener, new Object[] { (SequencerNote)n });
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			*/
		}
		
	}
	
	public void addListener (ChainDevice co)
	{
		cdListeners.add(co);
	}
	
	public void addChildSequencer (Sequencer s)
	{
		seqListeners.add(s);
	}
	
	/*
	public void sendNoteToReceiever (SequencerNote n)
	{
		SequencerNoteReceiver nr = getNoteReceiver();
		if (nr != null)
		{
			nr.receiveNote(n);
		}
	}
	*/
	public void modifyNote (int index, double newPitch, boolean mute )
	{
		SequencerNote n = notes.remove(index);
		n.setPitch(newPitch);
		n.setMute(mute);
		
		notes.add(index, n);
	
	}
	
	
	/// Get and Set methods
	
	public void setClockRef(SequencerClock c)
	{
		this.clock = c;
	}
	public SequencerClock getClockRef()
	{
		return this.clock;
	}
	public void setTempo(int value)
	{
		this.clock.setTempo(value);
	}
	
	public void setScaleRef(SequencerScale s)
	{
		this.scale = s;
		for (Sequencer seq : seqListeners)
		{
			seq.setScaleRef(s);
		}	
		
		for (SequencerNote n : notes)
		{
			n.setScale(s);
		}
	}
	public SequencerScale getScaleRef()
	{
		return this.scale;
	}
	
	public void play()
	{
		clock.start();
	}
	public void stop()
	{
		clock.stop();
	}
	
	public boolean isPlaying()
	{
		return clock.isPlaying();
	}
	
	/*
	public void setNoteReceiver(SequencerNoteReceiver nr) 
	{
		this.receiver = nr;
	}
	public SequencerNoteReceiver getNoteReceiver()
	{
		return this.receiver;
	}
	public SequencerNoteReceiver clearNoteReceiever()
	{
		SequencerNoteReceiver nr = getNoteReceiver();
		this.receiver = null;
		return nr;
	}
	*/

	
}
