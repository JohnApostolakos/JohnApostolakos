
/**
 * Client class is the main method for the program which provides method calls and ensures files are validated before continuing
 * 
 * @author John Apostolakos
 * @version March 9, 2014
 */
public class Client
{

    public static void main(String[]args)
    {
        boolean badFile; 
        SystemProcess processor = new SystemProcess();

        System.out.println("***********************************************\n" +
            "Welcome to the shopping list program.");

        System.out.print("Enter the name of the meals file:     ");
        processor.readFileName();

        if(!processor.badInputFile()) // Validates whether first file was valid before continuing
        {
          System.out.print("Enter the name of the inventory file:    ");
            processor.readFileName();
        }

        if(!processor.badInputFile()) // Validates whether first and second file were valid before continuing
        {
           System.out.print("Enter the name of the grocery file to create:   ");
            processor.writeToFile();
        }

        if(processor.badInputFile())        
            System.out.println("\n\nThe program was not able to complete its operations due to improper input files.");
        else
            System.out.println("\nThanks for using the grocery list system.");
    }
}

