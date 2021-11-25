package input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.GraphicsTools;

public class TextField extends Input{
	
	private String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	
	private int x, y, width, height;
	private String text;
	private String hintText;
	private boolean selected = false;
	private boolean hovered = false;
	private boolean pressed = false;
	private Font font;
	
	private double hoveredAlpha = 0.3;
	private double selectedAlpha = 0.1;
	private double pressedAlpha = 0.5;
	private double hintTextAlpha = 0.35;
	
	private Color selectedColor;
	
	public TextField(int x, int y, int width, String hintText, String name) {
		super(name);
		this.x = x;
		this.y = y;
		this.font = new Font("Dialogue", Font.PLAIN, 12);	//default font for java swing
		this.width = width;
		this.height = 16;
		this.text = "";
		this.hintText = hintText;
		
		this.selectedColor = Color.gray;
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setFont(font);
		
		if(this.pressed) {
			g2.setComposite(GraphicsTools.makeComposite(pressedAlpha));
			g2.setColor(selectedColor);
			g2.fillRect(x, y, width, height);
		}
		
		else if(this.selected) {
			g2.setComposite(GraphicsTools.makeComposite(selectedAlpha));
			g2.setColor(selectedColor);
			g2.fillRect(x, y, width, height);
		}
		
		else if(this.hovered) {
			g2.setComposite(GraphicsTools.makeComposite(hoveredAlpha));
			g2.setColor(selectedColor);
			g2.fillRect(x, y, width, height);
		}
		
		if(this.text.length() == 0) {
			g2.setComposite(GraphicsTools.makeComposite(hintTextAlpha));
			g2.drawString(hintText, x + 2, y + this.font.getSize() + 2);
		}
		
		g2.setComposite(GraphicsTools.makeComposite(1));
		g.setColor(Color.BLACK);
		
		g.drawRect(x, y, width, height);
		g.drawString(text, x + 2, y + this.font.getSize() + 2);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Font getFont() {
		return font;
	}
	
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	public boolean pressed(MouseEvent m) {
		Rectangle r = new Rectangle(x, y, width, height);
		if(r.contains(new Point(m.getX(), m.getY()))) {
			pressed = true;
			return true;
		}
		pressed = false;
		return false;
	}
	
	public boolean hovering(java.awt.Point m) {
		Rectangle r = new Rectangle(x, y, width, height);
		if(r.contains(m)) {
			hovered = true;
			return true;
		}
		this.hovered = false;
		return false;
	}
	
	public boolean clicked(MouseEvent arg0) {
		Rectangle r = new Rectangle(x, y, width, height);
		if(r.contains(new Point(arg0.getX(), arg0.getY()))) {
			this.selected = true;
			return true;
		}
		this.selected = false;
		return false;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(this.selected) {
			int k = arg0.getKeyCode();
			if(k == KeyEvent.VK_BACK_SPACE && this.text.length() != 0) {
				this.text = this.text.substring(0, this.text.length() - 1);
				return;
			}
			if(k == KeyEvent.VK_SPACE) {
				this.text += " ";
			}
			char key = arg0.getKeyChar();
			if(Character.isLetterOrDigit(key) || this.specialChars.contains((key + "").substring(0, 1))) {
				this.text += key;
			}
			
		}
	}


}
