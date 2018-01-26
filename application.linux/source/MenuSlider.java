import processing.core.PApplet;
import processing.core.PImage;
import sequencer.Sequencer;


public class MenuSlider {

	Sequencer s;
	int x, y, width, height;
	
	// relative to x pos
	int[] sl_bnd = { 0, -10, 58, 40 };
	
	int maxTempo = 50;
	int minTempo = 400;
	
	boolean hover, pressed, justPressed;
	int[] mousePressPos = {0, 0};
	
	PImage[] g;
	int im_state = 0;
	
	
	float tempoRatio, prevTempoRatio;
	
	
	public MenuSlider (MenuBar mb, Sequencer s, PApplet p, int x, int y) {
		this.x = x;
		this.y = y;
		this.s = s;
		width = 100;
		height = 31;
		
		g = new PImage[3];
		g[0] = p.loadImage("slider-neutral.png");
		g[1] = p.loadImage("slider-pressed.png");
		g[2] = p.loadImage("slider-hover.png");
		
		
		// setup tempo
		this.tempoRatio = (float).5;
		
		
		int newTempo = Math.round(minTempo - tempoRatio * (minTempo - maxTempo));
		s.setTempo(newTempo);
		
		
		sl_bnd[0] = Math.round((this.width + 12) * tempoRatio - 12);

		
	}


	public void update(Main p)
	{
		hover = pressed = justPressed = false;
		
		if (p.mouseX - (x + sl_bnd[0]) > 0 && p.mouseX - (x + sl_bnd[0]) < (x + sl_bnd[2]) && p.mouseY - (y + sl_bnd[1]) > 0 && p.mouseY - (y + sl_bnd[1])  < (y + sl_bnd[3]) )
		{
			hover = true;
		}
		
		if (p.mousePressed && hover)
		{
			pressed = true;
			justPressed = p.mouseJustPressed;
		}
		
		if (justPressed)
		{
			mousePressPos[0] = p.mouseX - this.x;
			mousePressPos[0] = p.mouseY - this.y;
		}
		
		
		if (pressed) im_state = 1;
		else if(hover) im_state = 2;
		else im_state = 0;
		
		if (this.pressed)
		{
			//this.x = initx +  p.mouseX - p.mousePressX;
			this.sl_bnd[0] = p.mouseX - this.x - this.mousePressPos[0] - 30;
			
			this.sl_bnd[0] = p.constrain(sl_bnd[0], -10, width + sl_bnd[2]);
		}
		
		this.tempoRatio = (float)(sl_bnd[0] + 10) / (float)(width + sl_bnd[2]); 
		
		if (tempoRatio != prevTempoRatio || p.frameCount % 4 == 0)
		{
			// chnge the tempo
			int newTempo = Math.round(minTempo - tempoRatio * (minTempo - maxTempo));
			s.setTempo(newTempo);
		}
		prevTempoRatio = tempoRatio;
		
	}
	
	public void draw(PApplet p) 
	{
		// this clause lights up the button while seq is playing
		p.image(g[im_state], x + sl_bnd[0], y+ 10+ sl_bnd[1]);
		//float xpos = x - 12 + (width + 60) * tempoRatio;
		//System.out.println("xpos: "+xpos);
		//p.image(g[im_state], xpos, y);
	}



}
