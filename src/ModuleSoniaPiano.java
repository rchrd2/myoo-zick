import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaPiano extends ModuleGUI {

	
	public ModuleSoniaPiano(PApplet p) {
		width = 150;
		height = 150;
		supportIN = true;
		supportOUT = false;
		
		this.initVars();
		this.init("ML_PIANO_FENDER.wav", p);
	}
	
	public void init(String sample_str, PApplet p)
	{
		this.chainDevice = new ChainSonia (sample_str, p);
	}
	
	public void draw(PApplet p)
	{
		
		if (dragPressed)
		{
			p.noStroke();
			p.fill(50, 100);
			p.rect(4, 9, width, height);			
		}
		p.stroke(100);
		p.fill(230);
		p.rect(0, 0, width, height);
		
		// Draw the menu bar
		if (dragPressed)
			p.fill(233);
		else
			p.fill(200);

		p.rect(dragHitArea[0], dragHitArea[1], dragHitArea[2] - dragHitArea[0], dragHitArea[3] - dragHitArea[1]);
		super.drawCloseBox(p);

		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("piano", width/2 + 8, height / 2 + 4);

		
		drawTerminals(p);
		
	}
}
