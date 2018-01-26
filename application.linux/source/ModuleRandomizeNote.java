import processing.core.*;
import sequencer.ChainPulse;
import sequencer.ChainRandomizeNote;


public class ModuleRandomizeNote extends ModuleGUI {

	PApplet p;
	
	public ModuleRandomizeNote() {
		width = 60;
		height = 60;
		supportIN = true;
		supportOUT = true;
						
		this.initVars();
		
		this.PORT_COLOR = bgColor = 0xFF0084de;
		this.init();
	}
	
	public void init()
	{
		this.chainDevice = new ChainRandomizeNote();
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("random", 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.drawBackground(p);
		super.drawCloseBox(p);
		
		// draw little visualalization;
		p.ellipseMode(p.CENTER);
		ChainRandomizeNote crn = (ChainRandomizeNote)chainDevice;
		double offset = crn.getLastRandomOffset();
		
		float ew = (float) (40 - 40 * offset);
		p.noStroke();
		p.fill(255);
		p.ellipse(5+ width/2, height/2, ew, ew);
				
		
		
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("random", width/2 + 10, height / 2 + 4);
		
	}
}
