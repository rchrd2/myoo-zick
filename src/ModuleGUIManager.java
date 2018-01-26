/**
 * Richard Caceres
 * rcaceres [at] ucla [dot] edu
 * $Id: ModuleGUIManager.java,v 391.2 2009-02-10 12:29:32-08 - - $ 
 */


import java.util.ArrayList;
import processing.core.PApplet;

public class ModuleGUIManager 
{
	
	private ArrayList<ModuleGUI> modules;
	
	public PApplet p;
	public boolean prevMousePressed;
	public boolean moduleBeingDragged;
	public int[] mousePressPos = {0, 0};
	public final int CONDIST = 25;
	
	public boolean enabled = true;
	public int[] boundaries = {170, 60, 1010, 680 };
	
	
	public ModuleGUIManager (PApplet p)
	{
		this.p = p;
		modules = new ArrayList<ModuleGUI>();
	}
	
	
	public void registerModule(ModuleGUI m)
	{
		modules.add(m);
		m.depth = modules.size();
	}
	public void removeModule(ModuleGUI m)
	{
		modules.remove(m);
	}
	
	
	public void update()
	{
		// Check if the mouse is in any of the GUI hit areas
		
		// if it is, 
		//	change the cursor to the potential grab
		//  let the object know that its hit area is rolledOver
		
		
		// Also check to see if the mouse is pressed
		
		// if it is,
		//  let the object know that the mouse is pressed on top of it.
		//
		
		// NOTE: 
		//  Should this class handle dragging, or should each object
		//  handle that individually?
		//    Well how about the module only knows its own internal geometry.
		//      This makes it sort of like the windows menu bar -- that is the
		//      area that is controlled by the operating system.
		//      This means that this class has to pass a relative mouse position
		//      to the objects. It will also pass mouse clicks to the objects.
		
		
		// Regulate dragging.
		//
		// NOTE:
		//  This is very much a hack job for the time being.
		////////////////////////////////////////////////////////////////////////
		
		//for (ModuleGUI m : modules)
		for (int i = modules.size() - 1; i >= 0; i--)
		{
			ModuleGUI m = modules.get(i);

			updateModuleMouseProperties(m);
			
			
			// IF THE CLOSE BUTTON WAS PRESSED, KILL THE MODULE
			if (m.closeHover && m.mousePressed == false && prevMousePressed == true)
			{
				System.out.println("going to remove module: "+i);
				modules.remove(i);
				m.die();
			}
			
			
			// If a module got dragged, don't check other modules
			else if (m.dragPressed == true)
			{
				handleModuleDragConnect(m);
				break;
			}
			
			
		}
		// Update module internal variables.
		///////////////////////////////////////////////////////////////////////
		// I do not understand why this has to be below handleModuleDragConnect
		// Check if the mouse is in the modules space.

		
		// Set the previous mouse pressed
		prevMousePressed = p.mousePressed;
	}
	
	public void updateModuleMouseProperties(ModuleGUI m)
	{

		if (pointInRect (p.mouseX, p.mouseY, m.xpos, m.ypos, m.xpos + m.width, m.ypos + m.height))
		{
			m.mousePressed = p.mousePressed;
			m.mouseX = p.mouseX - m.xpos;
			m.mouseY = p.mouseY - m.ypos;
			m.mouseIn = true;
			
			m.dragHover = pointInRect (m.mouseX, m.mouseY, 
					m.dragHitArea[0], 
					m.dragHitArea[1], 
					m.dragHitArea[2], 
					m.dragHitArea[3]);
			
			m.closeHover = pointInRect (m.mouseX, m.mouseY, 
					m.closeArea[0], m.closeArea[1], m.closeArea[2], m.closeArea[3]);
			
		}
		else
		{
			m.mouseIn = false;
			m.dragHover = false;
			m.closeHover = false;
		}				

		boolean dragPointInRect = 	m.dragHover = pointInRect (mousePressPos[0], mousePressPos[1], 
				m.xpos + m.dragHitArea[0], 
				m.ypos + m.dragHitArea[1], 
				m.xpos + m.dragHitArea[2], 
				m.ypos + m.dragHitArea[3]);
		
		
		if (moduleBeingDragged == true && m.dragPressed == false)
		{
			// do nothing
		}
		
		else if (m.dragPressed == true && p.mousePressed ==  true)
		{
			m.dragPressed = true;
			moduleBeingDragged = true;
			modules.remove(m);
			modules.add(m);
		}
		else if (m.dragHover && p.mousePressed)
		{
			m.dragPressed = true;
			m.mousePressPos[0] = mousePressPos[0] - m.xpos;
			m.mousePressPos[1] = mousePressPos[1] - m.ypos;
		}
		else
		{
			m.dragPressed = false;
			moduleBeingDragged = false;
		}
		
		

		m.update(p);
	
		
	}
	
