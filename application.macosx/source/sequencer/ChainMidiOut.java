package sequencer;
import processing.core.*;
import rwmidi.*;

public class ChainMidiOut extends ChainDevice {

	private static boolean midi_is_started = false;
	
	private static MidiOutput output;

	
	public int polyphony = 8;
	private PApplet p;
	
	// Chain Device variables
	boolean supportIn = true, supportOut = false;
	ChainDevice inDevice;
	
	public ChainMidiOut (PApplet p)
	{
		this.p = p;
		initSynth();
	}
	
	public String toString ()
	{
		return "ChainMidiOut Device";
	}
	
	public void receiveNote(SequencerNote n) {
		//System.out.println ("Chain MidiOut Recieve Note!!: " + n);
		
		if (midi_is_started)
		{
		  int ret = output.sendNoteOn(0 ,n.getNoteNumber(), (int)Math.round(n.getVelocity() * 127));
		  (new NOTE_OFF_THREAD ((int)(n.getDuration() * 200), "test",n.getNoteNumber(), output)).start();
		}
	}


	public void initSynth()
	{
		
	  if (midi_is_started == false)
	  {
		  output = RWMidi.getOutputDevices()[0].createOutput();

		  midi_is_started = true;
	  }

	}
	
	// Safely close the sound engine upon Browser shutdown.
	public void stopSynth()
	{
	  //Sonia.stop();
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
	
	
	
	/// INNER CLASS FOR SENDING NOTE OFFS
	class NOTE_OFF_THREAD extends Thread {
	    private boolean running;           // Is the thread running?  Yes or no?
	    private int wait;                  // How many milliseconds should we wait in between executions?
	    private String id;                 // Thread name
	    private int count;                 // counter
	    int notenum;
		MidiOutput output;

	    
	    // Constructor, create the thread
	    // It is not running by default
	    public NOTE_OFF_THREAD (int w, String s, int notenum, MidiOutput output){
	        wait = w;
	        running = false;
	        id = s;
	        count = 0;
	        this.notenum = notenum;
	        this.output = output;
	    }

	    // Overriding "start()"
	    public void start ()
	    {
	        // Set running equal to true
	        running = true;
	        // Print messages
	        //System.out.println("Starting thread (will execute every " + wait + " milliseconds.)");
	        // Do whatever start does in Thread, don't forget this!
	        super.start();
	    }

	    // We must implement run, this gets triggered by start()
	    public void run ()
	    {
	        while (running && count < 10){
	            //System.out.println(id + ": " + count);
	            count++;
	            // Ok, let's wait for however long we should wait
	            try {
	                sleep((long)(wait));
	            }
	            catch (Exception e) {
	            }
	        }
	        
	        output.sendNoteOn(0 , notenum, 0);
	        this.quit();
	        //System.out.println(id + " thread is done!");  // The thread is done when we get to the end of run()
	    }
	    // Our method that quits the thread
	    public void quit()
	    {
	        //System.out.println("Quitting.");
	        running = false;  // Setting running to false ends the loop in run()
	        interrupt(); // in case the thread is waiting. . .
	    }

		
	}
	

}
