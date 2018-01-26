import processing.core.PApplet;
import processing.core.PImage;


public class MenuBarButton {
	
	PImage[] g;
	public int x, y, width, height;
	public boolean hover = false, pressed = false, justPressed = false;
	public int im_state = 0;
	public int[] mousePressPos = {0, 0};
	
	
	public void update(Main p) {
		hover = pressed = justPressed = false;
		
		if (p.mouseX - x > 0 && p.mouseX - x < width && p.mouseY - y > 0 && p.mouseY - y < height)
		{
			hover = true;
		}
		
		if (p.mousePressed && p.mousePressX - x > 0 && p.mousePressX  - x < width && p.mousePressY  - y > 0 && p.mousePressY  - y < height)
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
	}
	
	public void draw(PApplet p)
	{
		
	}
}
