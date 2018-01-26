package sequencer;
import tactu5.*;

import java.util.ArrayList;

public class SequencerClock {
	Tactu5 tactu5;

	// creating an aggregator instance
	Aggregator aggregator;
	Note[] seqNotes;

	ArrayList<Sequencer> listeners;

	long timestamp = 0;
	int phraseSize = 16;
	float bpm = 120;
	float noteLength = 300;
	long lastMillis = 0;
	final int BUFFER_MILLIS = 10; 
	
	private boolean is_playing = false;
	
	public SequencerClock ()
	{

		// Create a 16 step loop
		Sequence seq = new Sequence();
		seqNotes = new Note[phraseSize];
		for (int i = 0; i < phraseSize; i++) {
			seqNotes[i] = new Note(440, noteLength, 1, 1, 100, false);
			seqNotes[i].setComment("" + i);
			seq.addNote(seqNotes[i]);
		}		

		aggregator = new Aggregator();

		// add the sequence to aggregator
		aggregator.addSequence(seq);

		// initialize and feed internal sequencer, boolean value indicates if
		// it will loop
		tactu5 = new Tactu5((Object)this, aggregator.getScore(), true);

		this.start();
		
		// INIT THE LISTENERS ARRAY LIST
		listeners = new ArrayList<Sequencer>();
	}
	
	public void setTempo (int value)
	{
		for (int i = 0; i < phraseSize; i++) 
		{
			Note n = seqNotes[i];
			n.setDuration(value);
		}

		Sequence seq = new Sequence();
		for (int i = 0; i < phraseSize; i++) {
			seq.addNote(seqNotes[i]);
		}

		aggregator.resetAll();
		// add the sequence to aggregator
		aggregator = new Aggregator();
		aggregator.addSequence(seq);
		tactu5.updateSequencer(aggregator.getScore());
		
	}
	
	public void addListener(Sequencer s)
	{
		listeners.add(s);
	}
	public void removeListener(Sequencer s)
	{
		listeners.remove(s);
	}
	
	public void noteReciver(Note n) 
	{
		long currMillis = System.currentTimeMillis();
		if (currMillis - lastMillis > BUFFER_MILLIS)
		{
			timestamp++;
			for (Sequencer s : listeners)
			{
				s.handleClockTick(new SequencerClockEvent(timestamp));
			}
		}
	}
	
	public void start()
	{
		// start sequencer
		tactu5.start();
		is_playing = true;
		
	}
	public void stop()
	{
		tactu5.stop();
		is_playing = false;
	}
	
	public boolean isPlaying()
	{
		return is_playing;
	}
}
