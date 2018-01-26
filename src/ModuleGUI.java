/**
 * Richard Caceres
 * rcaceres [at] ucla [dot] edu
 * $Id: ModuleGUI.java,v 391.1 2009-02-10 12:28:48-08 - - $ 
 */

import processing.core.PApplet;
import sequencer.ChainDevice;
import sequencer.ChainPulse;

public class ModuleGUI 
{
	public int mouseX, mouseY;
	public boolean mousePressed, mouseReleased, mouseIn, dragPressed, dragHover, closeHover;
	public int[] mousePressPos = {0, 0};

	
	public boolean supportIN = true, supportOUT = true;
	public ModuleGUI inConnection, outConnection;
	public ChainDevice chainDevice; // This is the musical representation
	
	public int width, height;
	public int depth = 0;
	
	public int HITAREA_W = 10, HITAREA_H = 20;
	public int PORT_COLOR = 0xffCCCCCC;
	public final int[] closeArea = {0, 0, 15, 15};
	
	public int[] dragHitArea, mInArea, mOutArea, mInAreaCenter, mOutAreaCenter;
	public int[] dragHitAreaA, dragHitAreaB;
	public int dragHitAreaColor = 200;
	public int bgColor = 0xFF000000;
		
	// Should it know its x and y pos, or should it be "context free"?
	// These could just be used for the drawing methods.
	// Or the object that calls this could perform a push matrix before
	// it calls this, which would allow it to just draw like normal.
	
	static public int sx = 0, sy = 0;
	
	public int xpos = 200 + (sx += 40) % 600; 
	public int ypos = 150 + (sy += 10) % 400;
	
	public ModuleGUI ()
	{
		width = height = 200;
		initVars();
	}
	
	protected void initVars()
	{
		dragHitAreaA = new int[4];
		//dragHitArea[0] = closeArea[2];
		dragHitAreaA[0] = 0;
		dragHitAreaA[1] = 0;
		dragHitAreaA[2] = width;
		dragHitAreaA[3] = 15;
		
		
		
		
		dragHitAreaB = new int[4];
		dragHitAreaB[0] = 0;
		dragHitAreaB[1] = 0;
		dragHitAreaB[2] = width;
		dragHitAreaB[3] = height;
		
		dragHitArea = dragHitAreaB;
		
		mInArea = new int[4];
		mInArea[0] = 0;
		mInArea[1] = height / 2 - HITAREA_H / 2;
		mInArea[2] = HITAREA_W;
		mInArea[3] = HITAREA_H;
		
		mOutArea = new int[4];
		mOutArea[0] = width;
		mOutArea[1] = height / 2 - HITAREA_H / 2;
		mOutArea[2] = HITAREA_W;
		mOutArea[3] = HITAREA_H;
		
		mInAreaCenter = new int[2];
		mInAreaCenter[0] = mInArea[0] + mInArea[2] / 2;
		mInAreaCenter[1] = mInArea[1] + mInArea[3] / 2;
		
		mOutAreaCenter = new int[2];
		mOutAreaCenter[0] = mOutArea[0] + mOutArea[2] / 2;
		mOutAreaCenter[1] = mOutArea[1] + mOutArea[3] / 2;
	}
	
	
	public static void drawIcon(PApplet p)
	{
		p.fill(0);
		p.rect(0,0, 128, 50);
	}
	
	
	public void update(PApplet p)
	{
		//if (mouseIn)
		//p.println ("mouseX: "+mouseX + ", mouseY: " + mouseY);
	}
	
	public void keyPressed(PApplet p){}
	
	public ModuleGUI getInConnection()
	{
		return inConnection;
	}
	
	public ModuleGUI getOutConnection()
	{
		return outConnection;
	}
	
	public void setInConnection(ModuleGUI m)
	{
		inConnection = m;
		
		// connect to the musical system
		this.chainDevice.setInChain(m.chainDevice);
		//m.chainDevice.setOutChain(this.chainDevice);
	}
	
	public void setOutConnection(ModuleGUI m)
	{
		outConnection = m;
		// connect to the musical system
		this.chainDevice.setOutChain(m.chainDevice);
		//m.chainDevice.setInChain(this.chainDevice);
	}
	
