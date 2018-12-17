public class SixSide extends Figure {
	private double side;
	
	public SixSide(double side) throws FigureException {
		if(side <= 0) throw new FigureException("side can't be negative");
		else this.side = side;
	}
	
	public double area() {
		return this.side*this.side*2.598076211353315940291169512258; //hardcoding to ease the calculations
	}
	
	public double circ() {
		return 6*this.side;
	}
}