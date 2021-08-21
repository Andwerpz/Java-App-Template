package shapes;

import java.awt.Graphics;

import util.MathTools;
import util.Point;
import util.Vector;

public class Circle extends Shape{
	
	public int x, y, radius;
	
	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(this.x - this.radius, this.y - this.radius, this.radius * 2, this.radius * 2);
	}

	@Override
	public double getDist(util.Point p) {
		return MathTools.dist(p.x, p.y, this.x, this.y) - (double)(this.radius); 
	}

	@Override
	public Vector getReflection(Vector vec, Point p) {
		// TODO Auto-generated method stub
		return null;
	}

}
