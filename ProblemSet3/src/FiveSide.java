public class FiveSide extends Figure {
	private double side;
	
	public FiveSide(double side) throws FigureException {
		if (side <= 0) throw new FigureException("side can't be negative");
		else this.side = side;
	}
	
	public double area() {
		return this.side*this.side*1.72047740058896692275901197738; //hardcoding to ease the calculations
	}
	
	public double circ() {
		return 5*this.side;
	}
}