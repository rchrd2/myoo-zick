import processing.core.PApplet;
import processing.core.PImage;
import sequencer.Sequencer;


public class MenuButtonPlay extends MenuBarButton {

	Sequencer s;
	
	public MenuButtonPlay (MenuBar mb, Sequencer s, PApplet p, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.s = s;
		width = 45;
		height = 31;
		
		g = new PImage[3];
		g[0] = p.loadImage("play-neutral.png");
		g[1] = p.loadImage("play-pressed.png");
		g[2] = p.loadImage("play-hover.png");
	}


	public void update(Main p)
	{
		super.update(p);
		
		if (justPressed)
		{
			s.play();
		}
	}
	
	public void draw(PApplet p) 
	{
		// this clause lights up the button while seq is playing
		if (s.isPlaying() && im_state == 0)
		{
			im_state = 2;
		}
		p.image(g[im_state], x, y);
	}



}
