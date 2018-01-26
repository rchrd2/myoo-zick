import processing.core.*;
import sequencer.Sequencer;
import java.lang.reflect.*;

public class ModulePalette {

	/* This class handles the module pallete that is used for instantiating
	 * modules.
	 */
	ModuleGUIManager mgm;
	Sequencer seq;
	
	public int width = 148, height = 701, x = 0, y =0;
	
	public boolean mousePressed, mouseReleased, mouseIn;
	public int[] mousePressPos = {0, 0};
	public int activeTab = 0;
	public PImage[] bgImages;
	public Class[][] modules;
	
	int icon_v_spacing = 40;
	int num_icons = Math.round((float)(height - 60) / (float)icon_v_spacing);
	
	/*
	private ModuleGUI[][] modulesGuis = {
			{ModuleDrawMelody.class.getClass(), ModulePulse.class.getClass()},
			{ModuleRandomizeNote.class.getClass()},
			{ModuleSoniaSk1Drum.class.getClass()}
	};
	*/
	
	ModulePalette (PApplet p, ModuleGUIManager mgm, Sequencer seq)
	{
		// Setup images
		bgImages = new PImage[3];
		bgImages[0] = p.loadImage("Pallette-Green.png");
		bgImages[1] = p.loadImage("Pallette-Blue.png");
		bgImages[2] = p.loadImage("Pallette-Yellow.png");
		
		// setup data
		
		
		this.mgm = mgm;
		this.seq = seq;
		
		
		// set-up the module gui arrays
		/*
		
		modules = new Class[3][20];
		
		try {
			
			// note generator modules
			
			modules[0][0] =  Class.forName("ModuleDrawMelody");
			modules[0][1] =  Class.forName("ModulePulse");
			
			// effector modules
			
			modules[1][0] = Class.forName("ModuleHarmony");
			modules[1][1] = Class.forName("ModuleRandomizeNote");
			modules[1][2] = Class.forName("ModuleVolume");
			
			
			// output modules
			
			modules[2][0] = Class.forName("ModuleSoniaSk1Piano"); 
			modules[2][1] = Class.forName("ModuleSoniaSk1Organ");
			modules[2][2] = Class.forName("ModuleSoniaSk1Flute");
			modules[2][3] = Class.forName("ModuleSoniaSk1Drum");
			modules[2][4] = Class.forName("ModuleMidiOutput");
			

		} catch (Exception e) {
			
		}
		*/
		

	

		//mgm.registerModule(new ModuleSoniaOutput(this, "samples/shake 5.aif"));		
		
		//mgm.registerModule(new ModuleRandomizeNote());
		//mgm.registerModule(new ModuleSoniaPiano (this, "samples/Roberta Flack - intro.aif"));
		//mgm.registerModule(new ModuleSoniaSynth1 (p));
		//mgm.registerModule(new ModuleSoniaSynth2 (this));
		//mgm.registerModule(new ModuleSoniaSynth3 (p));
	

		//mgm.registerModule(new ModuleSoniaOutput(this, "samples/mbnz_6.aif"));
		
	
	}
	
	public void update (PApplet p)
	{
		if (p.mouseX < width)
		{
			this.mouseIn = true;
		}
		else
		{
			this.mouseIn = false;
		}
		
		if (this.mouseIn)
		{
			this.mouseReleased = (this.mousePressed == true && p.mousePressed == false);
			
			if (p.mousePressed == true && this.mousePressed == false)
			{
				this.mousePressPos[0] = p.mouseX;
				this.mousePressPos[1] = p.mouseY;
			}
			this.mousePressed = p.mousePressed;
			

			
			
			checkTabButtons(p);
			checkModuleObjects(p);
		}	
		
		
	}
	public void checkTabButtons(PApplet p){
		
		if (mousePressed && mousePressPos[0] > 50 && mousePressPos[1] < 50 && mousePressPos[0] < 100)
		{
			this.activeTab = 1;
		}
		else if (mousePressed && mousePressPos[0] > 100 && mousePressPos[1] < 50 && mousePressPos[0] < 150)
		{
			this.activeTab = 2;
		}
		else if (mousePressed && mousePressPos[1] < 50 && mousePressPos[0] < 50)
		{
			this.activeTab = 0;
		}
		
		//p.println ("activeTab: "+activeTab);
	}
	
