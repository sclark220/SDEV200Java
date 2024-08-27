import java.util.Scanner;

public class ch631
{
    static String prefix = "";

    public static void main(String[] args)
    {
        try (Scanner input = new Scanner(System.in))
        {
            String cardNumber = input.nextLine();
            if (isValid(cardNumber))
            {
                System.out.println(cardNumber + "is a valid " + prefix);
            }
            else
            {
                System.out.println(cardNumber + "is an invalid card");
            }
        }
    }

    /** Return true if the card number is valid */
    public static boolean isValid(String cardNumber)
    {
        boolean isValid = false;
        int sumOfDoubleEvenPlace = sumOfDoubleEvenPlace(reverseString(cardNumber));
        int sumOfOddPlace = sumOfOddPlace(reverseString(cardNumber));
        int sumOfSums = sumOfDoubleEvenPlace + sumOfOddPlace;

        if (getSize(cardNumber) > 12 && getSize(cardNumber) < 17)
        {
            if (sumOfSums % 10 == 0)
            {   
                prefix = prefixMatched(cardNumber);

                if (prefix == "invalid")
                {
                    isValid = false;
                }
                else
                {
                    isValid = true;
                }
            }
            else
            {
                isValid = false;
            }
        }
        else
        {
            isValid = false;
        }
        
        return isValid;
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(String cardNumber)
    {
        int sumEven = 0;

        for (int index = 1; index < cardNumber.length(); index += 2)
        {
            int tempNumber = cardNumber.charAt(index) - '0';
            tempNumber *= 2;
            
            sumEven += getDigit(tempNumber);
        }

        return sumEven;
    }

    /** Return this number if it is a single digit, otherwise,
    * return the sum of the two digits */
    public static int getDigit(int number)
    {
        int sum = 0;

        if (number > 9)
        {
            while (number > 0)
            {
                // Finding the remainder (Last Digit)
                sum += number % 10;

                // Removing the last digit/current last digit
                number = number / 10;
            }
        }
        else
        {
            sum += number;
        }

        return sum;
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(String cardNumber)
    {
        int sumOdd = 0;

        for (int index = 0; index < cardNumber.length(); index += 2)
        {
            int tempNumber = cardNumber.charAt(index) - '0';

            sumOdd += tempNumber;  
        }

        return sumOdd;
    }

    /** Return true if the number d is a prefix for number */
    public static String prefixMatched(String cardNumber)
    {   
        int prefixNumber = cardNumber.charAt(0) - '0';

        switch (prefixNumber)
        {
            case 4:
                return "Visa card";
            case 5:
                return "Master card";
            case 3:
                if (cardNumber.charAt(1) - '0' == 7)
                {
                    return "American Express Card";
                }
                else
                {
                    return "invalid";
                }
                
            case 6:
                return "Discover Card";
            default:
                return "invalid";
        }
    }

    /** Return the number of digits in d */
    public static int getSize(String cardNumber)
    {
        int length = cardNumber.length();

        return length;
    }

    public static String reverseString(String cardNumber)
    {
        String reversedString = "";

        for (int i = 0; i < cardNumber.length(); i++)
        {
            reversedString = cardNumber.charAt(i) + reversedString;
        }

        return reversedString;
    }
}
