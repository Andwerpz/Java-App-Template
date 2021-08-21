package shapes;

import java.awt.Graphics;

public abstract class Shape3D {
	
	public Shape3D() {}
	
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract util.Point3D getIntersectPoint(util.Point3D p, util.Vector3D v);
	public abstract util.Vector3D getNormal(util.Point3D p, util.Vector3D v);
	
}
