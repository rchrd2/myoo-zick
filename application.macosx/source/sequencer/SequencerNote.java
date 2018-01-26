package sequencer;

public class SequencerNote {
	
	private SequencerScale scale;
	
	// Pitch is a simple ratio that can be scaled to any scale.
	private double pitch = .5;
	
	// Duration is a ratio of 1 measure... for example, .25 is one quarter note.
	private double duration = .25;
	
	private double velocity = .8;
	
	public SequencerNote (double pitch, double duration, SequencerScale scale)
	{
		this.pitch = pitch;
		this.duration = duration;
		this.scale = scale;
	}
	
	public double getPitch () {
		return this.pitch;
	}
	
	public double getDuration() {
		return this.duration;
	}
	
	public SequencerScale getScale() {
		return scale;
	}
	
	public void setScale(SequencerScale s)
	{
		this.scale = s;
	}
	
	public void setPitch(double pitch)
	{
		this.pitch = pitch;
	}
	
	public double getFrequency()
	{
		return scale.getNoteFreq(pitch);
	}
	public int getNoteNumber()
	{
		return scale.getNoteMidi(pitch);
	}
	
	public void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}
	public double getVelocity()
	{
		return this.velocity;
	}

	public boolean isMuted()
	{
		return velocity == 0 ? true : false;
	}
	
	public void setMute(boolean mute)
	{
		if (mute) this.velocity = 0;
		else this.velocity = .8;
	}
	
	//public void toString(){	}

	
}
