import processing.core.*;
import sequencer.ChainHarmony;


public class ModuleHarmony extends ModuleGUI {

	PApplet p;
	
	public ModuleHarmony() {
		width = 60;
		height = 60;
		supportIN = true;
		supportOUT = true;
		

		
		this.initVars();
		
		this.PORT_COLOR = this.bgColor = 0xFF0084de;

		this.init();
	}
	
	public void init()
	{
		this.chainDevice = new ChainHarmony();
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("harmony", 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.drawBackground(p);
		super.drawCloseBox(p);
		
		// draw little visualalization;
		
		p.fill(255);
		p.textAlign(p.CENTER);
		p.text("harmony", width/2 + 10, height / 2 + 4);
		
	}
}
