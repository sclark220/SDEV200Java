import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ch213
{
    // code from listing 21.7 in the book
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists())
        {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        }
        else
        {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception
    {
        // Array of all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"};

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));

        int count = 0;

        Scanner input = new Scanner(file);

        boolean insideString = false;

        while (input.hasNext())
        {
            String word = input.next();

            for (int i = 0; i < word.length(); i++) // loop through each character
            {
                if (word.charAt(i) == '"') // check if we are inside a string
                {
                    if (insideString)
                    {
                        insideString = false;
                    }
                    else
                    {
                        insideString = true;
                    }
                }
            }

            if (keywordSet.contains(word))
            {
                if (!insideString)
                {
                    count++;
                    System.out.println(word);
                }
            }
            
        }
        return count;
    }
}
