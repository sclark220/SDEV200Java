import java.util.Arrays;
import java.util.Scanner;

public class ch829
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter list 1: ");

        int[][] m1 = new int[3][3];
        for (int i = 0; i < 3; i++) // nested loops to add to array
        {
            for (int j = 0; j < 3; j++)
            {
                m1[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter list 2: ");

        int[][] m2 = new int[3][3];
        for (int i = 0; i < 3; i++) // nested loops to add to array
        {
            for (int j = 0; j < 3; j++)
            {
                m2[i][j] = scanner.nextInt();
            }
        }

        if (equals(m1, m2)) // print results
        {
            System.out.println("The two arrays are identical.");
        }
        else
        {
            System.out.println("The two arrays are not identical.");
        }

        scanner.close();
    }

    public static boolean equals(int[][] m1, int[][] m2) // check if equal
    {
        int number = 0;
        int[] array1 = new int[9]; // since m1 and m2 are 3x3 this should always be 9.
        int[] array2 = new int[9];

        for (int i = 0; i < 3; i++) // nested loops to add each element to 1 dimension array
        {
            for (int j = 0; j < 3; j++)
            {   
                array1[number] = m1[i][j];
                array2[number] = m2[i][j];
                number += 1;
            }
        }

        Arrays.sort(array1); // sort both
        Arrays.sort(array2); // too bad this doesn't work for multi dimensional arrays

        boolean isEqual = Arrays.equals(array1, array2); // check if equal

        return isEqual;
    }
}
