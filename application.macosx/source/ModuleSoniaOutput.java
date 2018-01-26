import processing.core.*;
import sequencer.ChainSonia;


public class ModuleSoniaOutput extends ModuleGUI {

	
	public ModuleSoniaOutput(PApplet p, String sample_str) {
		width = 60;
		height = 60;
		supportIN = true;
		supportOUT = false;
				
		this.bgColor = 0xFFe7dc21;
		
		this.dragHitArea = dragHitAreaB;
		
		this.initVars();
		this.init(sample_str, p);
	}
	

	
	public void init(String sample_str, PApplet p)
	{
		this.chainDevice = new ChainSonia (sample_str, p);
	}
	
	public void draw(PApplet p)
	{
		
		super.drawBackground(p);
		super.drawCloseBox(p);

		
	}
	

}
