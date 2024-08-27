public class test
{
    /** Convert from feet to meters */
    public static double feetToMeters(double feet)
    {
        double meter = 0.305 * feet;
        return meter;
    }

    /** Convert from meters to feet */
    public static double metersToFeet(double meters)
    {   
        double foot = 3.279 * meters;
        return foot;
    }

    private static void theLoop()
    {   
        double feet = 1;
        double meters = 20;

        System.out.printf("%-4s to %-6s | %-6s to %-4s%n","Feet", "Meters", "Meters", "Feet");
        System.out.printf("---------------------------------%n");

        for (int i = 0; i < 10; i++)
        {
            double ftmResult = feetToMeters(feet);
            double mtfResult = metersToFeet(meters);
            
            System.out.printf("%-4s to %-6s | %-4s to %-6s%n", feet, ftmResult, meters, mtfResult);

            feet += 1;
            meters += 5;
        }
          
    }
    
    public static void main(String[] args)
    {
        theLoop();
    }
}
