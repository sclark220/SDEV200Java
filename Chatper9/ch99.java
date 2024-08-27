public class ch99
{
    // --------------------Testing------------------------
    public static void main(String[] args)
    {
        ch99 c = new ch99(); // I did this so I can have the file names match the assignment
        RegularPolygon poly1 = c.new RegularPolygon(4, 3);
        RegularPolygon poly2 = c.new RegularPolygon(10, 4, 5.6, 7.8);
        RegularPolygon poly3 = c.new RegularPolygon(3, 1, 0, 0);

        System.out.println("Perimeter: " + poly1.getPerimeter());
        System.out.println("Perimeter: " + poly2.getPerimeter());
        System.out.println("Perimeter: " + poly3.getPerimeter());

        System.out.println("Area: " + poly1.getArea());
        System.out.println("Area: " + poly2.getArea());
        System.out.println("Area: " + poly3.getArea());
    }

    public class RegularPolygon
    {
        private int n = 3; // number of sides
        private double side = 1; // side length

        private double x = 0; // x coordinate
        private double y = 0; // y coordinate

        public RegularPolygon(int numberOfSides, double sideLength)
        {
            n = numberOfSides;
            side = sideLength;
        }

        public RegularPolygon(int numberOfSides, double sideLength, double xCoord, double yCoord)
        {
            n = numberOfSides;
            side = sideLength;
            x = xCoord;
            y = yCoord;
        }

        public double getPerimeter()
        {
            double perimeter = n * side; // number of sides multiplayed by the length of sides

            return perimeter;
        }

        public double getArea()
        {
            double area = (n * (side * side)) / (4 * Math.tan(Math.PI / n)); // Formula included in book

            return area;
        }
    }
}