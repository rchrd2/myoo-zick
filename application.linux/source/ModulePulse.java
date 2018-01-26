import processing.core.*;
import sequencer.ChainPulse;
import sequencer.Sequencer;


public class ModulePulse extends ModuleGUI {
	
	private int lastNoteMillis;
	private boolean noteFlag = false;
	
	public ModulePulse(Sequencer gs) {
		width = 60;
		height = 60;
		supportIN = false;
		supportOUT = true;
		
		bgColor = 0xFF009952; // greeeeen
		
		this.initVars();
		this.init(gs);
	}
	
	public void init(Sequencer gs)
	{
		this.chainDevice = new ChainPulse (gs);
		
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("pulse", 128/2, 22);
	}
	
	public void draw(PApplet p)
	{
		super.drawBackground(p);
		super.drawCloseBox(p);

		// note flag
		
		boolean cdflag = ((ChainPulse)chainDevice).noteFlag;
		
		if (this.noteFlag != cdflag)
		{
			this.noteFlag = cdflag;
			lastNoteMillis = p.millis();
		}
		if (p.millis() - lastNoteMillis < 100)
		{
			p.noStroke();
			p.fill(100);
			p.ellipse(width/2, height/ 2, 20, 20);
		}
		
		
		p.fill(255);
		p.textAlign(p.CENTER);
		p.text("pulse", width/2 + 2, height / 2 + 4);
	}
}
