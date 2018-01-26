package sequencer;

/* This is an abstract class to represent musical modules that will talk to
 * eachother. It runs parrallel to the graphical interface. They are not tightly
 * integraded at the moment.
 */

public abstract class ChainDevice {
	
	public abstract void receiveNote(SequencerNote n);

	//abstract void sendInformation();
	public abstract boolean supportIn();
	public abstract boolean supportOut();
	public abstract ChainDevice getInChain();
	public abstract ChainDevice getOutChain();
	public abstract void setInChain(ChainDevice cd);
	public abstract void setOutChain(ChainDevice cd);
	public abstract ChainDevice clearInChain();
	public abstract ChainDevice clearOutChain();
	public abstract void handleSequencerBroadcastNote(SequencerNote n);
	public abstract void die();
}
