public class ch129
{
    static ch129 x = new ch129(); // idk why this works VSCode auto generated it

    public static void main(String[] args)
    {
        //Test
        String binaryString1 = "1010";
        String binaryString2 = "1234";

        try
        {
            System.out.println("\nBinary number: " + binaryString1 + " converted to decimal is " + bin2Dec(binaryString1));
            System.out.println("Binary number: " + binaryString2 + " converted to decimal is " + bin2Dec(binaryString2) + "\n");
        }
        catch (NumberFormatException numberFormatException)
        {
            System.out.println(numberFormatException);
        }
    }

    public static int bin2Dec(String binaryString)
    {
        int decimalValue = 0;

        if (!checkBinaryString(binaryString))
        {
            throw x.new BinaryFormatException(binaryString);
        }

        char[] binaryCharacters = binaryString.toCharArray();
        int binaryIndex = 0;
        for (int i = binaryCharacters.length - 1; i >= 0; i--)
        {
            decimalValue += getBinaryValue(binaryCharacters[i], binaryIndex);
            binaryIndex++;
        }

        return decimalValue;
    }

    static boolean checkBinaryString(String binaryString)
    {
        char[] binaryStringValues = binaryString.toCharArray();

        for (char character : binaryStringValues)
        {
            if (character != '0' && character != '1')
            {
                return false;
            }
        }

        return true;
    }

    static int getBinaryValue(char binaryCharacter, int index)
    {
        int weight = (int)Math.pow(2, index);

        if (binaryCharacter == '1')
        {
            return weight;
        }
        
        return 0;
    }

    public class BinaryFormatException extends NumberFormatException
    {
        public BinaryFormatException(String binaryString)
        {
            super("Invalid binary string: " + binaryString);
        }
    }
}

