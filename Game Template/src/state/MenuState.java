package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import input.InputManager;
import input.Button;
import input.SliderButton;
import input.TextField;
import input.ToggleButton;
import util.ScrollWindow;
import util.TextBox;

public class MenuState extends State{
	
	InputManager im;
	
	TextBox tb;
	
	TestScrollWindow tw;

	public MenuState(StateManager gsm) {
		super(gsm);
		
		im = new InputManager();
		
		im.addInput(new SliderButton(50, 50, 200, 10, 0, 100, "slider 1", "slider1", Color.RED));
		im.addInput(new SliderButton(50, 100, 400, 10, 0, 100, "best slider", "slider2", Color.YELLOW));
		
		im.addInput(new Button(50, 200, 100, 100, "weird button", "button1", Color.pink, Color.blue));
		
		im.addInput(new ToggleButton(200, 200, 100, 100, "Toggle", "toggle1"));
		
		im.addInput(new TextField(25, 125, 200, "name the button", "textfield1", new Font("Arial", Font.BOLD, 24)));
		
		tb = new TextBox(100, 300, 300, 300, "This simulation is based off of the Numberphile video, Darts in Higher Dimensions. "
				+ "If you haven't watched it yet, you should go check it out. In the beginning of the video, "
				+ "3Blue1Brown introduces us to a strange game of darts, not one in which you are trying n"
				+ "to get the most points, but one in which you are just trying to throw the most amount "
				+ "of darts. The way the game works is whenever you throw a dart and it lands inside the "
				+ "board, you define the new board diameter by drawing a chord that is perpendicular to "
				+ "the line made from the point to the center. The length of this chord is the new diameter "
				+ "of the circle. Naturally, as you continue to throw more darts, the board continually "
				+ "shrinks, and when a dart lands outside of the board, it's game over. Now, the question posed "
				+ "is as follows: Lets say the board has a diameter of 1, and it is placed with its center at "
				+ "(0.5, 0.5). If a player were to throws darts, with the landing points x and y picked between "
				+ "0 and 1 with a uniform random distribution, what is the average score of the player. Now, he "
				+ "goes on to solve this problem with probability and math, but I just wanted to see if I "
				+ "could get the answer using a simulation.", new Font("Times New Roman", 0, 12));
		
		tw = new TestScrollWindow(450, 150, 300, 400, 2000);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(Point mouse) {

		im.tick(mouse);
		tw.tick(mouse);
		
	}

	@Override
	public void draw(Graphics g) {
		
		im.draw(g);
		tb.draw(g);
		tw.draw(g);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		im.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		im.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		im.keyTyped(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		tw.mouseClicked(arg0);

		String which = im.mouseClicked(arg0);
		
		if(which == null) {
			return;
		}
		
		switch(which) {
		case "button1":
			im.setVal("slider1", 50);
			im.setVal("slider2", 75);
			break;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		im.mousePressed(arg0);
		tw.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		im.mouseReleased(arg0);
		tw.mouseReleased(arg0);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		tw.mouseWheelMoved(arg0);
	}

}

class TestScrollWindow extends ScrollWindow{
	
	public int counter = 250;

	public TestScrollWindow(int x, int y, int width, int height, int realHeight) {
		super(x, y, width, height, realHeight);
		im.addInput(new Button(100, 100, 100, 100, "Click Me", "button1"));
		im.addInput(new SliderButton(50, 50, 200, 10, 0, 100, "slider 1", "slider1"));
	}

	@Override
	public void repaint(Graphics g, BufferedImage b) {
		im.draw(g);
		for(int i = 0; i < 7; i++) {
			g.setColor(new Color(i * 33, i * 33, i * 33));
			g.fillRect(0, i * 33, 33, 33);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(this.containsPoint(mouse)) {

			String which = im.mouseClicked(convertMouseEvent(arg0));
			
			if(which == null) {
				return;
			}
			
			switch(which) {
			case "button1":
				im.setVal("slider1", 50);
				im.addInput(new Button(100, counter, 100, 100, "Click Me", "button1"));
				counter += 150;
				this.setRealHeight(this.getRealHeight() + 150);
				break;
			}
		}
	}
	
}
