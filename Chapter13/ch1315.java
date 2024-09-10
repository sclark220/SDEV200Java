import java.math.BigInteger;
import java.util.Scanner;

public class ch1315
{
    public static void main(String[] args)
    {   
        //testing
        ch1315 test = new ch1315();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first rational number (EX:3 454): ");

        int numerator01 = scanner.nextInt();
        int denominator01 = scanner.nextInt();

        System.out.println("Enter the second rational number (EX:7 2389): ");

        int numerator02 = scanner.nextInt();
        int denominator02 = scanner.nextInt();

        Rational r1 = test.new Rational(numerator01, denominator01);
        Rational r2 = test.new Rational(numerator02, denominator02);

        System.out.println(r1.getNumerator() + "/" + r1.getDenominator() + " + " + r2.getNumerator() + "/" + r2.getDenominator() + " = " + r1.add(r2));
        System.out.println(r1.getNumerator() + "/" + r1.getDenominator() + " - " + r2.getNumerator() + "/" + r2.getDenominator() + " = " + r1.subtract(r2));
        System.out.println(r1.getNumerator() + "/" + r1.getDenominator() + " * " + r2.getNumerator() + "/" + r2.getDenominator() + " = " + r1.multiply(r2));
        System.out.println(r1.getNumerator() + "/" + r1.getDenominator() + " / " + r2.getNumerator() + "/" + r2.getDenominator() + " = " + r1.divide(r2));
        System.out.println(r2.getNumerator() + "/" + r2.getDenominator() + " is " + r2.doubleValue());
    }

    public class Rational extends Number implements Comparable<Rational>
    {
        // Data fields for numerator and denominator
        private BigInteger numerator = BigInteger.ZERO;
        private BigInteger denominator = BigInteger.ONE;

        // Construct a rational with default properties
        public Rational()
        {
            this(0, 1);
        }

        // Construct a rational with specified numerator and denominator
        public Rational(long numerator, long denominator)
        {
            BigInteger gcd = gcd(numerator, denominator);
            this.numerator = BigInteger.valueOf(((denominator > 0) ? 1 : -1) * numerator).divide(gcd);
            this.denominator = BigInteger.valueOf(Math.abs(denominator)).divide(gcd);
        }

        // Find GCD of two numbers
        private static BigInteger gcd(long n, long d)
        {
            BigInteger b1 = BigInteger.valueOf(n);
            BigInteger b2 = BigInteger.valueOf(d);
            return b1.gcd(b2);
        }

        // Return numerator
        public long getNumerator()
        {
            return numerator.longValue();
        }

        // Return denominator
        public long getDenominator()
        {
            return denominator.longValue();
        }

        // Add a rational number to this rational
        public Rational add(Rational secondRational)
        {
            long n = numerator.longValue() * secondRational.getDenominator() + denominator.longValue() * secondRational.getNumerator();
            long d = denominator.longValue() * secondRational.getDenominator();
            return new Rational(n, d);
        }

        // Subtract a rational number from this rational
        public Rational subtract(Rational secondRational)
        {
            long n = numerator.longValue() * secondRational.getDenominator() - denominator.longValue() * secondRational.getNumerator();
            long d = denominator.longValue() * secondRational.getDenominator();
            return new Rational(n, d);
        }

        // Multiply a rational number by this rational
        public Rational multiply(Rational secondRational)
        {
            long n = numerator.longValue() * secondRational.getNumerator();
            long d = denominator.longValue() * secondRational.getDenominator();
            return new Rational(n, d);
        }

        // Divide a rational number by this rational
        public Rational divide(Rational secondRational)
        {
            long n = numerator.longValue() * secondRational.getDenominator();
            long d = denominator.longValue() * secondRational.numerator.longValue();
            return new Rational(n, d);
        }

        public String toString()
        {
            if (denominator.intValue() == 1)
            {
                return numerator + "";
            }
            else
            {
                return numerator + "/" + denominator;
            }
        }

        // Override the equals method in the Object class
        public boolean equals(Object other)
        {
            if ((this.subtract((Rational)(other))).getNumerator() == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        // Implement the abstract intValue method in Number
        public int intValue()
        {
            return (int)doubleValue();
        }

        @Override // Implement the abstract floatValue method in Number
        public float floatValue()
        {
            return (float)doubleValue();
        }

        // Implement the doubleValue method in Number
        public double doubleValue()
        {
            return numerator.longValue() *  1.0 / denominator.longValue();
        }

        // Implement the abstract longValue method in Number
        public long longValue()
        {
            return (long)doubleValue();
        }

        // Implement the compareTo method in Comparable
        public int compareTo(Rational o)
        {
            if (this.subtract(o).getNumerator() > 0)
            {
                return 1;
            }
            else if (this.subtract(o).getNumerator() < 0)
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