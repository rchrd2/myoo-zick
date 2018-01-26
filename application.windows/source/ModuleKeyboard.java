import processing.core.*;
import sequencer.ChainKeyboard;
import sequencer.Sequencer;


public class ModuleKeyboard extends ModuleGUI {
	
	private boolean keyboardPressed = false; 
	private int keyboardMillis = 0;
	private double lastPitch;
	
	public ModuleKeyboard(Main p, Sequencer gs) {
		width = 60;
		height = 60;
		supportIN = false;
		supportOUT = true;
		
		
		
		bgColor = 0xFF009952; // greeeeen
		
		this.initVars();
		this.init(p, gs);
	}
	
	public void init(Main p, Sequencer gs)
	{
		p.addKeyboardListener((ModuleGUI)this);
		this.chainDevice = new ChainKeyboard (gs);
	}
	
	public static void drawIcon(PApplet p)
	{
		p.fill(255);
		p.rect(0,0, 128, 35);
		p.fill(0);
		p.textAlign(p.CENTER);
		p.text("keyboard", 128/2, 22);
	}
	
	public void keyPressed(PApplet p)
	{
		System.out.println("module keybaord keypressed");
		ChainKeyboard ck = (ChainKeyboard)this.chainDevice;
		
		double pitch;
		
		switch (p.key)
		{
			case 'a':
				pitch = 0.0 / 10.0;
				break;
			case 's':
				pitch = 1.0 / 10.0;
				break;
			case 'd':
				pitch = 2.0 / 10.0;
				break;
			case 'f':
				pitch = 3.0 / 10.0;
				break;
			case 'g':
				pitch = 4.0 / 10.0;
				break;
			case 'h':
				pitch = 5.0 / 10.0;
				break;
			case 'j':
				pitch = 6.0 / 10.0;
			 	break;
			case 'k':
				pitch = 7.0 / 10.0;
				break;
			case 'l':
				pitch = 8.0 / 10.0;
				break;
			case ';':
				pitch = 9.0 / 10.0;
				break;
			case '\'':
				pitch = 10.0 / 10.0;
				break;
			default:
				pitch = -1.0;
		}
		
		if (pitch >= 0)
		{
			keyboardPressed = true;
			keyboardMillis = p.millis();
			lastPitch = pitch;
			ck.playNote(pitch);
		}
	}
	
	public void update(PApplet p)
	{
		super.update(p);
		if (p.millis() - keyboardMillis > 300)
			keyboardPressed = false;
	}
	
	public void draw(PApplet p)
	{
		super.drawBackground(p);
		super.drawCloseBox(p);

		if (keyboardPressed == true)
		{
			p.noStroke();
			p.fill(100);
			float w = (float) (40 - lastPitch * 40);
			p.ellipse(width/2, height/2, w, w);
		}
		
		p.fill(255);
		p.textAlign(p.CENTER);
		p.text("keyboard\n asdfghjk", width/2 + 2, height / 2);
	}
}
