import processing.core.*;
import sequencer.ChainMidiOut;


public class ModuleMidiOutput extends ModuleGUI {

	int bgColor;
	
	public ModuleMidiOutput(PApplet p) {
		width = 60;
		height = 60;
		supportIN = true;
		supportOUT = false;
		
		this.bgColor = 0xFFe7dc21;

		
		this.initVars();
		this.init(p);
	}
	
	public void init(PApplet p)
	{
		this.chainDevice = new ChainMidiOut (p);
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("midi out", 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.drawBackground(p);
		super.drawCloseBox(p);
		
		
		p.fill(255);
		p.textAlign(p.CENTER);
		p.text("midi out", width/2 + 10, height / 2 + 4);
	}
	

}
