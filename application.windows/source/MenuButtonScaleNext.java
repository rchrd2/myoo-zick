import processing.core.PApplet;
import processing.core.PImage;
import sequencer.Sequencer;
import sequencer.SequencerScale;


public class MenuButtonScaleNext extends MenuBarButton {

	Sequencer s;
	
	public MenuButtonScaleNext (MenuBar mb, Sequencer s, PApplet p, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.s = s;
		width = 45;
		height = 31;
		
		g = new PImage[3];
		g[0] = p.loadImage("scale-next-neutral.png");
		g[1] = p.loadImage("scale-next-pressed.png");
		g[2] = p.loadImage("scale-next-hover.png");
	}


	public void update(Main p)
	{
		super.update(p);
		
		if (justPressed)
		{
			int index = s.getScaleRef().getDegreeIndex();
			int newIndex = (index + 1 ) % SequencerScale.SCALES.length;
			SequencerScale ns = new SequencerScale(SequencerScale.SCALES[newIndex]);
			s.setScaleRef(ns);		
		}
	}
	
	public void draw(PApplet p) 
	{
		// this clause lights up the button while seq is playing
		p.image(g[im_state], x, y);
	}



}
