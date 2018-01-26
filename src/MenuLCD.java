import processing.core.*;
import sequencer.ChainDevice;
import sequencer.ChainLED;
import sequencer.Sequencer;

public class MenuLCD {
	
	
	int x, y;
	PFont fnt;
	Sequencer s;
	
	
	public MenuLCD(PApplet p, Sequencer s, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.s = s;
		fnt = p.loadFont("LCDDot-16.vlw");
	}
	
	public void draw (PApplet p)
	{
		p.fill(49, 154, 39);
		p.textFont(fnt, 16); 
		p.textAlign(p.LEFT, p.TOP);
		p.text(s.getScaleRef().toString(), x, y);

	}
}