	public void checkModuleObjects(PApplet p)
	{
		// MODULE OBJECTS
		
		if (mouseReleased)
		{
		
			/*
			if (mousePressPos[1] > 100 && mousePressPos[1] < 200
				&& p.mouseY > 100 && p.mouseY < 200)
			{
				mgm.registerModule(new ModuleSoniaSk1Drum (p));

			}
			*/
			
			//System.out.println((float)mousePressPos[1]/ (height - 60));
			float ratio = (float)(mousePressPos[1] - 60)  / (height - 60);
			int moduleIndex = (int) Math.floor( ratio * num_icons );
			//System.out.println(ratio);
			
			switch(activeTab)
			{
				case 2:
					handleModuleInstantiateC(moduleIndex, p);
					
					break;
				case 1:
					handleModuleInstantiateB(moduleIndex, p);
					break;
				default :
					handleModuleInstantiateA(moduleIndex, p);
					break;
			}
			
		}
	}
	
	public void draw (PApplet p)
	{
		p.noStroke();
		switch (activeTab)
		{
			case (2):
				p.image(bgImages[2], 0, 0);
				drawIconsC(p);
				break;
			case (1):
				p.image(bgImages[1], 0,0);
				drawIconsB(p);

				break;
			case (0):
			default:
				p.image(bgImages[0], 0, 0);
				drawIconsA(p);

		}
		
		
		//p.rect(0, 100, width, 100);
	}
	
	
	/////////////// GENERATORS ////////////////

	
	public void handleModuleInstantiateA(int index, PApplet p)
	{
		switch(index)
		{
			case 0:
				mgm.registerModule(new ModuleDrawMelody(seq));
				break;
			case 1:
				mgm.registerModule(new ModulePulse(seq));
				break;
			case 2:
				mgm.registerModule(new ModuleKeyboard((Main)p, seq));
				break;	
		}
		
	}
	

	public void drawIconsA(PApplet p)
	{
		p.pushMatrix();
		p.translate (x, y);
		
		p.translate(7, 55);
		ModuleDrawMelody.drawIcon(p);
		
		p.translate(0, icon_v_spacing);
		ModulePulse.drawIcon(p);
		
		p.translate(0, icon_v_spacing);
		ModuleKeyboard.drawIcon(p);
		
		p.popMatrix();

	
		//mgs.cast(mgs).drawIcon(p);
	}
	
	/////////////// END GENERATORS ////////////////

	
	
	/////////////// EFFECTORS ////////////////
	
	
	public void handleModuleInstantiateB(int index, PApplet p)
	{
		switch(index)
		{
			case 0:
				mgm.registerModule(new ModuleHarmony());
				break;
			case 1:
				mgm.registerModule(new ModuleRandomizeNote());
				break;
			case 2:
				mgm.registerModule(new ModuleVolume());
				break;
			case 3:
				mgm.registerModule(new ModuleTranspose());
				break;	
		}
		
		
		
	}

	
	public void drawIconsB(PApplet p)
	{
		p.pushMatrix();
		p.translate (x, y);
		
		p.translate(7, 55);
		ModuleHarmony.drawIcon(p);
		
		p.translate(0, icon_v_spacing);
		ModuleRandomizeNote.drawIcon(p);
		
		
		p.translate(0, icon_v_spacing);
		ModuleVolume.drawIcon(p);
		
		p.translate(0, icon_v_spacing);
		ModuleTranspose.drawIcon(p);
		

		
		p.popMatrix();
	}
	
	/////////////// END EFFECTORS ////////////////

	
	/////////////// OUTPUTS ////////////////
	
	
	public void handleModuleInstantiateC(int index, PApplet p)
	{
		switch(index)
		{
			case 0:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/ML_PIANO_FENDER_2.aif", "piano"));
				break;
			case 1:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/KU_8040000p - ORGAN.aif", "organ"));
				break;
			case 2:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/ML_ACC-01 GUITAR.aif", "guitar"));
				break;				
			case 3:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/Bass1.aif", "bass"));
				break;
			case 4:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/ML_Strings 2.aif", "violin"));
				break;
			case 5:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/KU_SynMarimba.aif", "marimba"));
				break;	
			case 6:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/JMB_HiHats.aif", "cymbol"));
				break;	
			case 7:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/JMB_AcousKick.aif", "drum"));
				break;		
			case 8:
				mgm.registerModule(new ModuleSoniaOutputAuto (p, "samples/SL_C4Bongos.aif", "bongos"));
				break;		
					
				
				
			case 9:
				mgm.registerModule(new ModuleMidiOutput (p));
				break;
		}
		
	}
	
	public void drawIconsC(PApplet p)
	{
		p.pushMatrix();
		p.translate (x, y);
		
		p.translate(7, 55);
		ModuleSoniaOutputAuto.drawIcon(p, "piano");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "organ");

		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "guitar");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "bass");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "violin");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "marimba");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "cymbol");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "drum");
		
		p.translate(0, icon_v_spacing);
		ModuleSoniaOutputAuto.drawIcon(p, "bongos");
		
		p.translate(0, icon_v_spacing);
		ModuleMidiOutput.drawIcon(p);
		

		p.popMatrix();
		
	}
	
	/////////////// END OUTPUTS ////////////////

	
}
