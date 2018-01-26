import processing.core.PApplet;
import processing.core.PImage;
import sequencer.Sequencer;


public class MenuButtonHelp extends MenuBarButton {

	Sequencer s;
	
	public MenuButtonHelp (MenuBar mb, Sequencer s, PApplet p, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.s = s;
		width = 45;
		height = 31;
		
		g = new PImage[3];
		g[0] = p.loadImage("help-neutral.png");
		g[1] = p.loadImage("help-pressed.png");
		g[2] = p.loadImage("help-hover.png");
	}


	public void update(Main p)
	{
		super.update(p);
		
		if (justPressed)
		{
		}
	}
	
	public void draw(PApplet p) 
	{
		p.image(g[im_state], x, y);
	}



}
