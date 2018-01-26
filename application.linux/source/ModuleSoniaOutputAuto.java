import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaOutputAuto extends ModuleSoniaOutput {

	String name;
	
	public ModuleSoniaOutputAuto(PApplet p, String samplestr, String name) {
		//super(p, "samples/SK1 Piano A3.wav");
		super(p, samplestr);
		
		this.name = name;
		bgColor = p.color(250, 238, 42);

	}
	public static void drawIcon(PApplet p, String name)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text(name, 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.draw(p);
		
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text(name, width/2 + 8, height / 2 + 4);
		
	}

}
