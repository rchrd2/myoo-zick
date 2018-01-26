import processing.core.*;
import sequencer.*;

public class MenuBar {

	PImage[] stopG;
	int stopGState = 0;
	int x = 160, y = 0;
	int mouseX, mouseY;
	
	MenuBarButton[] btns;
	MenuLED led;
	MenuSlider slider;
	MenuLCD lcd;
	
	public MenuBar (PApplet p, Sequencer s)
	{
		//Setup buttons
		
		btns = new MenuBarButton[8];
		
		btns[0] = new MenuButtonStop(this, s, p, x + 7, y + 8);
		btns[1] = new MenuButtonPlay(this, s, p, x + 52, y + 8);
		btns[2] = new MenuButtonScaleNext(this, s, p, x + 647, y + 8);
		btns[3] = new MenuButtonScaleBack(this, s, p, x + 603, y + 8);
		btns[4] = new MenuButtonSave(this, s, p, x + 716, y + 8);
		btns[5] = new MenuButtonLoad(this, s, p, x + 760, y + 8);		
		btns[6] = new MenuButtonHelp(this, s, p, x + 815, y + 8);


		
		led = new MenuLED(p, s, x + 100, y + 14	);
		
		lcd = new MenuLCD(p, s, x + 418, y+ 15);
		
		slider = new MenuSlider(this, s, p, x + 140, y + 9);
	}



	
	public void update(PApplet p)
	{
		this.mouseX = p.mouseX - x;
		this.mouseY = p.mouseY - y;
		
		btns[0].update((Main)p);
		btns[1].update((Main)p);
		btns[2].update((Main)p);
		btns[3].update((Main)p);
		btns[4].update((Main)p);
		btns[5].update((Main)p);
		btns[6].update((Main)p);
		

		slider.update((Main)p);
	}
	
	public void draw(PApplet p)
	{
		btns[0].draw(p);
		btns[1].draw(p);
		btns[2].draw(p);
		btns[3].draw(p);
		btns[4].draw(p);
		btns[5].draw(p);
		btns[6].draw(p);

		
		led.draw(p);
		lcd.draw(p);
		
		slider.draw(p);
	}
	
}
