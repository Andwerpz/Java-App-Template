package util;

public class MathTools {
	
	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public static double dist3D(Point3D a, Point3D b) {
		return new Vector3D(a, b).getMagnitude();
	}
	
	public static double slope(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return dx / dy;
	}
	
	public static double dotProduct(Vector a, Vector b) {
		return a.x * b.x + a.y * b.y;
	}
	
	public static double dotProduct3D(Vector3D a, Vector3D b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	public static Vector3D crossProduct(Vector3D a, Vector3D b) {
		
		Vector3D normal = new Vector3D(0, 0, 0);
		
		normal.x = a.y * b.z - a.z * b.y;
		normal.y = a.z * b.x - a.x * b.z;
		normal.z = a.x * b.y - a.y * b.x;
		
		return normal;
		
	}
	
	public static double radianAngleBetweenVectors(Vector a, Vector b) {
		return Math.acos(dotProduct(a, b) / (a.getMagnitude() * b.getMagnitude()));
	}
	
	public static double sigmoid(double x) {
		return (1d / (1d + Math.pow(Math.E,(-1d * x))));
	}
	
	//derivative of sigmoid function with center at 0
	
	public static double sigmoidDerivative(double x) {
		return sigmoid(x) * (1d - sigmoid(x));
	}
	
	//for ML usage
	
	public static double relu(double x) {
		return Math.max(0, x);
	}

}
