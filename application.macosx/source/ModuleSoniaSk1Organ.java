import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaSk1Organ extends ModuleSoniaOutput {

	
	public ModuleSoniaSk1Organ(PApplet p) {
		super(p, "samples/KU_8040000p - ORGAN.aif");
		bgColor = p.color(250, 238, 42);
	}

	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("organ", 128/2, 22);
	}
	public void draw(PApplet p)
	{
		super.draw(p);
		
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("organ", width/2 + 8, height / 2 + 4);
		
	}
}
