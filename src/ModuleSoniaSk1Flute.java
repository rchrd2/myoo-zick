import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaSk1Flute extends ModuleSoniaOutput {

	
	public ModuleSoniaSk1Flute(PApplet p) {
		super(p, "samples/SK1 Flute.wav");
		bgColor = p.color(250, 238, 42);
	}

	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("flute", 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.draw(p);
		
		
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("flute", width/2 + 8, height / 2 + 4);
		
	}
}