	public void clearAll()
	{
		if (inConnection != null)
		{
			inConnection.clearOutConnection();
		}
		if (outConnection != null)
		{
			outConnection.clearInConnection();
		}
		clearInConnection();
		clearOutConnection();
	}
	
	public void clearInConnection()
	{
		inConnection = null;
		// connect to the musical system
		this.chainDevice.clearInChain();
	}
	
	public void clearOutConnection()
	{
		outConnection = null;
		// connect to the musical system
		this.chainDevice.clearOutChain();
	}
	
	public boolean hasInConnected()
	{
		if (inConnection == null)
			return false;
		else 
			return true;
	}
	
	public boolean hasOutConnected()
	{
		if (outConnection == null)
			return false;
		else 
			return true;
	}
	
	public void draw(PApplet p)
	{
		
		if (dragPressed)
		{
			
			p.noStroke();
			p.fill(50, 100);
			p.rect(5, 5, width, height);
			
			p.rect(mOutArea[0] + 5, mOutArea[1] + 5, mOutArea[2], mOutArea[3]);
		}
		
		p.stroke(255);
		p.fill(255);
		p.rect(0, 0, width, height);
		p.line(0, 0, width, height);
		
		drawDragHitArea(p);
		
		// Draw the terminals
		p.line(3, height/2, 10, height/2);
		p.rect(mInArea[0], mInArea[1], mInArea[2], mInArea[3]);
		
		p.rect(mOutArea[0], mOutArea[1], mOutArea[2], mOutArea[3]);

	}
	
	
	public void drawBackground(PApplet p)
	{
		// Shadow (sort of recursive hack);
		
		if (dragPressed)
		{
			p.noStroke();
			p.fill(50,100);
			p.pushMatrix();
			p.translate(4, 9);
			drawBoxShape(p);
			p.popMatrix();		
		}
		
		



		

		
		
		p.stroke(255);
		p.fill(bgColor);
		
		drawBoxShape(p);

		

		// Draw the menu
		
		if (dragPressed)
			p.fill(PORT_COLOR);
		else
			p.fill(PORT_COLOR);
		
		if (dragHitArea == dragHitAreaA)
		{
			p.rect(dragHitArea[0], dragHitArea[1], dragHitAreaA[2], dragHitAreaA[3]);
		}
		

	}
	

	
	private void drawBoxShape(PApplet p)
	{
		p.beginShape();
		p.vertex(0, 0);
		p.vertex(width, 0);
		
		if (supportOUT)
		{
			p.vertex(mOutArea[0], mOutArea[1]);
			p.vertex(mOutArea[0] + mOutArea[2], mOutArea[1]);
			p.vertex(mOutArea[0] + mOutArea[2], mOutArea[1] + mOutArea[3]);
			p.vertex(mOutArea[0], mOutArea[1] + mOutArea[3]);
		}
		
		
		p.vertex(width, height);

		p.vertex(0, height);
		
		if (supportIN)
		{
			p.vertex(0, mInArea[1] + mInArea[3]);
			p.vertex(mInArea[2], mInArea[1] + mInArea[3]);
			p.vertex(mInArea[2], mInArea[1]);
			p.vertex(0, mInArea[1]);
		}
		
		
		p.vertex(0,0);

		p.endShape();
	}

	public void drawDragHitArea(PApplet p)
	{
		// nothing...
	}
	
	public void drawTerminals(PApplet p)
	{
		/*
		// Draw the terminals
		p.noStroke();
		p.fill(PORT_COLOR);
		if (supportOUT)
			p.rect(mOutArea[0], mOutArea[1], mOutArea[2], mOutArea[3]);
		//if (supportIN)
			//p.rect(mInArea[0], mInArea[1], mInArea[2], mInArea[3]);
			 
			 */
	}
	
	public void drawCloseBox(PApplet p)
	{
		// draw the 'x'
		
		if (this.mouseIn)
		{
			p.fill(200);
			p.stroke(255);

			p.rect(closeArea[0], closeArea[1], closeArea[2], closeArea[3]);
			p.line(closeArea[0], closeArea[1], closeArea[2], closeArea[3]);
			p.line(closeArea[0], closeArea[3], closeArea[2], closeArea[1]);
		}
	}
	
	
	public void die()
	{
		clearAll();
		chainDevice.die();
	}
	
}
