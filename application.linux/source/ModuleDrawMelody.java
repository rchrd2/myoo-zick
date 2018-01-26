import processing.core.*;
import sequencer.ChainDrawMelody;
import sequencer.ChainPulse;
import sequencer.Sequencer;


public class ModuleDrawMelody extends ModuleGUI {

	float noteWidth;
	int playHeadColor;// = color(103, 101, 98);
	int noteColor;// = color(167, 164, 158);
	int bgColor2;// = color(242, 242, 242);
	int gridColor1, gridColor2;// = color(205, 201, 194);
	int offAreaColor;
	
	
	public ModuleDrawMelody(Sequencer gs) {
		width = 300;
		height = 140;
		supportIN = false;
		supportOUT = true;
		
		this.initVars();
		
		this.offAreaColor = this.PORT_COLOR =  this.bgColor = 0xFF009952; // greeeeen

		
		this.init(gs);
		
		
		this.dragHitArea = dragHitAreaA;

	}
	
	public void init(Sequencer gs)
	{
		chainDevice = new ChainDrawMelody (gs, 32);		
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("draw melody", 128/2, 22);
	}
	
	
	public void draw(PApplet p)
	{
		
		
		checkForDrawing(p);
	
		
		
		super.drawBackground(p);
		super.drawCloseBox(p);
		
		playHeadColor = p.color(103, 101, 98);
		 //noteColor = p.color(167, 164, 158);
		noteColor = playHeadColor =  0xFF009952;
		 bgColor2 = p.color(242, 242, 242);
		 gridColor1 = p.color(205, 201, 194);
		 gridColor2 = p.color(205 - 50, 201- 50, 194- 50);

		 offAreaColor = p.color(231, 226, 219, 200);

		 
			
		// DRAW THE SEQUENCER STUFF
		
		ChainDrawMelody cdm = (ChainDrawMelody)chainDevice;
		double[] pitches = cdm.getNotePitchArray();
		float plen = pitches.length;
		float xoffset = (width/ plen / 2);
		
		// draw the bg
		p.fill(bgColor2);
		p.rect(0,dragHitArea[3],width,height - dragHitArea[3]);
		
		// draw the notes
		
		//p.rectMode(p.CENTER);
		p.fill(0);
		p.strokeWeight(1);
		
		float elw = (float)(width / plen * .5);
		
		for (int i = 0; i < plen; i++)
		{
			float xpos = i / plen * width;
			float ypos = (float)((1 - pitches[i]) * (height - elw));
			
			if (i % 2 == 0)
				p.stroke(gridColor2);

			else
				p.stroke(gridColor1);
			
			p.line(xpos, dragHitArea[3] + 2, xpos, height - 2);
			
			p.noStroke();
			p.fill(noteColor);
			p.rect(xpos, ypos, elw, elw);
		}
		
		//p.rectMode(p.CORNER);

		// playhead
		
		
		float posRatio = (float)cdm.getBeatNum() / (float)cdm.getPatternLength();
		
		p.stroke(playHeadColor);
		p.strokeWeight(2);

		p.line(posRatio * width, dragHitArea[3] + 1, posRatio * width, height - 1);
		p.strokeWeight(1);

		/*
		p.stroke(100);
		p.noFill();
		p.rect(0, 0, width, height);
		*/
		
		 
		/// draw note off area
		p.fill(offAreaColor);
		p.noStroke();
		p.rect(0, height - 10, width, 10);
		
		
		
		// draw title
		p.fill(255);
		p.textAlign(p.CENTER);
		p.text("draw melody", width/2 + 2, 12);
		

	}
	
	public void checkForDrawing(PApplet p)
	{
		if (mousePressed && mouseY > dragHitArea[3])
		{
			ChainDrawMelody cdm = (ChainDrawMelody)chainDevice;

			float xratio = (float)mouseX / (float)width;
			
			int noteIndex = Math.round(xratio * cdm.getPatternLength());
			noteIndex = p.constrain(noteIndex, 0, cdm.getPatternLength() - 1);
			
			
			boolean mute = false;
			
			float yratio = 1 - (float)mouseY / (float)(height);
			
			if (yratio <= .1) mute = true;
			
			float pitch = (float) ((yratio - .1) / .9);
			
			//p.println ("yratio: "+yratio);

			
			cdm.updateNote(noteIndex, yratio, mute);
			
		}
	}
}
