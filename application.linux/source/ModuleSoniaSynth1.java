import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaSynth1 extends ModuleSoniaOutput {

	
	public ModuleSoniaSynth1(PApplet p) {
		super(p, "samples/md_2_002_oct_3.wav");
		bgColor = p.color(100, 100, 255);
	}

	
	public void draw(PApplet p)
	{
		super.draw(p);
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("SYNTH 1", width/2, height/2);
		
	}
}
