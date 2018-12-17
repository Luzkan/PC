public class Circle extends Figure {
	private double radius;
	
	public Circle(double radius) throws FigureException {
		if(radius <= 0) throw new FigureException("radius can't be lower than 0");
		this.radius = radius;
	}
	
	public double area() {
		return Math.PI*this.radius*this.radius;
	}
	
	public double circ() {
		return 2*Math.PI*this.radius;
	}
}	