	public void handleModuleDragConnect(ModuleGUI m)
	{
		if (m.dragPressed)
		{
			int[] newPos = new int[2];
			
			//newPos[0] = p.mouseX - m.mouseX;
			//newPos[1] = p.mouseY - m.mouseY;
							
			newPos[0] = p.mouseX - m.mousePressPos[0];
			newPos[1] = p.mouseY - m.mousePressPos[1];

			// check to see if the out terminal is near any of the IN terminals
			
			if (m.supportOUT == true && m.hasOutConnected() == false)
			{
				for (ModuleGUI mOther : modules)
				{
					if (mOther != m && mOther.supportIN)
					{
						float x1 = newPos[0] + m.mOutAreaCenter[0];
						float y1 = newPos[1] + m.mOutAreaCenter[1];
						float x2 = mOther.xpos + mOther.mInAreaCenter[0];
						float y2 = mOther.ypos + mOther.mInAreaCenter[1];
						float distance = p.dist(x1, y1, x2, y2);
						
						//System.out.printf("%f, %f, %f, %f%n", x1, y1, x2, y2);
						//p.println ("distance: "+distance);
						
						if (distance <= CONDIST)
						{

							m.setOutConnection(mOther);
							mOther.setInConnection(m);

							p.println("OUT CONNETION MADE INTO ANOTHER BOX!: "+ distance);
							break; // break out of this for loop
						}
						
					}
				}
			}
			else if (m.hasOutConnected() == true)
			{
				// check if it is disconnected with its neighbor
				ModuleGUI mOther = m.getOutConnection();
				float x1 = newPos[0] + m.mOutAreaCenter[0];
				float y1 = newPos[1] + m.mOutAreaCenter[1];
				float x2 = mOther.xpos + mOther.mInAreaCenter[0];
				float y2 = mOther.ypos + mOther.mInAreaCenter[1];
				float distance = p.dist(x1, y1, x2, y2);
				
				if (distance > CONDIST)
				{
					// Disconnect
					m.clearOutConnection();
					mOther.clearInConnection();
					//p.println ("OUT CONNECTION BROKEN!!!!");
					
				}
				else
				{
					// snap connect the module
					newPos[0] = (mOther.xpos + mOther.mInAreaCenter[0]) - (m.mOutAreaCenter[0]);
					newPos[1] = (mOther.ypos + mOther.mInAreaCenter[1]) - (m.mOutAreaCenter[1]);
				}
			}
			
			
			// check to see if the in terminal is near any of the OUT terminals
			
			
			if (m.supportIN == true && m.hasInConnected() == false)
			{
			
				for (ModuleGUI mOther : modules)
				{
					if (mOther != m && mOther.supportOUT)
					{
						float x1 = newPos[0] + m.mInAreaCenter[0];
						float y1 = newPos[1] + m.mInAreaCenter[1];
						float x2 = mOther.xpos + mOther.mOutAreaCenter[0];
						float y2 = mOther.ypos + mOther.mOutAreaCenter[1];
						float distance = p.dist(x1, y1, x2, y2);
						
						//p.println ("distance: "+distance);
						
						if (distance <= CONDIST)
						{
							// connect the module
							p.println("REVERSE CONNETION MADE!");
							
							m.setInConnection(mOther);
							mOther.setOutConnection(m);
							
							break; // break out of this for loop
						}
						
					}
				}
			}
			else if (m.hasInConnected() == true)
			{
				// check if it is disconnected with its neighbor
				ModuleGUI mOther = m.getInConnection();
				float x1 = newPos[0] + m.mInAreaCenter[0];
				float y1 = newPos[1] + m.mInAreaCenter[1];
				float x2 = mOther.xpos + mOther.mOutAreaCenter[0];
				float y2 = mOther.ypos + mOther.mOutAreaCenter[1];
				float distance = p.dist(x1, y1, x2, y2);

				//float distance = p.dist(mousePressPos[0], mousePressPos[1], p.mouseX, p.mouseY);
				
				if (distance > CONDIST)
				{
					// Disconnect
					//System.out.println("disconnect");
					m.clearInConnection();
					mOther.clearOutConnection();
				}
				else
				{
					newPos[0] = (mOther.xpos + mOther.mOutAreaCenter[0]) - m.mInAreaCenter[0];
					newPos[1] = (mOther.ypos + mOther.mOutAreaCenter[1]) - m.mInAreaCenter[1];
				}
			}
			
			
			
			// CONSTRAINTS
			if (newPos[0] < boundaries[0]) newPos[0] = boundaries[0];
			if (newPos[0] + m.width > boundaries[2]) newPos[0] = boundaries[2] - m.width;
			if (newPos[1] < boundaries[1]) newPos[1] = boundaries[1];
			if (newPos[1] + m.height > boundaries[3]) newPos[1] = boundaries[3] - m.height;
			
			
			
			m.xpos = newPos[0];
			m.ypos = newPos[1];
		}
	}
	
	
	public void mousePressed()
	{
		mousePressPos[0] = p.mouseX;
		mousePressPos[1] = p.mouseY;
	}
	
	public void mouseDragged()
	{
		update();
		draw();
	}
	
	private boolean pointInRect(int px, int py, int rx1, int ry1, int rx2, int ry2)
	{
		return (px > rx1 && px < rx2 && py > ry1 && py < ry2);
	}
	
	public void draw()
	{
		for (ModuleGUI m : modules)
		{
			// Translate the PApplet Coordinate system.
			
			p.pushMatrix();
			p.translate(m.xpos, m.ypos);
			
			// Call the module draw.
			m.draw(p);
			
			// Un-translate the coordinate system.
			p.popMatrix();
		}
	}
}
