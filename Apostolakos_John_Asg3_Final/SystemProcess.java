import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * SystemProcess is the processor class which reads the file name from the keyboard input and 
 * validates the file parameters.
 * Class also is responsible to invoke the methods of the shoppingList class and pass parameters read from the files
 * Class will finally write information to a text file from the keyboard input
 * 
 * @author John Apostolakos
 * @version March 9,2014
 */
public class SystemProcess
{
    private final int MIN_FILE = 1;
    private final int MAX_FILE = 30;
    private final int MAX_ATTEMPTS = 3;

    private Scanner scan;
    private Scanner text;
    private boolean badFile;
    private String fileName;

    private ShoppingList shop;
    private PrintWriter out;

    public void readFileName() // Processes request for file name for meals and inventory
    {
        int attempts = 0;
        int count = 0;
        boolean fileFound = false;
        shop = new ShoppingList();
        
        // Called from Client interface to accept a text file and verify its validity
        while(attempts < MAX_ATTEMPTS && !fileFound )
        {
            try  // File not found exception
            {
                scan = new Scanner(System.in); 
                fileName = (scan.next() + ".txt"); // Reads filename from user input

                //  Verifies the length of the filename to include the maximum 30 constraints              
                if(fileName.length() >= MIN_FILE && fileName.length() <= MAX_FILE)   
                {
                    text = new Scanner (new File (fileName));
                    fileFound = true;
                    System.out.println ("\nThe file  " + fileName + " has been found.\n");
                }
                else
                {
                    attempts++;
                    System.out.println("The file name " + fileName + " is too long.\nMaximum characters for filename is " 
                        + MAX_FILE + ". You have " + (MAX_ATTEMPTS - attempts) + " attempts remaining.");  

                }
            }
            catch(FileNotFoundException file)
            {
                attempts++;
                System.out.println (fileName + " for the could not be located in this directory.\nPlease try again. You have " 
                    + (MAX_ATTEMPTS - attempts) + " attempts remaining.");
            }  
            if(attempts > MAX_ATTEMPTS)
                badFile = true;
        }

        if(attempts >= MAX_ATTEMPTS) // Holds true in instance variable for use by Client to verify bad input
            badFile = true;
        else
        {
            if(fileFound) // Counts the sequence so that readFile can be reused
                count += 1;
            if(count == 1)
                processMenu();
            else if(count == 2)
                processInventory();   
        }
    }

    public boolean badInputFile() // Returns the state of the badFile boolean
    {
        return badFile;
    }

    public void processMenu() // Reads through the file and invokes the ShoppingList class method addItem() if valid
    {
        int line = 0;
        String item;
        int quantity;
        while(text.hasNextLine())
        {
            String readLine = text.nextLine();
            line++;
            if(!readLine.equals("")) // Ensures that blank lines are not included
            {
                String delims = "[ ]";
                String[]split = readLine.split(delims); // String array using space delimiter
                String first = split[0];
                String second = "";
                for(int index = 1; index < split.length; index++)
                    second += split[index] + " ";

                try
                {
                    quantity = Integer.parseInt(first);               
                    item = second; 
                    shop.addItem(item, quantity); // Invokes method and passes parameters to ShoppingList class
                }
                catch(NumberFormatException e)
                {
                    System.out.println("\n\n***********************************File Error***********************************************");
                    System.out.println("Line " + line + " is invalid and will not be included \nin the processing of the grocery list.");
                    System.out.println("***********************************File Error***********************************************\n\n");
                }

            }

        }
        text.close();
    }

    public void processInventory() // Reads through the file and invokes the ShoppingList class method removeItem()if valid
    {
        int line = 0;
        String item;
        int quantity;
        while(text.hasNextLine())
        {
            String readLine = text.nextLine();
            line++;
            if(!readLine.equals(""))
            {
                String delims = "[ ]";
                String[]split = readLine.split(delims);
                String first = split[0];
                String second = "";
                for(int index = 1; index < split.length; index++)
                    second += split[index] + " ";

                try
                {
                    quantity = Integer.parseInt(first);               
                    item = second; 
                    shop.removeItem(item, quantity);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("\n\n***********************************File Error***********************************************");
                    System.out.println("Line " + line + " is invalid and will not be included \nin the processing of the grocery list.");
                    System.out.println("***********************************File Error***********************************************\n\n");
                }

            }

        }
        text.close();
    }

    public void writeToFile() // Reads file name from keyboard and writes toString() from ShoppingList to file
    {    
        try
        {
            scan = new Scanner(System.in);
            fileName = (scan.next() + ".txt");
            if(fileName.length() >= MIN_FILE && fileName.length() <= MAX_FILE)
            {     
                System.out.println("\n\nList has been written to " + fileName);
                out = new PrintWriter(fileName);
                out.println(shop.toString());
                out.close(); 
            }
            else
                System.out.println("The file name is too long. Maximum characters for filename is " + MAX_FILE + ". Please try again.");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " was not accepted. Please try again");
        }
    }
}

