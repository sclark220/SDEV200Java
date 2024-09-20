import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ch2011
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Using testingBraces.java: ");
        if (CheckSymbols())
        {
            System.out.println("This file seems alright");
        }
        else
        {
            System.out.println("File is not alright or something went wrong");
        }
    }

    public static boolean CheckSymbols() throws IOException
    {
        try (FileInputStream file = new FileInputStream("Chapter20/testingBraces.java"))
        {
            Scanner input = new Scanner(file);
            List<Character> openSymbols = new ArrayList<>();
            List<Character> closeSymbols = new ArrayList<>();

            while (input.hasNextLine()) // loop through each line
            {
                String line = input.nextLine();

                for (int i = 0; i < line.length(); i++) // loop through each character
                {
                    char currentChar = line.charAt(i);

                    switch (currentChar) // check for open and closing symbols
                    {
                        case '{':
                            openSymbols.add(currentChar);
                            break;
                        case '}':
                            closeSymbols.add(currentChar);
                            break;
                        case '[':
                            openSymbols.add(currentChar);
                            break;
                        case ']':
                            closeSymbols.add(currentChar);
                            break;
                        case '(':
                            openSymbols.add(currentChar);
                            break;
                        case ')':
                            closeSymbols.add(currentChar);
                            break;
                    }
                }
            }

            input.close();

            if (closeSymbols.size() == openSymbols.size()) // return true if they oepn and closing symbols are equal
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (FileNotFoundException e) // auto generated exception from vscode
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}