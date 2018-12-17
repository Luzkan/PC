public class Rhombus extends FourSide {
	public Rhombus(double side1, double side2, double side3, double side4, double angle) {
		super(side1, side2, side3, side4, angle);
	}
	
	
	public double area() {
		return this.side1*this.side1*Math.sin(Math.toRadians(this.angle));
	}
}