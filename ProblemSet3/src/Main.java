import java.util.*;

public class Main {
	public static void main(String[] args) {
		//c - circle, f - four sides, p - five sides (pentagon), s - six sides
		if(args.length == 0) return;
		String symbols = args[0];
		List<Figure> figures = new ArrayList<Figure>();
		int currentIndex = 1;
		for(int i = 0; i < symbols.length(); i++) {
			switch(symbols.charAt(i)) {
				case 'c':
					if (args.length < currentIndex + 1) {
						System.out.println("too few arguments to create a circle");
						break;
					}
					try {
						double radius = Double.parseDouble(args[currentIndex]);
						currentIndex++;
						try {
							Circle temp = new Circle(radius);
							figures.add(temp);
						} catch(FigureException err) {
							System.out.println(err.getMsg());
						}
					} catch (NumberFormatException err) {
						System.out.println("invalid argument for circle radius");
						currentIndex++;
					}
					break;
				case 'f':
					if (args.length < currentIndex + 5) {
						System.out.println("too few arguments to create a four side");
						break;
					}
					try {
						double side1 = Double.parseDouble(args[currentIndex]);
						double side2 = Double.parseDouble(args[currentIndex + 1]);
						double side3 = Double.parseDouble(args[currentIndex + 2]);
						double side4 = Double.parseDouble(args[currentIndex + 3]);
						double angle = Double.parseDouble(args[currentIndex + 4]);
						currentIndex += 5;
						
						if(side1 <= 0 || side2 <= 0 || side3 <= 0 || side3 <= 0) {
							System.out.println("side can't be negative");
							break;
						}
						
						double[] arr = {side1, side2, side3, side4};
						Arrays.sort(arr);
						
						if(angle == 90) {
							if(side1 == side2 && side2 == side3 & side3 == side4) {
								Square temp = new Square(side1, side2, side3, side4, angle);
								figures.add(temp);
							} else if (arr[0] == arr[1] && arr[2] == arr[3]) {	
								Rectangle temp = new Rectangle(arr[0], arr[3], arr[2], arr[4], angle);
								figures.add(temp);					
							} else {
								System.out.println("no figure with this arguments can be created");
								break;
							}
						} else if (angle > 0 && angle < 180) {
							Rhombus temp = new Rhombus(side1, side2, side3, side4, angle);
							figures.add(temp);
						} else {
							System.out.println("invalid angle");
							break;
						}
					} catch (NumberFormatException err) {
						System.out.println("invalid argument for side/angle");
						currentIndex += 5;
					}
					break;
				case 'p':
					if(args.length < currentIndex + 1) {
						System.out.println("too few argument to create a pentagon");
						break;
					}
					try {
						double side = Double.parseDouble(args[currentIndex]);
						currentIndex++;
						
						if(side <= 0) {
							System.out.println("side can't be negative");
							break;
						}
						
						try {
							FiveSide temp = new FiveSide(side);
							figures.add(temp);
						} catch(FigureException err) {
							System.out.println(err.getMsg());
						}
					} catch(NumberFormatException err) {
						System.out.println("invalid argument");
						currentIndex++;
					}
					break;
				case 's':
					if(args.length < currentIndex + 1) {
						System.out.println("too few argument to create a sixside");
						break;
					}
					try {
						double side = Double.parseDouble(args[currentIndex]);
						currentIndex++;
						
						if(side <= 0) {
							System.out.println("side can't be negative");
							break;
						}
						
						try {
							SixSide temp = new SixSide(side);
							figures.add(temp);
						} catch(FigureException err) {
							System.out.println(err.getMsg());
						}
					} catch(NumberFormatException err) {
						System.out.println("invalid argument");
						currentIndex++;
					}
				default:
					System.out.println("invalid symbol for a figure");
					break;
			}
		}
		for(int i = 0; i < figures.size(); i++) {
			System.out.println("figure " + i + " circ: " + figures.get(i).circ());
			System.out.println("figure " + i + " area: " + figures.get(i).area() + "\n");
		}
	}
}