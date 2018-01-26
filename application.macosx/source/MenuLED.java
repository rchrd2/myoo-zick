import processing.core.*;
import sequencer.ChainDevice;
import sequencer.ChainLED;
import sequencer.Sequencer;

public class MenuLED {
	
	PImage[] g;
	ChainLED cd;
	
	int x, y;
	
	boolean metStatus = false;
	
	public MenuLED(PApplet p, Sequencer s, int x, int y)
	{
		this.cd = new ChainLED(s);
		
		this.x = x;
		this.y = y;
		
		g = new PImage[2];
		g[0] = p.loadImage("led-off.png");
		g[1] = p.loadImage("led-on.png");
	}
	
	
	public void draw (PApplet p)
	{
		int image_id = 0;
		if (cd.getMetStatus() == true)
		{
			image_id = 1;
		}
		
		p.image(g[image_id], x, y);

	}
}
