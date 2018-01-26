/**
 * Richard Caceres
 * rcaceres [at] ucla [dot] edu
 * $Id: Main.java,v 391.1 2009-02-10 12:28:48-08 - - $ 
 */

import java.util.ArrayList;

import controlP5.*;
import processing.core.*;
import sequencer.Sequencer;
import sequencer.SequencerClock;
import sequencer.SequencerScale;


public class Main extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// General Application Variables.
	
	public final int APPWIDTH = 1024;
	public final int APPHEIGHT = 701;
	public int APPBGCOLOR = 240; 
	public PImage BGIMAGE;
	
	public boolean prevMousePressed;
	public boolean mouseJustPressed;
	public int mousePressX, mousePressY;
	
	
	public ArrayList<ModuleGUI> keyListeners;
		
	// menu bar instance
	MenuBar mb;
	
	ControlP5 controlP5;
	MultiList l;
	Slider slider;
	MultiListButton[] btns;
	
	
	// GLOBAL SEQUENCER VARIABLES !!
	SequencerScale sl;
	SequencerClock cl;
	Sequencer seq;
	
	// Module variables.
	ModuleGUIManager mgm;
	ModulePalette mp;
	
	//font
	PFont fnt;

	public void setup() 
	{
		size(APPWIDTH, APPHEIGHT);
		
		BGIMAGE = loadImage ("background-base.png");
		
		background(BGIMAGE);
		
		fnt = loadFont("FacsimileLL-9.vlw");
		textFont(fnt, 9); 
		
		
		keyListeners = new ArrayList<ModuleGUI>();
		
		// Setup the sequencer
		sl = new SequencerScale (SequencerScale.MINOR_BLUES);
		cl = new SequencerClock();
		seq = new Sequencer(16, cl, sl);
		
		
		
		// Instantiate the Module Gui Manager.
		mgm = new ModuleGUIManager (this);
		mp = new ModulePalette (this, mgm, seq);


		
		// CREATE THE MENU BAR
		mb = new MenuBar(this, seq);
		//setupMenuBar(seq);
		
	}
	
	public void controlEvent(ControlEvent theEvent) {
		  println(theEvent.controller().name()+" = "+theEvent.value());  
		  // uncomment the line below to remove a multilist item when clicked.
		  // theEvent.controller().remove();
		  int index = (int) theEvent.controller().value();
		  SequencerScale sl = new SequencerScale(SequencerScale.SCALES[index]);
		  seq.setScaleRef(sl);
		  
		  
		  // set the background color of the items
		  //theEvent.controller().setColorBackground(200);
		  //l.
		  
	}


	public void handleTempoSlider(int value) {
		float ratio = 1 - (float)value / (float)100;
		int tempo = (int) (ratio * 500 + 60);		
		seq.setTempo(tempo);
		System.out.println ("handleTempoSlider: "+tempo);
	}


	
	public void setupMenuBar(Sequencer seq)
	{
		  // create a multiListButton which we will use to
		  // add new buttons to the multilist
		  
		  controlP5 = new ControlP5(this);
		  
		  // add a multiList to controlP5.
		  // elements of the list have default dimensions
		  // here, a width of 100 and a height of 12
		  l = controlP5.addMultiList("myList",width - 210,100,100,12);
		  
		  // create a multiListButton which we will use to
		  // add new buttons to the multilist

		  MultiListButton b;
		  b = l.add("Scale",1);
		  		  
		  // add items to a sublist of button "level1"
		  //b.add("MISC", 0).setLabel("MISC");
		  b.add("INSEN",1).setLabel("JAPANESE");	
		  b.add("PERFECT_FOURTHS",2).setLabel("FOURTHS");		  
		  b.add("MINOR_BLUES",3).setLabel("BLUES");
		  b.add("PENTATONIC_MAJOR", 4).setLabel("5 NOTES");
		  b.add("C_MAJOR", 5).setLabel("C MAJOR");
		  
		  
		  // add slider
		 slider = controlP5.addSlider("handleTempoSlider", 0, 100, (20 + 2000) / 2, width - 320, 100, 100, 20);
		 slider.setLabelVisible(false);
		 //slider.setLabel("Tempo");
	}
	

	public void update()
	{
		// mouse pressed pos variables
		mouseJustPressed = false;

		if (prevMousePressed == false && mousePressed == true)
		{
			mouseJustPressed = true;
			mousePressX = mouseX;
			mousePressY = mouseY;
		}
		prevMousePressed =  mousePressed;
		
		
		mb.update(this);
		mp.update(this);
		

		mgm.update();
		
	}
	
	public void mousePressed()
	{
		mgm.mousePressed();
	}
	
	public void mouseDragged()
	{
		mgm.mouseDragged();
		update();
		graphics();
	}
	
	public void graphics()
	{
		background (BGIMAGE);
		mb.draw(this);
		
		
		textFont(fnt, 9); 
		mp.draw(this);
		
		mgm.draw();
		
		//fill(0);
		//textAlign(CORNER);
		//text("prototype by r.caceres\n02.11.09", 10,18);
	}
	
	public void addKeyboardListener(ModuleGUI mg)
	{
		keyListeners.add(mg);
	}
	public void keyPressed()
	{
		for(int i =0; i < keyListeners.size(); i++)
		{
			keyListeners.get(i).keyPressed(this);
		}
	}
	
	public void draw() 
	{
		//println(frameRate);

		update();
		graphics();
	}

	public static void main(String args[]) {
		// PApplet.main(new String[] { "--present", "MyProcessingSketch" });
		PApplet.main(new String[] { "Main" });
		// PApplet.main(new String[] {});
	}

}