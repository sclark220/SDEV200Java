import java.util.Scanner;

public class ch111
{
    public static void main(String[] args)
    {   
        ch111 test = new ch111();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the three sides of the triangle:\n");

        System.out.print("Side 1: ");
        double one = input.nextDouble();

        System.out.print("Side 2: ");
        double two = input.nextDouble();

        System.out.print("Side 3: ");
        double three = input.nextDouble();

        Triangle triangle = test.new Triangle(one, two, three);

        System.out.print("What color is the triangle?: ");
        String color = input.next();
        triangle.setColor(color);

        System.out.print("Is the triangle filled? y / n (lowercase only): ");
        String filled = input.next();

        if ( filled.equals("y"))
        {
            triangle.setFilled(true);
        }

        System.out.println("\nFor " + triangle.toString() + "The area is " + triangle.getArea() + " square units " + "\nThe perimeter is " + triangle.getPerimeter());

        System.out.println("The current color is " + triangle.getColor() + " \nThe triangle filled state is: " + triangle.isFilled() + "\n");

        input.close();
    }

    public class Triangle extends GeometricObject
    {
        private double side1 = 1.0;
        private double side2 = 1.0;
        private double side3 = 1.0;
    
        public Triangle()
        {

        }
    
        public Triangle(double newSide1, double newSide2, double newSide3)
        {
            this.side1 = newSide1;
            this.side2 = newSide2;
            this.side3 = newSide3;
        }
    
        public double getArea()
        {
            double s = (this.side1 + this.side2 + this.side3) / 2;
            return Math.sqrt(s * (s - this.side1) * (s - this.side2) * (s - this.side3));
        }
    
        public double getPerimeter()
        {
            double p = this.side1 + this.side2 + this.side3;
            return p;
        }
    
        public double getSide1()
        {
            return side1;
        }
        public double getSide2()
        {
            return side2;
        }
        public double getSide3()
        {
            return side3;
        }
    
        public String toString()
        {
            return "Triangle:\n    side1 = " + this.side1 + "\n    side2 = " + this.side2 + "\n    side3 = " + this.side3 + "\n"; 
        }
    }
}