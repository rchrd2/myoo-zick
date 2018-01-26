import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaSynth2 extends ModuleSoniaOutput {

	
	public ModuleSoniaSynth2(PApplet p) {
		super(p, "samples/md_0_003_oct_1.wav");
		bgColor = p.color(255, 100, 255);
	}
	
	public void draw(PApplet p)
	{
		super.draw(p);
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("SYNTH 2", width/2, height/2);
		
	}
}
