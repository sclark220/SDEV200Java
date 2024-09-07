public class ch139
{
    public static void main(String[] args)
    {
        ch139 test = new ch139();

        Circle c1 = test.new Circle(12.23);
        Circle c2 = test.new Circle(67.89);
        Circle c3 = test.new Circle(12.23);

        System.out.println("c1.equals(c2): " + c1.equals(c2));
        System.out.println("c2.equals(c3): " + c2.equals(c3));
        System.out.println("c3.equals(c1): " + c3.equals(c1));
    }

    public class Circle extends GeometricObject implements Comparable<Circle>
    {
        private double radius;

        public Circle()
        {

        }

        public Circle(double radius)
        {
            this.radius = radius;
        }

        public Circle(double radius, String color, boolean filled)
        {
            this.radius = radius;
            setColor(color);
            setFilled(filled);
        }

        public double getRadius()
        {
            return radius;
        }
        public void setRadius(double radius)
        {
            this.radius = radius;
        }

        public double getArea()
        {
            return radius * radius * Math.PI;
        }

        public double getDiameter()
        {
            return 2 * radius;
        }

        public double getPerimeter()
        {
            return 2 * radius * Math.PI;
        }

        public boolean equals(Circle otherCircle)
        {
            if (this.radius == otherCircle.radius)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public int compareTo(Circle otherCircle)
        {
            if (this.radius > otherCircle.radius)
            {
                return 1;
            }
            else if (this.radius < otherCircle.radius)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }
}
