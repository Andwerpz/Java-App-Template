package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

import util.Point;
import util.Vector;

public class Polygon extends Shape {
	
	public ArrayList<Point> verticies;
	public ArrayList<Line> lines;
	
	public Polygon(ArrayList<Point> v) {	//verticies must be defined counterclockwise for lines to be drawn properly
		verticies = v;
		lines = new ArrayList<Line>();
		for(int i = 0; i < v.size(); i++) {
			lines.add(new Line(v.get(i), v.get((i + 1) % v.size())));
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		for(Line l : lines) {
			l.draw(g);
		}
	}

	@Override
	public double getDist(util.Point p) {
		double minDist = 0;
		for(int i = 0; i < lines.size(); i++) {
			if(i == 0) {
				minDist = lines.get(i).getDist(p);
			}
			else {
				minDist = Math.min(minDist, lines.get(i).getDist(p));
			}
		}
		return minDist;
	}

	@Override
	public Vector getReflection(Vector vec, Point p) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
