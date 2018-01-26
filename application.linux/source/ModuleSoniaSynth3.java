import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaSynth3 extends ModuleSoniaOutput {

	
	public ModuleSoniaSynth3(PApplet p) {
		super(p, "samples/md_2_003_oct_4.wav");
		bgColor = p.color(200);
	}

	public void draw(PApplet p)
	{
		super.draw(p);
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("SYNTH 3", width/2, height/2);	
	}
}
