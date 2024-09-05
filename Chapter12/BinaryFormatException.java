
public class BinaryFormatException extends NumberFormatException
{
    public BinaryFormatException(String binaryString)
    {
        super("\nBinaryFormatException: " + binaryString + " is not binary. Notice the numbers that are not 1 or 0.\n");
    }
}
