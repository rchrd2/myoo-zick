import processing.core.PApplet;
import processing.core.PImage;
import sequencer.Sequencer;
import sequencer.SequencerScale;


public class MenuButtonScaleBack extends MenuBarButton {

	Sequencer s;
	
	public MenuButtonScaleBack (MenuBar mb, Sequencer s, PApplet p, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.s = s;
		width = 45;
		height = 31;
		
		g = new PImage[3];
		g[0] = p.loadImage("scale-back-neutral.png");
		g[1] = p.loadImage("scale-back-pressed.png");
		g[2] = p.loadImage("scale-back-hover.png");
	}


	public void update(Main p)
	{
		super.update(p);
		
		if (justPressed)
		{
			int index = s.getScaleRef().getDegreeIndex();
			int backIndex = (index - 1 ) < 0 ? (index -1 + SequencerScale.SCALES.length) : index -1;
			SequencerScale ns = new SequencerScale(SequencerScale.SCALES[backIndex]);
			s.setScaleRef(ns);
		}
	}
	
	public void draw(PApplet p) 
	{
		// this clause lights up the button while seq is playing
		p.image(g[im_state], x, y);
	}



}
