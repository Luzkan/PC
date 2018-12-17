public class Square extends FourSide {
	
	public Square(double side1, double side2, double side3, double side4, double angle) {
		super(side1, side2, side3, side4, angle);
	}
	
	
	public double area() {
		return this.side1*this.side1;
	}
}