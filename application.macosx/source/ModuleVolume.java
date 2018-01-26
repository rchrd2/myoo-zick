import processing.core.*;
import sequencer.ChainVolume;


public class ModuleVolume extends ModuleGUI {

	PApplet p;
	
	public ModuleVolume() {
		width = 60;
		height = 60;
		supportIN = true;
		supportOUT = true;
						
		this.initVars();
		
		this.PORT_COLOR  = 0xFF0084de;
		this.bgColor = 0xFF151515;
		
		this.init();
		
		this.dragHitArea = dragHitAreaA;
	}
	
	public void init()
	{
		this.chainDevice = new ChainVolume();
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("volume", 128/2, 22);
	}	
	
	public void draw(PApplet p)
	{
		
		super.drawBackground(p);
		super.drawDragHitArea(p);
		super.drawCloseBox(p);

		
		//
		ChainVolume cv = (ChainVolume)this.chainDevice;
		double velocity = cv.getVelocity();
		
		double relH = height - dragHitArea[3];

		
		int h = (int) Math.round(relH * velocity);
		
		//p.println ("h: "+h);

		//p.rect(, this.dragHitArea[3], width, height - this.dragHitArea[3]);
		
		
		
		p.fill(100);
		p.noStroke();

		p.rect (15, dragHitArea[3] + 1, width - 30, (float) relH - 1);
		
		
		p.fill(0, 132, 222);
		p.rect (15, (float) (height - h), width - 30, h);



		
		// check for mouse interaction
		
		if (this.mousePressed)
		{
			double relMY = mouseY - dragHitArea[3];
			if (relMY > 0)
			{
				double newVel = 1 - (double)relMY / (double)relH;
				cv.setVelocity(newVel);
			}
		}


		
		
		
		p.fill(255);
		p.textAlign(p.CENTER);
		//p.text("volume", width/2 + 10, height / 2 + 4);
		p.text("volume", width/2 + 2, 12);

		
	}
}
